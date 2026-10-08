package com.booking.homestay.admin.service;

import com.booking.homestay.admin.dto.DistrictRequest;
import com.booking.homestay.admin.dto.DistrictResponse;
import com.booking.homestay.admin.mapper.DistrictMapper;
import com.booking.homestay.exception.SpringException;
import com.booking.homestay.model.City;
import com.booking.homestay.model.District;
import com.booking.homestay.model.Village;
import com.booking.homestay.repository.CityRepository;
import com.booking.homestay.repository.DistrictRepository;
import com.booking.homestay.repository.VillageRepository;
import com.booking.homestay.utils.BookingCode;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

@Service
@AllArgsConstructor
@Transactional
public class DistrictService {

    private final DistrictRepository districtRepository;
    private final DistrictMapper districtMapper;
    private final VillageRepository villageRepository;
    private final CityRepository cityRepository;

    public void save(DistrictRequest districtRequest) {
        City city = cityRepository.findByCityName(districtRequest.getCityName()).orElseThrow(() -> new SpringException(BookingCode.NOT_CITY_NAME, districtRequest.getCityName()));
        List<District> districtName = districtRepository.findByDistrictNameAndCity_Id(districtRequest.getDistrictName(), city.getId());
        if (!districtName.isEmpty()) {
            throw new SpringException(BookingCode.DISTRICT_EXITS);
        } else {
            districtRepository.save(districtMapper.map(districtRequest, city));
        }
    }

    @Transactional(readOnly = true)
    public List<DistrictResponse> getAllDistrict() {
        return districtRepository.findAll()
                .stream()
                .map(districtMapper::mapToDto)
                .collect(toList());
    }

    public void deleteDistrict(Long id) {
        Optional<List<Village>> village = villageRepository.findByDistrict_Id(id);
        if (village.isEmpty()) {
            throw new SpringException(BookingCode.BAD_REQUEST);
        } else {
            districtRepository.deleteById(id);
        }
    }


    public void editDistrict(DistrictRequest districtRequest) {
        City city = cityRepository.findByCityName(districtRequest.getCityName()).orElseThrow(() -> new SpringException("Không tìm thấy thành phố có tên - " + districtRequest.getCityName()));
        List<District> districtName = districtRepository.findByDistrictNameAndCity_Id(districtRequest.getDistrictName(), city.getId());
        if (districtName.isEmpty()) {
            districtRepository.save(districtMapper.mapEditToDtoById(districtRequest, city));
        } else if (districtName.get(0).getDistrictName().equals(districtRequest.getDistrictName()) && districtName.get(0).getId().equals(districtRequest.getId())) {
            districtRepository.save(districtMapper.mapEditToDtoById(districtRequest, city));
        } else {
            throw new SpringException(BookingCode.DISTRICT_EXITS);
        }
    }

    @Transactional(readOnly = true)
    public DistrictResponse getDistrictById(Long id) {
        District district = districtRepository.findById(id).orElseThrow(() -> new SpringException("Không có quận nào có ID - " + id));
        return districtMapper.mapToDto(district);
    }

    @Transactional(readOnly = true)
    public List<DistrictResponse> getAllDistrictbyCity(String cityName) {
        return districtRepository.findByCity_CityName(cityName)
                .stream()
                .map(districtMapper::mapToDto)
                .collect(toList());
    }
}
