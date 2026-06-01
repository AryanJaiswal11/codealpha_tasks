package com.CodeAlpha_JavaProgramming.HotelReservationSystem.DTO;

import com.CodeAlpha_JavaProgramming.HotelReservationSystem.ENUMS.Type;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Builder
@Getter
@Setter
public class ReservationDTO {

    private String name;
    private String phoneNumber;
    private Type roomType;
    private LocalDate checkInDate;
    private LocalDate checkOutDate;
}