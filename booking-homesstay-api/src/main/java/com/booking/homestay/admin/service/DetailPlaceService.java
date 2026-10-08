package com.booking.homestay.admin.service;

import com.booking.homestay.admin.dto.DetailPlaceRequest;
import com.booking.homestay.admin.dto.DetailPlaceResponse;
import com.booking.homestay.admin.mapper.DetailPlaceMapper;
import com.booking.homestay.exception.SpringException;
import com.booking.homestay.model.*;
import com.booking.homestay.repository.DetailPlaceRepository;
import com.booking.homestay.repository.HomeStayRepository;
import com.booking.homestay.repository.PlaceRepository;
import com.booking.homestay.utils.BookingCode;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
@AllArgsConstructor
@Transactional
public class DetailPlaceService {

    private final DetailPlaceRepository detailPlaceRepository;
    private final DetailPlaceMapper detailPlaceMapper;
    private final HomeStayRepository homeStayRepository;
    private final PlaceRepository placeRepository;

    public void save(DetailPlaceRequest detailPlaceRequest) {
        HomeStay homeStay = homeStayRepository.findById(detailPlaceRequest.getId_homeStay()).orElseThrow(() -> new SpringException(BookingCode.HOMESTAY_NOT_EXITS , detailPlaceRequest.getId_homeStay()));
        Place place = placeRepository.findById(detailPlaceRequest.getId_place()).orElseThrow(() -> new SpringException(BookingCode.LOCATION_NOT_EXITS , detailPlaceRequest.getId_place()));
        List<DetailPlace> detailPlaces = detailPlaceRepository.findByPlaceAndHomeStay(place, homeStay);
        if (!detailPlaces.isEmpty()) {
            throw new SpringException(BookingCode.PLACE_EXITS);
        }
        detailPlaceRepository.save(detailPlaceMapper.mapToSave(place, homeStay));
    }

    @Transactional(readOnly = true)
    public List<DetailPlaceResponse> getAllByHomeStay(Long id) {
        return detailPlaceRepository.findByHomeStay_Id(id)
                .stream()
                .map(detailPlaceMapper::mapToDto)
                .collect(toList());
    }

    public void deleteDetailPlace(Long id) {
        detailPlaceRepository.deleteById(id);
    }

}
