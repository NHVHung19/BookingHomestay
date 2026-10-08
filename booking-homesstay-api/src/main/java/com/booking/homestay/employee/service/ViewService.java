package com.booking.homestay.employee.service;

import com.booking.homestay.employee.dto.ViewRequest;
import com.booking.homestay.employee.dto.ViewResponse;
import com.booking.homestay.employee.mapper.ViewMapper;
import com.booking.homestay.exception.SpringException;
import com.booking.homestay.model.DetailView;
import com.booking.homestay.model.View;
import com.booking.homestay.repository.DetailViewRepository;
import com.booking.homestay.repository.ViewRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

@Service
@AllArgsConstructor
@Transactional
public class ViewService {

    private final ViewRepository viewRepository;
    private final DetailViewRepository detailViewRepository;
    private final ViewMapper viewMapper;

    public void save(ViewRequest viewRequest) {
        Optional<View> view = viewRepository.findByViewName(viewRequest.getViewName());
        if (view.isPresent()) {
            throw new SpringException("Cảnh quan đã tồn tại");
        } else {
            viewRepository.save(viewMapper.map(viewRequest));
        }
    }

    @Transactional(readOnly = true)
    public List<ViewResponse> getAllView() {
        return viewRepository.findAll()
                .stream()
                .map(viewMapper::mapToDto)
                .collect(toList());
    }

    public void deleteView(Long id) {
        Optional<List<DetailView>> detailViews = detailViewRepository.findByView_Id(id);
        if(detailViews.isEmpty()){
            throw new SpringException("Cảnh quan đã tồn tại trong nhà");
        }
        viewRepository.deleteById(id);
    }


    public void editView(ViewRequest viewRequest) {
        Optional<View> viewName = viewRepository.findByViewName(viewRequest.getViewName());
        if (viewName.isEmpty()) {
            viewRepository.save(viewMapper.mapEditToDtoById(viewRequest));
        } else if(viewName.get().getViewName().equals(viewRequest.getViewName()) && viewName.get().getId().equals(viewRequest.getId())){
            viewRepository.save(viewMapper.mapEditToDtoById(viewRequest));
        } else {
            throw new SpringException("Cảnh quan đã tồn tại");
        }
    }

    @Transactional(readOnly = true)
    public ViewResponse getViewById(Long id) {
        View view = viewRepository.findById(id).orElseThrow(() -> new SpringException("Không tồn tại cảnh quan I - " + id));
        return viewMapper.mapToDto(view);
    }
}
