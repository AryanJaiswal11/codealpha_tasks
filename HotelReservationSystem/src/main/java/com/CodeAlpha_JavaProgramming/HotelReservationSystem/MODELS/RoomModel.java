package com.CodeAlpha_JavaProgramming.HotelReservationSystem.MODELS;

import com.CodeAlpha_JavaProgramming.HotelReservationSystem.ENUMS.Availability;
import com.CodeAlpha_JavaProgramming.HotelReservationSystem.ENUMS.Status;
import com.CodeAlpha_JavaProgramming.HotelReservationSystem.ENUMS.Type;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class RoomModel {
    @Id
    private Integer roomId;

    private Long timesBooked;

    @Enumerated(EnumType.STRING)
    private Type roomType;

    @Enumerated(EnumType.STRING)
    private Availability availability;

}
