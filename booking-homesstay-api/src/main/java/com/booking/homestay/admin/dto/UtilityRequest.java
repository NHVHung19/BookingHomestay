package com.booking.homestay.admin.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UtilityRequest {

    private Long id;

    private String utilityName;

    private String image;

    private Long id_typeUtility;

}
