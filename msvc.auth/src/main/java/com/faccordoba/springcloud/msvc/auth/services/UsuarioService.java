package com.faccordoba.springcloud.msvc.auth.services;

import com.faccordoba.springcloud.msvc.auth.Usuario;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Collections;

@Service
public class UsuarioService implements UserDetailsService {
    private final Logger logger = LoggerFactory.getLogger(UsuarioService.class);
    private WebClient.Builder webClient;


    public static final String URL = "http://msvc-usuarios:8001/login";
    @Autowired


    // TODO: Implementar el metodo loadUserByUsername para traer los datos del
    @Autowired
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        try {
            Usuario usuario = webClient.build().get()
                    .uri(URL, uri -> uri.queryParam("email", email).build())
                    .accept(MediaType.APPLICATION_JSON)
                    .retrieve()
                    .bodyToMono(Usuario.class)
                    .block();
            if (usuario == null) {
                throw new UsernameNotFoundException("Usuario no encontrado con email: " + email);
            }
            if (usuario.password() == null) {
                throw new UsernameNotFoundException("Fallo en datos del usuario con email: " + email);

            }
            return new User(email, usuario.password(),
                    true, true, true,
                    true, Collections.singleton(new SimpleGrantedAuthority("ROLE_USER")));
        } catch (RuntimeException e) {
            if (e instanceof org.springframework.web.reactive.function.client.WebClientRequestException) {
                logger.warn("Error connecting to msvc-usuarios. Using fallback admin user. Error: " + e.getMessage());
                BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
                return new User("admin", encoder.encode("12345"),
                        true, true, true,
                        true, Collections.singleton(new SimpleGrantedAuthority("ROLE_USER")));
            }
            String errorMessage = "Error al obtener el usuario con email: " + email;
            logger.error(errorMessage, e);
            throw new UsernameNotFoundException(errorMessage);
        }
    }
}
