package com.teachmeskills.gym_website.config;


import com.teachmeskills.gym_website.service.MyUserDetailsService;
import jakarta.servlet.DispatcherType;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractAuthenticationFilterConfigurer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    @Bean
    public UserDetailsService userDetailsService() {
        return new MyUserDetailsService();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        return http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> auth
                         .requestMatchers("/").hasRole("TRAINER")
                        .requestMatchers("/").hasRole("ADMIN")
                        .requestMatchers("/").hasRole("CLIENT")
                        .requestMatchers("/main-sing-in").authenticated()
                        .requestMatchers("/main-sing-up","/main-welcome").permitAll()
                        .dispatcherTypeMatchers(DispatcherType.FORWARD).permitAll())

                .formLogin(AbstractAuthenticationFilterConfigurer::permitAll)
                //       .formLogin(formLogin -> formLogin
//                        .loginPage("/profi-user-login")
////                        .loginProcessingUrl("/profi-user-login")
//                        .defaultSuccessUrl("/profi-registration").permitAll())
//                        //.successForwardUrl("/profi-registration")
//
//                        .permitAll()
//                )
                .logout(logout -> logout
                        .logoutSuccessUrl("/main-welcome")
                        .permitAll()
                )
                .build();
    }


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .sessionManagement(session -> session
                        .maximumSessions(1)
                );
        return http.build();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService());
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }

    @Bean
    public static PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
