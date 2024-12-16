package org.bookings.dto;

import lombok.Data;
import org.bookings.enums.Role;

@Data
public class AuthenticationResponse {

    private String jwt;

    private Long userId;

    private Role role;
}
