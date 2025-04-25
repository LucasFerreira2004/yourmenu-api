package com.yourmenu.yourmenu_api.administrator;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface AdministratorRepository extends JpaRepository<Administrator, String> {
    UserDetails findByEmail(String email);
}
