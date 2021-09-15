package co.com.poli.bookings.entities;

import co.com.poli.bookings.model.Movie;
import co.com.poli.bookings.model.Showtime;
import co.com.poli.bookings.model.User;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.Objects;

@Setter
@Getter
@Entity
@Table(name="bookings")
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id", updatable = false,nullable = false,unique = true)
    private Long id;

    @NotEmpty(message = "El user id no debe ser vacio")
    @Column(name="userid" )
    private Long userId;
    @Transient
    private User user;

    @NotEmpty(message = "El show time id no debe ser vacio")
    @Column(name="showtimeid")
    private Long showtimeid;
    @Transient
    private Showtime showtime;

    @Column(name="movies")
    private Long moviesid;
    @Transient
    private Movie movie;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Booking booking = (Booking) o;
        return Objects.equals(id, booking.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
