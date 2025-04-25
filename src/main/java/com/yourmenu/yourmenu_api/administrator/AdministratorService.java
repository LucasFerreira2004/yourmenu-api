package com.yourmenu.yourmenu_api.administrator;

import com.yourmenu.yourmenu_api.administrator.dto.AdministratorRegisterDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class AdministratorService {
    @Autowired
    private AdministratorRepository administratorRepository;

    public ResponseEntity save(AdministratorRegisterDTO dto) {
        if(this.administratorRepository.findByEmail(dto.email()) != null){
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Email already exists");
        }
        String encryptedPassword = new BCryptPasswordEncoder().encode(dto.password());
        String firstName = splitFirstName(dto.fullName());
        String lastName = splitLastName(dto.fullName());
        Administrator adm = new Administrator(firstName, lastName, dto.email(), encryptedPassword);
        administratorRepository.save(adm);
        return ResponseEntity.ok().build(); //alterar para created depois
    }

    private String splitFirstName(String fullName){
        String[] splitted = fullName.split(" ");
        return splitted[0];
    }
    private String splitLastName(String fullName){
        String[] splitted = fullName.split(" ");
        StringBuilder lastName = new StringBuilder();
        for(int i = 1; i < splitted.length; i++){
            lastName.append(splitted[i]);
            if (i != splitted.length - 1){
                lastName.append(" ");
            }
        }
        return lastName.toString();
    }
}
