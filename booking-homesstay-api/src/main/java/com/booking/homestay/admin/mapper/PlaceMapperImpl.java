package com.booking.homestay.admin.mapper;

import com.booking.homestay.admin.dto.PlaceRequest;
import com.booking.homestay.admin.dto.PlaceResponse;
import com.booking.homestay.model.Place;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
        value = "org.mapstruct.ap.MappingProcessor",
        date = "2024-03-02T22:47:17+0700",
        comments = "version: 1.4.2.Final, compiler: javac, environment: Java 11.0.21 (Oracle Corporation)"
)
@Component
public class PlaceMapperImpl extends PlaceMapper {

    @Override
    public Place map(PlaceRequest placeRequest) {
        if ( placeRequest == null ) {
            return null;
        }

        Place place = new Place();

        place.setPlaceName( placeRequest.getPlaceName() );
        place.setImage( placeRequest.getImage() );

        return place;
    }

    @Override
    public PlaceResponse mapToDto(Place place) {
        if ( place == null ) {
            return null;
        }

        PlaceResponse placeResponse = new PlaceResponse();

        placeResponse.setId( place.getId() );
        placeResponse.setPlaceName( place.getPlaceName() );
        placeResponse.setImage( place.getImage() );

        return placeResponse;
    }

    @Override
    public Place mapEditToDtoById(PlaceRequest placeRequest, Place place) {
        if ( placeRequest == null && place == null ) {
            return null;
        }

        Place place1 = new Place();

        if ( placeRequest != null ) {
            place1.setId( placeRequest.getId() );
            place1.setPlaceName( placeRequest.getPlaceName() );
            place1.setImage( placeRequest.getImage() );
        }

        return place1;
    }
}
