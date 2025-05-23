package com.yourmenu.yourmenu_api.auth.token;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.yourmenu.yourmenu_api.administrator.Administrator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {
    @Value("${api.security.token.secret}")
    private String secret;

    public String generateToken(Administrator administrator) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            String token = JWT.create()
                    .withIssuer("yourmenu-api")   //nossa api
                    .withSubject(administrator.getId()) //usuario recebedor do token
                    .withExpiresAt(generateExpirationDate())
                    .sign(algorithm);
            return token;
        } catch (JWTCreationException e) {
            throw new RuntimeException("erro ao gerar o token", e);
        }
    }

    public String validateToken(String token) {
        try{
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.require(algorithm)
                    .withIssuer("yourmenu-api")
                    .build()
                    .verify(token)
                    .getSubject(); //retorna UUID
        }catch(JWTVerificationException e){
            return ""; //ao retornar uma string vazia o spring security saberá que é invlálido e retornará 403
        }
    }

    private Instant generateExpirationDate () {
        return LocalDateTime.now().plusMonths(1).toInstant(ZoneOffset.of("-03:00"));
    }
}
