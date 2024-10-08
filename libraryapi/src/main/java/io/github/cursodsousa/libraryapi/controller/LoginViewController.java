package io.github.cursodsousa.libraryapi.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class LoginViewController {

    @GetMapping("/login")
    public String paginaLogin(){
        return "login";
    }

    @GetMapping("/")
    @ResponseBody
    public String paginaInicial(Authentication auth){
        String identificacao = auth.getName();
//        if(auth instanceof OAuth2AuthenticationToken oAuth2AuthenticationToken){
//            DefaultOidcUser user = (DefaultOidcUser) oAuth2AuthenticationToken.getPrincipal();
//            identificacao = "Nome: " +  user.getAttribute("name") + ", Email: " + user.getEmail();
//        }
        return "Você está logado como: " + identificacao + " roles: " + auth.getAuthorities();
    }
}
