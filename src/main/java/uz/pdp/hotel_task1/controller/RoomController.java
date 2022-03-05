package uz.pdp.hotel_task1.controller;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import uz.pdp.hotel_task1.entity.Room;
import uz.pdp.hotel_task1.payload.RoomDTO;
import uz.pdp.hotel_task1.repository.HotelRepository;
import uz.pdp.hotel_task1.repository.RoomRepository;

import java.util.List;

@RestController
@RequestMapping("/room")
@RequiredArgsConstructor
public class RoomController {

    final HotelRepository hotelRepository;
    final RoomRepository roomRepository;

    @GetMapping
    public Page<Room> all(@RequestParam Integer pageNum, @RequestParam Integer pageSize){
        Pageable pageable =  PageRequest.of(pageNum,pageSize);
        return roomRepository.findAll(pageable);
    }

    @GetMapping("/{id}")
    public Room one(@PathVariable Integer id){
        if (roomRepository.findById(id).isPresent()) {
            return roomRepository.getById(id);
        }
        return new Room();
    }

       @DeleteMapping("/{id}")
    public String delete(@PathVariable Integer id){
        if (roomRepository.findById(id).isPresent()) {
             roomRepository.deleteById(id);
             return "deleted;";

        }
        return "not found";
    }


    @PostMapping
    public String add(@RequestBody RoomDTO dto){
        // agar unga qarashli bo'lgan hotel bo'lsa uni qo'shamiz

        if (hotelRepository.findById(dto.getHotelId()).isPresent()
        && (!roomRepository.existsByNumber(dto.getNumber()))) {
            Room room = new Room();

            room.setFloor(dto.getFloor());
            room.setHotel(hotelRepository.getById(dto.getHotelId()));
            room.setSize(dto.getSize());
            room.setNumber(dto.getNumber());

            roomRepository.save(room);
            return "saved";
        }
        return "fake details";
    }

     @PutMapping("/{id")
    public String add(@PathVariable Integer id,@RequestBody RoomDTO dto){
        // agar unga qarashli bo'lgan hotel bo'lsa uni qo'shamiz

        if (hotelRepository.findById(dto.getHotelId()).isPresent()
        && (!roomRepository.existsByNumber(dto.getNumber()))
        && roomRepository.existsById(id)) {
            Room room = roomRepository.getById(id);

            room.setFloor(dto.getFloor());
            room.setHotel(hotelRepository.getById(dto.getHotelId()));
            room.setSize(dto.getSize());
            room.setNumber(dto.getNumber());

            roomRepository.save(room);
            return "edited";
        }
        return "fake details";
    }



}
