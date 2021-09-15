package co.com.poli.bookings.services;

import co.com.poli.bookings.client.MovieClient;
import co.com.poli.bookings.client.ShowtimeClient;
import co.com.poli.bookings.client.UserClient;
import co.com.poli.bookings.entities.Booking;
import co.com.poli.bookings.model.Movie;
import co.com.poli.bookings.model.Showtime;
import co.com.poli.bookings.model.User;
import co.com.poli.bookings.repositories.BookingRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookingServiceImpl implements BookingService {

    private final BookingRepository bookingRepository;
    private final UserClient userClient;
    private final ShowtimeClient showtimeClient;
    private final MovieClient movieClient;

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
        Booking booking = bookingRepository.findByUserId(userId);
        ModelMapper modelMapper = new ModelMapper();
        User user =
                modelMapper.map(userClient.findById(booking.getUserId()).getData(),
                        User.class);
        booking.setUser(user);

        Showtime showtime =
                modelMapper.map(showtimeClient.findById(booking.getShowtimeid()).getData(),
                        Showtime.class);
        booking.setShowtime(showtime);

        Movie movie =
                modelMapper.map(movieClient.findById(booking.getMoviesid()).getData(),
                        Movie.class);
        booking.setMovie(movie);

        return bookingRepository.findByUserId(userId);
    }

}


