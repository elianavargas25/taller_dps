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
import java.util.Optional;
import java.util.stream.Collectors;

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
        Booking bookings = bookingRepository.save(booking);
        ModelMapper modelMapper = new ModelMapper();
        User user =
                modelMapper.map(userClient.findById(bookings.getUserId()).getData(),
                        User.class);
        bookings.setUser(user);

        Showtime showtime =
                modelMapper.map(showtimeClient.findById(bookings.getShowtimeId()).getData(),
                        Showtime.class);
        bookings.setShowtime(showtime);

        Movie movie =
                modelMapper.map(movieClient.findById(bookings.getMoviesId()).getData(),
                        Movie.class);
        bookings.setMovie(movie);
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
        List<Booking> booking = bookingRepository.findAll();
        ModelMapper modelMapper = new ModelMapper();
        booking.stream().map(bookings -> {
            User user =
                    modelMapper.map(userClient.findById(bookings.getUserId()).getData(),
                            User.class);
            bookings.setUser(user);
            Showtime showtime =
                    modelMapper.map(showtimeClient.findById(bookings.getShowtimeId()).getData(),
                            Showtime.class);
            bookings.setShowtime(showtime);
            Movie movie =
                    modelMapper.map(movieClient.findById(bookings.getMoviesId()).getData(),
                            Movie.class);
            bookings.setMovie(movie);
            return bookings;

        }).collect(Collectors.toList());
        return bookingRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Booking findById(Long id) {
        Optional<Booking> booking = Optional.ofNullable(bookingRepository.findById(id)).orElse(null);
        if (booking.isPresent()) {
            ModelMapper modelMapper = new ModelMapper();
            User user =
                    modelMapper.map(userClient.findById(booking.get().getUserId()).getData(),
                            User.class);
            booking.orElse(null).setUser(user);
            Showtime showtime =
                    modelMapper.map(showtimeClient.findById(booking.get().getShowtimeId()).getData(),
                            Showtime.class);
            booking.orElse(null).setShowtime(showtime);

            Movie movie =
                    modelMapper.map(movieClient.findById(booking.get().getMoviesId()).getData(),
                            Movie.class);
            booking.orElse(null).setMovie(movie);
            return bookingRepository.findById(id).orElse(null);
        } else {
            return bookingRepository.findById(id).orElse(null);
        }
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
                modelMapper.map(showtimeClient.findById(booking.getShowtimeId()).getData(),
                        Showtime.class);
        booking.setShowtime(showtime);

        Movie movie =
                modelMapper.map(movieClient.findById(booking.getMoviesId()).getData(),
                        Movie.class);
        booking.setMovie(movie);

        return bookingRepository.findByUserId(userId);
    }

}


