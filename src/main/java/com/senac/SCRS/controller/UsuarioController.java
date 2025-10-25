package com.senac.SCRS.controller;

import com.senac.SCRS.model.Usuario;
import com.senac.SCRS.service.UsuarioService;
import jakarta.servlet.http.HttpSession;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/usuarios")
public class UsuarioController {
    
    @Autowired
    private UsuarioService usuarioService;
    
    @GetMapping("/cadastro")
    public String paginaCadastro(Model model){
        model.addAttribute("usuario", new Usuario());
        return "cadastro";
    }
    
    @PostMapping("/cadastro")
    public String cadastrar(@ModelAttribute Usuario usuario, Model model){
        if(usuarioService.existeUsuario(usuario.getUsername())){
            model.addAttribute("erro", "Usuário já existe!");
            return "cadastro";
        }
        
        usuarioService.salvar(usuario);
        model.addAttribute("mensagem", "Usuário cadastrado com sucesso!");
        return "redirect:/";
    }
    
    @GetMapping("/lista")
    public String listarUsuarios(HttpSession session, Model model){
        Usuario logado = (Usuario) session.getAttribute("usuarioLogado");
        if(logado == null || !"GERENTE".equalsIgnoreCase(logado.getTipoUsuario())){
            return "redirect:/menu";
        }
        
        List<Usuario> usuarios = usuarioService.listarTodos();
        model.addAttribute("usuarios", usuarios);
        return "usuarios";
    }
    
    @GetMapping("/excluir/{id}")
    public String excluirUsuario(@PathVariable Integer id, HttpSession session){
        Usuario logado = (Usuario) session.getAttribute("usuarioLogado");
        if(logado == null || !"Gerente".equalsIgnoreCase(logado.getTipoUsuario())){
            return "redirect:/menu";
        }
        
        if(logado.getId().equals(id)){
            return "redirect:/usuarios/lista?erro=naoPodeExcluirProprioUsuario";
        }
        
        usuarioService.excluir(id);
        return "redirect:/usuarios/lista?sucesso=usuarioExcluido";
    }
}
