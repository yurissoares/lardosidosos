package com.ufrb.lardosidosos.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ufrb.lardosidosos.entity.DocumentoMorador;

@Repository
public interface IDocumentoMoradorRepository extends JpaRepository<DocumentoMorador, Long> {
	List<DocumentoMorador> findByMoradorId(Long id);
}
