package co.com.poli.bookings.client;

import co.com.poli.bookings.model.Showtime;
import co.com.poli.bookings.utils.Response;
import co.com.poli.bookings.utils.ResponseBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ShowtimeClientFallback implements  ShowtimeClient{

    private final ResponseBuilder builder;

    @Override
    public Response findById(Long id) {
        Showtime showtime = new Showtime();
        return builder.success(showtime);
    }
}
