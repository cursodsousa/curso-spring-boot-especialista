package io.github.cursodsousa.sbootexpsecurity.config;

import io.github.cursodsousa.sbootexpsecurity.domain.security.CustomAuthentication;
import io.github.cursodsousa.sbootexpsecurity.domain.security.IdentificacaoUsuario;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class SenhaMasterAuthenticationProvider implements AuthenticationProvider {
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        var login = authentication.getName();
        var senha = (String) authentication.getCredentials();

        String loginMaster = "master";
        String senhaMaster = "@321";

        if(loginMaster.equals(login) && senhaMaster.equals(senha)){
            IdentificacaoUsuario identificacaoUsuario = new IdentificacaoUsuario();
            identificacaoUsuario.setNome("Usuario Administrador");
            identificacaoUsuario.setLogin("master");
            identificacaoUsuario.setPermissoes(Arrays.asList("ADMIN"));

            return new CustomAuthentication(identificacaoUsuario);
        }

        return null;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return true;
    }
}
