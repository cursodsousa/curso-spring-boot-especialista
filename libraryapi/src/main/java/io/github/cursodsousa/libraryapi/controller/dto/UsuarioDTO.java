package io.github.cursodsousa.libraryapi.controller.dto;

import java.util.List;

public record UsuarioDTO(String login, String email, String senha, List<String> roles) {
}
