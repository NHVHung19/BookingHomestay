package com.booking.homestay.repository;

import com.booking.homestay.model.TypeUtility;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TypeUtilityRepository extends JpaRepository<TypeUtility, Long> {

    Optional<TypeUtility> findByTypeName(String name);

}
