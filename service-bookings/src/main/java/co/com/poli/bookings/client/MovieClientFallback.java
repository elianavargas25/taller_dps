package co.com.poli.bookings.client;


import co.com.poli.bookings.model.Movie;
import co.com.poli.bookings.utils.Response;
import co.com.poli.bookings.utils.ResponseBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MovieClientFallback implements  MovieClient{
    private final ResponseBuilder builder;

    @Override
    public Response findById(Long id) {
        Movie movie = new Movie();
        return builder.success(movie);
    }
}
