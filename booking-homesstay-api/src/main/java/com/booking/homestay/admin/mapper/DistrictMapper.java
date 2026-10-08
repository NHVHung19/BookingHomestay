package com.booking.homestay.admin.mapper;


import com.booking.homestay.admin.dto.DistrictRequest;
import com.booking.homestay.admin.dto.DistrictResponse;
import com.booking.homestay.model.City;
import com.booking.homestay.model.District;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public abstract class DistrictMapper {

    //Các phương thức ánh xạ trong Mapper của bạn được sử dụng để ánh xạ giữa các đối tượng District và DistrictRequest, cũng như giữa District và DistrictResponse

    //map: Phương thức này ánh xạ từ một đối tượng DistrictRequest và một đối tượng City thành một đối tượng District.
    // Các trường được ánh xạ bao gồm districtName từ districtRequest và id của city từ city
    @Mapping(target = "id",  ignore = true )
    @Mapping(target = "districtName", source = "districtRequest.districtName" )
    @Mapping(target = "type", source = "districtRequest.type" )
    @Mapping(target = "city.id", source = "city.id" )
    public abstract District map(DistrictRequest districtRequest, City city);


    //mapToDto: Phương thức này ánh xạ từ một đối tượng District thành một đối tượng DistrictResponse.
    // Các trường được ánh xạ bao gồm id, districtName và type từ district, cũng như cityName từ city liên kết với district.
    @Mapping(target = "id",  source = "district.id" )
    @Mapping(target = "districtName", source = "district.districtName" )
    @Mapping(target = "type", source = "district.type" )
    @Mapping(target = "cityName", source =  "district.city.cityName")
    public abstract DistrictResponse mapToDto(District district);

    //mapEditToDtoById: Phương thức này ánh xạ từ một đối tượng DistrictRequest và một đối tượng City thành một đối tượng District.
    // Các trường được ánh xạ tương tự như phương thức map, nhưng có thêm trường id từ districtRequest
    @Mapping(target = "id",  source = "districtRequest.id"  )
    @Mapping(target = "districtName", source = "districtRequest.districtName" )
    @Mapping(target = "type", source = "districtRequest.type" )
    @Mapping(target = "city.id", source = "city.id" )
    public abstract District mapEditToDtoById(DistrictRequest districtRequest, City city);


}
