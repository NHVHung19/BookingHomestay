package com.booking.homestay.member.service;

import com.booking.homestay.exception.SpringException;
import com.booking.homestay.member.dto.FeedbackRequest;
import com.booking.homestay.member.dto.FeedbackResponse;
import com.booking.homestay.member.mapper.FeedbackMapper;
import com.booking.homestay.model.FeedBack;
import com.booking.homestay.model.TransactionInfo;
import com.booking.homestay.repository.FeedBackRepository;
import com.booking.homestay.repository.TransactionInfoRepository;
import com.booking.homestay.shared.service.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
@AllArgsConstructor
@Transactional
public class FeedbackService {
    private final FeedBackRepository feedBackRepository;
    private final TransactionInfoRepository transactionInfoRepository;
    private final FeedbackMapper feedbackMapper;
    private final AuthService authService;

    public void save(FeedbackRequest feedbackRequest) {
        List<FeedBack> feedBack = feedBackRepository.findByHouse_IdAndUser(feedbackRequest.getId_house(), authService.getCurrentUser());
        if (!feedBack.isEmpty()) {
            throw new SpringException("Bạn đã đánh giá căn nhà này");
        }
        List<TransactionInfo> transactionInfos = transactionInfoRepository.findByHouseAndUser(feedbackRequest.getId_house(), authService.getCurrentUser());
        if (transactionInfos.isEmpty()) {
            throw new SpringException("Bạn chưa từng sử dụng nhà này");
        }
        feedBackRepository.save(feedbackMapper.mapToSave(feedbackRequest, authService.getCurrentUser()));
    }

    @Transactional(readOnly = true)
    public List<FeedbackResponse> getAllFeedBackByHouse(Long id) {
        return feedBackRepository.findByHouse_Id(id)
                .stream()
                .map(feedbackMapper::mapToDto)
                .collect(toList());
    }

    @Transactional(readOnly = true)
    public List<FeedbackResponse> getAllFeedBack() {
        return feedBackRepository.findAll()
                .stream()
                .map(feedbackMapper::mapToDto)
                .collect(toList());
    }
}
