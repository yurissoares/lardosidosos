package com.ufrb.lardosidosos.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.ufrb.lardosidosos.constant.NotFoundErrorMsg;
import com.ufrb.lardosidosos.exception.NegocioException;
import com.ufrb.lardosidosos.entity.Usuario;
import com.ufrb.lardosidosos.repository.IUsuarioRepository;

@Service
public class UsuarioService implements IUsuarioService {

	private IUsuarioRepository usuarioRepository;
	
	@Autowired
	public UsuarioService(IUsuarioRepository usuarioRepository) {
		this.usuarioRepository = usuarioRepository;
	}
	
	@Override
	public void verificaSeUsuarioExiste(Long id) {
		if (!this.usuarioRepository.findById(id).isPresent())
			throw new NegocioException(NotFoundErrorMsg.NOT_FOUND_USUARIO.getValor(), HttpStatus.NOT_FOUND);
	}

	@Override
	public List<Usuario> listar() {
		return this.usuarioRepository.findAll();
	}

	@Override
	public Usuario cadastrar(Usuario usuario) {
		usuario.setSenha(new BCryptPasswordEncoder().encode(usuario.getSenha()));
		return this.usuarioRepository.save(usuario);
	}

	@Override
	public Usuario buscar(Long id) {
		Optional<Usuario> usuarioOptional = this.usuarioRepository.findById(id);
		if (!usuarioOptional.isPresent()) {
			throw new NegocioException(NotFoundErrorMsg.NOT_FOUND_USUARIO.getValor(), HttpStatus.NOT_FOUND);
		}
		return usuarioOptional.get();
	}

	@Override
	public Usuario editar(Long id, Usuario usuario) {
		this.buscar(id);
		usuario.setId(id);
		return this.cadastrar(usuario);
	}

	@Override
	public void excluir(Long id) {
		this.buscar(id);
		this.usuarioRepository.deleteById(id);
	}

	@Override
	public List<Usuario> buscarPorNome(String nome) {
		List<Usuario> usuarios = usuarioRepository.findByNomeResumidoContainingOrderByNomeResumidoAsc(nome);
		if (usuarios.isEmpty()) {
			throw new NegocioException(NotFoundErrorMsg.NOT_FOUND_USUARIO.getValor(), HttpStatus.NOT_FOUND);
		}
		return usuarios;
	}

}
