package com.booking.homestay.admin.mapper;

import com.booking.homestay.admin.dto.DetailPlaceResponse;
import com.booking.homestay.model.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public abstract class DetailPlaceMapper {

    //mapToSave: Phương thức này ánh xạ từ đối tượng Place và HomeStay sang đối tượng DetailPlace để lưu trữ
    // Trong đó: id: Trường này được bỏ qua,place: Trường này được ánh xạ từ trường place của đối tượng nguồn,
    //     homeStay: Trường này được ánh xạ từ trường homeStay.
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "place", source = "place")
    @Mapping(target = "homeStay", source = "homeStay")
    public abstract DetailPlace mapToSave(Place place, HomeStay homeStay);

    //mapToDto: Phương thức này ánh xạ từ đối tượng DetailPlace sang đối tượng DetailPlaceResponse để trả về dữ liệu cho người dùng
    // Trong đó: id: Trường này được ánh xạ từ trường id của DetailPlace.
    //           id_homeStay: Trường này được ánh xạ từ trường id của HomeStay mà DetailPlace thuộc về.
    // placeName,image,id_place: Trường này được ánh xạ từ trường placeName,image,id của Place mà DetailPlace tham chiếu đến.
    @Mapping(target = "id", source = "detailPlace.id")
    @Mapping(target = "id_homeStay", source = "detailPlace.homeStay.id")
    @Mapping(target = "placeName", source = "detailPlace.place.placeName")
    @Mapping(target = "image", source = "detailPlace.place.image")
    @Mapping(target = "id_place", source = "detailPlace.place.id")
    public abstract DetailPlaceResponse mapToDto(DetailPlace detailPlace);

}
