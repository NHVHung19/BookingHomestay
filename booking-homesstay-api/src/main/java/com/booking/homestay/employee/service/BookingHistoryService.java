package com.booking.homestay.employee.service;


import com.booking.homestay.employee.dto.BookingHistoryResponse;
import com.booking.homestay.employee.mapper.BookingHistoryMapper;
import com.booking.homestay.model.Booking;
import com.booking.homestay.repository.BookingHistoryRepository;

import com.booking.homestay.shared.service.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Transactional
public class BookingHistoryService {

    private final BookingHistoryRepository bookingHistoryRepository;
    private final BookingHistoryMapper bookingHistoryMapper;
    private final AuthService authService;

    public void save(Booking booking) {
        bookingHistoryRepository.save(bookingHistoryMapper.mapToSave(booking, authService.getCurrentUser()));
    }

    @Transactional(readOnly = true)
    public List<BookingHistoryResponse> getAllBookingHistory(Long id) {
        return bookingHistoryRepository.findByBookingId(id)
                .stream()
                .map(bookingHistoryMapper::mapToDto)
                .collect(Collectors.toList());
    }

}
