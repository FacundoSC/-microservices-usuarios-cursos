package org.faccordoba.springcloud.msvc.usuarios.msvc.usuarios.domain.port.out.security;

public interface SecurityPort {
    String encode(String rawPassword);
}
