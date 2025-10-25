package com.senac.SCRS.service;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class FidelidadeServiceTest {
    
    @Test
    void testDescontoCarro(){
        FidelidadeService servico = new FidelidadeService();
        double valorDesconto = servico.calcularValor("Carro", 1000.0, true);
        assertEquals(950.0, valorDesconto, 0.001);
    }
    
    @Test
    void testDescontoCasa(){
        FidelidadeService servico = new FidelidadeService();
        double valorDesconto = servico.calcularValor("Casa", 1000.0, true);
        assertEquals(900.0, valorDesconto, 0.001);
    }
    
    @Test
    void testDescontoVida(){
        FidelidadeService servico = new FidelidadeService();
        double valorDesconto = servico.calcularValor("Vida", 1000.0, true);
        assertEquals(850.0, valorDesconto, 0.001);
    }
    
    @Test
    void testSemDesconto(){
        FidelidadeService servico = new FidelidadeService();
        double valorDesconto = servico.calcularValor("Carro", 1000.0, false);
        assertEquals(1000.0, valorDesconto, 0.001);
    }
}
