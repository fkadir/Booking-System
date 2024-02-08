package org.bookings;

import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import org.bookings.Role;
import org.bookings.AdminException;
import org.bookings.Admin;
import org.bookings.AdminDao;
import org.bookings.AdminService;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@AllArgsConstructor
@Transactional
public class AdminServiceImpl implements AdminService {

    private AdminDao dao;
    private PasswordEncoder passwordEncoder;

    @Override
    public Admin registerAdmin(Admin admin) throws AdminException {
        Optional<Admin> adminExist = dao.findByEmail(admin.getEmail());

        if (adminExist.isPresent()) {
            throw new AdminException("Admin already registered with this email!");
        }

        String hashedPassword = passwordEncoder.encode(admin.getPassword());
        admin.setPassword(hashedPassword);
        admin.setRole(Role.ROLE_ADMIN);

        return dao.save(admin);
    }

}
