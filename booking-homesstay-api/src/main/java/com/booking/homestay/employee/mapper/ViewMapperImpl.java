package com.booking.homestay.employee.mapper;

import com.booking.homestay.employee.dto.ViewRequest;
import com.booking.homestay.employee.dto.ViewResponse;
import com.booking.homestay.model.View;
import org.springframework.stereotype.Component;

import javax.annotation.Generated;

@Generated(
        value = "org.mapstruct.ap.MappingProcessor",
        date = "2024-03-02T22:47:17+0700",
        comments = "version: 1.4.2.Final, compiler: javac, environment: Java 11.0.21 (Oracle Corporation)"
)
@Component
public class ViewMapperImpl extends ViewMapper{
    @Override
    public View map(ViewRequest viewRequest) {
        if ( viewRequest == null ) {
            return null;
        }

        View view = new View();

        view.setViewName( viewRequest.getViewName() );
        view.setImage( viewRequest.getImage() );

        return view;
    }

    @Override
    public ViewResponse mapToDto(View view) {
        if ( view == null ) {
            return null;
        }

        ViewResponse viewResponse = new ViewResponse();

        viewResponse.setId( view.getId() );
        viewResponse.setViewName( view.getViewName() );
        viewResponse.setImage( view.getImage() );

        return viewResponse;
    }

    @Override
    public View mapEditToDtoById(ViewRequest viewRequest) {
        if ( viewRequest == null ) {
            return null;
        }

        View view = new View();

        view.setId( viewRequest.getId() );
        view.setViewName( viewRequest.getViewName() );
        view.setImage( viewRequest.getImage() );

        return view;
    }
}
