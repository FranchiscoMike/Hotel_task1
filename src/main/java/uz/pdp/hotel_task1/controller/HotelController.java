package uz.pdp.hotel_task1.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import uz.pdp.hotel_task1.entity.Hotel;
import uz.pdp.hotel_task1.repository.HotelRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/hotel")
@RequiredArgsConstructor
public class HotelController {
    final HotelRepository hotelRepository;

    @GetMapping
    public List<Hotel> all(){
        return hotelRepository.findAll();
    }

    @GetMapping("/{id}")
    public Hotel one(@PathVariable Integer id){
        Optional<Hotel> byId = hotelRepository.findById(id);
        return byId.orElseGet(Hotel::new);
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Integer id){
        hotelRepository.deleteById(id);
        return "deleted";
    }

    @PostMapping
    public String add(@RequestBody Hotel hotel){
        Optional<Hotel> byName = hotelRepository.findByName(hotel.getName());
        if (byName.isEmpty()) {
            hotelRepository.save(hotel);
            return "added";
        }
        return "already exists";
    }

    @PutMapping("/{id}")
    public String edit(@PathVariable Integer id, @RequestBody Hotel hotel){
        Optional<Hotel> byName = hotelRepository.findByName(hotel.getName());
        if (byName.isPresent()) {
            Hotel hotel1 = byName.get();
            hotel1.setName(hotel.getName());
            hotelRepository.save(hotel1);
            return "edited";
        }
        return "not found";
    }
}
