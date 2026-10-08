package com.booking.homestay.shared.mapper;

import com.booking.homestay.model.HomeStay;
import com.booking.homestay.model.User;
import com.booking.homestay.shared.dto.ProfileRequest;
import com.booking.homestay.shared.dto.ProfileResponse;
import org.springframework.stereotype.Component;

import javax.annotation.Generated;

@Generated(
        value = "org.mapstruct.ap.MappingProcessor",
        date = "2024-03-02T22:47:17+0700",
        comments = "version: 1.4.2.Final, compiler: javac, environment: Java 11.0.21 (Oracle Corporation)"
)
@Component
public class ProfileMapperImpl  extends ProfileMapper{
    @Override
    public ProfileResponse mapToDtoByUserName(User user) {
        if (user == null) {
            return null;
        }

        ProfileResponse profileResponse = new ProfileResponse();

        profileResponse.setId(user.getId());
        profileResponse.setUserName(user.getUserName());
        profileResponse.setPassword(user.getPassword());
        profileResponse.setEmail(user.getEmail());
        profileResponse.setFirstName(user.getFirstName());
        profileResponse.setLastName(user.getLastName());
        profileResponse.setPhone(user.getPhone());
        profileResponse.setAddress(user.getAddress());
        profileResponse.setImage(user.getImage());
        profileResponse.setRole(user.getRole());
        if (user.getCreatedDate() != null) {
            profileResponse.setCreatedDate(user.getCreatedDate().toString());
        }
        profileResponse.setSex(user.getSex());
        profileResponse.setEnabled(user.isEnabled());
        profileResponse.setStatus(user.isStatus());
        profileResponse.setId_creator(userCreatorId(user));
        profileResponse.setId_homeStay(userHomeStayId(user));
        profileResponse.setHomeStayName(userHomeStayHomeStayName(user));

        profileResponse.setDateOfBirth(getFormatDate(user));

        return profileResponse;
    }



    @Override
    public User mapEditToDtoById(ProfileRequest profileRequest, User user) {
        if (profileRequest == null && user == null) {
            return null;
        }

        User user1 = new User();

        if (profileRequest != null) {
            user1.setEmail(profileRequest.getEmail());
            user1.setFirstName(profileRequest.getFirstName());
            user1.setLastName(profileRequest.getLastName());
            user1.setPhone(profileRequest.getPhone());
            user1.setAddress(profileRequest.getAddress());
            user1.setImage(profileRequest.getImage());
            user1.setDateOfBirth(profileRequest.getDateOfBirth());
            user1.setSex(profileRequest.getSex());
        }
        if (user != null) {
            user1.setId(user.getId());
            user1.setUserName(user.getUserName());
            user1.setPassword(user.getPassword());
            user1.setRole(user.getRole());
            user1.setCreatedDate(user.getCreatedDate());
            user1.setEnabled(user.isEnabled());
            user1.setStatus(user.isStatus());
            user1.setHomeStay(user.getHomeStay());
        }

        return user1;
    }

    @Override
    public User mapUpdateToDtoById(ProfileRequest profileRequest, User user) {
        if (profileRequest == null && user == null) {
            return null;
        }

        User user1 = new User();

        if (profileRequest != null) {
            user1.setFirstName(profileRequest.getFirstName());
            user1.setLastName(profileRequest.getLastName());
            user1.setAddress(profileRequest.getAddress());
            user1.setImage(profileRequest.getImage());
            user1.setDateOfBirth(profileRequest.getDateOfBirth());
            user1.setSex(profileRequest.getSex());
        }
        if (user != null) {
            user1.setId(user.getId());
            user1.setUserName(user.getUserName());
            user1.setPassword(user.getPassword());
            user1.setEmail(user.getEmail());
            user1.setPhone(user.getPhone());
            user1.setRole(user.getRole());
            user1.setCreatedDate(user.getCreatedDate());
            user1.setEnabled(user.isEnabled());
            user1.setStatus(user.isStatus());
            user1.setHomeStay(user.getHomeStay());
        }

        return user1;
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

    private Long userCreatorId(User user) {
        if (user == null) {
            return null;
        }
        return user.getCreator() == 0l? 0 : user.getCreator() ;
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

    protected Long userToUser1(User user) {
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

        return user1.getCreator();
    }
}
