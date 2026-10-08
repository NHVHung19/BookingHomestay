package com.booking.homestay.employee.mapper;

import com.booking.homestay.employee.dto.HouseRequest;
import com.booking.homestay.employee.dto.HouseResponse;
import com.booking.homestay.model.HomeStay;
import com.booking.homestay.model.House;
import com.booking.homestay.model.User;
import org.springframework.stereotype.Component;

import javax.annotation.Generated;

@Generated(
        value = "org.mapstruct.ap.MappingProcessor",
        date = "2024-03-02T22:47:17+0700",
        comments = "version: 1.4.2.Final, compiler: javac, environment: Java 11.0.21 (Oracle Corporation)"
)
@Component
public class HouseMapperImpl extends HouseMapper {
    @Override
    public House mapToSave(HouseRequest houseRequest, User user) {
        if ( houseRequest == null && user == null ) {
            return null;
        }

        House house = new House();

        if ( houseRequest != null ) {
            house.setHouseName( houseRequest.getHouseName() );
            house.setAmountRoom( houseRequest.getAmountRoom() );
            house.setPrice( houseRequest.getPrice() );
            house.setSize( houseRequest.getSize() );
            house.setCapacity( houseRequest.getCapacity() );
            house.setImage( houseRequest.getImage() );
            house.setDescription( houseRequest.getDescription() );
        }
        if ( user != null ) {
            house.setHomeStay( user.getHomeStay() );
        }
        house.setStatus( true );

        return house;
    }

    @Override
    public HouseResponse mapToDto(House house) {
        if ( house == null ) {
            return null;
        }

        HouseResponse houseResponse = new HouseResponse();

        houseResponse.setId( house.getId() );
        houseResponse.setHouseName( house.getHouseName() );
        houseResponse.setAmountRoom( house.getAmountRoom() );
        houseResponse.setPrice( house.getPrice() );
        houseResponse.setSize( house.getSize() );
        houseResponse.setCapacity( house.getCapacity() );
        houseResponse.setImage( house.getImage() );
        houseResponse.setDescription( house.getDescription() );
        houseResponse.setStatus( house.isStatus() );
        houseResponse.setId_homeStay( houseHomeStayId( house ) );

        houseResponse.setFeedbackResponses( getAllFeedBack(house.getId()) );
        houseResponse.setStar( getStar(house.getId()) );
        houseResponse.setScores( getScores(house.getId()) );

        return houseResponse;
    }

    @Override
    public House mapToEdit(HouseRequest houseRequest, House house) {
        if ( houseRequest == null && house == null ) {
            return null;
        }

        House house1 = new House();

        if ( houseRequest != null ) {
            house1.setId( houseRequest.getId() );
            house1.setHouseName( houseRequest.getHouseName() );
            house1.setSize( houseRequest.getSize() );
            house1.setAmountRoom( houseRequest.getAmountRoom() );
            house1.setPrice( houseRequest.getPrice() );
            house1.setCapacity( houseRequest.getCapacity() );
            house1.setImage( houseRequest.getImage() );
            house1.setDescription( houseRequest.getDescription() );
        }
        if ( house != null ) {
            house1.setStatus( house.isStatus() );
            house1.setHomeStay( house.getHomeStay() );
        }

        return house1;
    }

    private Long houseHomeStayId(House house) {
        if ( house == null ) {
            return null;
        }
        HomeStay homeStay = house.getHomeStay();
        if ( homeStay == null ) {
            return null;
        }
        Long id = homeStay.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }
}
