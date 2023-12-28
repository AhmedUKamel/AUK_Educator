package com.ahmedukamel.educator.impl;

import com.ahmedukamel.educator.model.AppUser;
import com.ahmedukamel.educator.model.Role;
import com.ahmedukamel.educator.repository.UserRepository;
import com.ahmedukamel.educator.service.AuthService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Set;

import static com.ahmedukamel.educator.util.ExceptionMessage.UsernameNotFoundMessage;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;

    @Override
    public boolean login(HttpServletRequest request, final String email, final String password) {
        AppUser user = userRepository.findByEmail(email).orElseThrow(
                () -> new UsernameNotFoundException(String.format(UsernameNotFoundMessage, email)));
        if (passwordEncoder.matches(password, user.getPassword())) {
            authenticateSession(request, email, password, user.getAuthorities());
            return true;
        }
        return false;
    }

    @Override
    public void register(HttpServletRequest request, String email, String password, String name, String title) {
        AppUser user = AppUser
                .builder()
                .name(name.strip())
                .title(title.strip())
                .email(email)
                .password(passwordEncoder.encode(password))
                .enabled(true)
                .roles(Set.of(Role.STUDENT))
                .accountLocked(false)
                .instructor(false)
                .accountExpired(false)
                .credentialsExpired(false)
                .build();
        userRepository.save(user);
        authenticateSession(request, email, password, user.getAuthorities());
    }


    private void authenticateSession(HttpServletRequest request, String email, String password, Collection<? extends GrantedAuthority> authorities) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(email, password, authorities));
        SecurityContext securityContext = SecurityContextHolder.getContext();
        securityContext.setAuthentication(authentication);
        HttpSession httpSession = request.getSession(true);
        httpSession.setAttribute("SPRING_SECURITY_CONTEXT", securityContext);
    }
}
