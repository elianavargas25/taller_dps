package co.com.poli.bookings.client;

import co.com.poli.bookings.model.User;
import co.com.poli.bookings.utils.Response;
import co.com.poli.bookings.utils.ResponseBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserClientFallback implements  UserClient{

    private final ResponseBuilder builder;

    @Override
    public Response findById(Long id) {
        User user = new User();
        return builder.success(user);
    }



}

