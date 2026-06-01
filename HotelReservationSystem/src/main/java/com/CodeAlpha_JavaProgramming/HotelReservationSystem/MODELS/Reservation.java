package com.CodeAlpha_JavaProgramming.HotelReservationSystem.MODELS;

import com.CodeAlpha_JavaProgramming.HotelReservationSystem.ENUMS.Status;
import com.CodeAlpha_JavaProgramming.HotelReservationSystem.ENUMS.Type;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    @Column(unique = true)
    private String externalTxnId;
    
    private String name;
    private String Phone;
    private Integer roomId;

    private LocalDate checkInDate;
    private LocalDate checkOutDate;

    @CreationTimestamp
    private LocalDateTime createdAt;

    private Long amount;
    @Enumerated(EnumType.STRING)
    private Type type;

    @Enumerated(EnumType.STRING)
    private Status status;
}
