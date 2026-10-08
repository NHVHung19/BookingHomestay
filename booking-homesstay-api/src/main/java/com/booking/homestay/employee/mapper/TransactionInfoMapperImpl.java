package com.booking.homestay.employee.mapper;

import com.booking.homestay.employee.dto.TransactionInfoResponse;
import com.booking.homestay.model.Booking;
import com.booking.homestay.model.TransactionInfo;
import com.booking.homestay.model.User;
import org.springframework.stereotype.Component;

import javax.annotation.Generated;

@Generated(
        value = "org.mapstruct.ap.MappingProcessor",
        date = "2024-03-02T22:47:17+0700",
        comments = "version: 1.4.2.Final, compiler: javac, environment: Java 11.0.21 (Oracle Corporation)"
)
@Component
public class TransactionInfoMapperImpl  extends TransactionInfoMapper{
    @Override
    public TransactionInfo mapToSave(Booking booking, User user) {
        if ( booking == null && user == null ) {
            return null;
        }

        TransactionInfo transactionInfo = new TransactionInfo();

        if ( booking != null ) {
            transactionInfo.setBooking( bookingToBooking( booking ) );
        }
        if ( user != null ) {
            transactionInfo.setUser( user );
        }
        transactionInfo.setDateRelease( java.time.Instant.now() );
        transactionInfo.setTotalPrice( getTotal(booking) );

        return transactionInfo;
    }


    @Override
    public TransactionInfoResponse mapToDto(TransactionInfo transactionInfo) {
        if ( transactionInfo == null ) {
            return null;
        }

        TransactionInfoResponse transactionInfoResponse = new TransactionInfoResponse();

        transactionInfoResponse.setId( transactionInfo.getId() );
        if ( transactionInfo.getDateRelease() != null ) {
            transactionInfoResponse.setDateRelease( transactionInfo.getDateRelease().toString() );
        }
        transactionInfoResponse.setTotalPrice( transactionInfo.getTotalPrice() );
        transactionInfoResponse.setCreatorName( transactionInfoUserUserName( transactionInfo ) );

        transactionInfoResponse.setBookingResponse( getBookingById(transactionInfo.getBooking().getId()) );

        return transactionInfoResponse;
    }

    private String transactionInfoUserUserName(TransactionInfo transactionInfo) {
        if ( transactionInfo == null ) {
            return null;
        }
        User user = transactionInfo.getUser();
        if ( user == null ) {
            return null;
        }
        String userName = user.getUserName();
        if ( userName == null ) {
            return null;
        }
        return userName;
    }

    private Booking bookingToBooking(Booking booking) {
        if ( booking == null ) {
            return null;
        }

        Booking booking1 = new Booking();

        booking1.setId( booking.getId() );

        return booking1;
    }

}
