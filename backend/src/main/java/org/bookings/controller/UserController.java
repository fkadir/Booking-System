package org.bookings.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.bookings.dto.response.UserResponse;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/bookings/user")
@AllArgsConstructor
public class UserController {

    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<UserResponse> registerUser(@RequestBody UserRequest userRequest) {
        UserResponse res = userService.registerUser(userRequest);
        return new ResponseEntity<>(res, HttpStatus.CREATED);
    }

    @PutMapping("/update/name")
    public ResponseEntity<String> updateName(@RequestBody UpdateRequest updateRequest) {
        String res = userService.updateName(updateRequest);
        return new ResponseEntity<>(res, HttpStatus.OK);

    }

    @PutMapping("/update/password")
    public ResponseEntity<String> updatePassword(@RequestBody UpdatePasswordRequest updatePasswordRequest) {
        String res = userService.updatePassword(updatePasswordRequest);
        return new ResponseEntity<>(res, HttpStatus.OK);

    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteUser(@RequestBody UpdateRequest updateRequest) {
        String res = userService.deleteUser(updateRequest);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @GetMapping("/view-profile")
    public ResponseEntity<UserResponse> getUserById() {
        UserResponse res = userService.viewProfile();
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @GetMapping("/get/to-be-deleted")
    public ResponseEntity<List<UserResponse>> getAllUser() {
        List<UserResponse> res = userService.getToBeDeletedUser();
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

}
