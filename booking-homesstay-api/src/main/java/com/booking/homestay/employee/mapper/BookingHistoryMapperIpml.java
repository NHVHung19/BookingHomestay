package com.booking.homestay.employee.mapper;

import com.booking.homestay.employee.dto.BookingHistoryResponse;
import com.booking.homestay.model.Booking;
import com.booking.homestay.model.BookingHistory;
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
public class BookingHistoryMapperIpml  extends BookingHistoryMapper{
    @Override
    public BookingHistory mapToSave(Booking booking, User user) {
        if ( booking == null && user == null ) {
            return null;
        }

        BookingHistory bookingHistory = new BookingHistory();

        if ( booking != null ) {
            bookingHistory.setHouse( houseToHouse( booking.getHouse() ) );
            bookingHistory.setBooking( bookingToBooking( booking ) );
            bookingHistory.setFullName( booking.getFullName() );
            bookingHistory.setAddress( booking.getAddress() );
            bookingHistory.setEmail( booking.getEmail() );
            bookingHistory.setPhone( booking.getPhone() );
            bookingHistory.setDateIn( booking.getDateIn() );
            bookingHistory.setDateOut( booking.getDateOut() );
            if ( booking.getPrice() != null ) {
                bookingHistory.setPrice( booking.getPrice() );
            }
            bookingHistory.setCostsIncurred( booking.getCostsIncurred() );
            bookingHistory.setDiscount( booking.getDiscount() );
            bookingHistory.setDescription( booking.getDescription() );
        }
        if ( user != null ) {
            bookingHistory.setUser( userToUser( user ) );
        }
        bookingHistory.setCreateDate( java.time.Instant.now() );

        return bookingHistory;
    }

    @Override
    public BookingHistoryResponse mapToDto(BookingHistory bookingHistory) {
        if ( bookingHistory == null ) {
            return null;
        }

        BookingHistoryResponse bookingHistoryResponse = new BookingHistoryResponse();

        bookingHistoryResponse.setId( bookingHistory.getId() );
        bookingHistoryResponse.setFullName( bookingHistory.getFullName() );
        bookingHistoryResponse.setAddress( bookingHistory.getAddress() );
        bookingHistoryResponse.setEmail( bookingHistory.getEmail() );
        bookingHistoryResponse.setPhone( bookingHistory.getPhone() );
        bookingHistoryResponse.setDateIn( bookingHistory.getDateIn() );
        bookingHistoryResponse.setDateOut( bookingHistory.getDateOut() );
        bookingHistoryResponse.setPrice( bookingHistory.getPrice() );
        bookingHistoryResponse.setCostsIncurred( bookingHistory.getCostsIncurred() );
        bookingHistoryResponse.setDiscount( bookingHistory.getDiscount() );
        bookingHistoryResponse.setDescription( bookingHistory.getDescription() );
        if ( bookingHistory.getCreateDate() != null ) {
            bookingHistoryResponse.setCreateDate( bookingHistory.getCreateDate().toString() );
        }
        bookingHistoryResponse.setCreatorName( bookingHistoryUserUserName( bookingHistory ) );
        bookingHistoryResponse.setHouseName( bookingHistoryHouseHouseName( bookingHistory ) );
        bookingHistoryResponse.setId_booking( bookingHistoryBookingId( bookingHistory ) );

        return bookingHistoryResponse;
    }

    protected User userToUser(User user) {
        if ( user == null ) {
            return null;
        }

        User user1 = new User();

        user1.setId( user.getId() );

        return user1;
    }

    protected House houseToHouse(House house) {
        if ( house == null ) {
            return null;
        }

        House house1 = new House();

        house1.setId( house.getId() );
        house1.setHouseName( house.getHouseName() );
        house1.setAmountRoom( house.getAmountRoom() );
        house1.setPrice( house.getPrice() );
        house1.setSize( house.getSize() );
        house1.setCapacity( house.getCapacity() );
        house1.setImage( house.getImage() );
        house1.setDescription( house.getDescription() );
        house1.setStatus( house.isStatus() );
        house1.setHomeStay( house.getHomeStay() );

        return house1;
    }

    protected Booking bookingToBooking(Booking booking) {
        if ( booking == null ) {
            return null;
        }

        Booking booking1 = new Booking();

        booking1.setId( booking.getId() );

        return booking1;
    }

    private String bookingHistoryUserUserName(BookingHistory bookingHistory) {
        if ( bookingHistory == null ) {
            return null;
        }
        User user = bookingHistory.getUser();
        if ( user == null ) {
            return null;
        }
        String userName = user.getUserName();
        if ( userName == null ) {
            return null;
        }
        return userName;
    }

    private String bookingHistoryHouseHouseName(BookingHistory bookingHistory) {
        if ( bookingHistory == null ) {
            return null;
        }
        House house = bookingHistory.getHouse();
        if ( house == null ) {
            return null;
        }
        String houseName = house.getHouseName();
        if ( houseName == null ) {
            return null;
        }
        return houseName;
    }

    private Long bookingHistoryBookingId(BookingHistory bookingHistory) {
        if ( bookingHistory == null ) {
            return null;
        }
        Booking booking = bookingHistory.getBooking();
        if ( booking == null ) {
            return null;
        }
        Long id = booking.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }
}
