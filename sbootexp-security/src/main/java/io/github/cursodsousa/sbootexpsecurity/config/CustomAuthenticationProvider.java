package io.github.cursodsousa.sbootexpsecurity.config;

import io.github.cursodsousa.sbootexpsecurity.domain.entity.Usuario;
import io.github.cursodsousa.sbootexpsecurity.domain.security.CustomAuthentication;
import io.github.cursodsousa.sbootexpsecurity.domain.security.IdentificacaoUsuario;
import io.github.cursodsousa.sbootexpsecurity.domain.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CustomAuthenticationProvider implements AuthenticationProvider {

    private final UsuarioService usuarioService;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String login = authentication.getName();
        Usuario usuario = usuarioService.obterPorLoginComPermissoes(login);
        if(usuario != null){
            String senha = (String) authentication.getCredentials();
            boolean senhasBatem = passwordEncoder.matches(senha, usuario.getSenha());
            if(senhasBatem){
                IdentificacaoUsuario identificacaoUsuario = new IdentificacaoUsuario(
                        usuario.getId(), usuario.getNome(), usuario.getPermissoes());
                return new CustomAuthentication(identificacaoUsuario);
            }
        }

        return null;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return true;
    }
}
