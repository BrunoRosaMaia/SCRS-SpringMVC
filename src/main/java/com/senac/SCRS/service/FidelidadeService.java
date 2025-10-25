package com.senac.SCRS.service;

import com.senac.SCRS.strategy.DescontoCarro;
import com.senac.SCRS.strategy.DescontoCasa;
import com.senac.SCRS.strategy.DescontoStrategy;
import com.senac.SCRS.strategy.DescontoVida;
import com.senac.SCRS.strategy.SemDesconto;
import java.util.HashMap;
import java.util.Map;
import org.springframework.stereotype.Service;

@Service
public class FidelidadeService {
    
    private final Map<String, DescontoStrategy> estrategiasDesconto;
    
    public FidelidadeService(){
        this.estrategiasDesconto = new HashMap<>();
        estrategiasDesconto.put("CARRO", new DescontoCarro());
        estrategiasDesconto.put("CASA", new DescontoCasa());
        estrategiasDesconto.put("VIDA", new DescontoVida());
    }
    
    public Double calcularValor(String tipoSeguro, Double valorOriginal, Boolean fiel){
        if(!fiel) return valorOriginal;
        
        DescontoStrategy estrategia = estrategiasDesconto.getOrDefault(
                tipoSeguro.toUpperCase(), new SemDesconto());
        return estrategia.aplicarDesconto(valorOriginal);
    }
}
