package com.senac.SCRS.strategy;

import org.springframework.stereotype.Component;

@Component
public class DescontoCarro implements DescontoStrategy {
    @Override
    public Double aplicarDesconto(Double valorOriginal){
        return valorOriginal * 0.95;
    }
}
