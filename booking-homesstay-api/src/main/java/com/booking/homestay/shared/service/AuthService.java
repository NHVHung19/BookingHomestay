package com.booking.homestay.shared.service;

import com.booking.homestay.exception.SpringException;
import com.booking.homestay.model.*;
import com.booking.homestay.repository.UserRepository;
import com.booking.homestay.repository.VerificationTokenAccountRepository;
import com.booking.homestay.repository.VerificationTokenPasswordRepository;
import com.booking.homestay.security.service.JwtProvider;
import com.booking.homestay.shared.dto.*;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
@Transactional
public class AuthService {
    
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final VerificationTokenAccountRepository verificationTokenAccountRepository;
    private final VerificationTokenPasswordRepository verificationTokenPasswordRepository;

    private final MailService mailService;

    private final AuthenticationManager authenticationManager;
    private final JwtProvider jwtProvider;
    private final RefreshTokenService refreshTokenService;

    //  // ĐĂNG KÝ TÀI KHOẢN mới cho người dùng
    public void register(RegisterRequest registerRequest) {

        //Kiểm tra tính duy nấp của tên tài khoản (username) ,email, số điện thoại

        Optional<User> username = userRepository.findByUserName(registerRequest.getUserName());
        Optional<User> email = userRepository.findByEmail(registerRequest.getEmail());
        Optional<User> phone = userRepository.findByPhone(registerRequest.getPhone());
        if (username.isPresent() || email.isPresent() || phone.isPresent()) {
            throw new SpringException("Tài khoản, email hoặc số điện thoại bị trùng");
        }

        //Tạo 1 tài khoản mới với thông tin cung cấp và mã hóa mật khẩu
        // Nếu đăng ký tài khoản thì mặc định là role Member

        User user = new User();
        user.setUserName(registerRequest.getUserName());
        user.setEmail(registerRequest.getEmail());
        user.setFirstName(registerRequest.getFirstName());
        user.setLastName(registerRequest.getLastName());
        user.setPhone(registerRequest.getPhone());
        user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        user.setCreatedDate(Instant.now());
        user.setEnabled(false);
        user.setStatus(true);
        user.setRole("Member");
        userRepository.save(user);

        //Tạo 1 mã token xác minh tài khoản và gửi mail xác minh đến người dùng
        String token = generateVerificationTokenAccount(user);
        mailService.sendMail(new NotificationEmail("Vui lòng kích hoạt tài khoản mới của bạn",
                user.getEmail(), "Cảm ơn bạn đã đăng ký tại khoản tại website chúng tôi, " +
                "Vui lòng nhấp vào đường dẫn này để kích hoạt tài khoản bạn mới đăng ký, " +
                "Hạn sử dụng trong vòng 2 giờ : " +
                "http://localhost:4200/accountVerification/" + token));
    }

    // // TẠO MỘT MÃ TOKEN Ngẫu nhiên DUY NHẤT cho tài khoản người dùng và lưu trữ thông tin token vào cơ sở dữ liệu
    // Mã token này sẽ hết hạn trong 2h(7200000)
    private String generateVerificationTokenAccount(User user) {
        String token = UUID.randomUUID().toString();
        VerificationTokenAccount verificationTokenAccount = new VerificationTokenAccount();
        verificationTokenAccount.setToken(token);
        verificationTokenAccount.setUser(user);
        verificationTokenAccount.setExpiryDate(Instant.now().plusMillis(7200000));
        verificationTokenAccountRepository.save(verificationTokenAccount);
        return token;
    }

    //Xác minh tính hợp lệ của mã token
    //Kích hoạt tài khoản nếu mã token còn hiệu lực và không hết hạn
    public void verifyAccount(String token) {
        Optional<VerificationTokenAccount> verificationToken = verificationTokenAccountRepository.findByToken(token);
        fetchUserAndEnable(verificationToken.orElseThrow(() -> new SpringException("Mã token không hợp lệ")));
    }

    //Kiểm tra xem token đã hết hạn chưa,sau đó kích hoat tài khoản và xóa mã token đã dc sử dụng ra khỏi csdl
    private void fetchUserAndEnable(VerificationTokenAccount verificationTokenAccount) {
        Instant time = Instant.now();
        if (time.isAfter(verificationTokenAccount.getExpiryDate())) {
            verificationTokenAccountRepository.deleteById(verificationTokenAccount.getId());
            userRepository.deleteById(verificationTokenAccount.getUser().getId());
            throw new SpringException("Mã token đã hết hạn.");
        }
        String username = verificationTokenAccount.getUser().getUserName();
        User user = userRepository.findByUserName(username).orElseThrow(() -> new SpringException("Không tìm thấy tên tài khoản là: " + username));
        user.setEnabled(true);
        userRepository.save(user);
        verificationTokenAccountRepository.deleteById(verificationTokenAccount.getId());
    }

    //Xác minh thông tin đăng nhập của người dùng
    //Tạo JWT token cho người dùng đã xác thực
    //Tạo và lưu trữ 1 RefeshToken cho người dùng để duy trì phiên làm việc

    public AuthenticationResponse login(LoginRequest loginRequest) {
        userRepository.findByUserName(loginRequest.getUserName()).orElseThrow(() -> new SpringException("Tài khoản không tồn tại"));
        User user = userRepository.findByUserNameAndStatusTrue(loginRequest.getUserName()).orElseThrow(() -> new SpringException("Tài khoản của bạn đã bị khóa, liên hệ quản trị viên để biết thêm thông tin chi tiết"));
        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUserName(),
                loginRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authenticate);
        String token = jwtProvider.generateToken(authenticate);
        RefreshToken refreshToken = refreshTokenService.generateRefreshToken(user);
        return AuthenticationResponse.builder()
                .authenticationToken(token)
                .refreshToken(refreshToken.getToken())
                .expiresAt(Instant.now().plusMillis(jwtProvider.getJwtExpirationInMillis()))
                .userName(loginRequest.getUserName())
                .role(user.getRole())
                .image(user.getImage())
                .build();
    }

    //Nó đại diện dt User hiện tại,sử dụng SercurityContextHolder để lấy thông tin về người dùng hiện tại
    @Transactional(readOnly = true)
    public User getCurrentUser() {
        try {
            org.springframework.security.core.userdetails.User principal = (org.springframework.security.core.userdetails.User) SecurityContextHolder.
                    getContext().getAuthentication().getPrincipal();
            return userRepository.findByUserName(principal.getUsername())
                    .orElseThrow(() -> new UsernameNotFoundException("Không tìm thấy tài khoản nào có user name là - " + principal.getUsername()));
        } catch (Exception e) {
            return null;
        }
    }

    //Gửi email xác minh cho người dùng yêu cầu đặt lại mật khẩu
    //Tạo và lưu trữ 1 mã token xác minh mật khẩu cho người dùng

    public void forgotPassword(EmailRequest emailRequest) {
        User user = userRepository.findByEmail(emailRequest.getEmail()).orElseThrow(() -> new SpringException("Không tìm thấy tài khoản nào có email là: " + emailRequest.getEmail()));
        String token = generateVerificationTokenPassword(user);
        mailService.sendMail(new NotificationEmail("Xác nhận yêu cầu lấy lại mật khẩu",
                user.getEmail(), "Vui lòng nhấp vào đường dẫn bên dưới để làm mới mật khẩu của bạn, " +
                "Hạn sủ dụng 2 giờ: " +
                "http://localhost:4200/passwordVerification/" + token));
    }

    //Tạo token mới sau khi người dùng quên mật khẩu
    private String generateVerificationTokenPassword(User user) {
        String token = UUID.randomUUID().toString();
        VerificationTokenPassword verificationTokenPassword = new VerificationTokenPassword();
        verificationTokenPassword.setToken(token);
        verificationTokenPassword.setUser(user);
        verificationTokenPassword.setExpiryDate(Instant.now().plusMillis(7200000));
        verificationTokenPasswordRepository.save(verificationTokenPassword);
        return token;
    }

    //Xác minh tính hợp lệ của mã token
    //Trả về username tương ứng với mã token nêếu mã token còn hiệu lực và không hết hạn

    public String verifyPassword(String token) {
        VerificationTokenPassword verificationToken = verificationTokenPasswordRepository.findByToken(token)
                .orElseThrow(() -> new SpringException("Mã token không hợp lệ - " + token));
        Instant time = Instant.now();
        if (time.isAfter(verificationToken.getExpiryDate())) {
            verificationTokenAccountRepository.deleteById(verificationToken.getId());
            throw new SpringException("Mã token đã hết hạn");
        }
        return verificationToken.getUser().getUserName();
    }

    //Xác minh tính hợp lệ của RefreshToken và người dùng tương ứng
    //Tạo 1 JWT Token mới với username đã cung cấp

    public AuthenticationResponse refreshToken(RefreshTokenRequest refreshTokenRequest) {
        refreshTokenService.validateRefreshToken(refreshTokenRequest.getRefreshToken(), refreshTokenRequest.getUserName());
        String token = jwtProvider.generateTokenWithUserName(refreshTokenRequest.getUserName());
        User user = userRepository.findByUserName(refreshTokenRequest.getUserName()).orElseThrow(() -> new SpringException("Không tìm thấy tên tài khoản là: " + refreshTokenRequest.getUserName()));
        return AuthenticationResponse.builder()
                .authenticationToken(token)
                .refreshToken(refreshTokenRequest.getRefreshToken())
                .expiresAt(Instant.now().plusMillis(jwtProvider.getJwtExpirationInMillis()))
                .userName(refreshTokenRequest.getUserName())
                .role(user.getRole())
                .build();
    }


    //Thực hiện thay đổi mật khẩu cho tài khoản người dùng và xóa các mã token xác minh mk cũ
    public void editPassword(AccoutEditRequest accoutEditRequest) {
        User username = userRepository.findByUserName(accoutEditRequest.getUserName()).orElseThrow(() -> new SpringException("Không tìm thấy tên tài khoản là: " + accoutEditRequest.getUserName()));
        username.setPassword(passwordEncoder.encode(accoutEditRequest.getPassword()));
        userRepository.save(username);
        List<VerificationTokenPassword> listTokenByUser = verificationTokenPasswordRepository.findByUser_Id(username.getId());
        for (VerificationTokenPassword verificationTokenPassword : listTokenByUser) {
            verificationTokenPasswordRepository.deleteById(verificationTokenPassword.getId());
        }
    }

    //Ktra ngưi dùng đã đăng nhaapj chưa
    public boolean isLoggedIn() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return !(authentication instanceof AnonymousAuthenticationToken) && authentication.isAuthenticated();
    }


}
