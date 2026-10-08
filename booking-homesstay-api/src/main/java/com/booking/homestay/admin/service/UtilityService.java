package com.booking.homestay.admin.service;

import com.booking.homestay.admin.dto.UtilityRequest;
import com.booking.homestay.admin.dto.UtilityResponse;
import com.booking.homestay.admin.mapper.UtilityMapper;
import com.booking.homestay.exception.SpringException;
import com.booking.homestay.model.DetailUtility;
import com.booking.homestay.model.Utility;
import com.booking.homestay.repository.DetailUtilityRepository;
import com.booking.homestay.repository.UtilityRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

@Service
@AllArgsConstructor
@Transactional
public class UtilityService {

    private final UtilityRepository utilityRepositoty;
    private final DetailUtilityRepository detailUtilityRepository;
    private final UtilityMapper utilityMapper;

    public void save(UtilityRequest utilityRequest) {
        Optional<Utility> utility = utilityRepositoty.findByUtilityName(utilityRequest.getUtilityName());
        if (utility.isPresent()) {
            throw new SpringException("Tiện ích đã tồn tại");
        } else {
            utilityRepositoty.save(utilityMapper.map(utilityRequest));
        }
    }

    @Transactional(readOnly = true)
    public List<UtilityResponse> getUtilityByType(Long id) {
        return utilityRepositoty.findByTypeUtility_Id(id)
                .stream()
                .map(utilityMapper::mapToDto)
                .collect(toList());
    }

    @Transactional(readOnly = true)
    public List<UtilityResponse> getAllUtility() {
        return utilityRepositoty.findAll()
                .stream()
                .map(utilityMapper::mapToDto)
                .collect(toList());
    }

    public void deleteUtility(Long id) {
        Optional<List<DetailUtility>> detailUtility = detailUtilityRepository.findByUtility_Id(id);
        if(detailUtility.isEmpty()){
            throw new SpringException("Tiện ích đã tồn tại nhà");
        }
        utilityRepositoty.deleteById(id);
    }


    public void editUtility(UtilityRequest utilityRequest) {
        Utility utility = utilityRepositoty.findById(utilityRequest.getId()).orElseThrow(() -> new SpringException("Không tồn tại tiện ích ID - " + utilityRequest.getId()));
        Optional<Utility> utilityName = utilityRepositoty.findByUtilityName(utilityRequest.getUtilityName());
        if (utilityName.isEmpty()) {
            utilityRepositoty.save(utilityMapper.mapEditToDtoById(utilityRequest, utility));
        } else if(utilityName.get().getUtilityName().equals(utilityRequest.getUtilityName()) && utilityName.get().getId().equals(utilityRequest.getId())){
            utilityRepositoty.save(utilityMapper.mapEditToDtoById(utilityRequest, utility));
        } else {
            throw new SpringException("Tiện ích đã tồn tại");
        }
    }

    @Transactional(readOnly = true)
    public UtilityResponse getUtilityById(Long id) {
        Utility utility = utilityRepositoty.findById(id).orElseThrow(() -> new SpringException("Không tồn tại tiện ích ID  - " + id));
        return utilityMapper.mapToDto(utility);
    }

}
