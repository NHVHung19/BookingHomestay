package com.booking.homestay.admin.mapper;

import com.booking.homestay.admin.dto.TypeUtilityRequest;
import com.booking.homestay.admin.dto.TypeUtilityResponse;
import com.booking.homestay.model.TypeUtility;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
        value = "org.mapstruct.ap.MappingProcessor",
        date = "2024-03-02T22:47:17+0700",
        comments = "version: 1.4.2.Final, compiler: javac, environment: Java 11.0.21 (Oracle Corporation)"
)
@Component
public class TypeUtilityMapperImpl extends TypeUtilityMapper {

    @Override
    public TypeUtility map(TypeUtilityRequest typeUtilityRequest) {
        if ( typeUtilityRequest == null ) {
            return null;
        }

        TypeUtility typeUtility = new TypeUtility();

        typeUtility.setTypeName( typeUtilityRequest.getTypeName() );

        return typeUtility;
    }

    @Override
    public TypeUtilityResponse mapToDto(TypeUtility typeUtility) {
        if ( typeUtility == null ) {
            return null;
        }

        TypeUtilityResponse typeUtilityResponse = new TypeUtilityResponse();

        typeUtilityResponse.setId( typeUtility.getId() );
        typeUtilityResponse.setTypeName( typeUtility.getTypeName() );

        typeUtilityResponse.setUtility( getAllUtility(typeUtility.getId()) );

        return typeUtilityResponse;
    }

    @Override
    public TypeUtility mapEditToDtoById(TypeUtilityRequest typeUtilityRequest, TypeUtility typeUtility) {
        if ( typeUtilityRequest == null && typeUtility == null ) {
            return null;
        }

        TypeUtility typeUtility1 = new TypeUtility();

        if ( typeUtilityRequest != null ) {
            typeUtility1.setId( typeUtilityRequest.getId() );
            typeUtility1.setTypeName( typeUtilityRequest.getTypeName() );
        }

        return typeUtility1;
    }
}
