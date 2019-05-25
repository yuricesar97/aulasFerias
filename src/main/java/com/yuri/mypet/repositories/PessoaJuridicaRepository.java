package com.yuri.mypet.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.yuri.mypet.domain.PessoaJuridica;

@Repository
public interface PessoaJuridicaRepository extends JpaRepository<PessoaJuridica, Integer>{
  
     @Transactional(readOnly = true) // diz que transação não necessita ser envolvida no banco de dados
     PessoaJuridica findByEmail(String email); // cria busca com o campo Email
}  
