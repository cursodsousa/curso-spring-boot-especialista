package io.github.cursodsousa.arquiteturaspring;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ExemploValue {

    @Value("${app.config.variavel}")
    private String variavel;

    public void imprimirVarivel(){
        System.out.println(variavel);
    }
}
