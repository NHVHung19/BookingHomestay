package com.booking.homestay.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum BookingCode {
    USER_NAME_EXITS(400, "Thông tin username da ton tai"),
    SEND_MAIL_FAIL(500, "Gửi mail thất bại ! "),
    TOKEN_NOT_VALID(500, "Token khong hop le "),
    TOKEN_EXPERIENCED(500, "Token het han "),
    USER_AND_TOKEN_NOTE_MATCH(500, "User va token khong trung khop"),
    USER_NOTE_FOUND(400, "Khong tim thay user "),
    CITY_EXITS(400, "Thành phố đã tồn tại"),
    DISTRICT_EXITS(400, "Có quận tồn tại trong thành phố"),
    CITY_NOT_EXITS(400, "Không co thành phố có ID - "),
    HOMESTAY_NOT_EXITS(400, "Không tồn tại cở sở ID - "),
    LOCATION_NOT_EXITS(400, "Không tồn tại địa điểm ID - "),
    PLACE_EXITS(400,"Địa điểm này đã tồn tại trong cơ sở"),
    NOT_CITY_NAME(400,"Không tìm thấy thành phố có tên - "),
    BAD_REQUEST(400, "Thông tin không hợp lệ"),
    INTERNAL_SERVER(500, "Hệ thống đang bị gián đoạn! Xin vui lòng thử lại sau");
    private final int code;
    private final String message;
}
