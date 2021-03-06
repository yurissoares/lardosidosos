package com.ufrb.lardosidosos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ufrb.lardosidosos.entity.TipoOcorrencia;

@Repository
public interface ITipoOcorrenciaRepository extends JpaRepository<TipoOcorrencia, Long> {

}
