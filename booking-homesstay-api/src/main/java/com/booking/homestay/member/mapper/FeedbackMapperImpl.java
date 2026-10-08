package com.booking.homestay.member.mapper;

import com.booking.homestay.member.dto.FeedbackRequest;
import com.booking.homestay.member.dto.FeedbackResponse;
import com.booking.homestay.model.FeedBack;
import com.booking.homestay.model.House;
import com.booking.homestay.model.User;
import org.springframework.stereotype.Component;

import javax.annotation.Generated;

@Generated(
        value = "org.mapstruct.ap.MappingProcessor",
        date = "2024-03-02T22:47:17+0700",
        comments = "version: 1.4.2.Final, compiler: javac, environment: Java 11.0.21 (Oracle Corporation)"
)
@Component
public class FeedbackMapperImpl extends FeedbackMapper {
    @Override
    public FeedBack mapToSave(FeedbackRequest feedbackRequest, User user) {
        if ( feedbackRequest == null && user == null ) {
            return null;
        }

        FeedBack feedBack = new FeedBack();

        if ( feedbackRequest != null ) {
            feedBack.setHouse( feedbackRequestToHouse( feedbackRequest ) );
            feedBack.setContent( feedbackRequest.getContent() );
            feedBack.setRate( feedbackRequest.getRate() );
        }
        if ( user != null ) {
            feedBack.setUser( userToUser( user ) );
        }
        feedBack.setCreateDate( java.time.Instant.now() );

        return feedBack;
    }

    @Override
    public FeedbackResponse mapToDto(FeedBack feedBack) {
        if ( feedBack == null ) {
            return null;
        }

        FeedbackResponse feedbackResponse = new FeedbackResponse();

        feedbackResponse.setId( feedBack.getId() );
        feedbackResponse.setContent( feedBack.getContent() );
        if ( feedBack.getCreateDate() != null ) {
            feedbackResponse.setCreateDate( feedBack.getCreateDate().toString() );
        }
        feedbackResponse.setRate( feedBack.getRate() );
        feedbackResponse.setUserName( feedBackUserUserName( feedBack ) );
        feedbackResponse.setImage( feedBackUserImage( feedBack ) );
        feedbackResponse.setHouseName( feedBackHouseHouseName( feedBack ) );
        feedbackResponse.setId_house( feedBackUserId( feedBack ) );
        feedbackResponse.setId_user( feedBackHouseId( feedBack ) );

        return feedbackResponse;
    }

    protected User userToUser(User user) {
        if ( user == null ) {
            return null;
        }

        User user1 = new User();

        user1.setId( user.getId() );

        return user1;
    }

    protected House feedbackRequestToHouse(FeedbackRequest feedbackRequest) {
        if ( feedbackRequest == null ) {
            return null;
        }

        House house = new House();

        house.setId( feedbackRequest.getId_house() );

        return house;
    }

    private String feedBackUserUserName(FeedBack feedBack) {
        if ( feedBack == null ) {
            return null;
        }
        User user = feedBack.getUser();
        if ( user == null ) {
            return null;
        }
        String userName = user.getUserName();
        if ( userName == null ) {
            return null;
        }
        return userName;
    }

    private String feedBackUserImage(FeedBack feedBack) {
        if ( feedBack == null ) {
            return null;
        }
        User user = feedBack.getUser();
        if ( user == null ) {
            return null;
        }
        String image = user.getImage();
        if ( image == null ) {
            return null;
        }
        return image;
    }

    private String feedBackHouseHouseName(FeedBack feedBack) {
        if ( feedBack == null ) {
            return null;
        }
        House house = feedBack.getHouse();
        if ( house == null ) {
            return null;
        }
        String houseName = house.getHouseName();
        if ( houseName == null ) {
            return null;
        }
        return houseName;
    }

    private Long feedBackUserId(FeedBack feedBack) {
        if ( feedBack == null ) {
            return null;
        }
        User user = feedBack.getUser();
        if ( user == null ) {
            return null;
        }
        Long id = user.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private Long feedBackHouseId(FeedBack feedBack) {
        if ( feedBack == null ) {
            return null;
        }
        House house = feedBack.getHouse();
        if ( house == null ) {
            return null;
        }
        Long id = house.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }
}
