package ru.testtask.taskuser.config.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import ru.testtask.taskuser.config.security.jwt.JwtFilter;
import ru.testtask.taskuser.config.security.jwt.RoleCode;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    final JwtFilter jwtFilter;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.httpBasic().disable()
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers("/api/users/**").hasRole(RoleCode.ROLE_USER.getRoleWithoutPrefix())
                .antMatchers("/api/phones/**").hasRole(RoleCode.ROLE_USER.getRoleWithoutPrefix())
                .antMatchers("/api/profiles/**").hasRole(RoleCode.ROLE_USER.getRoleWithoutPrefix())
                .antMatchers("/api/admin/**").hasRole(RoleCode.ROLE_ADMIN.getRoleWithoutPrefix())
                .antMatchers("/api/security/auth/**").permitAll()
                .and()
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
