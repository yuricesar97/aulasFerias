package com.yuri.mypet.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


import com.yuri.mypet.domain.PessoaFisica;

@Repository
public interface PessoaFisicaRepository extends JpaRepository<PessoaFisica, Integer>{
  
     @Transactional(readOnly = true) // diz que transação não necessita ser envolvida no banco de dados
     PessoaFisica  findByEmail(String email); // cria busca com o campo Email
}  
