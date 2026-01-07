package org.faccordoba.springcloud.msvc.usuarios.msvc.usuarios.adapter.out.security;

import org.faccordoba.springcloud.msvc.usuarios.msvc.usuarios.domain.port.out.security.SecurityPort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class SecurityAdapter implements SecurityPort {

    private final BCryptPasswordEncoder passwordEncoder;

    public SecurityAdapter(BCryptPasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public String encode(String rawPassword) {
        return passwordEncoder.encode(rawPassword);
    }
}
