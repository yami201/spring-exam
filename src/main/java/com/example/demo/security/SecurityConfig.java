 package com.example.demo.security;


 import jakarta.servlet.DispatcherType;
 import org.springframework.context.annotation.Bean;
 import org.springframework.context.annotation.Configuration;
 import org.springframework.http.HttpMethod;
 import org.springframework.security.config.Customizer;
 import org.springframework.security.config.annotation.web.builders.HttpSecurity;
 import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
 import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
 import org.springframework.security.core.userdetails.User;
 import org.springframework.security.core.userdetails.UserDetails;
 import org.springframework.security.provisioning.InMemoryUserDetailsManager;
 import org.springframework.security.web.SecurityFilterChain;

 @Configuration
 @EnableWebSecurity
 public class SecurityConfig {

     @Bean
     public SecurityFilterChain web(HttpSecurity http) throws Exception {
         http
                 .csrf(AbstractHttpConfigurer::disable)
                 .authorizeHttpRequests(
                         auth -> auth
                                 .dispatcherTypeMatchers(DispatcherType.FORWARD,DispatcherType.ERROR).permitAll()
                                 .requestMatchers(HttpMethod.GET).permitAll()
                                 .requestMatchers(HttpMethod.DELETE).hasRole("admin")
                                 .requestMatchers(HttpMethod.POST,"/ficheEmprunt/emprunter/**").hasRole("client")
                                 .requestMatchers(HttpMethod.POST).hasRole("admin")
                                 .requestMatchers(HttpMethod.PUT).hasRole("admin")
                                 .anyRequest().permitAll()
                 ).httpBasic(Customizer.withDefaults());

         return http.build();
     }

     @Bean
     public InMemoryUserDetailsManager users() {
         UserDetails client = User.withDefaultPasswordEncoder()
                 .username("client")
                 .password("client-pass")
                 .roles("client")
                 .build();

         UserDetails admin = User.withDefaultPasswordEncoder()
                 .username("admin")
                 .password("admin-pass")
                 .roles("admin","client")
                 .build();

         return new InMemoryUserDetailsManager(client,admin);
     }
 }
