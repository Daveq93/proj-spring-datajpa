package com.example.projspringdatajpa.security;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.firewall.HttpFirewall;
import org.springframework.security.web.firewall.StrictHttpFirewall;


@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
   protected void configure(HttpSecurity http) throws Exception{
        http.authorizeRequests()
                .antMatchers("/listar").permitAll()
                .antMatchers("/guardar").hasRole("ADMIN")//se puede adicionar /** para permitir todos los que estarian dependiendo de /guardar
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .and()
                .httpBasic();
       // return http.build();
//        http.csrf()
//                .disable()
//                .authorizeHttpRequests()
//                .requestMatchers(HttpMethod.DELETE)
//                .hasRole("ADMIN")
//                .requestMatchers("/admin/**")
//                .hasAnyRole("ADMIN")
//                .requestMatchers("/user/**")
//                .hasAnyRole("USER", "ADMIN")
//                .requestMatchers("/login/**")
//                .anonymous()
//                .anyRequest()
//                .authenticated()
//                .and()
//                .httpBasic()
//                .and()
//                .sessionManagement()
//                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
//        return http.build();
    }

    public HttpFirewall looseHttpFirewal(){
        StrictHttpFirewall sf= new StrictHttpFirewall();

        sf.setAllowBackSlash(true);
        sf.setAllowSemicolon(true);

        return sf;
    }


@Override
    protected void configure(AuthenticationManagerBuilder auth)throws Exception{
auth
        .inMemoryAuthentication()
        .passwordEncoder(passwordEncoder())
        .withUser("user").password(passwordEncoder().encode("password")).roles("User")
        .and()
        .withUser("admin")
        .password(passwordEncoder().encode("password")).roles("USER","ADMIN");
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }









}
