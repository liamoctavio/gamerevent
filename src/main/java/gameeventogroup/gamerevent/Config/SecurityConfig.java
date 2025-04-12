package gameeventogroup.gamerevent.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity
public class SecurityConfig {


    private static final String LOGIN_URL = "/login";
    private static final String INICIO_URL = "/inicio";
    

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(authz -> authz
                .requestMatchers("/css/**", "/js/**", LOGIN_URL, "/registro", "/registrar", INICIO_URL, "/").permitAll()
                .anyRequest().authenticated()
            )
            .formLogin(form -> form
                .loginPage(LOGIN_URL)
                .loginProcessingUrl(LOGIN_URL)
                .defaultSuccessUrl(INICIO_URL, true)
                .failureUrl("/login?error=true")
            )
            .logout(logout -> logout
                .logoutUrl("/logout")
                .logoutSuccessUrl(INICIO_URL)
            )
            .headers(headers -> headers
                .contentSecurityPolicy(csp -> csp
                    .policyDirectives("default-src 'self'; " +
                                      "script-src 'self' https://cdn.jsdelivr.net https://cdnjs.cloudflare.com; " +
                                      "style-src 'self' https://cdn.jsdelivr.net https://cdnjs.cloudflare.com https://fonts.googleapis.com; " +
                                      "img-src 'self' https://cdn.jsdelivr.net https://cdnjs.cloudflare.com https://www.google.com https://lh3.googleusercontent.com https://www.pexels.com; " +
                                      "font-src 'self' https://fonts.gstatic.com; " +
                                      "object-src 'none'; " +
                                      "base-uri 'self'; " +
                                      "frame-ancestors 'none'; " +
                                      "form-action 'self';")
                )
                .frameOptions(frame -> frame.disable()) // mejor desactivarlo si te da warning con deny()
                .httpStrictTransportSecurity(hsts -> hsts
                    .includeSubDomains(true)
                    .maxAgeInSeconds(31536000))
            );
    
        return http.build();
    }
    

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();  
    }

}
