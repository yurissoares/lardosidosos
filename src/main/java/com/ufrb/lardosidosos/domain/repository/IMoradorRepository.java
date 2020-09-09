package com.ufrb.lardosidosos.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ufrb.lardosidosos.domain.model.Morador;

@Repository
public interface IMoradorRepository extends JpaRepository<Morador, Long> {
	List<Morador> findByNomeContainingOrderByNomeAsc(String nome);
}