package com.booking.homestay.repository;

import com.booking.homestay.model.HomeStay;
import com.booking.homestay.model.House;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;

public interface HouseRepository extends JpaRepository<House,Long> {



    List<House> findByHomeStayAndStatusTrue(HomeStay homeStay);

    List<House> findByHomeStay(HomeStay homeStay);
    
    List<House> findHouseByStatusTrue();


    List<House> findByHomeStay_IdAndStatusTrue(Long id);

    List<House> findByHouseNameAndHomeStay(String houseName, HomeStay homeStay);



}
