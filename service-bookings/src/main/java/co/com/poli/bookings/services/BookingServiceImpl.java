package co.com.poli.bookings.services;

import co.com.poli.bookings.entities.Booking;
import co.com.poli.bookings.repositories.BookingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookingServiceImpl implements  BookingService{

    private final BookingRepository bookingRepository;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void save(Booking booking) {
        bookingRepository.save(booking);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(Booking booking) {
        bookingRepository.delete(booking);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Booking> findAll() {
        return bookingRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Booking findById(Long id) {
        return bookingRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional(readOnly = true)
    public Booking findByUserId(Long userId) {
        return bookingRepository.findByUserId(userId);
    }

}


