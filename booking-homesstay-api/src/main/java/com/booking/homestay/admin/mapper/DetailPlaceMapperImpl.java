package com.booking.homestay.admin.mapper;

import com.booking.homestay.admin.dto.DetailPlaceResponse;
import com.booking.homestay.model.DetailPlace;
import com.booking.homestay.model.HomeStay;
import com.booking.homestay.model.Place;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
        value = "org.mapstruct.ap.MappingProcessor",
        date = "2024-03-01T16:03:25+0700",
        comments = "version: 1.4.2.Final, compiler: javac, environment: Java 17.0.5 (Oracle Corporation)"
)
@Component
public class DetailPlaceMapperImpl extends DetailPlaceMapper {

    //Pt mapToSave nhận đối tượng Place và HomeStay và ánh xạ chúng vào một đối tượng DetailPlace để lưu trữ
    @Override
    public DetailPlace mapToSave(Place place, HomeStay homeStay) {
        if ( place == null && homeStay == null ) { //Ktra nếu cả place và homeStay đều là null, trong tr.hop này, không có đủ thông tin để tạo một DetailPlace nên pt trả về null
            return null;
        }


        DetailPlace detailPlace = new DetailPlace();
        //Nếu place ko phải là null, đtượng place được thiết lập cho trường place của đối tượng DetailPlace mới
        if ( place != null ) {
            detailPlace.setPlace( place );
        }
        //nếu homeStay không phải là null, đối tượng homeStay được thiết lập cho trường homeStay của đối tượng DetailPlace mới
        if ( homeStay != null ) {
            detailPlace.setHomeStay( homeStay );
        }

        return detailPlace;
    }

    //Pt mapToDto nhận một đối tượng DetailPlace và ánh xạ các thông tin từ đối tượng này vào một đối tượng DetailPlaceResponse để trả về
    @Override
    public DetailPlaceResponse mapToDto(DetailPlace detailPlace) {
        if ( detailPlace == null ) { //nếu đối tượng detailPlace là null, nếu có, phương thức trả về null
            return null;
        }

        //Nếu detailPlace không phải là null:
        //Một đối tượng DetailPlaceResponse mới được tạo.
        //Các thông tin từ detailPlace được sao chép vào đối tượng DetailPlaceResponse mới, bao gồm id, id của homeStay, tên của place, hình ảnh của place và id của place.
        //Các giá trị nhận được thông qua các phương thức private được sử dụng để truy xuất các thông tin cần thiết từ detailPlace
        DetailPlaceResponse detailPlaceResponse = new DetailPlaceResponse();

        detailPlaceResponse.setId( detailPlace.getId() );
        detailPlaceResponse.setId_homeStay( detailPlaceHomeStayId( detailPlace ) );
        detailPlaceResponse.setPlaceName( detailPlacePlacePlaceName( detailPlace ) );
        detailPlaceResponse.setImage( detailPlacePlaceImage( detailPlace ) );
        detailPlaceResponse.setId_place( detailPlacePlaceId( detailPlace ) );

        return detailPlaceResponse;
    }

    //Pt detailPlaceHomeStayId được sử dụng để trích xuất ID của homeStay từ đối tượng DetailPlace
    private Long detailPlaceHomeStayId(DetailPlace detailPlace) {
        if ( detailPlace == null ) { //nếu đối tượng detailPlace là null, nếu có, phương thức trả về null
            return null;
        }

        //Nếu detailPlace không phải là null. Lấy đối tượng HomeStay từ detailPlace.
        HomeStay homeStay = detailPlace.getHomeStay();
        if ( homeStay == null ) { //ktra nếu homeStay là null, nếu có, phương thức trả về null
            return null;
        }
        Long id = homeStay.getId();
        if ( id == null ) { //Nếu homeStay không phải là null, trích xuất ID của nó
            return null;
        }
        return id;
    }

    //Pt detailPlacePlacePlaceName được sử dụng để trích xuất tên của Place từ đối tượng DetailPlace
    private String detailPlacePlacePlaceName(DetailPlace detailPlace) {
        if ( detailPlace == null ) {
            return null;
        }
        Place place = detailPlace.getPlace();
        if ( place == null ) {
            return null;
        }
        String placeName = place.getPlaceName();
        if ( placeName == null ) {
            return null;
        }
        return placeName;
    }

    ////Pt detailPlacePlacePlaceImage được sử dụng để trích xuất ảnh của Place từ đối tượng DetailPlace
    private String detailPlacePlaceImage(DetailPlace detailPlace) {
        if ( detailPlace == null ) {
            return null;
        }
        Place place = detailPlace.getPlace();
        if ( place == null ) {
            return null;
        }
        String image = place.getImage();
        if ( image == null ) {
            return null;
        }
        return image;
    }

    //Pt detailPlacePlaceId được sử dụng để trích xuất ID của Place từ đối tượng DetailPlace
    private Long detailPlacePlaceId(DetailPlace detailPlace) {
        if ( detailPlace == null ) {
            return null;
        }
        Place place = detailPlace.getPlace();
        if ( place == null ) {
            return null;
        }
        Long id = place.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }
}
