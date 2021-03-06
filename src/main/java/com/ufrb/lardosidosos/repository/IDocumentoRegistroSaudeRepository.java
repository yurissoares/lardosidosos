package com.ufrb.lardosidosos.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ufrb.lardosidosos.entity.DocumentoRegistroSaude;

@Repository
public interface IDocumentoRegistroSaudeRepository extends JpaRepository<DocumentoRegistroSaude, Long> {
	List<DocumentoRegistroSaude> findByRegistroSaudeId(Long regSaudeId);
	
}
