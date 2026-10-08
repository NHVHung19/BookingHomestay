package com.booking.homestay.employee.service;

import com.booking.homestay.employee.dto.BookingRequest;
import com.booking.homestay.employee.dto.BookingResponse;
import com.booking.homestay.employee.dto.HouseResponse;
import com.booking.homestay.employee.mapper.BookingMapper;
import com.booking.homestay.exception.SpringException;
import com.booking.homestay.model.Booking;
import com.booking.homestay.model.HomeStay;
import com.booking.homestay.model.NotificationEmail;
import com.booking.homestay.repository.BookingRepository;
import com.booking.homestay.shared.dto.SearchRequest;
import com.booking.homestay.shared.service.AuthService;
import com.booking.homestay.shared.service.MailService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

@Service
@AllArgsConstructor
@Transactional
public class BookingService {

    private final BookingRepository bookingRepository;
    private final BookingMapper bookingMapper;
    private final AuthService authService;
    private final MailService mailService;
    private final BookingHistoryService bookingHistoryService;
    private final TransactionInfoService transactionInfoService;
    private final HouseService houseService;

    public void save(BookingRequest bookingRequest) {
        Booking booking;
        checkDateAdd(bookingRequest);
        bookingRequest.setDeposit(true);
        if (bookingRequest.getId_user() == null) {
            booking = bookingRepository.save(bookingMapper.mapNotId(bookingRequest, authService.getCurrentUser()));
        } else {
            booking = bookingRepository.save(bookingMapper.map(bookingRequest, authService.getCurrentUser()));
        }
        mailService.sendMail(new NotificationEmail(
                "Xác nhận đặt phòng thành công",
                bookingRequest.getEmail(),
                "cảm ơn bạn đã đặt phòng. Mã đặt phòng của bạn là  : " + booking.getId())
        );
    }


    public void editBookingCheckIn(BookingRequest bookingRequest) {
        Booking booking = bookingRepository.findById(bookingRequest.getId()).orElseThrow(() -> new SpringException("Không tồn tại đơn đặt nhà ở với mã ID: " + bookingRequest.getId()));
        LocalDate today = LocalDate.now();
        LocalDate checkIn = bookingRequest.getDateIn().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate checkInOld = booking.getDateIn().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        Duration diff = Duration.between(today.atStartOfDay(), checkIn.atStartOfDay());
        Duration diffOld = Duration.between(today.atStartOfDay(), checkInOld.atStartOfDay());
        long diffDays = diff.toDays();
        long diffDaysOld = diffOld.toDays();
        if (diffDays > 0 || diffDaysOld > 0) {
            throw new SpringException("Ngày bắt đầu phải là hôm nay");
        }
        if (diffDays < 0 || diffDaysOld < 0) {
            if (!booking.getHouse().getId().equals(bookingRequest.getId_house()) || !booking.getDateOut().toInstant().equals(bookingRequest.getDateOut().toInstant())
                    || !booking.getDateIn().toInstant().equals(bookingRequest.getDateIn().toInstant()) || !booking.getHouse().getPrice().equals(bookingRequest.getPrice())) {
                throw new SpringException("Đã quá hạn thay đổi thông tin nhà, giá nhà và lịch");
            }
        } else {
            if (booking.getHouse().getId().equals(bookingRequest.getId_house())) {
                if (!booking.getDateOut().toInstant().equals(bookingRequest.getDateOut().toInstant())) {
                    checkDateEdit(bookingRequest, booking);
                }
            } else {
                checkDateAdd(bookingRequest);
            }
        }
        bookingHistoryService.save(booking);
        bookingRepository.save(bookingMapper.mapToEdit(bookingRequest, booking));
        mailService.sendMail(new NotificationEmail("Bạn đã thay đổi thông tin đặt chỗ của mình",
                bookingRequest.getEmail(), "Bạn đã thay đổi thông tin đặt chỗ của mình, hay vào trang cá nhân hoặc tra cứu để biết thêm thông tin."));
    }

    public void editBookingNotCheckIn(BookingRequest bookingRequest) {
        Booking booking = bookingRepository.findById(bookingRequest.getId()).orElseThrow(() -> new SpringException("Không tồn tại đơn đặt nhà ở với mã ID: " + bookingRequest.getId()));
        if (!booking.getHouse().getId().equals(bookingRequest.getId_house())) {
            checkDateAdd(bookingRequest);
        } else {
            if (booking.getDateIn().toInstant().equals(bookingRequest.getDateIn().toInstant()) && booking.getDateOut().toInstant().equals(bookingRequest.getDateOut().toInstant())) {
            } else {
                checkDateEdit(bookingRequest, booking);
            }
        }
        bookingHistoryService.save(booking);
        bookingRepository.save(bookingMapper.mapToEdit(bookingRequest, booking));
        mailService.sendMail(new NotificationEmail("Bạn đã thay đổi thông tin đặt chỗ của mình",
                bookingRequest.getEmail(), "Bạn đã thay đổi thông tin đặt chỗ của mình, hay vào trang cá nhân hoặc tra cứu để biết thêm thông tin."));
    }

    @Transactional(readOnly = true)
    public List<BookingResponse> getAllBookingNotCheckIn() {
        HomeStay homeStay = authService.getCurrentUser().getHomeStay();
        List<Booking> byHouseHomeStayAndStatusNot = bookingRepository.findByHouseHomeStayAndStatusNot(authService.getCurrentUser().getHomeStay());
        return bookingRepository.findByHouseHomeStayAndStatusNot(authService.getCurrentUser().getHomeStay())
                .stream()
                .map(bookingMapper::mapToDto)
                .collect(toList());
    }

    @Transactional(readOnly = true)
    public List<BookingResponse> getAllBookingCheckIn() {
        return bookingRepository.findByHouseHomeStayAndStatusCheckIn(authService.getCurrentUser().getHomeStay())
                .stream()
                .map(bookingMapper::mapToDto)
                .collect(toList());
    }

    @Transactional(readOnly = true)
    public List<BookingResponse> getAllBookingCheckOut() {
        return bookingRepository.findByHouseHomeStayAndStatusCheckOut(authService.getCurrentUser().getHomeStay())
                .stream()
                .map(bookingMapper::mapToDto)
                .collect(toList());
    }

    @Transactional(readOnly = true)
    public List<BookingResponse> getAllGeneralApplicationCancellation() {
        if (authService.getCurrentUser().getHomeStay() == null) {
            return bookingRepository.findByStatusCancellation()
                    .stream()
                    .map(bookingMapper::mapToDto)
                    .collect(toList());
        } else {
            return bookingRepository.findByHouseHomeStayAndStatusCancellation(authService.getCurrentUser().getHomeStay())
                    .stream()
                    .map(bookingMapper::mapToDto)
                    .collect(toList());
        }
    }

    @Transactional(readOnly = true)
    public List<BookingResponse> getAllMember() {
        return bookingRepository.findByMember(authService.getCurrentUser())
                .stream()
                .map(bookingMapper::mapToDto)
                .collect(toList());
    }

    @Transactional(readOnly = true)
    public BookingResponse getBookingById(Long id) {
        Booking booking = bookingRepository.findById(id).orElseThrow(() -> new SpringException("Không tồn tại đơn đặt nhà ở với mã ID: " + id));
        return bookingMapper.mapToDto(booking);
    }


    public void submitBooking(Long id) {
        Booking booking = bookingRepository.findById(id).orElseThrow(() -> new SpringException("Không tồn tại đơn đặt nhà ở với mã ID: " + id));
        booking.setDeposit(true);
        bookingRepository.save(booking);
        mailService.sendMail(new NotificationEmail("Bạn đã thanh toán tiền phòng, mã đăng ký của bạn",
                booking.getEmail(), "Vui lòng mang theo mã này khi bạn đến nhận phòng của chúng tôi" + "  - Mã: " + booking.getId()));
    }


    @Transactional(readOnly = true)
    public BookingResponse getCheckBooking(Long id) {
        Instant time = Instant.now();
        Booking booking = bookingRepository.findById(id).orElseThrow(() -> new SpringException("Không tồn tại đơn đặt nhà ở với mã ID:" + id));
        if (booking.isDeposit()) {
            throw new SpringException("Đơn hàng này của bạn đã được thanh toán");
        } else if (time.isAfter(booking.getCreateDate().plusMillis(7200000))) {
            bookingRepository.deleteById(booking.getId());
            throw new SpringException("Đơn hàng đã bị hủy do quá hạn");
        } else {
            return bookingMapper.mapToDto(booking);
        }
    }

    public List<BookingResponse> seachBooking(String phone, Long idBook) {
        return bookingRepository.seachBooking(phone, idBook)
                .stream()
                .map(bookingMapper::mapToDto)
                .collect(toList());
    }


    public List<HouseResponse> searchHouse(SearchRequest searchRequest) {
        System.out.println(searchRequest.getCapacity());
        List<HouseResponse> listHouses = houseService.getAllHouse();
        List<HouseResponse> houseResponseList = new ArrayList<>();
        List<LocalDate> listDate = dateFilter(searchRequest.getDateIn(), searchRequest.getDateOut());
        for (HouseResponse listHouse : listHouses) {
            List<Booking> booking = bookingRepository.findByHouseId(listHouse.getId());
            if (!booking.isEmpty()) {
                boolean status = false;
//                for (Booking list : booking) {
//                    if (list.getDateIn().toInstant().equals(searchRequest.getDateIn().toInstant()) && list.getDateOut().toInstant().equals(searchRequest.getDateOut().toInstant())) {
//                        status = true;
//                    }
//                    for (LocalDate list2 : listDate) {
//                        if (list2.equals(list.getDateOut().toInstant().atZone(ZoneId.systemDefault()).toLocalDate()) || list2.equals(list.getDateIn().toInstant().atZone(ZoneId.systemDefault()).toLocalDate())) {
//                            status = true;
//                        }
//                    }
//                }
                //
                Instant searchDateIn = searchRequest.getDateIn().toInstant();
                Instant searchDateOut = searchRequest.getDateOut().toInstant();

                for (Booking list : booking) {
                    Instant bookedDateIn = list.getDateIn().toInstant();
                    Instant bookedDateOut = list.getDateOut().toInstant();

                    // Nếu hai khoảng ngày giao nhau => nhà đã được đặt
                    boolean overlap = !(searchDateOut.isBefore(bookedDateIn) || searchDateIn.isAfter(bookedDateOut));

                    if (overlap) {
                        status = true;
                        break; // Đã trùng thì không cần kiểm tra tiếp
                    }
                }
                //
                if (!status) {
                    if (searchRequest.getCapacity() != null) {
                        if (Objects.equals(listHouse.getCapacity(), searchRequest.getCapacity())) {
                            houseResponseList.add(listHouse);
                        }
                    } else {
                        houseResponseList.add(listHouse);
                    }
                }
            } else {
                if (searchRequest.getCapacity() != null) {
                    if (Objects.equals(listHouse.getCapacity(), searchRequest.getCapacity())) {
                        houseResponseList.add(listHouse);
                    }
                } else {
                    houseResponseList.add(listHouse);
                }
            }
        }
        return houseResponseList;
    }


    public void checkOut(Long id) {
        Booking booking = bookingRepository.findById(id).orElseThrow(() -> new SpringException("Không tồn tại đơn đặt với mã ID: " + id));
        booking.setStatus("CheckOut");
        bookingRepository.save(booking);
        transactionInfoService.save(booking);
    }

    public void processing(Long id) {
        DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern(" HH:mm:ss dd-MM-yyyy")
                .withZone(ZoneId.systemDefault());
        Booking booking = bookingRepository.findById(id).orElseThrow(() -> new SpringException("Không tồn tại đơn đặt với mã ID: " + id));
        bookingHistoryService.save(booking);
        booking.setStatus("Processing");
        booking.setDescription("------ Đã hủy đơn chờ xét hoàn lại cọc lúc: " + DATE_TIME_FORMATTER.format(new Date().toInstant()) + " ------");
        bookingRepository.save(booking);
    }

    public void refunded(Long id) {
        DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern(" HH:mm:ss dd-MM-yyyy")
                .withZone(ZoneId.systemDefault());
        Booking booking = bookingRepository.findById(id).orElseThrow(() -> new SpringException("Không tồn tại đơn đặt với mã ID: " + id));
        bookingHistoryService.save(booking);
        booking.setStatus("Refunded");
        booking.setDescription("------ Đã hoàn cọc lúc: " + DATE_TIME_FORMATTER.format(new Date().toInstant()) + " ------");
        bookingRepository.save(booking);
    }

    public void cancel(Long id) {
        DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern(" HH:mm:ss dd-MM-yyyy")
                .withZone(ZoneId.systemDefault());
        Booking booking = bookingRepository.findById(id).orElseThrow(() -> new SpringException("Không tồn tại đơn đặt với mã ID: " + id));
        bookingHistoryService.save(booking);
        booking.setStatus("Cancel");
        booking.setDescription("------ Đã hủy đơn : " + DATE_TIME_FORMATTER.format(new Date().toInstant()) + " ------");
        bookingRepository.save(booking);
    }

    public void addIdentityCard(BookingRequest bookingRequest) {
        Booking booking = bookingRepository.findById(bookingRequest.getId()).orElseThrow(() -> new SpringException("Không tồn tại đơn đặt nhà ở với mã ID: " + bookingRequest.getId()));
        booking.setIdentityCard(bookingRequest.getIdentityCard());
        bookingRepository.save(booking);
    }

    @Transactional(readOnly = true)
    public void checkDateAdd(BookingRequest bookingRequest) {
        // Convert Date -> LocalDate
        LocalDate localDateIn = bookingRequest.getDateIn().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate localDateOut = bookingRequest.getDateOut().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

        if (!localDateOut.isAfter(localDateIn)) {
            throw new SpringException("Thời gian kết thúc phải sau ngày bắt đầu (ít nhất qua ngày hôm sau)");
        }
        List<Booking> booking = bookingRepository.findByHouseId(bookingRequest.getId_house());
        if (!booking.isEmpty()) {
            for (Booking list : booking) {
                if (list.getDateIn().toInstant().equals(bookingRequest.getDateIn().toInstant()) && list.getDateOut().toInstant().equals(bookingRequest.getDateOut().toInstant())) {
                    throw new SpringException("Những ngày này không còn trống");
                }
                List<LocalDate> listDate = dateFilter(bookingRequest.getDateIn(), bookingRequest.getDateOut());
                for (LocalDate list2 : listDate) {
                    if (list2.equals(list.getDateOut().toInstant().atZone(ZoneId.systemDefault()).toLocalDate()) || list2.equals(list.getDateIn().toInstant().atZone(ZoneId.systemDefault()).toLocalDate())) {
                        throw new SpringException("Những ngày này không còn trống");
                    }
                }
            }
        }
    }

    @Transactional(readOnly = true)
    public void checkDateEdit(BookingRequest bookingRequest, Booking booking) {
        List<Booking> bookingHouse = bookingRepository.findByHouseId(bookingRequest.getId_house());
        if (!bookingHouse.isEmpty()) {
            for (Booking list : bookingHouse) {
                if (list.getDateIn().toInstant().equals(booking.getDateIn().toInstant()) && list.getDateOut().toInstant().equals(booking.getDateOut().toInstant())) {
                } else {
                    if (list.getDateIn().toInstant().equals(bookingRequest.getDateIn().toInstant()) && list.getDateOut().toInstant().equals(bookingRequest.getDateOut().toInstant())) {
                        throw new SpringException("Những ngày này không còn trống");
                    }
                    List<LocalDate> listDate = dateFilter(bookingRequest.getDateIn(), bookingRequest.getDateOut());
                    for (LocalDate list2 : listDate) {
                        if (list2.equals(list.getDateOut().toInstant().atZone(ZoneId.systemDefault()).toLocalDate()) || list2.equals(list.getDateIn().toInstant().atZone(ZoneId.systemDefault()).toLocalDate())) {
                            throw new SpringException("Những ngày này không còn trống");
                        }
                    }
                }
            }
        }
    }

    ////////////////////////////////////////////////////////////////////////Load front/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @Transactional(readOnly = true)
    public List<LocalDate> checkDateByHouse(Long id) {
        List<Booking> booking = bookingRepository.findByHouseId(id);
        List<LocalDate> listResponse = new ArrayList<>();
        List<LocalDate> listResponse2 = new ArrayList<>();
        if (!booking.isEmpty()) {
            for (Booking list : booking) {
                List<LocalDate> listDate = dateFilter(list.getDateIn(), list.getDateOut());
                listResponse.addAll(listDate);
            }
            for (LocalDate list1 : listResponse) {
                for (LocalDate list2 : listResponse) {
                    if (list1.plusDays(2).equals(list2)) {
                        listResponse2.add(list1.plusDays(1));
                    }
                }
            }
            listResponse.addAll(listResponse2);
        }
        return listResponse;
    }

    @Transactional(readOnly = true)
    public List<LocalDate> checkDateEditByHouse(Long idHouse, Long idBook) {
        List<Booking> bookingHouse = bookingRepository.findByHouseId(idHouse);
        Booking booking = bookingRepository.findById(idBook).orElseThrow(() -> new SpringException("Không tồn tại đơn đặt nhà ở với mã ID: " + idBook));
        List<LocalDate> listResponse = new ArrayList<>();
        List<LocalDate> listResponse2 = new ArrayList<>();
        if (!bookingHouse.isEmpty()) {
            for (Booking list : bookingHouse) {
                if (booking.getDateIn().equals(list.getDateIn()) && booking.getDateOut().equals(list.getDateOut()) && booking.getId().equals(list.getId())) {
                } else {
                    List<LocalDate> listDate = dateFilter(list.getDateIn(), list.getDateOut());
                    listResponse.addAll(listDate);
                }
            }
            for (LocalDate list1 : listResponse) {
                for (LocalDate list2 : listResponse) {
                    if (list1.plusDays(2).equals(list2)) {
                        listResponse2.add(list1.plusDays(1));
                    }
                }
            }
            listResponse.addAll(listResponse2);
        }
        return listResponse;
    }

    public List<LocalDate> dateFilter(Date dateIn, Date dateOut) {
        try {
            LocalDate startDate = dateIn.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            LocalDate endDate = dateOut.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            long numOfDays = ChronoUnit.DAYS.between(startDate, endDate);
            return Stream.iterate(startDate.plusDays(1), date -> date.plusDays(1))
                    .limit(numOfDays - 1)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new SpringException("Khoảng ngày không được phép");
        }
    }

    public void checkIn(Long id) {
        DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern(" HH:mm:ss dd-MM-yyyy")
                .withZone(ZoneId.systemDefault());
        Booking booking = bookingRepository.findById(id).orElseThrow(() -> new SpringException("Không tồn tại đơn đặt nhà ở với mã ID: " + id));
        Instant timeNow = Instant.now();
        LocalDate timeCheckIn = booking.getDateIn().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        Instant instant = timeCheckIn.atStartOfDay(ZoneId.systemDefault()).toInstant();
        if (timeNow.isAfter(instant)) {
            booking.setStatus("CheckIn");
            booking.setDescription("------ Đã nhận phòng lúc: " + DATE_TIME_FORMATTER.format(new Date().toInstant()) + " ------");
            bookingRepository.save(booking);
        } else {
            throw new SpringException("Chưa đến thời điểm nhận phòng");
        }
    }
}
