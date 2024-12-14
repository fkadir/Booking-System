package org.bookings.services.auth;

import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityExistsException;
import lombok.RequiredArgsConstructor;
import org.bookings.dto.SignupRequest;
import org.bookings.dto.UserDto;
import org.bookings.entity.User;
import org.bookings.enums.Role;
import org.bookings.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService{

    private final UserRepository userRepository;

    @PostConstruct
    public void createAnAdminAccount() {
        Optional<User> adminAccount = userRepository.findByRole(Role.ADMIN);
        if(adminAccount.isEmpty()) {
            User user = new User();
            user.setEmail("admin@test.com");
            user.setName("Admin");
            user.setRole(Role.ADMIN);
            user.setPassword(new BCryptPasswordEncoder().encode("admin"));
            userRepository.save(user);
            System.out.println("Admin account created");
        } else {
            System.out.println("Admin account already exists");
        }
    }

    public UserDto createUser(SignupRequest signupRequest) {
        if(userRepository.findFirstByEmail(signupRequest.getEmail()).isPresent()) {
            throw new EntityExistsException("User already present with email " + signupRequest.getEmail());
        }

        User user = new User();
        user.setEmail(signupRequest.getEmail());
        user.setName(signupRequest.getName());
        user.setRole(Role.CUSTOMER);
        user.setPassword(new BCryptPasswordEncoder().encode(signupRequest.getPassword()));
        User createdUser = userRepository.save(user);
        return createdUser.getUserDto();
    }
}
