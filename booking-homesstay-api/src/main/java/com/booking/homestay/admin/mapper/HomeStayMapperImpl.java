package com.booking.homestay.admin.mapper;

import com.booking.homestay.admin.dto.HomeStayRequest;
import com.booking.homestay.admin.dto.HomeStayResponse;
import com.booking.homestay.model.City;
import com.booking.homestay.model.District;
import com.booking.homestay.model.HomeStay;
import com.booking.homestay.model.Village;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
        value = "org.mapstruct.ap.MappingProcessor",
        date = "2024-03-02T22:47:17+0700",
        comments = "version: 1.4.2.Final, compiler: javac, environment: Java 11.0.21 (Oracle Corporation)"
)
@Component
public class HomeStayMapperImpl extends HomeStayMapper {

    @Override
    public HomeStay map(HomeStayRequest homeStayRequest, Village village) {
        if ( homeStayRequest == null && village == null ) {
            return null;
        }

        HomeStay homeStay = new HomeStay();

        if ( homeStayRequest != null ) {
            homeStay.setHomeStayName( homeStayRequest.getHomeStayName() );
            homeStay.setDescription( homeStayRequest.getDescription() );
            homeStay.setPhone( homeStayRequest.getPhone() );
            homeStay.setImage( homeStayRequest.getImage() );
        }
        if ( village != null ) {
            homeStay.setVillage( villageToVillage( village ) );
        }
        homeStay.setStatus( true );

        return homeStay;
    }

    @Override
    public HomeStayResponse mapToDto(HomeStay homeStay) {
        if ( homeStay == null ) {
            return null;
        }

        HomeStayResponse homeStayResponse = new HomeStayResponse();

        homeStayResponse.setId( homeStay.getId() );
        homeStayResponse.setHomeStayName( homeStay.getHomeStayName() );
        homeStayResponse.setDescription( homeStay.getDescription() );
        homeStayResponse.setPhone( homeStay.getPhone() );
        homeStayResponse.setImage( homeStay.getImage() );
        homeStayResponse.setStatus( homeStay.isStatus() );
        homeStayResponse.setVillageName( homeStayVillageVillageName( homeStay ) );
        homeStayResponse.setCityName( homeStayVillageDistrictCityCityName( homeStay ) );
        homeStayResponse.setDistrictName( homeStayVillageDistrictDistrictName( homeStay ) );

        homeStayResponse.setDetailPlace( getAllDetailPlace(homeStay.getId()) );

        return homeStayResponse;
    }

    @Override
    public HomeStay mapEditToDtoById(HomeStayRequest homeStayRequest, HomeStay homeStay, Village village) {
        if ( homeStayRequest == null && homeStay == null && village == null ) {
            return null;
        }

        HomeStay homeStay1 = new HomeStay();

        if ( homeStayRequest != null ) {
            homeStay1.setId( homeStayRequest.getId() );
            homeStay1.setHomeStayName( homeStayRequest.getHomeStayName() );
            homeStay1.setDescription( homeStayRequest.getDescription() );
            homeStay1.setPhone( homeStayRequest.getPhone() );
            homeStay1.setImage( homeStayRequest.getImage() );
        }
        if ( homeStay != null ) {
            homeStay1.setStatus( homeStay.isStatus() );
        }
        if ( village != null ) {
            homeStay1.setVillage( villageToVillage1( village ) );
        }

        return homeStay1;
    }

    protected Village villageToVillage(Village village) {
        if ( village == null ) {
            return null;
        }

        Village village1 = new Village();

        village1.setId( village.getId() );

        return village1;
    }

    private String homeStayVillageVillageName(HomeStay homeStay) {
        if ( homeStay == null ) {
            return null;
        }
        Village village = homeStay.getVillage();
        if ( village == null ) {
            return null;
        }
        String villageName = village.getVillageName();
        if ( villageName == null ) {
            return null;
        }
        return villageName;
    }

    private String homeStayVillageDistrictCityCityName(HomeStay homeStay) {
        if ( homeStay == null ) {
            return null;
        }
        Village village = homeStay.getVillage();
        if ( village == null ) {
            return null;
        }
        District district = village.getDistrict();
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

    private String homeStayVillageDistrictDistrictName(HomeStay homeStay) {
        if ( homeStay == null ) {
            return null;
        }
        Village village = homeStay.getVillage();
        if ( village == null ) {
            return null;
        }
        District district = village.getDistrict();
        if ( district == null ) {
            return null;
        }
        String districtName = district.getDistrictName();
        if ( districtName == null ) {
            return null;
        }
        return districtName;
    }

    protected Village villageToVillage1(Village village) {
        if ( village == null ) {
            return null;
        }

        Village village1 = new Village();

        village1.setId( village.getId() );

        return village1;
    }
}
