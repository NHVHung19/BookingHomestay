package com.booking.homestay.admin.service;

import com.booking.homestay.admin.dto.CityRequest;
import com.booking.homestay.admin.dto.CityResponse;
import com.booking.homestay.admin.mapper.CityMapper;
import com.booking.homestay.exception.SpringException;
import com.booking.homestay.model.City;
import com.booking.homestay.model.District;
import com.booking.homestay.repository.CityRepository;
import com.booking.homestay.repository.DistrictRepository;
import com.booking.homestay.utils.BookingCode;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

@Service
@RequiredArgsConstructor
@Transactional
public class CityService {

    private final CityRepository cityRepository;
    private final DistrictRepository districtRepository;
    private final CityMapper cityMapper;

    public void save(CityRequest cityRequest) {
        Optional<City> city = cityRepository.findByCityName(cityRequest.getCityName());
        if (city.isPresent()) {
            throw new SpringException(BookingCode.CITY_EXITS);
        } else {
            cityRepository.save(cityMapper.map(cityRequest));
        }
    }

    @Transactional(readOnly = true)
    public List<CityResponse> getAllCity() {
        return cityRepository.findAll()
                .stream()
                .map(cityMapper::mapToDto)
                .collect(toList());
    }

    public void deleteCity(Long id) {
        List<District> district = districtRepository.findByCity_Id(id);
        if (!district.isEmpty()) {
            throw new SpringException(BookingCode.DISTRICT_EXITS);
        } else {
            cityRepository.deleteById(id);
        }
    }


    public void editCity(CityRequest cityRequest) {
        Optional<City> city = cityRepository.findByCityName(cityRequest.getCityName());
        if (city.isEmpty()) {
            cityRepository.save(cityMapper.mapEditToDtoById(cityRequest));
        } else if (city.get().getCityName().equals(cityRequest.getCityName()) && city.get().getId().equals(cityRequest.getId())) {
            cityRepository.save(cityMapper.mapEditToDtoById(cityRequest));
        } else {
            throw new SpringException(BookingCode.CITY_EXITS);
        }
    }

    @Transactional(readOnly = true)
    public CityResponse getCityById(Long id) {
        City city = cityRepository.findById(id).orElseThrow(() -> new SpringException(BookingCode.CITY_NOT_EXITS , Math.toIntExact(id)));
        return cityMapper.mapToDto(city);
    }

}
