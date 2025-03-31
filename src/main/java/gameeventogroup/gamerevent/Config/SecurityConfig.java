package gameeventogroup.gamerevent.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import gameeventogroup.gamerevent.security.CustomAuthFailureHandler;
import gameeventogroup.gamerevent.security.LoginAttemptFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private LoginAttemptFilter loginAttemptFilter;

    @Autowired
    private CustomAuthFailureHandler customAuthFailureHandler;

    // @Bean
    // public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    //     http
    //         .authorizeRequests(authorize -> authorize
    //             .requestMatchers("/css/**", "/js/**", "/login", "/registro", "/registrar", "/inicio", "/").permitAll()  
    //             .anyRequest().authenticated()  
    //         )
    //         .formLogin(form -> form
    //             .loginPage("/login")  
    //             .loginProcessingUrl("/login")  
    //             .defaultSuccessUrl("/inicio", true) 
    //             .failureUrl("/login?error=true")  
    //         )
    //         .logout(logout -> logout
    //             .logoutUrl("/logout")  
    //             .logoutSuccessUrl("/inicio")  
    //         )
    //         .headers(headers -> headers
    //             .contentSecurityPolicy("default-src 'self'; " +
    //                                    "img-src 'self' https://cdn.jsdelivr.net https://cdnjs.cloudflare.com; https://www.google.com https://lh3.googleusercontent.com; https://www.pexels.com" +
    //                                    "script-src 'self' https://cdn.jsdelivr.net https://cdnjs.cloudflare.com; " +
    //                                    "style-src 'self' https://cdn.jsdelivr.net https://cdnjs.cloudflare.com https://fonts.googleapis.com; " +
    //                                    "font-src 'self' https://fonts.gstatic.com;")
    //         )
    //         .and()
    //         // Protección contra Clickjacking
    //         .frameOptions().deny()
    //         .and()
    //         // Protección XSS básica
    //         .xssProtection(xss -> xss.block(true))
    //         .and()
    //         // HSTS recomendado
    //         .httpStrictTransportSecurity(hsts -> hsts
    //             .includeSubDomains(true)
    //             .maxAgeInSeconds(31536000))
    //     );

    //     return http.build();
    // }
    // SecurityConfig.java

// SecurityConfig.java

@Bean
public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http
        .authorizeHttpRequests(authorize -> authorize
            .requestMatchers("/css/**", "/js/**", "/login", "/registro", "/registrar", "/inicio", "/").permitAll()
            .anyRequest().authenticated()
        )
        .formLogin(form -> form
            .loginPage("/login")
            .loginProcessingUrl("/login")
            .defaultSuccessUrl("/inicio", true)
            .failureUrl("/login?error=true")
        )
        .logout(logout -> logout
            .logoutUrl("/logout")
            .logoutSuccessUrl("/inicio")
        )
        .headers(headers -> headers
            .contentSecurityPolicy("default-src 'self'; " +
                                   "img-src 'self' https://cdn.jsdelivr.net https://cdnjs.cloudflare.com https://www.google.com https://lh3.googleusercontent.com https://www.pexels.com; " +
                                   "script-src 'self' https://cdn.jsdelivr.net https://cdnjs.cloudflare.com; " +
                                   "style-src 'self' https://cdn.jsdelivr.net https://cdnjs.cloudflare.com https://fonts.googleapis.com; " +
                                   "font-src 'self' https://fonts.gstatic.com;")
            .and()
            .frameOptions(frame -> frame.deny())
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

    

    // @Override
    // protected void configure(HttpSecurity http) throws Exception {
    //     http
    //         .addFilterBefore(loginAttemptFilter, UsernamePasswordAuthenticationFilter.class)
    //         .formLogin()
    //             .loginPage("/login")
    //             .failureHandler(customAuthFailureHandler)
    //         .and()
    //         .authorizeHttpRequests()
    //             .anyRequest().permitAll();
    // }

}
