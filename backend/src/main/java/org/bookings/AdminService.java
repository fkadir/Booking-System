package org.bookings;

import org.bookings.AdminException;
import org.bookings.Admin;

public interface AdminService {
    public Admin registerAdmin(Admin admin) throws AdminException;

}
