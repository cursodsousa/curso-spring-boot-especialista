package io.github.cursodsousa.resourceserver;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RSController {

    @GetMapping("/public")
    public ResponseEntity<String> publicEndpoint(){
        return ResponseEntity.ok("PUBLIC ENDPOINT OK!");
    }

    @GetMapping("/private")
    public ResponseEntity<String> privateEndpoint(){
        return ResponseEntity.ok("PRIVATE ENDPOINT OK!");
    }
}
