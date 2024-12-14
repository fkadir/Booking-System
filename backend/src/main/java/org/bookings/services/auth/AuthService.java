package org.bookings.services.auth;

import org.bookings.dto.SignupRequest;
import org.bookings.dto.UserDto;

public interface AuthService {

    UserDto createUser(SignupRequest signupRequest);
}
