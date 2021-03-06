package com.ufrb.lardosidosos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ufrb.lardosidosos.entity.AntecedentePessoal;

@Repository
public interface IAntecedentePessoalRepository extends JpaRepository<AntecedentePessoal, Long> {

}
