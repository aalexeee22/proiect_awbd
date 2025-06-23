package com.awbd.musicshop.config;

import com.awbd.musicshop.services.security.JpaUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
public class SecurityJpaConfig {

    private final JpaUserDetailsService userDetailsService;

    public SecurityJpaConfig(JpaUserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
                // Disable CSRF for H2 console only
                .csrf(csrf -> csrf.disable())

                // Allow H2 console to be loaded in iframe
                .headers(headers -> headers.frameOptions(frame -> frame.disable()))

                .authorizeHttpRequests(auth -> auth
                        // Allow access to static resources
                        .requestMatchers(new AntPathRequestMatcher("/webjars/**")).permitAll()
                        .requestMatchers(new AntPathRequestMatcher("/css/**")).permitAll()
                        .requestMatchers(new AntPathRequestMatcher("/js/**")).permitAll()
                        .requestMatchers(new AntPathRequestMatcher("/images/**")).permitAll()
                        .requestMatchers(new AntPathRequestMatcher("/static/**")).permitAll()
                        .requestMatchers(new AntPathRequestMatcher("/resources/**")).permitAll()

                        // Allow access to H2 console
                        .requestMatchers(new AntPathRequestMatcher("/h2-console/**")).permitAll()

                        // Allow public pages
                        .requestMatchers(new AntPathRequestMatcher("/")).permitAll()
                        .requestMatchers(new AntPathRequestMatcher("/login")).permitAll()
                        .requestMatchers(new AntPathRequestMatcher("/register")).permitAll()
                        .requestMatchers(new AntPathRequestMatcher("/auth/register")).permitAll()

                        // Role-based access
                        .requestMatchers(new AntPathRequestMatcher("/products/form")).hasRole("ADMIN")
                        .requestMatchers(new AntPathRequestMatcher("/products/**")).hasAnyRole("ADMIN", "GUEST")
                        .requestMatchers(new AntPathRequestMatcher("/categories/**")).hasAnyRole("ADMIN", "GUEST")

                        // All other requests need authentication
                        .anyRequest().authenticated()
                )
                .userDetailsService(userDetailsService)
                .formLogin(formLogin -> formLogin
                        .loginPage("/login")
                        .permitAll()
                        .loginProcessingUrl("/perform_login")
                        .defaultSuccessUrl("/products", true)
                )
                .logout(logout -> logout.permitAll())
                .exceptionHandling(ex -> ex.accessDeniedPage("/access_denied"))
                .httpBasic(Customizer.withDefaults());

        return http.build();
    }
}
