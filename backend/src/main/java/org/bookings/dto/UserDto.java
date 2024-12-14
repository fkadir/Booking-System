package org.bookings.dto;

import lombok.Data;
import org.bookings.enums.Role;

@Data
public class UserDto {

    private Long id;

    private String email;

    private String name;

    private Role role;
}
