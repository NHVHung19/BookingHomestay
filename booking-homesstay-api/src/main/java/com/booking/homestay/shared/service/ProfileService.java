package com.booking.homestay.shared.service;

import com.booking.homestay.exception.SpringException;
import com.booking.homestay.model.User;
import com.booking.homestay.repository.UserRepository;
import com.booking.homestay.shared.dto.ProfileRequest;
import com.booking.homestay.shared.dto.ProfileResponse;
import com.booking.homestay.shared.mapper.ProfileMapper;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@AllArgsConstructor
@Transactional
public class ProfileService {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final AuthService authService;
    private final ProfileMapper profileMapper;


   @Transactional(readOnly = true)  //annotations này chỉ cho đọc không cho sửa
   //KIỂM TRA LOẠI NGƯỜI DÙNG VÀ TRẠNG THÁI PROFILE CỦA NGƯỜI DÙNG
    public String checkUser() {
       //lấy thông tin người dùng hiện tại
       User user = authService.getCurrentUser();
       //Kiểm tra các vai trò và trạng thái profile
       if(user.getRole().equals("Employee") && user.getHomeStay() == null) {
           return "EmployeeNot";
       }else {
           if(user.getAddress() != null && user.getDateOfBirth() != null
                   && user.getFirstName() != null && user.getLastName() != null
                    && user.getSex() != null){
               return "null";
           }else if(user.getRole().equals("Employee")){
               return "Employee";
           }else if(user.getRole().equals("Admin")){
               return "Admin";
           }
           if(user.getRole().equals("Member")){
               return "Member";
           }
       }
       return null;
    }
    
    @Transactional(readOnly=true)
    // LẤY THÔNG TIN PROFILE CỦA NGƯỜI DÙNG ĐỂ TRẢ VỀ ProfileResponse
    public ProfileResponse getProfile() {
       //Hàm gọi pthuc getCurrentUser từ authService lấy thông tin về người dùng hiện tại
       User user = authService.getCurrentUser();
       //ánh xạ thông tin đó thành một đối tượng ProfileResponse bằng cách sử dụng profileMapper, và trả về đối tượng ProfileResponse này cho việc sử dụng trong ứng dụng.
       return profileMapper.mapToDtoByUserName(user);
    }

    @Transactional(readOnly = true)
    //LẤY THÔNG TIN NGƯỜI DÙNG QUA ID
    public ProfileResponse getProfileId(Long id) {
       //tìm kiếm thông tin người dùng qua id, nếu không thấy thì ném ra thông báo
       User user = userRepository.findById(id).orElseThrow(() -> new SpringException("Không có tài khoản nào có ID:"+id));
       //thông tin được ánh xạ thành một đối tượng ProfileResponse bằng cách sử dụng profileMapper, và đối tượng ProfileResponse này được trả về cho việc sử dụng trong ứng dụng.
       return profileMapper.mapToDtoByUserName(user);
    }

    //CẬP NHẬT THÔNG TIN NGƯỜI DÙNG DỰA VÀO THÔNG TIN DC CUNG CẤP TRONG 1 DT PROFILE REQUEST
    public void update(ProfileRequest profileRequest) {
       User user = authService.getCurrentUser();
       //Ánh xạ thông tin từ profileRequest và người dùng hiện tại sang đối tượng User và lưu vào csdl
       userRepository.save(profileMapper.mapUpdateToDtoById(profileRequest,user));
    }
    public void edit(ProfileRequest profileRequest) {
    Optional<User> email = userRepository.findByEmail(profileRequest.getEmail());
    Optional<User> phone = userRepository.findByPhone(profileRequest.getPhone());
        if (email.isEmpty() && phone.isEmpty()) {
        userRepository.save(profileMapper.mapEditToDtoById(profileRequest, authService.getCurrentUser()));
    } else if (email.isPresent() && phone.isEmpty()) {
        if (email.get().getEmail().equals(profileRequest.getEmail()) && email.get().getId() == (profileRequest.getId())) {
            userRepository.save(profileMapper.mapEditToDtoById(profileRequest, authService.getCurrentUser()));
        } else {
            throw new SpringException("Email đã tồn tại");
        }
    } else if (email.isEmpty()) {
        if (phone.get().getPhone().equals(profileRequest.getPhone()) && phone.get().getId() == (profileRequest.getId())) {
            userRepository.save(profileMapper.mapEditToDtoById(profileRequest, authService.getCurrentUser()));
        } else {
            throw new SpringException("Số điện thoại đã tồn tại");
        }
    } else {
        if (email.get().getEmail().equals(profileRequest.getEmail()) && phone.get().getPhone().equals(profileRequest.getPhone()) && email.get().getId() == (profileRequest.getId()) && phone.get().getId() == (profileRequest.getId())) {
            userRepository.save(profileMapper.mapEditToDtoById(profileRequest, authService.getCurrentUser()));
        } else if (!email.get().getEmail().equals(profileRequest.getEmail()) && phone.get().getPhone().equals(profileRequest.getPhone()) && phone.get().getId() == (profileRequest.getId())) {
            throw new SpringException("Email đã tồn tại");
        } else if (email.get().getEmail().equals(profileRequest.getEmail()) && !phone.get().getPhone().equals(profileRequest.getPhone()) && email.get().getId() == (profileRequest.getId())) {
            throw new SpringException("Số điện thoại đã tồn tại");
        } else {
            throw new SpringException("Email và số điện thoại đã tồn tại");
        }
    }
}

    public void editPassword(ProfileRequest profileRequest) {
       if(passwordEncoder.matches(profileRequest.getPassword(),authService.getCurrentUser().getPassword())){
           User user = authService.getCurrentUser();
           user.setPassword(passwordEncoder.encode(profileRequest.getPasswordEdit()));
           userRepository.save(user);

       }else{
           throw new SpringException("Mật khẩu cũ không khớp");
       }
    }
}








































