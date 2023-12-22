package io.github.cursodsousa.sbootexpsecurity.domain.repository;

import io.github.cursodsousa.sbootexpsecurity.domain.entity.Usuario;
import io.github.cursodsousa.sbootexpsecurity.domain.entity.UsuarioGrupo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UsuarioGrupoRepository extends JpaRepository<UsuarioGrupo, String> {

    @Query("""
            
            select distinct g.nome
            from UsuarioGrupo ug
            join ug.grupo g
            join ug.usuario u
            where u = ?1
            
    """)
    List<String> findPermissoesByUsuario(Usuario usuario);
}
