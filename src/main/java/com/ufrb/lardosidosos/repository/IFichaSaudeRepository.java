package com.ufrb.lardosidosos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ufrb.lardosidosos.entity.FichaSaude;

@Repository
public interface IFichaSaudeRepository extends JpaRepository<FichaSaude, Long> {
}
