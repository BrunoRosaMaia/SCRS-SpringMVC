package com.senac.SCRS.service;

import com.senac.SCRS.model.Usuario;
import com.senac.SCRS.repository.UsuarioRepository;
import java.util.Optional;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class UsuarioServiceTest {
    
    @Mock
    private UsuarioRepository usuarioRepository;
    
    @InjectMocks
    private UsuarioService usuarioService;
    
    private Usuario usuario;
    
    @BeforeEach
    void setup(){
        usuario = new Usuario();
        usuario.setUsername("gerente1");
        usuario.setSenha("1234");
        usuario.setTipoUsuario("Gerente");
    }
    
    @Test
    void testSalvarUsuario(){
        
        when(usuarioRepository.save(any(Usuario.class))).thenReturn(usuario);
        
        Usuario salvo = usuarioService.salvar(usuario);
        
        assertThat(salvo.getUsername()).isEqualTo("gerente1");
        verify(usuarioRepository, times(1)).save(any(Usuario.class));
    }
    
    @Test
    void testVerificarExistenciaUsuario(){
        
        when(usuarioRepository.findByUsername("admin")).thenReturn(Optional.of(usuario));
        
        boolean existe = usuarioService.existeUsuario("admin");
        
        assertThat(existe).isTrue();
        verify(usuarioRepository).findByUsername("admin");
    }
}
