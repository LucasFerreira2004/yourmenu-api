package com.yourmenu.yourmenu_api.administrator;

import com.yourmenu.yourmenu_api.administrator.dto.AdministratorRegisterDTO;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Table
@Entity
@Data //do lombok
public class Administrator implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @NotNull
    @NotBlank
    private String firstName;

    @NotNull
    @NotBlank
    @Column(columnDefinition = "text")
    private String lastName;

    @NotNull
    @Column(unique = true, columnDefinition = "text")
    @NotBlank
    private String email;

    @NotNull
    @Column(columnDefinition = "text")
    @NotBlank
    private String password;

    //esse construtor e funções associadas devem sumir com o uso do dataMapper
    public Administrator(String firstName, String lastName, String email, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
    }

    //userDetails
    @Override
    public String getUsername() {
        return this.email;
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
