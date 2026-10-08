package com.booking.homestay.admin.mapper;

import com.booking.homestay.admin.dto.VillageRequest;
import com.booking.homestay.admin.dto.VillageResponse;
import com.booking.homestay.model.City;
import com.booking.homestay.model.District;
import com.booking.homestay.model.Village;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
        value = "org.mapstruct.ap.MappingProcessor",
        date = "2024-03-02T22:47:17+0700",
        comments = "version: 1.4.2.Final, compiler: javac, environment: Java 11.0.21 (Oracle Corporation)"
)
@Component
public class VillageMapperImpl extends VillageMapper {

    @Override
    public Village map(VillageRequest villageRequest, District district) {
        if ( villageRequest == null && district == null ) {
            return null;
        }

        Village village = new Village();

        if ( villageRequest != null ) {
            village.setVillageName( villageRequest.getVillageName() );
            village.setType( villageRequest.getType() );
        }
        if ( district != null ) {
            village.setDistrict( districtToDistrict( district ) );
        }

        return village;
    }

    @Override
    public VillageResponse mapToDto(Village village) {
        if ( village == null ) {
            return null;
        }

        VillageResponse villageResponse = new VillageResponse();

        villageResponse.setId( village.getId() );
        villageResponse.setVillageName( village.getVillageName() );
        villageResponse.setType( village.getType() );
        villageResponse.setDistrictName( villageDistrictDistrictName( village ) );
        villageResponse.setCityName( villageDistrictCityCityName( village ) );

        return villageResponse;
    }

    @Override
    public Village mapEditToDtoById(VillageRequest villageRequest, District district) {
        if ( villageRequest == null && district == null ) {
            return null;
        }

        Village village = new Village();

        if ( villageRequest != null ) {
            village.setId( villageRequest.getId() );
            village.setVillageName( villageRequest.getVillageName() );
            village.setType( villageRequest.getType() );
        }
        if ( district != null ) {
            village.setDistrict( districtToDistrict1( district ) );
        }

        return village;
    }

    protected District districtToDistrict(District district) {
        if ( district == null ) {
            return null;
        }

        District district1 = new District();

        district1.setId( district.getId() );

        return district1;
    }

    private String villageDistrictDistrictName(Village village) {
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

    private String villageDistrictCityCityName(Village village) {
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

    protected District districtToDistrict1(District district) {
        if ( district == null ) {
            return null;
        }

        District district1 = new District();

        district1.setId( district.getId() );

        return district1;
    }
}
