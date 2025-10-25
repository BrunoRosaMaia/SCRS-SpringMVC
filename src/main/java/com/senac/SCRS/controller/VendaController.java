package com.senac.SCRS.controller;

import com.senac.SCRS.dto.RegistroDTO;
import com.senac.SCRS.model.RegistroVenda;
import com.senac.SCRS.model.Usuario;
import com.senac.SCRS.repository.VendaRepository;
import com.senac.SCRS.service.FidelidadeService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/vendas")
public class VendaController {
    
    @Autowired
    private VendaRepository vendaRepository;
    
    @Autowired
    private FidelidadeService fidelidadeService;
    
    @GetMapping("/registro")
    public String paginaRegistro(HttpSession session, Model model){
        if(session.getAttribute("usuarioLogado") == null){
            return "redirect:/login";
        }
        
        model.addAttribute("venda", new RegistroDTO());
        return "registro";
    }
    
    @PostMapping("/registro")
    public String registrarOuEditarVenda(@Valid @ModelAttribute("venda") RegistroDTO dto, BindingResult result, Model model){
        if(result.hasErrors()){
            return "registro";
        }
        
        if(!dto.getCpfCliente().matches("\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}")){
            model.addAttribute("erro", "CPF deve estar no formato 000.000.000-00");
            return "registro";
        }
        
        Double valorFinal = fidelidadeService.calcularValor(dto.getTipoSeguro(), dto.getValorApolice(), dto.getFiel());
        RegistroVenda venda = dtoParaEntidade(dto);
        venda.setValorApolice(valorFinal);
        
        try{
            vendaRepository.save(venda);
            model.addAttribute("mensagem", "Venda registrada com sucesso!");
        }
        catch(Exception e){
            e.printStackTrace();
            model.addAttribute("erro", "Erro ao registrar venda.");
            return "registro";
        }
        
        
        return "redirect:/vendas/lista?sucesso=registrado";
    }
    
    @GetMapping("/lista")
    public String listaVendas(HttpSession session, Model model){
        if(session.getAttribute("usuarioLogado") == null){
            return "redirect:/login";
        }
        
        List<RegistroVenda> vendas = vendaRepository.findAllByOrderByDataVendaDesc();
        model.addAttribute("vendas", vendas);
        return "lista";
    }
    
    @GetMapping("/editar/{id}")
    public String editarVenda(@PathVariable Integer id, HttpSession session, Model model){
        var usuario = (Usuario) session.getAttribute("usuarioLogado");
        if(usuario == null || !"Gerente".equalsIgnoreCase(usuario.getTipoUsuario())){
            return "redirect:/menu";
        }
        
        var vendaOpt = vendaRepository.findById(id);
        if(vendaOpt.isPresent()){
            RegistroVenda v = vendaOpt.get();
            RegistroDTO dto = entidadeParaDTO(v);
            model.addAttribute("venda", dto);
            return "registro";
        }
        
        return "redirect:/vendas/lista";
    }
    
    @GetMapping("/excluir/{id}")
    public String excluirVenda(@PathVariable Integer id, HttpSession session){
        var usuario = (Usuario) session.getAttribute("usuarioLogado");
        if(usuario == null || !"Gerente".equalsIgnoreCase(usuario.getTipoUsuario())){
            return "redirect:/menu";
        }
        
        if(vendaRepository.existsById(id)){
            vendaRepository.deleteById(id);
        }
        
        return "redirect:/vendas/lista";
    }
    
    private RegistroDTO entidadeParaDTO(RegistroVenda v){
        RegistroDTO dto = new RegistroDTO();
        dto.setId(v.getId());
        dto.setNomeCliente(v.getNomeCliente());
        dto.setCpfCliente(v.getCpfCliente());
        dto.setTipoSeguro(v.getTipoSeguro());
        dto.setDataVenda(v.getDataVenda());
        dto.setValorApolice(v.getValorApolice());
        dto.setFiel(v.getFiel());
        return dto;
    }
    
    private RegistroVenda dtoParaEntidade(RegistroDTO dto){
        RegistroVenda v = new RegistroVenda();
        v.setId(dto.getId());
        v.setNomeCliente(dto.getNomeCliente());
        v.setCpfCliente(dto.getCpfCliente());
        v.setTipoSeguro(dto.getTipoSeguro());
        v.setDataVenda(dto.getDataVenda());
        v.setValorApolice(dto.getValorApolice());
        v.setFiel(dto.getFiel());
        return v;
    }
}
