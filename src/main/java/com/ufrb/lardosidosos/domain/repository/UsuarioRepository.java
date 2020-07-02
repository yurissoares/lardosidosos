package com.ufrb.lardosidosos.domain.repository;

import java.util.Optional;

import com.ufrb.lardosidosos.domain.model.Usuario;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByEmail(String email);
}