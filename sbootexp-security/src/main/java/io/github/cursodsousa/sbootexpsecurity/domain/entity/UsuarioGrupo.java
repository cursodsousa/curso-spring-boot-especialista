package io.github.cursodsousa.sbootexpsecurity.domain.entity;


import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class UsuarioGrupo {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "id_grupo")
    private Grupo grupo;

    public UsuarioGrupo(Usuario usuario, Grupo grupo) {
        this.usuario = usuario;
        this.grupo = grupo;
    }
}
