package com.ufrb.lardosidosos.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ufrb.lardosidosos.entity.Ocorrencia;

@Repository
public interface IOcorrenciaRepository extends JpaRepository<Ocorrencia, Long> {
	List<Ocorrencia> findByMoradorIdOrderByDataDesc(Long id);
	
}
