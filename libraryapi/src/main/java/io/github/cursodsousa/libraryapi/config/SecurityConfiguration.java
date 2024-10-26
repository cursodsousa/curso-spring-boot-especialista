package io.github.cursodsousa.libraryapi.config;

import io.github.cursodsousa.libraryapi.security.CustomUserDetailsService;
import io.github.cursodsousa.libraryapi.security.JwtCustomAuthenticationFilter;
import io.github.cursodsousa.libraryapi.security.LoginSocialSuccessHandler;
import io.github.cursodsousa.libraryapi.service.UsuarioService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.core.GrantedAuthorityDefaults;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.security.oauth2.server.resource.web.authentication.BearerTokenAuthenticationFilter;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(securedEnabled = true, jsr250Enabled = true)
public class SecurityConfiguration {

    @Bean
    public SecurityFilterChain securityFilterChain(
            HttpSecurity http,
            LoginSocialSuccessHandler successHandler,
            JwtCustomAuthenticationFilter jwtCustomAuthenticationFilter) throws Exception {
        return http
                .csrf(AbstractHttpConfigurer::disable)
                .formLogin(configurer -> {
                    configurer.loginPage("/login");
                })
                .authorizeHttpRequests(authorize -> {
                    authorize.requestMatchers("/login/**").permitAll();
                    authorize.requestMatchers(HttpMethod.POST, "/usuarios/**").permitAll();

                    authorize.anyRequest().authenticated();
                })
                .oauth2Login(oauth2 -> {
                    oauth2
                        .loginPage("/login")
                        .successHandler(successHandler);
                })
                .oauth2ResourceServer(oauth2RS -> oauth2RS.jwt(Customizer.withDefaults()))
                .addFilterAfter(jwtCustomAuthenticationFilter, BearerTokenAuthenticationFilter.class)
                .build();
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer(){
        return web -> web.ignoring().requestMatchers(
                    "/v2/api-docs/**",
                    "/v3/api-docs/**",
                    "/swagger-resources/**",
                    "/swagger-ui.html",
                    "/swagger-ui/**",
                    "/webjars/**",
                    "/actuator/**"
            );
    }

    // CONFIGURA O PREFIXO ROLE
    @Bean
    public GrantedAuthorityDefaults grantedAuthorityDefaults(){
        return new GrantedAuthorityDefaults("");
    }

    // CONFIGURA, NO TOKEN JWT, O PREFIXO SCOPE
    @Bean
    public JwtAuthenticationConverter jwtAuthenticationConverter(){
        var authoritiesConverter = new JwtGrantedAuthoritiesConverter();
        authoritiesConverter.setAuthorityPrefix("");

        var converter = new JwtAuthenticationConverter();
        converter.setJwtGrantedAuthoritiesConverter(authoritiesConverter);

        return converter;
    }
}
