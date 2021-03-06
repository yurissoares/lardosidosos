package com.ufrb.lardosidosos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ufrb.lardosidosos.entity.Doenca;

@Repository
public interface IDoencaRepository extends JpaRepository<Doenca, Long> {

}
