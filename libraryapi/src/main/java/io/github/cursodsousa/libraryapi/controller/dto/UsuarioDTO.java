package io.github.cursodsousa.libraryapi.controller.dto;

import jakarta.validation.constraints.NotBlank;

import java.util.List;

public record UsuarioDTO(
        @NotBlank(message = "campo obrigatorio")
        String login,
        @NotBlank(message = "campo obrigatorio")
        String senha,
        List<String> roles) {
}
