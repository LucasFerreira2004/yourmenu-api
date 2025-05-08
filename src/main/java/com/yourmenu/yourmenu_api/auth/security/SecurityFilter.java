package com.yourmenu.yourmenu_api.auth.security;

import com.yourmenu.yourmenu_api.administrator.AdministratorRepository;
import com.yourmenu.yourmenu_api.auth.token.TokenService;
import com.yourmenu.yourmenu_api.globalExceptions.UserNotAuthenticatedException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class SecurityFilter extends OncePerRequestFilter {
    @Autowired
    TokenService tokenService;

    @Autowired
    AdministratorRepository administratorRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        var token = this.recoverToken(request);
        if (token != null) {
            String AdministratorId = tokenService.validateToken(token);
            UserDetails user = administratorRepository.findById(AdministratorId)
                    .orElseThrow(() -> new UserNotAuthenticatedException("token"));
            //para mandar de forma tratada para o user eu teria que criar um try catch para capturar essa exceção e montar o json
            var authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        filterChain.doFilter(request, response);
    }

    private String recoverToken(HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization");
        if (authHeader == null) return null;
        return authHeader.replace("Bearer ", "");
    }
}
