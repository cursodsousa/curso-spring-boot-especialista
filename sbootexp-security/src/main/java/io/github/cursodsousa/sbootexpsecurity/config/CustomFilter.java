package io.github.cursodsousa.sbootexpsecurity.config;

import io.github.cursodsousa.sbootexpsecurity.domain.security.CustomAuthentication;
import io.github.cursodsousa.sbootexpsecurity.domain.security.IdentificacaoUsuario;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Component
public class CustomFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain) throws ServletException, IOException {

        String secretHeader = request.getHeader("x-secret");

        if(secretHeader != null){
            if(secretHeader.equals("secr3t")){
                IdentificacaoUsuario ide = new IdentificacaoUsuario();
                ide.setNome("Usuario Secreto");
                ide.setLogin("secret");
                ide.setPermissoes(Arrays.asList("USER"));
                Authentication authentication = new CustomAuthentication(ide);

                SecurityContext securityContext = SecurityContextHolder.getContext();
                securityContext.setAuthentication(authentication);
            }
        }

        filterChain.doFilter(request, response);
    }
}
