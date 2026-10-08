package com.booking.homestay.admin.service;

import com.booking.homestay.admin.dto.TypeUtilityRequest;
import com.booking.homestay.admin.dto.TypeUtilityResponse;
import com.booking.homestay.admin.mapper.TypeUtilityMapper;
import com.booking.homestay.exception.SpringException;
import com.booking.homestay.model.*;
import com.booking.homestay.repository.TypeUtilityRepository;
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
public class TypeUtilityService {

    private final TypeUtilityRepository typeUtilityRepository;
    private final UtilityRepository utilityRepositoty;
    private final TypeUtilityMapper typeUtilityMapper;

    public void save(TypeUtilityRequest typeUtilityRequest) {
        Optional<TypeUtility> typeUtility = typeUtilityRepository.findByTypeName(typeUtilityRequest.getTypeName());
        if (typeUtility.isPresent()) {
            throw new SpringException("Loại tiện ích đã tồn tại");
        } else {
            typeUtilityRepository.save(typeUtilityMapper.map(typeUtilityRequest));
        }
    }

    @Transactional(readOnly = true)
    public List<TypeUtilityResponse> getAllTypeUtility() {
        return typeUtilityRepository.findAll()
                .stream()
                .map(typeUtilityMapper::mapToDto)
                .collect(toList());
    }

    public void deleteTypeUtility(Long id) {
        List<Utility> utility = utilityRepositoty.findByTypeUtility_Id(id);
        if (!utility.isEmpty()) {
            throw new SpringException("Loại tiện tích đã tồn tại tiện ích");
        } else {
            typeUtilityRepository.deleteById(id);
        }
    }


    // ktra xem loại tiện ích nếu chưa có gì thì thêm vào và lưu còn có gtri r thi hien thong bao
    public void editTypeUtility(TypeUtilityRequest typeUtilityRequest) {
        Optional<TypeUtility> typeUtilityName = typeUtilityRepository.findByTypeName(typeUtilityRequest.getTypeName());
        TypeUtility typeUtility = typeUtilityRepository.findById(typeUtilityRequest.getId()).orElseThrow(() -> new SpringException("Không tồn tại loại tiện ích ID - " + typeUtilityRequest.getId()));
        if (typeUtilityName.isEmpty()) {
            typeUtilityRepository.save(typeUtilityMapper.mapEditToDtoById(typeUtilityRequest, typeUtility));
        } else if (typeUtilityName.get().getTypeName().equals(typeUtilityRequest.getTypeName()) && typeUtilityName.get().getId().equals(typeUtilityRequest.getId())) {
            typeUtilityRepository.save(typeUtilityMapper.mapEditToDtoById(typeUtilityRequest, typeUtility));
        } else {
            throw new SpringException("Loại tiện ích đã tồn tại");
        }
    }

    @Transactional(readOnly = true)
    public TypeUtilityResponse getTypeUtilityById(Long id) {
        TypeUtility typeUtility = typeUtilityRepository.findById(id).orElseThrow(() -> new SpringException("Không tồn tại loại tiện ích ID - " + id));
        return typeUtilityMapper.mapToDto(typeUtility);
    }

}
