package com.senac.SCRS.service;

import com.senac.SCRS.model.Usuario;
import com.senac.SCRS.repository.UsuarioRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {
    
    @Autowired
    private UsuarioRepository usuarioRepository;
    
    public Optional<Usuario> autenticar(String username, String senha){
        return usuarioRepository.findByUsernameAndSenha(username, senha);
    }
    
    public Usuario salvar(Usuario usuario){
        return usuarioRepository.save(usuario);
    }
    
    public boolean existeUsuario(String username){
        return usuarioRepository.findByUsername(username).isPresent();
    }
    
    public List<Usuario> listarTodos(){
        return usuarioRepository.findAll();
    }
    
    public void excluir(Integer id){
        usuarioRepository.deleteById(id);
    }
}
