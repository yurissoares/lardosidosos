package com.ufrb.lardosidosos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ufrb.lardosidosos.entity.TipoRegistroSaude;

@Repository
public interface ITipoRegistroSaudeRepository extends JpaRepository<TipoRegistroSaude, Long> {

}
