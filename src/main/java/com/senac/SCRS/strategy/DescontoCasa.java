package com.senac.SCRS.strategy;

import org.springframework.stereotype.Component;

@Component
public class DescontoCasa implements DescontoStrategy {
    @Override
    public Double aplicarDesconto(Double valorOriginal){
        return valorOriginal * 0.90;
    }
}
