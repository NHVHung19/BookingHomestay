package com.booking.homestay.employee.mapper;

import com.booking.homestay.employee.dto.BookingRequest;
import com.booking.homestay.employee.dto.BookingResponse;
import com.booking.homestay.model.Booking;
import com.booking.homestay.model.HomeStay;
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
public class BookingMapperImpl  extends  BookingMapper{
    @Override
    public Booking map(BookingRequest bookingRequest, User user) {
        if ( bookingRequest == null && user == null ) {
            return null;
        }

        Booking booking = new Booking();

        if ( bookingRequest != null ) {
            booking.setUser( bookingRequestToUser( bookingRequest ) );
            booking.setHouse( bookingRequestToHouse( bookingRequest ) );
            booking.setFullName( bookingRequest.getFullName() );
            booking.setAddress( bookingRequest.getAddress() );
            booking.setEmail( bookingRequest.getEmail() );
            booking.setPhone( bookingRequest.getPhone() );
            booking.setDateIn( bookingRequest.getDateIn() );
            booking.setDateOut( bookingRequest.getDateOut() );
            booking.setPrice( bookingRequest.getPrice() );
            booking.setDeposit( bookingRequest.isDeposit() );
            booking.setDescription( bookingRequest.getDescription() );
        }
        if ( user != null ) {
            booking.setCreator( userToUser( user ) );
        }
        booking.setId( getID() );
        booking.setCostsIncurred( 0 );
        booking.setDiscount( 0 );
        booking.setDepositPrice( getDepositPrice(bookingRequest) );
        booking.setStatus( "Not" );
        booking.setCreateDate( java.time.Instant.now() );

        return booking;
    }

    @Override
    public Booking mapNotId(BookingRequest bookingRequest, User user) {
        if ( bookingRequest == null && user == null ) {
            return null;
        }

        Booking booking = new Booking();

        if ( bookingRequest != null ) {
            booking.setHouse( bookingRequestToHouse1( bookingRequest ) );
            booking.setFullName( bookingRequest.getFullName() );
            booking.setAddress( bookingRequest.getAddress() );
            booking.setEmail( bookingRequest.getEmail() );
            booking.setPhone( bookingRequest.getPhone() );
            booking.setDateIn( bookingRequest.getDateIn() );
            booking.setDateOut( bookingRequest.getDateOut() );
            booking.setPrice( bookingRequest.getPrice() );
            booking.setDeposit( bookingRequest.isDeposit() );
            booking.setDescription( bookingRequest.getDescription() );
        }
        if ( user != null ) {
            booking.setCreator( userToUser1( user ) );
        }
        booking.setId( getID() );
        booking.setCostsIncurred( 0 );
        booking.setDiscount( 0 );
        booking.setDepositPrice( getDepositPrice(bookingRequest) );
        booking.setStatus( "Not" );
        booking.setCreateDate( java.time.Instant.now() );

        return booking;
    }

    @Override
    public Booking mapNotCreator(BookingRequest bookingRequest, User user) {
        if ( bookingRequest == null && user == null ) {
            return null;
        }

        Booking booking = new Booking();

        if ( bookingRequest != null ) {
            booking.setHouse( bookingRequestToHouse2( bookingRequest ) );
            booking.setFullName( bookingRequest.getFullName() );
            booking.setAddress( bookingRequest.getAddress() );
            booking.setEmail( bookingRequest.getEmail() );
            booking.setPhone( bookingRequest.getPhone() );
            booking.setDateIn( bookingRequest.getDateIn() );
            booking.setDateOut( bookingRequest.getDateOut() );
            booking.setPrice( bookingRequest.getPrice() );
            booking.setDeposit( bookingRequest.isDeposit() );
            booking.setDescription( bookingRequest.getDescription() );
        }
        if ( user != null ) {
            booking.setUser( userToUser2( user ) );
        }
        booking.setId( getID() );
        booking.setCostsIncurred( 0 );
        booking.setDiscount( 0 );
        booking.setDepositPrice( getDepositPrice(bookingRequest) );
        booking.setStatus( "Not" );
        booking.setCreateDate( java.time.Instant.now() );

        return booking;
    }

    @Override
    public Booking mapNotCreatorNotId(BookingRequest bookingRequest) {
        if ( bookingRequest == null ) {
            return null;
        }

        Booking booking = new Booking();

        booking.setHouse( bookingRequestToHouse3( bookingRequest ) );
        booking.setFullName( bookingRequest.getFullName() );
        booking.setAddress( bookingRequest.getAddress() );
        booking.setEmail( bookingRequest.getEmail() );
        booking.setPhone( bookingRequest.getPhone() );
        booking.setDateIn( bookingRequest.getDateIn() );
        booking.setDateOut( bookingRequest.getDateOut() );
        booking.setPrice( bookingRequest.getPrice() );
        booking.setDeposit( bookingRequest.isDeposit() );
        booking.setDescription( bookingRequest.getDescription() );

        booking.setId( getID() );
        booking.setCostsIncurred( 0 );
        booking.setDiscount( 0 );
        booking.setDepositPrice( getDepositPrice(bookingRequest) );
        booking.setStatus( "Not" );
        booking.setCreateDate( java.time.Instant.now() );

        return booking;
    }




    @Override
    public BookingResponse mapToDto(Booking booking) {
        if ( booking == null ) {
            return null;
        }

        BookingResponse bookingResponse = new BookingResponse();

        bookingResponse.setId( booking.getId() );
        bookingResponse.setFullName( booking.getFullName() );
        bookingResponse.setAddress( booking.getAddress() );
        bookingResponse.setEmail( booking.getEmail() );
        bookingResponse.setPhone( booking.getPhone() );
        bookingResponse.setDateIn( booking.getDateIn() );
        bookingResponse.setDateOut( booking.getDateOut() );
        bookingResponse.setIdentityCard( booking.getIdentityCard() );
        bookingResponse.setPrice( booking.getPrice() );
        bookingResponse.setCostsIncurred( booking.getCostsIncurred() );
        bookingResponse.setDiscount( booking.getDiscount() );
        bookingResponse.setDepositPrice( booking.getDepositPrice() );
        bookingResponse.setStatus( booking.getStatus() );
        bookingResponse.setDeposit( booking.isDeposit() );
        bookingResponse.setDescription( booking.getDescription() );
        bookingResponse.setUserName( bookingUserUserName( booking ) );
        bookingResponse.setCreatorName( bookingCreatorUserName( booking ) );
        bookingResponse.setHouseName( bookingHouseHouseName( booking ) );
        bookingResponse.setHomestayName( bookingHouseHomeStayHomeStayName( booking ) );
        bookingResponse.setId_house( bookingHouseId( booking ) );
        if ( booking.getCreateDate() != null ) {
            bookingResponse.setCreateDate( booking.getCreateDate().toString() );
        }

        bookingResponse.setBookingHistoryResponses( getAllBookingHistory(booking.getId()) );

        return bookingResponse;
    }

    @Override
    public Booking mapToEdit(BookingRequest bookingRequest, Booking booking) {
        if ( bookingRequest == null && booking == null ) {
            return null;
        }

        Booking booking1 = new Booking();

        if ( bookingRequest != null ) {
            booking1.setHouse( bookingRequestToHouse4( bookingRequest ) );
            booking1.setId( bookingRequest.getId() );
            booking1.setFullName( bookingRequest.getFullName() );
            booking1.setAddress( bookingRequest.getAddress() );
            booking1.setEmail( bookingRequest.getEmail() );
            booking1.setPhone( bookingRequest.getPhone() );
            booking1.setDateIn( bookingRequest.getDateIn() );
            booking1.setDateOut( bookingRequest.getDateOut() );
            booking1.setIdentityCard( bookingRequest.getIdentityCard() );
            booking1.setPrice( bookingRequest.getPrice() );
            booking1.setCostsIncurred( bookingRequest.getCostsIncurred() );
            booking1.setDiscount( bookingRequest.getDiscount() );
            booking1.setDescription( bookingRequest.getDescription() );
        }
        if ( booking != null ) {
            booking1.setUser( userToUser3( booking.getUser() ) );
            booking1.setCreator( userToUser4( booking.getCreator() ) );
            booking1.setStatus( booking.getStatus() );
            booking1.setDepositPrice( booking.getDepositPrice() );
            booking1.setDeposit( booking.isDeposit() );
            booking1.setCreateDate( booking.getCreateDate() );
        }

        return booking1;
    }

    private User bookingRequestToUser(BookingRequest bookingRequest) {
        if ( bookingRequest == null ) {
            return null;
        }

        User user = new User();

        user.setId( bookingRequest.getId_user() );

        return user;
    }

    private User userToUser(User user) {
        if ( user == null ) {
            return null;
        }

        User user1 = new User();

        user1.setId( user.getId() );

        return user1;
    }

    private House bookingRequestToHouse(BookingRequest bookingRequest) {
        if ( bookingRequest == null ) {
            return null;
        }

        House house = new House();

        house.setId( bookingRequest.getId_house() );

        return house;
    }

    private User userToUser1(User user) {
        if ( user == null ) {
            return null;
        }

        User user1 = new User();

        user1.setId( user.getId() );

        return user1;
    }
    private House bookingRequestToHouse1(BookingRequest bookingRequest) {
        if ( bookingRequest == null ) {
            return null;
        }

        House house = new House();

        house.setId( bookingRequest.getId_house() );

        return house;
    }
    private User userToUser2(User user) {
        if ( user == null ) {
            return null;
        }

        User user1 = new User();

        user1.setId( user.getId() );

        return user1;
    }

    private House bookingRequestToHouse2(BookingRequest bookingRequest) {
        if ( bookingRequest == null ) {
            return null;
        }

        House house = new House();

        house.setId( bookingRequest.getId_house() );

        return house;
    }

    private House bookingRequestToHouse3(BookingRequest bookingRequest) {
        if ( bookingRequest == null ) {
            return null;
        }

        House house = new House();

        house.setId( bookingRequest.getId_house() );

        return house;
    }

    private String bookingUserUserName(Booking booking) {
        if ( booking == null ) {
            return null;
        }
        User user = booking.getUser();
        if ( user == null ) {
            return null;
        }
        String userName = user.getUserName();
        if ( userName == null ) {
            return null;
        }
        return userName;
    }
    private String bookingCreatorUserName(Booking booking) {
        if ( booking == null ) {
            return null;
        }
        User creator = booking.getCreator();
        if ( creator == null ) {
            return null;
        }
        String userName = creator.getUserName();
        if ( userName == null ) {
            return null;
        }
        return userName;
    }

    private String bookingHouseHouseName(Booking booking) {
        if ( booking == null ) {
            return null;
        }
        House house = booking.getHouse();
        if ( house == null ) {
            return null;
        }
        String houseName = house.getHouseName();
        if ( houseName == null ) {
            return null;
        }
        return houseName;
    }

    private String bookingHouseHomeStayHomeStayName(Booking booking) {
        if ( booking == null ) {
            return null;
        }
        House house = booking.getHouse();
        if ( house == null ) {
            return null;
        }
        HomeStay homeStay = house.getHomeStay();
        if ( homeStay == null ) {
            return null;
        }
        String homeStayName = homeStay.getHomeStayName();
        if ( homeStayName == null ) {
            return null;
        }
        return homeStayName;
    }

    private Long bookingHouseId(Booking booking) {
        if ( booking == null ) {
            return null;
        }
        House house = booking.getHouse();
        if ( house == null ) {
            return null;
        }
        Long id = house.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private User userToUser3(User user) {
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

    private User userToUser4(User user) {
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


    private House bookingRequestToHouse4(BookingRequest bookingRequest) {
        if ( bookingRequest == null ) {
            return null;
        }

        House house = new House();

        house.setId( bookingRequest.getId_house() );

        return house;
    }



























}
