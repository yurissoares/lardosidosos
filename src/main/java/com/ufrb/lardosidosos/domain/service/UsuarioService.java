package com.ufrb.lardosidosos.domain.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ufrb.lardosidosos.domain.constant.NotFoundMsg;
import com.ufrb.lardosidosos.domain.exception.NegocioException;
import com.ufrb.lardosidosos.domain.model.Usuario;
import com.ufrb.lardosidosos.domain.repository.IUsuarioRepository;

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
			throw new NegocioException(NotFoundMsg.NOT_FOUND_USUARIO.getValor());
	}

	@Override
	public List<Usuario> listar() {
		return this.usuarioRepository.findAll();
	}

	@Override
	public Usuario cadastrar(Usuario usuario) {
		return this.usuarioRepository.save(usuario);
	}

	@Override
	public Usuario buscar(Long id) {
		Optional<Usuario> usuarioOptional = this.usuarioRepository.findById(id);
		if (!usuarioOptional.isPresent()) {
			throw new NegocioException(NotFoundMsg.NOT_FOUND_USUARIO.getValor());
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
			throw new NegocioException(NotFoundMsg.NOT_FOUND_USUARIO.getValor());
		}
		return usuarios;
	}

}
