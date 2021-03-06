package com.ufrb.lardosidosos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ufrb.lardosidosos.entity.Usuario;

import java.util.List;
import java.util.Optional;

@Repository
public interface IUsuarioRepository extends JpaRepository<Usuario, Long>{
    List<Usuario> findByNomeResumidoContainingOrderByNomeResumidoAsc(final String nome);
    Optional<Usuario> findByNomeResumido(final String username);
}
