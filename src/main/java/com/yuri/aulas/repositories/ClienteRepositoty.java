package com.yuri.aulas.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.yuri.aulas.domain.Cliente;

@Repository
public interface ClienteRepositoty extends JpaRepository<Cliente, Integer>{
  
}  
