package com.booking.homestay.admin.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VillageRequest {

    private Long id;

    private String villageName;

    private String type;

    private String districtName;

    private String cityName;

}
