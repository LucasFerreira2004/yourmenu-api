package com.yourmenu.yourmenu_api.administrator;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

    public interface AdministratorRepository extends JpaRepository<Administrator, String> {
        Optional<Administrator> findByEmail(String email);
    }
