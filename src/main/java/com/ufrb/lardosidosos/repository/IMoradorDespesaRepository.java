package com.ufrb.lardosidosos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ufrb.lardosidosos.entity.MoradorDespesa;

@Repository
public interface IMoradorDespesaRepository extends JpaRepository<MoradorDespesa, Long> {

}
