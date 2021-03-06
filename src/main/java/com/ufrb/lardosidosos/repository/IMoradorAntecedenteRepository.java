package com.ufrb.lardosidosos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ufrb.lardosidosos.entity.MoradorAntecedente;

@Repository
public interface IMoradorAntecedenteRepository extends JpaRepository<MoradorAntecedente, Long> {

}
