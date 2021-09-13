package co.com.poli.showtimes.services;

import co.com.poli.showtimes.entities.Showtime;

import java.util.List;

public interface ShowtimeService {
    void save(Showtime showtime);
    void  delete(Showtime showtime);
    List<Showtime> findAll();
    Showtime findById(Long id);

}
