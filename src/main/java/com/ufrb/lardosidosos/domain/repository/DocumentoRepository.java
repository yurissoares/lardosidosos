package com.ufrb.lardosidosos.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ufrb.lardosidosos.domain.model.Documento;

@Repository
public interface DocumentoRepository extends JpaRepository<Documento, Long> {
	List<Documento> findByMoradorId(Long id);
}
