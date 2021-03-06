package com.ufrb.lardosidosos.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ufrb.lardosidosos.entity.Contato;

@Repository
public interface IContatoRepository extends JpaRepository<Contato, Long> {
	List<Contato> findAllByMoradorId(Long id);
}
