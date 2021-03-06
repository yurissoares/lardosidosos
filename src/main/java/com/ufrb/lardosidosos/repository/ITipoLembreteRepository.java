package com.ufrb.lardosidosos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ufrb.lardosidosos.entity.TipoLembrete;

@Repository
public interface ITipoLembreteRepository extends JpaRepository<TipoLembrete, Long> {

}
