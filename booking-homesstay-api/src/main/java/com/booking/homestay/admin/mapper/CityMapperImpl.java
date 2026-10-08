package com.booking.homestay.admin.mapper;

import com.booking.homestay.admin.dto.CityRequest;
import com.booking.homestay.admin.dto.CityResponse;
import com.booking.homestay.model.City;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
        value = "org.mapstruct.ap.MappingProcessor",
        date = "2024-03-01T16:03:25+0700",
        comments = "version: 1.4.2.Final, compiler: javac, environment: Java 17.0.5 (Oracle Corporation)"
)
@Component
public class CityMapperImpl extends CityMapper {

    // đây là các phương thức được trien khai thừ mapstruct ở mapper
    @Override
    public City map(CityRequest cityRequest) {
        if ( cityRequest == null ) { //Nếu cityRequest là null, phương thức sẽ trả về null, bởi vì không có dữ liệu nào để ánh xạ
            return null;
        }

        //Nếu cityRequest không null, một đối tượng City mới sẽ được tạo ra
        // và các thuộc tính cityName và type của đối tượng City mới sẽ được sao chép từ các trường tương ứng của cityRequest.
        City city = new City();

        city.setCityName( cityRequest.getCityName() );
        city.setType( cityRequest.getType() );

        return city;
    }

    @Override
    public CityResponse mapToDto(City city) {
        if ( city == null ) { //Nếu city là null, phương thức sẽ trả về null, bởi vì không có dữ liệu để ánh xạ sang đối tượng DTO
            return null;
        }

        //Nếu city không null, một đối tượng CityResponse mới sẽ được tạo ra.
        //và các thuộc tính id, cityName và type của đối tượng CityResponse mới sẽ được sao chép từ các trường tương ứng của city
        CityResponse cityResponse = new CityResponse();

        cityResponse.setId( city.getId() );
        cityResponse.setCityName( city.getCityName() );
        cityResponse.setType( city.getType() );

        return cityResponse;
    }

    @Override
    public City mapEditToDtoById(CityRequest cityRequest) {
        if ( cityRequest == null ) { //Nếu cityRequest là null, phương thức trả về null vì không có dữ liệu nào để ánh xạ.
            return null;
        }

        //Nếu cityRequest không null, một đối tượng City mới được tạo ra.
        //và thuộc tính id,cityName,type của đối tượng City mới được sao chép từ trường id của cityRequest
        City city = new City();

        city.setId( cityRequest.getId() );
        city.setCityName( cityRequest.getCityName() );
        city.setType( cityRequest.getType() );

        return city;
    }
}
