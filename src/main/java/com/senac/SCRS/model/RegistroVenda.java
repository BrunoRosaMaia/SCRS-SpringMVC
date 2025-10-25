package com.senac.SCRS.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "registro")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegistroVenda {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @Column(name = "nomeCliente", nullable = false)
    private String nomeCliente;
    
    @Column(name = "cpfCliente", nullable = false)
    private String cpfCliente;
    
    @Column(name = "tipoSeguro", nullable = false)
    private String tipoSeguro;
    
    @Column(name = "dataVenda", nullable = false)
    private LocalDate dataVenda;
    
    @Column(name = "valorApolice", nullable = false)
    private double valorApolice;
    
    @Column(name = "fiel", nullable = false)
    private Boolean fiel;
}
