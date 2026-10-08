package com.booking.homestay.employee.mapper;

import com.booking.homestay.employee.dto.DetailUtilityRequest;
import com.booking.homestay.employee.dto.DetailUtilityResponse;
import com.booking.homestay.model.DetailUtility;
import com.booking.homestay.model.House;
import com.booking.homestay.model.Utility;
import org.springframework.stereotype.Component;

import javax.annotation.Generated;

@Generated(
        value = "org.mapstruct.ap.MappingProcessor",
        date = "2024-03-02T22:47:17+0700",
        comments = "version: 1.4.2.Final, compiler: javac, environment: Java 11.0.21 (Oracle Corporation)"
)
@Component
public class DetailUtilityMapperImpl extends DetailUtilityMapper{
    @Override
    public DetailUtility mapToSave(House house, Utility utility) {
        if ( house == null && utility == null ) {
            return null;
        }

        DetailUtility detailUtility = new DetailUtility();

        if ( house != null ) {
            detailUtility.setHouse( house );
        }
        if ( utility != null ) {
            detailUtility.setUtility( utility );
        }
        detailUtility.setQuantity( 1 );

        return detailUtility;
    }

    @Override
    public DetailUtilityResponse mapToDto(DetailUtility detailUtility) {
        if ( detailUtility == null ) {
            return null;
        }

        DetailUtilityResponse detailUtilityResponse = new DetailUtilityResponse();

        detailUtilityResponse.setId( detailUtility.getId() );
        detailUtilityResponse.setId_house( detailUtilityHouseId( detailUtility ) );
        detailUtilityResponse.setQuantity( detailUtility.getQuantity() );
        detailUtilityResponse.setUtilityName( detailUtilityUtilityUtilityName( detailUtility ) );
        detailUtilityResponse.setImage( detailUtilityUtilityImage( detailUtility ) );
        detailUtilityResponse.setId_utility( detailUtilityUtilityId( detailUtility ) );

        return detailUtilityResponse;
    }

    @Override
    public DetailUtility mapToEdit(DetailUtility detailUtility, DetailUtilityRequest detailUtilityRequest) {
        if ( detailUtility == null && detailUtilityRequest == null ) {
            return null;
        }

        DetailUtility detailUtility1 = new DetailUtility();

        if ( detailUtility != null ) {
            detailUtility1.setUtility( detailUtility.getUtility() );
            detailUtility1.setHouse( detailUtility.getHouse() );
        }
        if ( detailUtilityRequest != null ) {
            detailUtility1.setId( detailUtilityRequest.getId() );
            detailUtility1.setQuantity( detailUtilityRequest.getQuantity() );
        }

        return detailUtility1;
    }
    private Long detailUtilityUtilityId(DetailUtility detailUtility) {
        if ( detailUtility == null ) {
            return null;
        }
        Utility utility = detailUtility.getUtility();
        if ( utility == null ) {
            return null;
        }
        Long id = utility.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private String detailUtilityUtilityImage(DetailUtility detailUtility) {
        if ( detailUtility == null ) {
            return null;
        }
        Utility utility = detailUtility.getUtility();
        if ( utility == null ) {
            return null;
        }
        String image = utility.getImage();
        if ( image == null ) {
            return null;
        }
        return image;
    }

    private String detailUtilityUtilityUtilityName(DetailUtility detailUtility) {
        if ( detailUtility == null ) {
            return null;
        }
        Utility utility = detailUtility.getUtility();
        if ( utility == null ) {
            return null;
        }
        String utilityName = utility.getUtilityName();
        if ( utilityName == null ) {
            return null;
        }
        return utilityName;
    }

    private Long detailUtilityHouseId(DetailUtility detailUtility) {
        if ( detailUtility == null ) {
            return null;
        }
        House house = detailUtility.getHouse();
        if ( house == null ) {
            return null;
        }
        Long id = house.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }


}
