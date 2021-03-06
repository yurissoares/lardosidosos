package com.ufrb.lardosidosos.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ufrb.lardosidosos.entity.RegistroSaude;

@Repository
public interface IRegistroSaudeRepository extends JpaRepository<RegistroSaude, Long> {
	List<RegistroSaude> findByMoradorIdOrderByDataDesc(Long id);
}
