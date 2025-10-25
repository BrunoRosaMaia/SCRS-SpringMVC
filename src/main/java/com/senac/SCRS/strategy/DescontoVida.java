package com.senac.SCRS.strategy;

import org.springframework.stereotype.Component;

@Component
public class DescontoVida implements DescontoStrategy {
    @Override
    public Double aplicarDesconto(Double valorOriginal){
        return valorOriginal * 0.85;
    }
}
