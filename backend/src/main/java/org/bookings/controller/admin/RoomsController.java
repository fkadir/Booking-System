package org.bookings.controller.admin;

import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.bookings.dto.RoomDto;
import org.bookings.services.admin.rooms.RoomsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class RoomsController {

    private final RoomsService roomsService;

    @PostMapping("/room")
    public ResponseEntity<?> postRoom(@RequestBody RoomDto roomDto) {
        boolean success = roomsService.postRoom(roomDto);
        if(success) {
            return ResponseEntity.status(HttpStatus.OK).build();
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

}
