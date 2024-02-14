package org.bookings.service.impl;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.bookings.dto.request.UserRequest;
import org.bookings.dto.response.UserResponse;
import org.bookings.exception.UserException;
import org.bookings.model.User;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import org.bookings.dto.request.UpdatePasswordRequest;
import org.bookings.dto.request.UpdateRequest;
import org.bookings.Role;
import org.bookings.model.DeleteReason;
import org.bookings.model.Reservation;
import org.bookings.repository.UserDao;
import org.bookings.repository.DeleteReasonDao;
import org.bookings.service.UserService;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@AllArgsConstructor
@Transactional
public class UserServiceImpl implements UserService {

    private UserDao dao;
//    private PasswordEncoder passwordEncoder;
//    private DeleteReasonDao deleteReasonDao;

    @Override
    public UserResponse registerUser(UserRequest userRequest) {
        log.info("Performing email validation");
        if (isEmailExists(userRequest.getEmail())) {
            throw new UserException("User is already exist ...!");
        }

        log.info("Validating password");
        String password = new String(userRequest.getPassword());
        if (!matchesRegex(password)) {
            throw new UserException("Password validation failed!");
        }

        User user = buildUser(userRequest);
        dao.save(user);

        log.info("Registration successful");
        return buildUserResponse(user);
    }

//    @Override
//    public String updateName(UpdateRequest updateRequest) {
//        User currentCustomer = getCurrentLoggedInCustomer();
//
//        log.info("Verifying credentials");
//        String password = new String(updateRequest.getPassword());
//        if (!passwordEncoder.matches(password, currentCustomer.getPassword())) {
//            throw new UserException("Wrong credentials!");
//        }
//        dao.setUserEmail(currentCustomer.getCustomerId(), updateRequest.getField());
//
//        log.info("negation successful");
//        return "Name updated successfully!";
//    }
//
//    @Override
//    public String updatePassword(UpdatePasswordRequest updatePasswordRequest) {
//        Customer currentCustomer = getCurrentLoggedInCustomer();
//
//        log.info("Verifying credentials");
//        String currentPassword = new String(updatePasswordRequest.getCurrentPassword());
//        if (!passwordEncoder.matches(currentPassword, currentCustomer.getPassword())) {
//            throw new CustomerException("Wrong credentials!");
//        }
//
//        log.info("Validating new password");
//        String newPassword = new String(updatePasswordRequest.getNewPassword());
//        if (!matchesRegex(newPassword)) {
//            throw new CustomerException("New password validation failed!");
//        }
//
//        dao.setCustomerPassword(currentCustomer.getCustomerId(), passwordEncoder.encode(newPassword));
//
//        log.info("negation successfully");
//        return "Password updated successfully!";
//    }
//
//    @Override
//    public String updatePhone(UpdateRequest updateRequest) {
//        Customer currentCustomer = getCurrentLoggedInCustomer();
//
//        log.info("Verifying credentials");
//        String password = new String(updateRequest.getPassword());
//        if (!passwordEncoder.matches(password, currentCustomer.getPassword())) {
//            throw new CustomerException("Wrong credentials!");
//        }
//        dao.setCustomerPhone(currentCustomer.getCustomerId(), updateRequest.getField());
//
//        log.info("negation successful");
//        return "Phone updated successfully!";
//    }
//
//    @Override
//    public String deleteCustomer(UpdateRequest updateRequest) {
//        Customer currentCustomer = getCurrentLoggedInCustomer();
//
//        log.info("Verifying credentials");
//        String password = new String(updateRequest.getPassword());
//        if (!passwordEncoder.matches(password, currentCustomer.getPassword())) {
//            throw new CustomerException("Wrong credentials!");
//        }
//
//        List<Reservation> reservations = currentCustomer.getReservations();
//        List<Reservation> pendingReservations = reservations.stream().filter(
//                        r -> r.getCheckoutDate().isAfter(LocalDate.now()) || r.getCheckoutDate().isEqual(LocalDate.now()))
//                .collect(Collectors.toList());
//
//        log.info("Checking for any pending reservations");
//        if (!pendingReservations.isEmpty())
//            throw new CustomerException("Pending reservations! Account can't be deleted");
//
//        log.info("Setting Account status to be deleted");
//        currentCustomer.setToBeDeleted(true);
//        currentCustomer.setDeletionScheduledAt(LocalDateTime.now());
//        dao.save(currentCustomer);
//
//        DeleteReason deleteReason = new DeleteReason();
//        deleteReason.setReason(updateRequest.getField());
//        deleteReasonDao.save(deleteReason);
//
//        log.info("Account scheduled for deletion and Logged out!");
//        return "Account scheduled for deletion. To recovered login again within 24 hours";
//    }
//
//    @Override
//    public List<CustomerResponse> getToBeDeletedCustomers() {
//        List<Customer> customers = dao.findByToBeDeleted(true);
//        if (customers.isEmpty())
//            throw new CustomerException("No accounts found for deletion");
//
//        return customers.stream().map(this::buildCustomerResponse).collect(Collectors.toList());
//    }
//
//    @Override
//    public UserResponse viewProfile() {
//        return buildUserResponse(getCurrentLoggedInCustomer());
//    }
//
//    private User getCurrentLoggedInUser() {
//        String email = SecurityContextHolder.getContext().getAuthentication().getName();
//        return dao.findByEmail(email).get();
//    }

    private boolean isEmailExists(String email) {
        return dao.findByEmail(email).isPresent();
    }

    private boolean matchesRegex(String input) {
        if(input.length()<8)
            return false;
        String regex = "(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}";
        return Pattern.compile(regex).matcher(input).matches();
    }

    private User buildUser(UserRequest userRequest) {
        return User.builder()
                .name(userRequest.getName())
                .email(userRequest.getEmail())
//                .password(passwordEncoder.encode(new String(userRequest.getPassword())))
                .password(new String(userRequest.getPassword()))
                .role(Role.ROLE_USER)
                .toBeDeleted(false)
                .reservations(new ArrayList<>())
                .build();
    }

    private UserResponse buildUserResponse(User user) {
        return UserResponse.builder()
                .customerId(user.getCustomerId())
                .name(user.getName())
                .email(user.getEmail())
                .build();
    }
}