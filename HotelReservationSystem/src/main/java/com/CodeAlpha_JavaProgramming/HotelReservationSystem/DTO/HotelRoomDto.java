package com.CodeAlpha_JavaProgramming.HotelReservationSystem.DTO;

import com.CodeAlpha_JavaProgramming.HotelReservationSystem.ENUMS.Availability;
import com.CodeAlpha_JavaProgramming.HotelReservationSystem.MODELS.RoomModel;
import com.CodeAlpha_JavaProgramming.HotelReservationSystem.ENUMS.Type;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HotelRoomDto {
    @NotNull
    private Integer roomId;

    @NotNull
    private Type roomType;

    public RoomModel to(){
       return RoomModel.builder()
                .roomId(roomId)
                .timesBooked(0L)
                .roomType(roomType)
               .availability(Availability.AVAILABLE)
                .build();
    }
}
