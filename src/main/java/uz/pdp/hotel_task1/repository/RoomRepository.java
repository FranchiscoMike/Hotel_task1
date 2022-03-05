package uz.pdp.hotel_task1.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.hotel_task1.entity.Room;

public interface RoomRepository extends JpaRepository<Room,Integer> {

   Boolean existsByNumber(Integer number);

   @Override
   Page<Room> findAll(Pageable pageable);
}
