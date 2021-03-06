package com.ufrb.lardosidosos.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ufrb.lardosidosos.entity.FichaEvDiaria;

@Repository
public interface IFichaEvDiariaRepository extends JpaRepository<FichaEvDiaria, Long> {
	Optional<FichaEvDiaria> findByFichaAdmissaoId(Long id);
	List<FichaEvDiaria> findByMoradorIdOrderByData(Long moradorId);
	List<FichaEvDiaria> findByMoradorIdAndDataBetweenOrderByData(Long moradorId, LocalDate dtInicio, LocalDate dtFinal);
}
