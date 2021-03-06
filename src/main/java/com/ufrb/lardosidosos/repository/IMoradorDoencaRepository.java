package com.ufrb.lardosidosos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ufrb.lardosidosos.entity.MoradorDoenca;

@Repository
public interface IMoradorDoencaRepository extends JpaRepository<MoradorDoenca, Long> {

}
