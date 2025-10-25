package com.senac.SCRS.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegistroDTO {
    
    private Integer id;
    
    @NotBlank(message = "Nome do cliente é obrigatório.")
    private String nomeCliente;
    
    @NotBlank(message = "CPF é obrigatório.")
    @Pattern(regexp = "\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}", message = "CPF deve estar no formato 000.000.000-00")
    private String cpfCliente;
    
    @NotNull(message = "Data da venda é obrigatória.")
    private LocalDate dataVenda;
    
    @NotBlank(message = "Tipo de seguro é obrigatório.")
    private String tipoSeguro;
    
    @NotNull(message = "Valor é obrigatório.")
    @Min(value = 1, message = "Valor deve ser maior que zero.")
    private Double valorApolice;
    
    @NotNull(message = "Campo fiel é obrigatório.")
    private Boolean fiel;
}
