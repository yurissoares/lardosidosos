package com.ufrb.lardosidosos.doctransfer.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ufrb.lardosidosos.doctransfer.model.Arquivo;

@Repository
public interface IArquivoRepository extends JpaRepository<Arquivo, Long> {
	List<Arquivo> findByDocumentoMoradorId(Long id);
	List<Arquivo> findAllByDocumentoMoradorId(Long id);
	List<Arquivo> findByDocumentoRegistroSaudeId(Long id);
	List<Arquivo> findAllByDocumentoRegistroSaudeId(Long id);
}
