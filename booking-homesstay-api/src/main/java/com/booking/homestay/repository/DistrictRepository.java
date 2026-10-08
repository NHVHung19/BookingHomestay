package com.booking.homestay.repository;

import com.booking.homestay.model.District;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DistrictRepository extends JpaRepository<District, Long> {

    List<District> findByCity_Id(Long id);

    List<District> findByCity_CityName(String cityName);

    List<District> findByDistrictNameAndCity_Id(String name, Long cityId);

}
