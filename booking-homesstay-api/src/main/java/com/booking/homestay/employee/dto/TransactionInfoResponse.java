package com.booking.homestay.employee.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransactionInfoResponse {
    private Long id;

    private String dateRelease;

    private int totalPrice;

    private String creatorName;

    private BookingResponse bookingResponse;
}
