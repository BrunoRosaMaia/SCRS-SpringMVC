package com.senac.SCRS.controller;

import com.senac.SCRS.service.UsuarioService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {
    
    @Autowired
    private UsuarioService usuarioService;
    
    @GetMapping("/")
    public String inicio(){
        return "index";
    }
    
    @GetMapping("/login")
    public String paginaLogin(){
        return "login";
    }
    
    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String senha, HttpSession session, Model model){
        var usuario = usuarioService.autenticar(username, senha);
        
        if(usuario.isPresent()){
            session.setAttribute("usuarioLogado", usuario.get());
            return "redirect:/menu";
        }
        else{
            model.addAttribute("erro", "Usuário ou senha inválidos.");
            return "login";
        }
    }
    
    @GetMapping("/logout")
    public String logout(HttpSession session){
        session.invalidate();
        return "redirect:/";
    }
    
    @GetMapping("/menu")
    public String menu(HttpSession session){
        if(session.getAttribute("usuarioLogado") == null){
            return "redirect:/login";
        }
        
        return "menu";
    }
}
