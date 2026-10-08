package com.booking.homestay.employee.mapper;

import com.booking.homestay.employee.dto.DetailViewResponse;
import com.booking.homestay.model.DetailView;
import com.booking.homestay.model.House;
import com.booking.homestay.model.View;
import org.springframework.stereotype.Component;

import javax.annotation.Generated;

@Generated(
        value = "org.mapstruct.ap.MappingProcessor",
        date = "2024-03-02T22:47:17+0700",
        comments = "version: 1.4.2.Final, compiler: javac, environment: Java 11.0.21 (Oracle Corporation)"
)
@Component
public class DetailViewMapperImpl extends DetailViewMapper{
    @Override
    public DetailView mapToSave(View view, House house) {
        if ( view == null && house == null ) {
            return null;
        }

        DetailView detailView = new DetailView();

        if ( view != null ) {
            detailView.setView( view );
        }
        if ( house != null ) {
            detailView.setHouse( house );
        }

        return detailView;
    }

    @Override
    public DetailViewResponse mapToDto(DetailView detailView) {
        if ( detailView == null ) {
            return null;
        }

        DetailViewResponse detailViewResponse = new DetailViewResponse();

        detailViewResponse.setId( detailView.getId() );
        detailViewResponse.setId_house( detailViewHouseId( detailView ) );
        detailViewResponse.setViewName( detailViewViewViewName( detailView ) );
        detailViewResponse.setImage( detailViewViewImage( detailView ) );
        detailViewResponse.setId_view( detailViewViewId( detailView ) );

        return detailViewResponse;
    }

    private Long detailViewViewId(DetailView detailView) {
        if ( detailView == null ) {
            return null;
        }
        View view = detailView.getView();
        if ( view == null ) {
            return null;
        }
        Long id = view.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private String detailViewViewImage(DetailView detailView) {
        if ( detailView == null ) {
            return null;
        }
        View view = detailView.getView();
        if ( view == null ) {
            return null;
        }
        String image = view.getImage();
        if ( image == null ) {
            return null;
        }
        return image;
    }

    private String detailViewViewViewName(DetailView detailView) {
        if ( detailView == null ) {
            return null;
        }
        View view = detailView.getView();
        if ( view == null ) {
            return null;
        }
        String viewName = view.getViewName();
        if ( viewName == null ) {
            return null;
        }
        return viewName;
    }

    private Long detailViewHouseId(DetailView detailView) {
        if ( detailView == null ) {
            return null;
        }
        House house = detailView.getHouse();
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
