package com.booking.homestay.exception;

import com.booking.homestay.utils.BookingCode;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class SpringException extends ResponseStatusException {
    public SpringException(BookingCode bookingCode) {
        super(HttpStatus.valueOf(bookingCode.getCode()), bookingCode.getMessage());
    }

    public SpringException(String message) {
        super(HttpStatus.BAD_REQUEST, message);
    }

    public SpringException(String message, Exception ex) {
        super(HttpStatus.BAD_REQUEST, message);
    }

    public SpringException(BookingCode bookingCode, long id) {
        super(HttpStatus.valueOf(bookingCode.getCode()), bookingCode.getMessage() + " " + id);
    }

    public SpringException(BookingCode bookingCode, String name) {
        super(HttpStatus.valueOf(bookingCode.getCode()), bookingCode.getMessage() + " " + name);
    }
}
