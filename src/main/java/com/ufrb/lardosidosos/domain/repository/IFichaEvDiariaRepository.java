package com.ufrb.lardosidosos.domain.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ufrb.lardosidosos.domain.model.FichaEvDiaria;

@Repository
public interface IFichaEvDiariaRepository extends JpaRepository<FichaEvDiaria, Long> {
	Optional<FichaEvDiaria> findByFichaAdmissaoId(Long id);
}
