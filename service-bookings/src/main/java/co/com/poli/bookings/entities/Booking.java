package co.com.poli.bookings.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="userid" )
    @JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
    private Long userid;

    @NotEmpty(message = "El show time id no debe ser vacio")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="showtimeid")
    @JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
    private Long showtimeid;

    //  @NotEmpty(message = "Movies no debe ser vacio")
    @Column(name="movies")
    private Long movies;


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
