package com.booking.homestay.repository;

import com.booking.homestay.model.DetailView;
import com.booking.homestay.model.House;
import com.booking.homestay.model.View;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface DetailViewRepository extends JpaRepository<DetailView,Long> {

    List<DetailView> findByHouse_Id(Long id);
    Optional<List<DetailView>> findByView_Id(Long id);
    void deleteByHouse_Id(Long id);

    List<DetailView> findByHouseAndView(House house, View view);
}
