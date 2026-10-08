package com.booking.homestay.repository;


import com.booking.homestay.model.HomeStay;
import com.booking.homestay.model.User;
import com.booking.homestay.shared.dto.UserDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query("select distinct u from User u where u.email =: email and u.password =: password ")
    User findByEmailAndPassword(String email, String password);

    @Query("select new com.booking.homestay.shared.dto.UserDto(" +
            "u.email,\n" +
            "u.password,\n" +
            "u.role)\n" +
            "from User u\n" +
            "where u.email = :email and u.status = :active")
    UserDto findUserDtoByEmail(@Param("email") String email, @Param("active") Boolean active);

    Optional<User> findByUserName(String username);

    Optional<User> findByUserNameAndStatusTrue(String username);

    Optional<User> findByEmail(String email);

    Optional<User> findByPhone(String phone);

    List<User> findByHomeStay(HomeStay homeStay);

    @Query(value = "SELECT u FROM User u WHERE u.role = 'Employee' and u.status = true ")
    List<User> findByEmployeeNotLock();

    @Query(value = "SELECT u FROM User u WHERE u.role = 'Employee' and u.status = false ")
    List<User> findByEmployeeLock();

    @Query(value = "SELECT u FROM User u WHERE u.role = 'Employee' and u.status = true and u.homeStay is null ")
    List<User> findByEmployeeCheck();

    @Query(value = "SELECT u FROM User u WHERE u.role = 'Member' and u.status = true ")
    List<User> findByMemberNotLock();

    @Query(value = "SELECT u FROM User u WHERE u.role = 'Member' and u.status = false ")
    List<User> findByMemberLock();

}
