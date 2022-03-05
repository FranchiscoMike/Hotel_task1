package uz.pdp.hotel_task1.entity;

import lombok.*;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer number;

    private Integer floor;

    private double size;

    @ManyToOne  // 1ta hotelda ko'p room bo'ladi
    private Hotel hotel;
}
