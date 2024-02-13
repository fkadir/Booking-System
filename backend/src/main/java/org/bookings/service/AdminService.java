package org.bookings.service;

import org.bookings.AdminException;
import org.bookings.model.Admin;

public interface AdminService {
    public Admin registerAdmin(Admin admin) throws AdminException;

}
