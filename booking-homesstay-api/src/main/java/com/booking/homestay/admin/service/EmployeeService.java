package com.booking.homestay.admin.service;

import com.booking.homestay.admin.dto.EmployeeRequest;
import com.booking.homestay.admin.dto.EmployeeResponse;
import com.booking.homestay.admin.mapper.EmployeeMapper;
import com.booking.homestay.exception.SpringException;
import com.booking.homestay.model.HomeStay;
import com.booking.homestay.model.NotificationEmail;
import com.booking.homestay.model.User;
import com.booking.homestay.repository.HomeStayRepository;
import com.booking.homestay.repository.UserRepository;
import com.booking.homestay.shared.service.MailService;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

@Service
@AllArgsConstructor
@Transactional
public class EmployeeService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final HomeStayRepository homeStayRepository;
    private final EmployeeMapper employeeMapper;
    private final MailService mailService;

    public void save(EmployeeRequest employeeRequest) {

        //mật khẩu mặc định cho nhân viên khi admin thêm nhân viên là homestay123

        String password = "homestay123";
        Optional<User> username = userRepository.findByUserName(employeeRequest.getUserName());

        //ktra trung email ,phone chưa

        Optional<User> email = userRepository.findByEmail(employeeRequest.getEmail());
        Optional<User> phone = userRepository.findByPhone(employeeRequest.getPhone());
        if (username.isPresent() || email.isPresent() || phone.isPresent()) {
            throw new SpringException("Tài khoản, email, số điện thoại đã tồn tại");
        } else {
            User user = new User();
            user.setUserName(employeeRequest.getUserName());
            user.setEmail(employeeRequest.getEmail());
            user.setPhone(employeeRequest.getPhone());
            user.setPassword(passwordEncoder.encode(password));
            user.setCreatedDate(Instant.now());
            user.setEnabled(true);
            user.setStatus(true);
            user.setRole("Employee");
            HomeStay homeStay = homeStayRepository.findById(employeeRequest.getId_homeStay()).orElseThrow(()
                    -> new SpringException("Không tồn tại home stay ID - " + employeeRequest.getId_homeStay()));
            user.setHomeStay(homeStay);
            userRepository.save(user);
            mailService.sendMail(new NotificationEmail("Thông tin tài khoản nhân viên",
                    user.getEmail(), "Bạn đã trở thành thành viên của công ty chúng tôi, " +
                    "Vui lòng nhấp vào liên kết này để trở lại trang chủ : " +
                    "http://localhost:4200/" + "     Tài khoản của bạn là ||  Username: "
                    + user.getUserName() + " Password: " + password + "  .Vui lòng thay đổi mật khẩu của bạn khi đăng nhập thành công"));
        }
    }
    @Transactional(readOnly = true)
    public List<EmployeeResponse> getAllEmployee() {
        return userRepository.findByEmployeeNotLock()
                .stream()
                .map(employeeMapper::mapToDto)
                .collect(toList());
    }

    public void deleteEmployee(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new SpringException("Tài khoản không tồn tại ID - " + id));
        user.setStatus(false);
        userRepository.save(user);
    }

    @Transactional(readOnly = true)
    public EmployeeResponse getEmployeeById(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new SpringException("Tài khoản không tồn tại ID - " + id));
        return employeeMapper.mapToDto(user);
    }

    public void editEmployee(EmployeeRequest employeeRequest) {
        User user = userRepository.findById(employeeRequest.getId()).orElseThrow(() -> new SpringException("Tài khoản không tồn tại ID - " + employeeRequest.getId()));
        Optional<User> email = userRepository.findByEmail(employeeRequest.getEmail());
        Optional<User> phone = userRepository.findByPhone(employeeRequest.getPhone());
        if (email.isEmpty() && phone.isEmpty()) {
            userRepository.save(employeeMapper.mapEditToDtoById(employeeRequest, user));
        } else if (email.isPresent() && phone.isEmpty()) {
            if (email.get().getEmail().equals(employeeRequest.getEmail()) && email.get().getId() == (employeeRequest.getId())) {
                userRepository.save(employeeMapper.mapEditToDtoById(employeeRequest, user));
            } else {
                throw new SpringException("Email đã tồn tại");
            }
        } else if (email.isEmpty()) {
            if (phone.get().getPhone().equals(employeeRequest.getPhone()) && phone.get().getId() == (employeeRequest.getId())) {
                userRepository.save(employeeMapper.mapEditToDtoById(employeeRequest, user));
            } else {
                throw new SpringException("Sô điện thoại đã tồn tại");
            }
        } else {
            if (email.get().getEmail().equals(employeeRequest.getEmail()) && phone.get().getPhone().equals(employeeRequest.getPhone()) && email.get().getId() == (employeeRequest.getId()) && phone.get().getId() == (employeeRequest.getId())) {
                userRepository.save(employeeMapper.mapEditToDtoById(employeeRequest, user));
            } else if (!email.get().getEmail().equals(employeeRequest.getEmail()) && phone.get().getPhone().equals(employeeRequest.getPhone()) && phone.get().getId() == (employeeRequest.getId())) {
                throw new SpringException("Email đã tồn tại");
            } else if (email.get().getEmail().equals(employeeRequest.getEmail()) && !phone.get().getPhone().equals(employeeRequest.getPhone()) && email.get().getId() == (employeeRequest.getId())) {
                throw new SpringException("Số điện thoại đã tồn tại");
            } else {
                throw new SpringException("Email và số điện thoại đã tồn tại");
            }
        }
    }

    @Transactional(readOnly = true)
    public List<EmployeeResponse> getEmployeeLock() {
        return userRepository.findByEmployeeLock()
                .stream()
                .map(employeeMapper::mapToDto)
                .collect(toList());
    }

    public void EmployeeUnlock(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new SpringException("Tài khoản không tồn tại ID - " + id));
        user.setStatus(true);
        userRepository.save(user);
    }

    @Transactional(readOnly = true)
    public List<EmployeeResponse> checkEmployeeWait() {
        return userRepository.findByEmployeeCheck()
                .stream()
                .map(employeeMapper::mapToDto)
                .collect(toList());
    }
}
