package com.booking.homestay.repository;

import com.booking.homestay.model.FeedBack;
import com.booking.homestay.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FeedBackRepository extends JpaRepository<FeedBack,Long> {
    List<FeedBack> findByHouse_Id(Long id);
    List<FeedBack> findByHouse_IdAndUser(Long id, User user);
}
