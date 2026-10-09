Dự án Booking Homestay cung cấp nền tảng quản lý và đặt phòng homestay trực tuyến. Hệ thống giúp người dùng dễ dàng tìm kiếm, chọn lựa và thực hiện đặt phòng, đồng thời hỗ trợ chủ nhà quản lý danh sách homestay và trạng thái đơn đặt phòng thông qua các RESTful API.
# Công Nghệ Sử Dụng (Tech Stack)
# Backend (Hệ thống API)
- Framework: Java Spring Boot (Spring Web, Spring Data JPA, Spring Security)
- Database: MySQL 
- Build Tool: Maven 
- Authentication: JWT (JSON Web Token)

# Frontend (Giao diện)
- Framework: Angular
- Environment: Node.js `v12.18.x` (LTS cũ/Legacy) & npm
- UI & Styling: Bootstrap / Angular, HTML, SCSS/CSS

# Tính Năng Chính
# Người Dùng (User)
 Đăng ký, đăng nhập và xác thực tài khoản (JWT).
 Tìm kiếm và lọc homestay theo địa điểm, giá cả.
 Xem thông tin chi tiết homestay, hình ảnh.
 Thực hiện đặt phòng và theo dõi lịch sử đặt phòng.

# Quản lý hệ thống / quản lý cơ sở homestay   (Admin / Nhân viên)
# Admin:
 Quản lý thông tin homestay (Thêm/sửa/xóa cơ sở homestay , cập nhật trạng thái).
 Quản lý địa chỉ các cơ sở homestay.
 Quản lý thông tin cảnh quan xung quanh cơ sở homestay.
 Quản lý các tiện ích được trang bị trong cơ sở homestay.
# Nhân viên:
 Quản lý thông tin các nhà/phòng trong homestay (Thêm, sửa , xóa, khóa/mở ).
 Quản lý đơn đặt phòng (Xác nhận, hủy đơn).
 Quản lý checkin/checkout phòng của khách.
 Quản lý hóa đơn của khách đặt phòng (chỉnh sửa thông tin đơn đặt, thanh toán).
 Thống kê báo cáo lượt đặt và doanh thu.

---


