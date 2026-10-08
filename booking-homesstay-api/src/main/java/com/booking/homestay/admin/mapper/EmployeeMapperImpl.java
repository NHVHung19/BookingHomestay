package com.booking.homestay.admin.mapper;

import com.booking.homestay.admin.dto.EmployeeRequest;
import com.booking.homestay.admin.dto.EmployeeResponse;
import com.booking.homestay.model.HomeStay;
import com.booking.homestay.model.User;

import javax.annotation.Generated;

import org.springframework.stereotype.Component;

@Generated(
        value = "org.mapstruct.ap.MappingProcessor",
        date = "2024-03-02T22:47:17+0700",
        comments = "version: 1.4.2.Final, compiler: javac, environment: Java 11.0.21 (Oracle Corporation)"
)
@Component
public class EmployeeMapperImpl extends EmployeeMapper {

    @Override
    public EmployeeResponse mapToDto(User user) {
        if (user == null) {
            return null;
        }

        EmployeeResponse employeeResponse = new EmployeeResponse();

        employeeResponse.setId(user.getId());
        employeeResponse.setUserName(user.getUserName());
        employeeResponse.setPassword(user.getPassword());
        employeeResponse.setEmail(user.getEmail());
        employeeResponse.setFirstName(user.getFirstName());
        employeeResponse.setLastName(user.getLastName());
        employeeResponse.setPhone(user.getPhone());
        employeeResponse.setAddress(user.getAddress());
        employeeResponse.setImage(user.getImage());
        employeeResponse.setRole(user.getRole());
        if (user.getCreatedDate() != null) {
            employeeResponse.setCreatedDate(user.getCreatedDate().toString());
        }
        employeeResponse.setSex(user.getSex());
        employeeResponse.setEnabled(user.isEnabled());
        employeeResponse.setStatus(user.isStatus());
        employeeResponse.setId_creator(userCreatorId(user));
        employeeResponse.setId_homeStay(userHomeStayId(user));
        employeeResponse.setHomeStayName(userHomeStayHomeStayName(user));

        employeeResponse.setDateOfBirth(getFormatDate(user));

        return employeeResponse;
    }

    @Override
    public User mapEditToDtoById(EmployeeRequest employeeRequest, User user) {
        if (employeeRequest == null && user == null) {
            return null;
        }

        User user1 = new User();

        if (employeeRequest != null) {
            user1.setHomeStay(employeeRequestToHomeStay(employeeRequest));
            user1.setId(employeeRequest.getId());
            user1.setEmail(employeeRequest.getEmail());
            user1.setFirstName(employeeRequest.getFirstName());
            user1.setLastName(employeeRequest.getLastName());
            user1.setPhone(employeeRequest.getPhone());
            user1.setAddress(employeeRequest.getAddress());
            user1.setDateOfBirth(employeeRequest.getDateOfBirth());
            user1.setSex(employeeRequest.getSex());
        }
        if (user != null) {
            user1.setUserName(user.getUserName());
            user1.setPassword(user.getPassword());
            user1.setImage(user.getImage());
            user1.setRole(user.getRole());
            user1.setCreatedDate(user.getCreatedDate());
            user1.setEnabled(user.isEnabled());
            user1.setStatus(user.isStatus());
        }

        return user1;
    }

    private Long userCreatorId(User user) {
        if (user == null) {
            return null;
        }
        if (user.getCreator() == 0l) {
            return null;
        }
        Long id = user.getCreator();
        if (id == null) {
            return null;
        }
        return id;
    }

    private Long userHomeStayId(User user) {
        if (user == null) {
            return null;
        }
        HomeStay homeStay = user.getHomeStay();
        if (homeStay == null) {
            return null;
        }
        Long id = homeStay.getId();
        if (id == null) {
            return null;
        }
        return id;
    }

    private String userHomeStayHomeStayName(User user) {
        if (user == null) {
            return null;
        }
        HomeStay homeStay = user.getHomeStay();
        if (homeStay == null) {
            return null;
        }
        String homeStayName = homeStay.getHomeStayName();
        if (homeStayName == null) {
            return null;
        }
        return homeStayName;
    }

    protected User userToUser(User user) {
        if (user == null) {
            return null;
        }

        User user1 = new User();

        user1.setId(user.getId());
        user1.setUserName(user.getUserName());
        user1.setPassword(user.getPassword());
        user1.setEmail(user.getEmail());
        user1.setFirstName(user.getFirstName());
        user1.setLastName(user.getLastName());
        user1.setPhone(user.getPhone());
        user1.setAddress(user.getAddress());
        user1.setImage(user.getImage());
        user1.setRole(user.getRole());
        user1.setCreatedDate(user.getCreatedDate());
        user1.setDateOfBirth(user.getDateOfBirth());
        user1.setSex(user.getSex());
        user1.setEnabled(user.isEnabled());
        user1.setStatus(user.isStatus());
        user1.setCreator(user.getCreator());
        user1.setHomeStay(user.getHomeStay());

        return user1;
    }

    protected HomeStay employeeRequestToHomeStay(EmployeeRequest employeeRequest) {
        if (employeeRequest == null) {
            return null;
        }

        HomeStay homeStay = new HomeStay();

        homeStay.setId(employeeRequest.getId_homeStay());

        return homeStay;
    }
}
