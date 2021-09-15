package co.com.poli.movies.entities;

import lombok.*;
import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Objects;

@Setter
@Getter
@Entity
@Table(name="movies")
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id", updatable = false,nullable = false,unique = true)
    private Long id;

    @NotEmpty (message = "El titulo no debe ser vacio")
    @Column(name="title")
    private String title;

   @NotEmpty(message = "El director no debe ser vacio")
    @Column(name="director")
    private String director;

   // @NotEmpty(message = "El director no debe ser vacio")
    @Range(min = 1, max = 5)
    @Column(name="rating")
    private int rating;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Movie movie = (Movie) o;
        return Objects.equals(id, movie.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
