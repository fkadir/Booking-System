package org.bookings.service;

import java.util.List;

import org.bookings.dto.request.UserRequest;
import org.bookings.dto.request.UpdatePasswordRequest;
import org.bookings.dto.request.UpdateRequest;
import org.bookings.dto.response.UserResponse;

public interface UserService {

    public UserResponse registerUser(UserRequest userRequest);

//    public String updateName(UpdateRequest updateRequest);
//
//    public String updatePassword(UpdatePasswordRequest updatePasswordRequest);
//
//    public String deleteUser(UpdateRequest updateRequest);
//
//    public List<UserResponse> getToBeDeletedUser();
//
//    public UserResponse viewProfile();

}
