package com.booking.homestay.utils;

/**
 * class chứa các biến các hàm ko thay đổi trong dự án
 */
public class BookingConst {

    public static class Values {
        /**
         * khoá bí mật của bảo mật security
         */
        public static final String JWT_SECRET = "booking_homestay_2024";
        public static final String BEARER_SPACE = "Bearer ";
        public static final Long ACCESS_TOKEN_EXPIRED = 86400000L;
        public static final Long REFRESH_TOKEN_EXPIRED = 172800000L;
    }

    public static class UserFields {

        private UserFields() {
        }

        public static final String ID_VAR = "id";
        public static final String NAME_VAR = "name";
        public static final String ROLE_VAR = "role";
        public static final String EMAIL_VAR = "email";
        public static final String PASSWORD = "password";
    }

    public static class Nouns {
        private Nouns() {
        }

        /**
         * field
         */
        public static final String EMAIL = "Email";
        public static final String PASSWORD_VI = "Mật khẩu";
        public static final String FIRST_NAME_VI = "Họ";
        public static final String LAST_NAME_VI = "Tên";
        public static final String DOB_VI = "Ngày sinh";
        public static final String GENDER_VI = "Giới tính";
        public static final String PHONE_VI = "Số điện thoại";
        public static final String AVATAR_VI = "Avatar";
        public static final String PRODUCT_VI = "Sản phẩm";
        public static final String PRODUCT_OPTION_VI = "Tùy chọn Sản phẩm";
        public static final String QTY_VI = "Số lượng";
        public static final String STATUS_VI = "Trạng thái";
        public static final String CATEGORY_VI = "Loại sản phẩm";
        public static final String MATERIAL_VI = "Chất liệu";
        public static final String COLOR_VI = "Màu sắc";
        public static final String SIZE_VI = "Kích thước";
        /**
         * Object
         */
        public static final String USER_VI = "Người dùng";
        public static final String CUSTOMER_VI = "Khách hàng";
        public static final String STAFF_VI = "Nhân viên";
        public static final String PASSWORD = "Password";
        public static final String ID = "ID";
        public static final String NAME = "Name";
        public static final String ROLE = "Role";
        public static final String ACCESS_TOKEN_FIELD = "accessToken";
        public static final String REFRESH_TOKEN_FIELD = "refreshToken";
    }

    /**
     * message + character
     */
    public static class Messages {

        private Messages() {
        }

        public static final String NOT_FOUND = "Không tìm thấy %s!";
        public static final String NOT_EXIST = "Không tồn tại";

        /**
         * Result
         */
        public static final String SUCCESS = "Thành công!";
        public static final String FAILED = "Thất bại!";

        /**
         * action
         */
        public static final String CREATE = "Thêm mới";
        public static final String UPDATE = "Cập nhật";
        public static final String DELETE = "Xóa";

        /**
         * hệ thống
         */
        public static final String FORBIDDEN = "Không có quyền!";
        public static final String
                TOKEN_EXPIRED = "Hết phiên sử dụng! Vui lòng đăng nhập lại!";
        public static final String UN_KNOW_EXCEPTION = "Lỗi không xác định!";
        public static final String SERVER_RETRY = "Vui lòng thử lại sau ít phút!";

        /**
         * validate
         */
        public static final String NOT_BLANK = "Không được để trống %s!";
        public static final String INVALID = "%s không hợp lệ!";
        public static final String INVALID_PASSWORD = "Mật khẩu không hợp lệ, phải dài từ 6 - 20 ký tự!";
        public static final String INVALID_CUSTOMER_AGE = "Tuổi không hợp lệ!(Từ %d - %d tuổi)";
        public static final String CART_EMPTY_EXCEPTION = "Không được để trống giỏ hàng!";
        public static final String CART_QTY_EXCEPTION = "Số lượng đặt quá số lượng trong kho. Vui lòng điều chỉnh lại!";

        /**
         * dấu
         */
        public static final String EXCLAMATION = "!";
        public static final String SPACE = " ";

    }
}
