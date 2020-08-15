package com.ufrb.lardosidosos.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ufrb.lardosidosos.domain.model.RegistroSaude;

@Repository
public interface RegistroSaudeRepository extends JpaRepository<RegistroSaude, Long> {
	
}
