package com.yuri.aulas.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.yuri.aulas.domain.Estado;

@Repository
public interface EstadoRepositoty extends JpaRepository<Estado, Integer>{
  
}  
