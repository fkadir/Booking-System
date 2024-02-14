package org.bookings.service.impl;

import java.util.Optional;

import org.bookings.repository.AdminDao;
import org.bookings.exception.AdminException;
import org.bookings.Role;
import org.bookings.model.Admin;
import org.bookings.service.AdminService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@AllArgsConstructor
@Transactional
public class AdminServiceImpl implements AdminService {

    private AdminDao dao;

    @Override
    public Admin registerAdmin(Admin admin) throws AdminException {
        Optional<Admin> adminExist = dao.findByEmail(admin.getEmail());

        if (adminExist.isPresent()) {
            throw new AdminException("Admin already registered with this email!");
        }

//        String hashedPassword = passwordEncoder.encode(admin.getPassword());
//        admin.setPassword(hashedPassword);
        admin.setPassword(admin.getPassword());
        admin.setRole(Role.ROLE_ADMIN);

        return dao.save(admin);
    }

}
