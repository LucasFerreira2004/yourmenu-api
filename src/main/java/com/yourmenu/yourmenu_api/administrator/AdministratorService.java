package com.yourmenu.yourmenu_api.administrator;

import com.yourmenu.yourmenu_api.administrator.dto.AdministratorRegisterDTO;
import com.yourmenu.yourmenu_api.shared.utils.NameDivider;
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
        if(this.administratorRepository.findByEmail(dto.email()).isPresent()){
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Email already exists");
        }
        String encryptedPassword = new BCryptPasswordEncoder().encode(dto.password());
        String firstName = NameDivider.getFirstName(dto.fullName()); //NameDivider está no pacote shared.utils
        String lastName = NameDivider.getLastname(dto.fullName());
        Administrator adm = new Administrator(firstName, lastName, dto.email(), encryptedPassword);
        administratorRepository.save(adm);
        return ResponseEntity.ok().build(); //alterar para created depois
    }

}
