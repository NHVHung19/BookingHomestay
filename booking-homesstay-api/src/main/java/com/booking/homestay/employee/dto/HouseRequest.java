package com.booking.homestay.employee.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HouseRequest {
    private Long id;

    private String houseName;

    private String description;

    private Integer amountRoom;

    private Integer price;

    private Integer size;

    private Integer capacity;

    private  String image;

    private boolean status;

    private Long id_homestay;
}
