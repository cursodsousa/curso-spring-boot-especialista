package io.github.cursodsousa.libraryapi.controller.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;
import java.util.UUID;

@Schema(name = "Autor")
public record AutorDTO(
        UUID id,
        @Schema(name = "nome", description = "nome do autor", nullable = false)
        @NotBlank(message = "campo obrigatorio")
        @Size(min = 2, max = 100, message = "campo fora do tamanho padrao")
        String nome,
        @Schema(name = "dataNascimento", description = "data de nascimento do autor", nullable = false)
        @NotNull(message = "campo obrigatorio")
        @Past(message = "nao pode ser uma data futura")
        LocalDate dataNascimento,
        @NotBlank(message = "campo obrigatorio")
        @Size(max = 50, min = 2, message = "campo fora do tamanho padrao")
        String nacionalidade
) {
}
