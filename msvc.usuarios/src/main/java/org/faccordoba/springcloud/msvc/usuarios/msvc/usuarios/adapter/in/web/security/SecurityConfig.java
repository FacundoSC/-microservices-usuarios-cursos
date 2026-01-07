package org.faccordoba.springcloud.msvc.usuarios.msvc.usuarios.adapter.in.web.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    public static final String SCOPE_READ = "SCOPE_read";
    public static final String SCOPE_WRITE = "SCOPE_write";
    public static final String SCOPE_DELETE = "SCOPE_delete";

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests((authHttp) -> authHttp
                .requestMatchers("/authorized", "/login").permitAll()
                .requestMatchers(HttpMethod.GET, "/", "/{id}", "/{ids}", "/email/{email}").hasAnyAuthority(SCOPE_READ)
                .requestMatchers(HttpMethod.POST, "/").hasAnyAuthority(SCOPE_WRITE)
                .requestMatchers(HttpMethod.PUT, "/{id}").hasAnyAuthority(SCOPE_WRITE)
                .requestMatchers(HttpMethod.DELETE, "/{id}").hasAnyAuthority(SCOPE_DELETE)
                .anyRequest().authenticated()) // todas las rutas que requieren autenticación
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // Deshabilitamos
                                                                                                              // para
                                                                                                              // que no
                                                                                                              // guarde
                                                                                                              // la
                                                                                                              // autenticación
                                                                                                              // en la
                                                                                                              // sesión
                                                                                                              // y
                                                                                                              // siempre
                                                                                                              // con
                                                                                                              // token
                .oauth2Login(login -> login.loginPage("/oauth2/authorization/msvc-usuarios-client"))
                .csrf(AbstractHttpConfigurer::disable)
                .oauth2Client(withDefaults()).csrf(AbstractHttpConfigurer::disable)
                .oauth2ResourceServer(
                        httpSecurityOAuth2ResourceServerConfigurer -> httpSecurityOAuth2ResourceServerConfigurer
                                .jwt(withDefaults())); // Habilitamos el uso de tokens JWT

        return http.build();

    }
}
