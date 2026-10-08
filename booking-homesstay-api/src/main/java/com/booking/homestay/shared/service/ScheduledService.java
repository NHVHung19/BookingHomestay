package com.booking.homestay.shared.service;

import com.booking.homestay.model.Booking;
import com.booking.homestay.model.VerificationTokenAccount;
import com.booking.homestay.model.VerificationTokenPassword;
import com.booking.homestay.repository.BookingRepository;
import com.booking.homestay.repository.UserRepository;
import com.booking.homestay.repository.VerificationTokenAccountRepository;
import com.booking.homestay.repository.VerificationTokenPasswordRepository;
import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;

@Service
@AllArgsConstructor
@Transactional
public class ScheduledService {

    private final BookingRepository bookingRepository;
    private final VerificationTokenAccountRepository verificationTokenAccountRepository;
    private final VerificationTokenPasswordRepository verificationTokenPasswordRepository;
    private final UserRepository iUserRepository;

    @Scheduled(fixedDelay = 43200000) //  6 tiếng lọc 1 lần
    public void TokenCleaning(){
        try {
            Instant time = Instant.now();
            System.out.println("Lọc token password " + time);
            List<VerificationTokenPassword> tokenPasswordList = verificationTokenPasswordRepository.findAll();
            if (!tokenPasswordList.isEmpty()) {
                for (VerificationTokenPassword verificationTokenPassword : tokenPasswordList) {
                    if (time.isAfter(verificationTokenPassword.getExpiryDate())) {
                        verificationTokenPasswordRepository.deleteById(verificationTokenPassword.getId());
                    }
                }
            }
            List<VerificationTokenAccount> tokenAccountList = verificationTokenAccountRepository.findAll();
            if (!tokenAccountList.isEmpty()) {
                for (VerificationTokenAccount verificationTokenAccount : tokenAccountList) {
                    if (time.isAfter(verificationTokenAccount.getExpiryDate())) {
                        verificationTokenAccountRepository.deleteById(verificationTokenAccount.getId());
                        iUserRepository.deleteById(verificationTokenAccount.getUser().getId());
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("Lọc token bị lỗi " + e);
        }
    }

    @Scheduled(fixedDelay = 600000) // 10 phut lọc 1 lần
    public void BookingCleaning() {
        try {
            Instant time = Instant.now();
            System.out.println("Lọc booking " + time);
            List<Booking> bookingsList = bookingRepository.findByDepositIsFalse();
            if (!bookingsList.isEmpty()) {
                for (Booking booking : bookingsList) {
                    if (time.isAfter(booking.getCreateDate().plusMillis(7200000))) {
                        bookingRepository.deleteById(booking.getId());
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("Lọc booking bị lỗi " + e);
        }
    }



}
