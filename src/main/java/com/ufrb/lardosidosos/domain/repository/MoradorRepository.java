package com.ufrb.lardosidosos.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ufrb.lardosidosos.domain.model.Morador;

@Repository
public interface MoradorRepository extends JpaRepository<Morador, Long> {

	Morador findByNome(String nome);
}
