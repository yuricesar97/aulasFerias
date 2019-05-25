package com.yuri.mypet.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.yuri.mypet.domain.Categoria;

@Repository
public interface CategoriasRepository extends JpaRepository<Categoria, Integer>{
  
}  
