package com.senac.SCRS.repository;

import com.senac.SCRS.model.Usuario;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
    
    @Query(value = "SELECT * FROM usuario WHERE BINARY username = :username AND senha = :senha", nativeQuery = true)
    Optional<Usuario> findByUsernameAndSenha(@Param("username") String username, @Param("senha") String senha);
    
    Optional<Usuario> findByUsername(String username);
}
