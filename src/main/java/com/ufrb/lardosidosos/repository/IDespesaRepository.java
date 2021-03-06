package com.ufrb.lardosidosos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ufrb.lardosidosos.entity.Despesa;

@Repository
public interface IDespesaRepository extends JpaRepository<Despesa, Long> {

}
