package org.bookings.services.admin.rooms;

import lombok.RequiredArgsConstructor;
import org.bookings.dto.RoomDto;
import org.bookings.entity.Room;
import org.bookings.repository.RoomRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoomsServiceImpl implements RoomsService{

    private final RoomRepository roomRepository;

    public boolean postRoom(RoomDto roomDto) {
        try {
            Room room = new Room();

            room.setName(roomDto.getName());
            room.setPrice(roomDto.getPrice());
            room.setType(roomDto.getType());
            room.setAvailable(true);

            roomRepository.save(room);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
