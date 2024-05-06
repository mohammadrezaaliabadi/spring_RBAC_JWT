package com.pureamorous.spring_rbac_jwt.configuration;

import com.pureamorous.spring_rbac_jwt.controller.customization.JwtAuthenticationFilter;
import com.pureamorous.spring_rbac_jwt.controller.customization.MyAccessDeniedHandler;
import com.pureamorous.spring_rbac_jwt.controller.customization.MyAuthenticationEntryPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.ExceptionTranslationFilter;

@Configuration
@EnableMethodSecurity
public class SecurityConfig {

    @Autowired
    private JwtAuthenticationFilter  jwtAuthenticationFilter;

    @Autowired
    private MyAccessDeniedHandler myAccessDeniedHandler;

    @Autowired
    private MyAuthenticationEntryPoint authenticationEntryPoint;


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http.logout(AbstractHttpConfigurer::disable);
        http.csrf(AbstractHttpConfigurer::disable); // todo enable it !!!!!!!!!!!!!!!!!!!
        http.sessionManagement(AbstractHttpConfigurer::disable);
        http.requestCache(AbstractHttpConfigurer::disable);
        http.addFilterAfter(jwtAuthenticationFilter, ExceptionTranslationFilter.class);

        http.authorizeHttpRequests(matcherRegistry->{
            matcherRegistry.requestMatchers("api/auth").permitAll();
            matcherRegistry.anyRequest().authenticated();
        });

        http.exceptionHandling(exceptionHandlingConfigurer ->{
            exceptionHandlingConfigurer.accessDeniedHandler(myAccessDeniedHandler);
            exceptionHandlingConfigurer.authenticationEntryPoint(authenticationEntryPoint);
        });
        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(UserDetailsService userDetailsService){
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        daoAuthenticationProvider.setUserDetailsService(userDetailsService);

        return new ProviderManager(daoAuthenticationProvider);
    }

    @Bean
    public FilterRegistrationBean<JwtAuthenticationFilter> disableFilterAutoRegistration(JwtAuthenticationFilter filter) {
        var registration = new FilterRegistrationBean<>(filter);
        registration.setEnabled(false);
        return registration;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
