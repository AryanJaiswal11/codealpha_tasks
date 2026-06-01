package com.CodeAlpha_JavaProgramming.HotelReservationSystem.CONFIGS;

import com.CodeAlpha_JavaProgramming.HotelReservationSystem.DTO.HotelRoomDto;
import com.CodeAlpha_JavaProgramming.HotelReservationSystem.MODELS.RoomModel;
import com.CodeAlpha_JavaProgramming.HotelReservationSystem.REPO.RoomRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.InputStream;
import java.util.List;
import com.fasterxml.jackson.core.type.TypeReference;

@Component
@Order(1)
public class RoomDataLoader implements CommandLineRunner {
    @Autowired
    RoomRepo roomRepo;

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Loading Room Data...");
        ObjectMapper mapper = new ObjectMapper();

        InputStream inputStream =
                getClass().getResourceAsStream("/rooms.json");

        List<HotelRoomDto> roomDtos = mapper.readValue(
                inputStream,
                new TypeReference<List<HotelRoomDto>>() {}
        );

        List<RoomModel> rooms = roomDtos.stream()
                .map(HotelRoomDto::to)
                .toList();

        this.roomRepo.saveAll(rooms);
        System.out.println("Rooms saved");
    }
}
