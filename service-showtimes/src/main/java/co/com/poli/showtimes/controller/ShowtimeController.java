package co.com.poli.showtimes.controller;


import co.com.poli.showtimes.entities.Showtime;
import co.com.poli.showtimes.services.ShowtimeService;
import co.com.poli.showtimes.utils.ErrorMessage;
import co.com.poli.showtimes.utils.Response;
import co.com.poli.showtimes.utils.ResponseBuilder;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping ("/showtime")
public class ShowtimeController {
    private final ShowtimeService showtimeService;
    private final ResponseBuilder builder;

    @PostMapping
    public Response save(@Valid @RequestBody Showtime showtime, BindingResult result){
        if(result.hasErrors()){
            return builder.failed(this.formatMessage((result)));
        }
        showtimeService.save(showtime);
        return builder.success(showtime);
    }


    @GetMapping
    public ResponseEntity<List<Showtime>> findAll(){
        List<Showtime> showtimes = showtimeService.findAll();
        if(showtimes.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(showtimes);
    }

    @GetMapping("/{id}")
    public Response findById(@PathVariable("id") Long id){
        Showtime showtime = showtimeService.findById(id);
        if(showtime==null){
            return builder.success(null);
        }
        return builder.success(showtime);
    }

    @PutMapping("/{id}")
    public Response update (@PathVariable("id") Long id, @RequestBody Showtime showtime){
        return builder.success(showtimeService.update(showtime, id));
    }

    private String formatMessage(BindingResult result){
        List<Map<String,String>> errors = result.getFieldErrors().stream()
                .map(err -> {
                    Map<String,String> error = new HashMap<>();
                    error.put(err.getField(),err.getDefaultMessage());
                    return error;
                }).collect(Collectors.toList());

        ErrorMessage errorMessage = ErrorMessage.builder()
                .code("01")
                .messages(errors)
                .build();
        ObjectMapper objectMapper = new ObjectMapper();
        String json = "";
        try{
            json = objectMapper.writeValueAsString(errorMessage);
        }catch (JsonProcessingException e){
            e.printStackTrace();
        }
        return json;
    }

}
