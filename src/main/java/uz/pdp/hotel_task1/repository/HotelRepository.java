package uz.pdp.hotel_task1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.hotel_task1.entity.Hotel;

import java.util.Optional;

public interface HotelRepository extends JpaRepository<Hotel,Integer> {

    Optional<Hotel> findByName(String name);
}
