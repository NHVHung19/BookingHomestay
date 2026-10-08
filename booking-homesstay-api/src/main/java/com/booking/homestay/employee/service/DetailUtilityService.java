package com.booking.homestay.employee.service;

import com.booking.homestay.employee.dto.DetailUtilityRequest;
import com.booking.homestay.employee.dto.DetailUtilityResponse;
import com.booking.homestay.employee.mapper.DetailUtilityMapper;
import com.booking.homestay.exception.SpringException;
import com.booking.homestay.model.DetailUtility;
import com.booking.homestay.model.House;
import com.booking.homestay.model.Utility;
import com.booking.homestay.repository.DetailUtilityRepository;
import com.booking.homestay.repository.HouseRepository;
import com.booking.homestay.repository.UtilityRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
@AllArgsConstructor
@Transactional
public class DetailUtilityService {

    private final DetailUtilityRepository detailUtilityRepository;
    private final DetailUtilityMapper detailUtilityMapper;
    private final HouseRepository houseRepository;
    private final UtilityRepository utilityRepository;

    public void save(DetailUtilityRequest detailUtilityRequest) {
        House house = houseRepository.findById(detailUtilityRequest.getId_house()).orElseThrow(() -> new SpringException("Không tồn tại nhà ID - " + detailUtilityRequest.getId_house()));
        Utility utility = utilityRepository.findById(detailUtilityRequest.getId_utility()).orElseThrow(() -> new SpringException("Không tồn tại tiện ích ID - " + detailUtilityRequest.getId_utility()));
        List<DetailUtility> detailUtility = detailUtilityRepository.findByUtilityAndHouse(utility, house);
        if (!detailUtility.isEmpty()) {
            throw new SpringException("Tiện ích đã có trong nhà");
        }
        detailUtilityRepository.save(detailUtilityMapper.mapToSave(house, utility));
    }
    @Transactional(readOnly = true)
    public List<DetailUtilityResponse> getAllByHouse(Long id) {
        return detailUtilityRepository.findByHouse_Id(id)
                .stream()
                .map(detailUtilityMapper::mapToDto)
                .collect(toList());
    }

    public DetailUtilityResponse getById(Long id) {
        DetailUtility detailUtility = detailUtilityRepository.findById(id).orElseThrow(() -> new SpringException("Không tồn tại loại tiện ích ID- " + id));
        return detailUtilityMapper.mapToDto(detailUtility);
    }

    public void deleteDetailUtility(Long id) {
        detailUtilityRepository.deleteById(id);
    }

    public void editDetailUtility(DetailUtilityRequest detailUtilityRequest) {
        DetailUtility detailUtility = detailUtilityRepository.findById(detailUtilityRequest.getId()).orElseThrow(() -> new SpringException("Không tồn tại loại tiện ích ID - " + detailUtilityRequest.getId()));
        detailUtilityRepository.save(detailUtilityMapper.mapToEdit(detailUtility, detailUtilityRequest));
    }
}
