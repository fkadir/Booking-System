package org.bookings.dto.response;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserResponse {

    private Long customerId;

    private String name;

    private String email;

    @JsonFormat(pattern = "dd-MM-yyyy HH:mm")
    private LocalDateTime registrationDateTime;

}
