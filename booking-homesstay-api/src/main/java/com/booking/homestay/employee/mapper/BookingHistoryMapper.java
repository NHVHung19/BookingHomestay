package com.booking.homestay.employee.mapper;

import com.booking.homestay.employee.dto.BookingHistoryResponse;
import com.booking.homestay.model.Booking;

import com.booking.homestay.model.BookingHistory;
import com.booking.homestay.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;



@Mapper(componentModel = "spring")
public abstract class BookingHistoryMapper {
//để ánh xạ từ các đối tượng thực thể (entity) như Booking và BookingHistory
// sang các đối tượng DTO như BookingHistoryResponse
//mapToSave() method: Đây là phương thức được sử dụng để ánh xạ từ đối tượng Booking và User sang đối tượng BookingHistory
@Mapping(target = "id", ignore = true)
@Mapping(target = "fullName", source = "booking.fullName")
@Mapping(target = "address", source = "booking.address")
@Mapping(target = "email", source = "booking.email")
@Mapping(target = "phone", source = "booking.phone")
@Mapping(target = "dateIn", source = "booking.dateIn")
@Mapping(target = "dateOut", source = "booking.dateOut")
@Mapping(target = "price", source = "booking.price")
@Mapping(target = "costsIncurred", source = "booking.costsIncurred")
@Mapping(target = "discount", source = "booking.discount")
@Mapping(target = "description", source = "booking.description")
@Mapping(target = "createDate", expression = "java(java.time.Instant.now())")
@Mapping(target = "user.id", source = "user.id")


@Mapping(target = "house.id", source = "booking.house.id")


@Mapping(target = "booking.id", source = "booking.id")
public abstract BookingHistory mapToSave(Booking booking, User user);

    @Mapping(target = "id", source = "bookingHistory.id")
    @Mapping(target = "fullName", source = "bookingHistory.fullName")
    @Mapping(target = "address", source = "bookingHistory.address")
    @Mapping(target = "email", source = "bookingHistory.email")
    @Mapping(target = "phone", source = "bookingHistory.phone")
    @Mapping(target = "dateIn", source = "bookingHistory.dateIn")
    @Mapping(target = "dateOut", source = "bookingHistory.dateOut")
    @Mapping(target = "price", source = "bookingHistory.price")
    @Mapping(target = "costsIncurred", source = "bookingHistory.costsIncurred")
    @Mapping(target = "discount", source = "bookingHistory.discount")
    @Mapping(target = "description", source = "bookingHistory.description")
    @Mapping(target = "createDate", source = "bookingHistory.createDate")
    @Mapping(target = "creatorName", source = "bookingHistory.user.userName")
    @Mapping(target = "houseName", source = "bookingHistory.house.houseName")
    @Mapping(target = "id_booking", source = "bookingHistory.booking.id")
    public abstract BookingHistoryResponse mapToDto(BookingHistory bookingHistory);

}
