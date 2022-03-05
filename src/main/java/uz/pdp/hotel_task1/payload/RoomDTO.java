package uz.pdp.hotel_task1.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.pdp.hotel_task1.entity.Hotel;

import javax.persistence.ManyToOne;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RoomDTO {

    private Integer number;

    private Integer floor;

    private double size;

    private Integer hotelId;
}
