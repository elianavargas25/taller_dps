package co.com.poli.bookings.client;

import co.com.poli.bookings.utils.Response;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "service-users", fallback = UserClientFallback.class)
public interface UserClient {
    @GetMapping("/user/{id}")
    Response findById(@PathVariable("id") Long id);
}
