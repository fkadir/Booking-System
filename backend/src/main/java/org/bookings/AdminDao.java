package org.bookings;

import java.util.Optional;

import org.bookings.model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminDao extends JpaRepository<Admin,Integer>{
    Optional<Admin> findByEmail(String email);
}
