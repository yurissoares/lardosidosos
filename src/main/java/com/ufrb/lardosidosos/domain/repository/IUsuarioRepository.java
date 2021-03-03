package com.ufrb.lardosidosos.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ufrb.lardosidosos.domain.model.Usuario;

import java.util.List;
import java.util.Optional;

@Repository
public interface IUsuarioRepository extends JpaRepository<Usuario, Long>{
    List<Usuario> findByNomeResumidoContainingOrderByNomeResumidoAsc(final String nome);
    Optional<Usuario> findByNomeResumido(final String username);
}
