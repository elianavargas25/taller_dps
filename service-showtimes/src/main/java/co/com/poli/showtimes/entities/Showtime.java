package co.com.poli.showtimes.entities;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.*;

@Setter
@Getter
@Entity
@Table(name = "showtimes")
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class Showtime {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false, unique = true)
    private Long id;

    @NotEmpty(message = "El campo date no debe ser vacio")
    @Column(name = "date")
    private Date date;

    @Column(name = "movie")
    private Long movie;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Showtime showtime = (Showtime) o;
        return Objects.equals(id, showtime.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
