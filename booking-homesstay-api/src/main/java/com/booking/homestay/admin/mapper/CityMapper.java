package com.booking.homestay.admin.mapper;

import com.booking.homestay.admin.dto.CityRequest;
import com.booking.homestay.admin.dto.CityResponse;
import com.booking.homestay.model.City;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring")
public abstract class CityMapper {

    //Phương thức map: Được sử dụng để ánh xạ từ một CityRequest sang một đối tượng City. Trong đó, id của City sẽ được bỏ qua và cityName, type sẽ được sao chép từ cityRequest.
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "cityName", source = "cityRequest.cityName")
    @Mapping(target = "type", source = "cityRequest.type")
    public abstract City map(CityRequest cityRequest);


    //Phương thức mapToDto: Được sử dụng để ánh xạ từ một đối tượng City sang một CityResponse. Trong đó, id, cityName, type của City sẽ được sao chép sang CityResponse
    @Mapping(target = "id", source = "city.id")
    @Mapping(target = "cityName", source = "city.cityName")
    @Mapping(target = "type", source = "city.type")
    public abstract CityResponse mapToDto(City city);


    //Phương thức mapEditToDtoById: Được sử dụng để ánh xạ từ một CityRequest sang một đối tượng City khi chỉ chỉnh sửa theo id. Trong đó, id của City sẽ được sao chép từ cityRequest, và cityName, type sẽ được cập nhật từ cityRequest.
    @Mapping(target = "id", source = "cityRequest.id")
    @Mapping(target = "cityName", source = "cityRequest.cityName")
    @Mapping(target = "type", source = "cityRequest.type")
    public abstract City mapEditToDtoById(CityRequest cityRequest);


}
