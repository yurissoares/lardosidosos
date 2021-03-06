package com.ufrb.lardosidosos.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ufrb.lardosidosos.entity.Morador;

@Repository
public interface IMoradorRepository extends JpaRepository<Morador, Long> {
	List<Morador> findByNomeContainingOrderByNomeAsc(String nome);
}
