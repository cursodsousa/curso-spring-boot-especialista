package io.github.cursodsousa.sbootexpsecurity.api;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rh")
public class RhController {

    @GetMapping("/tecnico")
    @PreAuthorize("hasAnyRole('ADMIN', 'GERENTE_RH', 'TECNICO_RH')")
    public String tecnico(){
        return "Pagina do tecnico";
    }

    @GetMapping("/gerente")
    @PreAuthorize("hasAnyRole('ADMIN', 'GERENTE_RH')")
    public String gerente(){
        return "Pagina do gerente";
    }
}
