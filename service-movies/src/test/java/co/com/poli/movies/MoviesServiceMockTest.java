package co.com.poli.movies;

import co.com.poli.movies.entities.Movie;
import co.com.poli.movies.repositories.MovieRepository;
import co.com.poli.movies.services.MovieService;
import co.com.poli.movies.services.MovieServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

public class MoviesServiceMockTest {

    @Mock
    private MovieRepository movieRepository;
    private MovieService movieService;


    @BeforeEach
    public void begin(){
        MockitoAnnotations.initMocks(this);
        movieService = new MovieServiceImpl(movieRepository);

        Movie movie = Movie.builder()
                .id(4L)
                .title("Test")
                .director("Test")
                .rating(1).build();
        Mockito.when(movieRepository.findById(4L))
                .thenReturn(Optional.of(movie));
    }

    @Test
    public void when_findById_return_product(){
        Movie product = movieService.findById(4L);
        Assertions.assertThat(product.getTitle()).isEqualTo("Test");
    }

}
