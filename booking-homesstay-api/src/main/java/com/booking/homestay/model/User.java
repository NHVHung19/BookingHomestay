package com.booking.homestay.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedBy;

import javax.persistence.*;
import javax.persistence.Table;
import java.time.Instant;
import java.util.Date;

import static javax.persistence.FetchType.LAZY;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
/*
 * tên table = user
 */
@Table(name = "user")
public class User {
    /**
     * id của bảng user
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    /**
     * nullable = false -> ko để rỗng trong db
     * tên người dùng
     */
    @Column(nullable = false)
    private String userName;

    /**
     * mật khẩu -<>dạng mã hoá</>
     */
    @Column(nullable = false)
    private String password;

    /**
     * email người dùng -> sau này sẽ gửi mail dựa vào thông tin này
     */
    @Column(nullable = false)
    private String email;
    /**
     * Họ
     */
    private String firstName;
    /**
     * Tên
     */
    private String lastName;

    /**
     * số điện thoại
     */
    @Column(nullable = false)
    private String phone;

    /**
     * Địa chỉ
     */
    private String address;

    /**
     * ảnh đại diện
     */
    private String image;

    /**
     * phân vai trò người dùng,.
     */
    private String role;

    /**
     * Ngày tạo
     */
    private Instant createdDate;

    /**
     * Ngày tháng năm sinh
     */
    private Date dateOfBirth;
    /**
     * Giới tính
     */
    private String sex;
    /**
     * Trạng thái hoạt động
     * enable = true -> hoạt động
     * enable = false -> ko hoạt động
     */
    @Column(nullable = false)
    private boolean enabled;

    /**
     * Trạng thái tài khoản
     */
    @Column(nullable = false)
    private boolean status;
    @CreatedBy
    private long creator;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "id_homestay")
    private HomeStay homeStay;
}
