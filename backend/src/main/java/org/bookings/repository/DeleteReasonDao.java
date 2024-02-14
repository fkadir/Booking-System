package org.bookings.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import org.bookings.model.DeleteReason;

public interface DeleteReasonDao extends JpaRepository<DeleteReason, Long>{

}
