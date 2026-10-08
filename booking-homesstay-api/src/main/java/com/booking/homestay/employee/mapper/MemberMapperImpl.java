package com.booking.homestay.employee.mapper;

import com.booking.homestay.employee.dto.MemberRequest;
import com.booking.homestay.employee.dto.MemberResponse;
import com.booking.homestay.model.User;
import org.springframework.stereotype.Component;

import javax.annotation.Generated;

@Generated(
        value = "org.mapstruct.ap.MappingProcessor",
        date = "2024-03-02T22:47:17+0700",
        comments = "version: 1.4.2.Final, compiler: javac, environment: Java 11.0.21 (Oracle Corporation)"
)
@Component
public class MemberMapperImpl extends MemberMapper {
    @Override
    public MemberResponse mapToDto(User user) {
        if ( user == null ) {
            return null;
        }

        MemberResponse memberResponse = new MemberResponse();

        memberResponse.setId( user.getId() );
        memberResponse.setUserName( user.getUserName() );
        memberResponse.setPassword( user.getPassword() );
        memberResponse.setEmail( user.getEmail() );
        memberResponse.setFirstName( user.getFirstName() );
        memberResponse.setLastName( user.getLastName() );
        memberResponse.setPhone( user.getPhone() );
        memberResponse.setAddress( user.getAddress() );
        memberResponse.setImage( user.getImage() );
        memberResponse.setRole( user.getRole() );
        if ( user.getCreatedDate() != null ) {
            memberResponse.setCreatedDate( user.getCreatedDate().toString() );
        }
        memberResponse.setSex( user.getSex() );
        memberResponse.setEnabled( user.isEnabled() );
        memberResponse.setStatus( user.isStatus() );
        memberResponse.setId_creator( userCreatorId( user ) );

        memberResponse.setDateOfBirth( getFormatDate(user) );

        return memberResponse;
    }



    @Override
    public User mapEditToDtoById(MemberRequest memberRequest, User user) {
        if ( memberRequest == null && user == null ) {
            return null;
        }

        User user1 = new User();

        if ( memberRequest != null ) {
            user1.setId( memberRequest.getId() );
            user1.setEmail( memberRequest.getEmail() );
            user1.setFirstName( memberRequest.getFirstName() );
            user1.setLastName( memberRequest.getLastName() );
            user1.setPhone( memberRequest.getPhone() );
            user1.setAddress( memberRequest.getAddress() );
            user1.setDateOfBirth( memberRequest.getDateOfBirth() );
            user1.setSex( memberRequest.getSex() );
        }
        if ( user != null ) {
            user1.setUserName( user.getUserName() );
            user1.setPassword( user.getPassword() );
            user1.setImage( user.getImage() );
            user1.setRole( user.getRole() );
            user1.setCreatedDate( user.getCreatedDate() );
            user1.setEnabled( user.isEnabled() );
            user1.setStatus( user.isStatus() );
            user1.setHomeStay( user.getHomeStay() );
        }

        return user1;
    }
    private Long userCreatorId(User user) {
        if ( user == null ) {
            return null;
        }
        return user.getCreator() != 0l ? user.getCreator() : 0l;
    }
    protected User userToUser(User user) {
        if ( user == null ) {
            return null;
        }

        User user1 = new User();

        user1.setId( user.getId() );
        user1.setUserName( user.getUserName() );
        user1.setPassword( user.getPassword() );
        user1.setEmail( user.getEmail() );
        user1.setFirstName( user.getFirstName() );
        user1.setLastName( user.getLastName() );
        user1.setPhone( user.getPhone() );
        user1.setAddress( user.getAddress() );
        user1.setImage( user.getImage() );
        user1.setRole( user.getRole() );
        user1.setCreatedDate( user.getCreatedDate() );
        user1.setDateOfBirth( user.getDateOfBirth() );
        user1.setSex( user.getSex() );
        user1.setEnabled( user.isEnabled() );
        user1.setStatus( user.isStatus() );
        user1.setCreator( user.getCreator() );
        user1.setHomeStay( user.getHomeStay() );

        return user1;
    }
}
