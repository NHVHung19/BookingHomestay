package com.booking.homestay.admin.service;

import com.booking.homestay.admin.dto.VillageRequest;
import com.booking.homestay.admin.dto.VillageResponse;
import com.booking.homestay.admin.mapper.VillageMapper;
import com.booking.homestay.exception.SpringException;
import com.booking.homestay.model.City;
import com.booking.homestay.model.District;
import com.booking.homestay.model.HomeStay;
import com.booking.homestay.model.Village;
import com.booking.homestay.repository.CityRepository;
import com.booking.homestay.repository.DistrictRepository;
import com.booking.homestay.repository.HomeStayRepository;
import com.booking.homestay.repository.VillageRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

@Service
@AllArgsConstructor
@Transactional
public class VillageService {

    private final VillageRepository villageRepository;
    private final DistrictRepository districtRepository;
    private final CityRepository cityRepository;
    private final HomeStayRepository homeStayRepository;
    private final VillageMapper villageMapper;

    public void save(VillageRequest villageRequest) {
        City city = cityRepository.findByCityName(villageRequest.getCityName()).orElseThrow(() -> new SpringException("Không tìm thấy thành phố có tên - " + villageRequest.getCityName()));
        List<District> district = districtRepository.findByDistrictNameAndCity_Id(villageRequest.getDistrictName(), city.getId());
        if (district.isEmpty()) {
            throw new SpringException("Quận không tồn tại trong thành phố");
        }
        List<Village> village = villageRepository.findByVillageNameAndDistrict(villageRequest.getVillageName(), district.get(0));
        if (!village.isEmpty()) {
            throw new SpringException("Phường đã tồn tại");
        } else {
            villageRepository.save(villageMapper.map(villageRequest, district.get(0)));
        }
    }

    @Transactional(readOnly = true)
    public List<VillageResponse> getAllVillage() {
        return villageRepository.findAll()
                .stream()
                .map(villageMapper::mapToDto)
                .collect(toList());
    }

    public void deleteVillage(Long id) {
        Optional<List<HomeStay>> homeStay = homeStayRepository.findByVillage_Id(id);
        if (homeStay.isEmpty()) {
            throw new SpringException("Phường này đã tồn tại khu nhà");
        } else {
            villageRepository.deleteById(id);
        }
    }


    public void editVillage(VillageRequest villageRequest) {
        City city = cityRepository.findByCityName(villageRequest.getCityName()).orElseThrow(() -> new SpringException("Không tìm thấy phường có tên - " + villageRequest.getCityName()));
        List<District> district = districtRepository.findByDistrictNameAndCity_Id(villageRequest.getDistrictName(), city.getId());
        if (district.isEmpty()) {
            throw new SpringException("Quận không tồn tại trong thành phố");
        }
        List<Village> village = villageRepository.findByVillageNameAndDistrict(villageRequest.getVillageName(), district.get(0));
        if (village.isEmpty()) {
            villageRepository.save(villageMapper.mapEditToDtoById(villageRequest, district.get(0)));
        } else if (village.get(0).getVillageName().equals(villageRequest.getVillageName()) && village.get(0).getId().equals(villageRequest.getId())) {
            villageRepository.save(villageMapper.mapEditToDtoById(villageRequest, district.get(0)));
        } else {
            throw new SpringException("Phường đã tồn tại");
        }
    }

    @Transactional(readOnly = true)
    public VillageResponse getVillageById(Long id) {
        Village village = villageRepository.findById(id).orElseThrow(() -> new SpringException("Không có phường nào ID - " + id));
        return villageMapper.mapToDto(village);
    }

}
