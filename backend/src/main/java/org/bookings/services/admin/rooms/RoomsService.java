package org.bookings.services.admin.rooms;

import org.bookings.dto.RoomDto;
import org.bookings.dto.RoomsResponseDto;

public interface RoomsService {

    boolean postRoom(RoomDto roomDto);

    RoomsResponseDto getAllRooms(int pageNum);

    RoomDto getRoomById(Long id);

    boolean updateRoom(Long id, RoomDto roomDto);

    public void deleteRoom(Long id);
}
