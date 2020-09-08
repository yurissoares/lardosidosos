package com.ufrb.lardosidosos.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ufrb.lardosidosos.domain.model.Contato;

@Repository
public interface IContatoRepository extends JpaRepository<Contato, Long> {
	List<Contato> findAllByMoradorId(Long id);
}
