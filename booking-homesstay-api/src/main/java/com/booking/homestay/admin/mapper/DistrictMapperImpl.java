package com.booking.homestay.admin.mapper;

import com.booking.homestay.admin.dto.DistrictRequest;
import com.booking.homestay.admin.dto.DistrictResponse;
import com.booking.homestay.model.City;
import com.booking.homestay.model.District;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
        value = "org.mapstruct.ap.MappingProcessor",
        date = "2024-03-02T22:47:17+0700",
        comments = "version: 1.4.2.Final, compiler: javac, environment: Java 11.0.21 (Oracle Corporation)"
)
@Component
public class DistrictMapperImpl extends DistrictMapper {

    //Pt map trong Mapper của bạn có nhiệm vụ ánh xạ từ một đối tượng DistrictRequest và một đối tượng City thành một đối tượng District.
    @Override
    public District map(DistrictRequest districtRequest, City city) {
        //kiểm tra xem các đối tượng đầu vào có null không. Nếu cả hai đều là null, nó sẽ trả về null
        if ( districtRequest == null && city == null ) {
            return null;
        }

        District district = new District();

        //ếu districtRequest không phải là null, các trường districtName và type được ánh xạ từ districtRequest vào đối tượng District mới tạo
        if ( districtRequest != null ) {
            district.setDistrictName( districtRequest.getDistrictName() );
            district.setType( districtRequest.getType() );
        }
        //Nếu city không phải là null, phương thức cityToCity được gọi để chuyển đổi city thành đối tượng City và gán vào trường city của đối tượng District mới tạo
        if ( city != null ) {
            district.setCity( cityToCity( city ) );
        }

        return district;
    }


    //Pt mapToDto trong Mapper của bạn có nhiệm vụ ánh xạ từ một đối tượng District thành một đối tượng DistrictResponse
    @Override
    public DistrictResponse mapToDto(District district) {
       //iểm tra xem đối tượng đầu vào district có null không. Nếu là null, nó sẽ trả về null
        if ( district == null ) {
            return null;
        }

        DistrictResponse districtResponse = new DistrictResponse();

        districtResponse.setId( district.getId() );
        districtResponse.setDistrictName( district.getDistrictName() );
        districtResponse.setType( district.getType() );
        districtResponse.setCityName( districtCityCityName( district ) );

        return districtResponse;
    }

    @Override
    public District mapEditToDtoById(DistrictRequest districtRequest, City city) {
        if ( districtRequest == null && city == null ) {
            return null;
        }

        District district = new District();

        if ( districtRequest != null ) {
            district.setId( districtRequest.getId() );
            district.setDistrictName( districtRequest.getDistrictName() );
            district.setType( districtRequest.getType() );
        }
        if ( city != null ) {
            district.setCity( cityToCity1( city ) );
        }

        return district;
    }

    protected City cityToCity(City city) {
        if ( city == null ) {
            return null;
        }

        City city1 = new City();

        city1.setId( city.getId() );

        return city1;
    }

    private String districtCityCityName(District district) {
        if ( district == null ) {
            return null;
        }
        City city = district.getCity();
        if ( city == null ) {
            return null;
        }
        String cityName = city.getCityName();
        if ( cityName == null ) {
            return null;
        }
        return cityName;
    }

    protected City cityToCity1(City city) {
        if ( city == null ) {
            return null;
        }

        City city1 = new City();

        city1.setId( city.getId() );

        return city1;
    }
}
