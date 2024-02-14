package org.bookings.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import org.bookings.model.User;

public interface UserDao extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);
    List<User> findByToBeDeleted(Boolean toBeDeleted);
    @Modifying
    @Query("update User set name=?2 where customerId=?1")
    Integer setUserEmail(Long customerId, String name);

    @Modifying
    @Query("update User set password=?2 where customerId=?1")
    Integer setUserPassword(Long customerId, String password);
}
