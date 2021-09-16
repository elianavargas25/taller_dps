package co.com.poli.movies;

import co.com.poli.movies.entities.Movie;
import co.com.poli.movies.repositories.MovieRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

@DataJpaTest
public class MoviesRepositoryMockTest {
    @Autowired
    private MovieRepository movieRepository;

    @Test
    public void when_findAll_return_listMovie(){
        Movie movie =Movie.builder().title("Ruben").director("Perez").rating(2).build();
        movieRepository.save(movie);
        List<Movie> movies= movieRepository.findAll();
        Assertions.assertThat(movies.size()).isEqualTo(4);
    }
}
