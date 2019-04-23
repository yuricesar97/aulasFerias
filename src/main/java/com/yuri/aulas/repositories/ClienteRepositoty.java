package com.yuri.aulas.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.yuri.aulas.domain.Cliente;

@Repository
public interface ClienteRepositoty extends JpaRepository<Cliente, Integer>{
  
     @Transactional(readOnly = true) // diz que transação não necessita ser envolvida no banco de dados
	Cliente findByEmail(String email); // cria busca com o campo Email
}  
