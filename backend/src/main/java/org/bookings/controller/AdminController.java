package org.bookings.controller;

import lombok.AllArgsConstructor;
import org.bookings.model.Admin;
import org.bookings.service.AdminService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/bookings/admin")
@AllArgsConstructor
public class AdminController {

    private AdminService adminService;

    @PostMapping("/register")
    public ResponseEntity<Admin> registerAdmin(@RequestBody Admin admin){

        Admin res = adminService.registerAdmin(admin);

        return new ResponseEntity<>(res, HttpStatus.CREATED);
    }
}
