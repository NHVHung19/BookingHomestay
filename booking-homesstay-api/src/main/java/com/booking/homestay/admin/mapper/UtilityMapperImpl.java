package com.booking.homestay.admin.mapper;

import com.booking.homestay.admin.dto.UtilityRequest;
import com.booking.homestay.admin.dto.UtilityResponse;
import com.booking.homestay.model.TypeUtility;
import com.booking.homestay.model.Utility;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
        value = "org.mapstruct.ap.MappingProcessor",
        date = "2024-03-02T22:47:17+0700",
        comments = "version: 1.4.2.Final, compiler: javac, environment: Java 11.0.21 (Oracle Corporation)"
)
@Component
public class UtilityMapperImpl extends UtilityMapper {

    @Override
    public Utility map(UtilityRequest utilityRequest) {
        if ( utilityRequest == null ) {
            return null;
        }

        Utility utility = new Utility();

        utility.setTypeUtility( utilityRequestToTypeUtility( utilityRequest ) );
        utility.setUtilityName( utilityRequest.getUtilityName() );
        utility.setImage( utilityRequest.getImage() );

        return utility;
    }

    @Override
    public UtilityResponse mapToDto(Utility utility) {
        if ( utility == null ) {
            return null;
        }

        UtilityResponse utilityResponse = new UtilityResponse();

        utilityResponse.setId( utility.getId() );
        utilityResponse.setUtilityName( utility.getUtilityName() );
        utilityResponse.setImage( utility.getImage() );
        utilityResponse.setId_typeUtility( utilityTypeUtilityId( utility ) );

        return utilityResponse;
    }

    @Override
    public Utility mapEditToDtoById(UtilityRequest utilityRequest, Utility utility) {
        if ( utilityRequest == null && utility == null ) {
            return null;
        }

        Utility utility1 = new Utility();

        if ( utilityRequest != null ) {
            utility1.setTypeUtility( utilityRequestToTypeUtility1( utilityRequest ) );
            utility1.setId( utilityRequest.getId() );
            utility1.setUtilityName( utilityRequest.getUtilityName() );
            utility1.setImage( utilityRequest.getImage() );
        }

        return utility1;
    }

    protected TypeUtility utilityRequestToTypeUtility(UtilityRequest utilityRequest) {
        if ( utilityRequest == null ) {
            return null;
        }

        TypeUtility typeUtility = new TypeUtility();

        typeUtility.setId( utilityRequest.getId_typeUtility() );

        return typeUtility;
    }

    private Long utilityTypeUtilityId(Utility utility) {
        if ( utility == null ) {
            return null;
        }
        TypeUtility typeUtility = utility.getTypeUtility();
        if ( typeUtility == null ) {
            return null;
        }
        Long id = typeUtility.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    protected TypeUtility utilityRequestToTypeUtility1(UtilityRequest utilityRequest) {
        if ( utilityRequest == null ) {
            return null;
        }

        TypeUtility typeUtility = new TypeUtility();

        typeUtility.setId( utilityRequest.getId_typeUtility() );

        return typeUtility;
    }
}
