package com.yourmenu.yourmenu_api.auth;

import com.yourmenu.yourmenu_api.administrator.AdministratorRepository;
import com.yourmenu.yourmenu_api.administrator.AdministratorService;
import com.yourmenu.yourmenu_api.administrator.dto.AdministratorRegisterDTO;
import com.yourmenu.yourmenu_api.auth.dto.LoginDTO;
import com.yourmenu.yourmenu_api.auth.dto.LoginResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("auth")
public class AuthenticationController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private AdministratorService administratorService;

    @Operation(summary = "Autentica um usuario e retorna um token de accesso")
    @PostMapping("/login")
    public LoginResponseDTO login(@RequestBody @Valid LoginDTO data){
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.email(), data.password());
        var auth = authenticationManager.authenticate(usernamePassword);

        return new LoginResponseDTO(null);
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Valid AdministratorRegisterDTO dto){
        return administratorService.save(dto);
    }
}
