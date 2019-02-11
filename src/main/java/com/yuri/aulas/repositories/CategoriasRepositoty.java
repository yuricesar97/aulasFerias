package com.yuri.aulas.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.yuri.aulas.domain.Categoria;

@Repository
public interface CategoriasRepositoty extends JpaRepository<Categoria, Integer>{
  
}  
