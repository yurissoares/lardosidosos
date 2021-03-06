package com.ufrb.lardosidosos.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ufrb.lardosidosos.entity.FichaAdmissao;

@Repository
public interface IFichaAdmissaoRepository extends JpaRepository<FichaAdmissao, Long> {
	Optional<FichaAdmissao> findByMoradorId(Long id);
}
