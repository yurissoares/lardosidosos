package com.ufrb.lardosidosos.doctransfer.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ufrb.lardosidosos.doctransfer.model.Arquivo;

@Repository
public interface ArquivoRepository extends JpaRepository<Arquivo, Long> {
	List<Arquivo> findByDocumentoId(Long id);
}
