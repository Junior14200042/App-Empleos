package com.devjr.app_empleo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class DataWebSecurity {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{


        httpSecurity
                .authorizeHttpRequests(auth->{

                    auth.requestMatchers("/bootstrap/**","/images/**","/tinymce/**","/logos/**", "/bcrypt/**").permitAll();
                    auth.requestMatchers("/", "/signup", "/search" ,"/vacantes/view/**").permitAll();

                    auth.requestMatchers("/vacantes/**").hasAnyAuthority("SUPERVISOR","ADMINISTRADOR");
                    auth.requestMatchers("/solicitudes/create/**","/solicitudes/save/**").hasAnyAuthority("USUARIO");
                    auth.requestMatchers("/solicitudes/**").hasAnyAuthority("SUPERVISOR","ADMINISTRADOR");
                    auth.requestMatchers("/categorias/**").hasAnyAuthority("SUPERVISOR","ADMINISTRADOR");
                    auth.requestMatchers("/usuarios/**").hasAnyAuthority("ADMINISTRADOR");


                    auth.anyRequest().authenticated();
                })
                .formLogin(p->p.defaultSuccessUrl("/index").loginPage("/login").permitAll())
                .csrf(c->c.disable());


        return  httpSecurity.build();
    }



    @Bean
    UserDetailsManager users(DataSource dataSource){
        JdbcUserDetailsManager users = new JdbcUserDetailsManager((dataSource));

        users.setUsersByUsernameQuery("select username,password, estatus from usuarios u where username=?");

        users.setAuthoritiesByUsernameQuery(
                "SELECT u.username, p.perfil " +
                        "FROM usuarioperfil up " +
                        "INNER JOIN usuarios u ON u.id = up.idUsuario " +
                        "INNER JOIN perfiles p ON p.id = up.idPerfil " +
                        "WHERE u.username = ?"
        );

        return users;
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
