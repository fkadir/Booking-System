package org.bookings.services.admin.rooms;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.bookings.dto.RoomDto;
import org.bookings.dto.RoomsResponseDto;
import org.bookings.entity.Room;
import org.bookings.repository.RoomRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.Collectors;

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

    public RoomsResponseDto getAllRooms(int pageNum) {
        Pageable pageable = PageRequest.of(pageNum, 10);
        Page<Room> roomPage =  roomRepository.findAll(pageable);

        RoomsResponseDto roomsResponseDto = new RoomsResponseDto();
        roomsResponseDto.setCurrentPage(roomPage.getPageable().getPageNumber());
        roomsResponseDto.setTotalPages(roomPage.getTotalPages());
        roomsResponseDto.setRoomDtoList(roomPage.stream().map(Room::getRoomDto).collect(Collectors.toList()));

        return roomsResponseDto;
    }

    public RoomDto getRoomById(Long id) {
        Optional<Room> optRoom = roomRepository.findById(id);
        if(optRoom.isPresent()) {
            return optRoom.get().getRoomDto();
        } else {
            throw new EntityNotFoundException("Room Not Found");
        }
    }

    public boolean updateRoom(Long id, RoomDto roomDto) {
        Optional<Room> optRoom = roomRepository.findById(id);
        if(optRoom.isPresent()) {
            Room room = optRoom.get();

            room.setName(roomDto.getName());
            room.setPrice(roomDto.getPrice());
            room.setType(roomDto.getType());

            roomRepository.save(room);

            return true;

        }
        return false;
    }

    public void deleteRoom(Long id) {
        Optional<Room> optRoom = roomRepository.findById(id);
        if(optRoom.isPresent()) {
            roomRepository.deleteById(id);
        } else {
            throw new EntityNotFoundException("Room Not Found");
        }
    }
}
