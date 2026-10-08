package com.booking.homestay.shared.service;

import com.booking.homestay.exception.SpringException;
import com.booking.homestay.model.RefreshToken;
import com.booking.homestay.model.User;
import com.booking.homestay.repository.RefreshTokenRepository;
import com.booking.homestay.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.UUID;

@Service
@AllArgsConstructor
@Transactional
public class RefreshTokenService {

    private final RefreshTokenRepository refreshTokenRepository;
    private final UserRepository userRepository;



    //TẠO MỘT REFRESH TOKEN MỚI CHO NGƯỜI DÙNG
    public RefreshToken generateRefreshToken(User user){
        //tạo ra một đối tượng refresh token mới để đại diện cho token mới sẽ dc tạo ra
        RefreshToken refreshToken = new RefreshToken();
        // delete các refresh token cũ  của người dùng, để dùng token mới
        refreshTokenRepository.deleteByUser(user);
        // tạo token bằng một chuỗi ngẫu nhiên bằng UUID
        refreshToken.setToken(UUID.randomUUID().toString());
        //liên kết refresh token với người dùng
        refreshToken.setCreateDate(Instant.now());
        refreshToken.setUser(user);
        //lưu trữ refresh token mới vào csdl thông qua repository
        return  refreshTokenRepository.save(refreshToken);
    }

    // XÁC MINH TÍNH HỢP LỆ CỦA MỘT REFRESH TOKEN, KIỂM TRA SỰ LIÊN KẾT VỚI NGƯỜI DÙNG
    //nhận đầu vào : token(đại diện refesh token) , username(tên người dung)
    void validateRefreshToken(String token, String username){
        // refreshTokenRepository để tìm kiếm refresh token trong cơ sở dữ liệu dựa trên giá trị của token. Nếu không tìm thấy refresh token tương ứng, một ngoại lệ được ném ra thông báo.
        RefreshToken refreshToken = refreshTokenRepository.findByToken(token).orElseThrow(() -> new SpringException("Mã token không hợp lệ"));
        //ìm kiếm người dùng trong cơ sở dữ liệu dựa trên giá trị của username. Nếu không tìm thấy người dùng tương ứng, một ngoại lệ được ném ra thông báo .
        User user = userRepository.findByUserName(username).orElseThrow(()-> new SpringException("Không tìm thấy tài khoản này "));
        //kiểm tra xem người dùng liên kết với refresh token có khớp với người dùng mong đợi hay không. Nếu không khớp, hàm ném ra một ngoại lệ thông báo.
        if(user.getId() != (refreshToken.getUser().getId())){
            throw  new SpringException("Người dùng không khớp với mã Token");
        }
    }
    //XÓA MÃ TOKEN RA KHỎI CSDL
    public void deleteRefreshToken(String token) {
        refreshTokenRepository.deleteByToken(token);
    }
}
