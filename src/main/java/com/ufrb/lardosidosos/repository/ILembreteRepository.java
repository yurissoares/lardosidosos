package com.ufrb.lardosidosos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ufrb.lardosidosos.entity.Lembrete;

import java.util.List;

@Repository
public interface ILembreteRepository extends JpaRepository<Lembrete, Long> {
    List<Lembrete> findByUsuarioDestinoId(Long id);
    List<Lembrete> findByUsuarioOrigemId(Long id);
    List<Lembrete> findByMoradorIdOrderByDataCriacaoDesc(Long id);
}
