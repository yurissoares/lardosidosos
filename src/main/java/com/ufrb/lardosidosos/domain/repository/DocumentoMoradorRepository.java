package com.ufrb.lardosidosos.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ufrb.lardosidosos.domain.model.DocumentoMorador;

@Repository
public interface DocumentoMoradorRepository extends JpaRepository<DocumentoMorador, Long> {
	List<DocumentoMorador> findByMoradorId(Long id);
}
