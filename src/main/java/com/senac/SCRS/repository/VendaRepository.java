package com.senac.SCRS.repository;

import com.senac.SCRS.model.RegistroVenda;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VendaRepository extends JpaRepository<RegistroVenda, Integer> {
    
    List<RegistroVenda> findAllByOrderByDataVendaDesc();
}
