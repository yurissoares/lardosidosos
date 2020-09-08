package com.ufrb.lardosidosos.domain.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ufrb.lardosidosos.domain.model.FichaAdmissao;

@Repository
public interface IFichaAdmissaoRepository extends JpaRepository<FichaAdmissao, Long> {
	Optional<FichaAdmissao> findByMoradorId(Long id);
}
