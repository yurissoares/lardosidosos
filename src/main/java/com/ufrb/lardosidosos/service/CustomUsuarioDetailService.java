package com.ufrb.lardosidosos.service;

import java.util.List;
import java.util.Optional;

import com.ufrb.lardosidosos.exception.NegocioException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import com.ufrb.lardosidosos.entity.Usuario;
import com.ufrb.lardosidosos.entity.enums.TipoUsuario;
import com.ufrb.lardosidosos.repository.IUsuarioRepository;

@Component
public class CustomUsuarioDetailService implements UserDetailsService {

	private IUsuarioRepository usuarioRepository;

	@Autowired
	public CustomUsuarioDetailService(final IUsuarioRepository usuarioRepository) {
		this.usuarioRepository = usuarioRepository;
	}

	@Override
	public UserDetails loadUserByUsername(final String username) throws InternalAuthenticationServiceException {
		Optional<Usuario> usuario = usuarioRepository.findByNomeResumido(username);

		if(!usuario.isPresent()) {
			throw new NegocioException("Usuario nao encontrado.", HttpStatus.NOT_FOUND);
		}

		List<GrantedAuthority> authorityListDiretor = AuthorityUtils.createAuthorityList("ROLE_DIRETOR");
		List<GrantedAuthority> authorityListAssistenteSocial = AuthorityUtils.createAuthorityList("ROLE_ASSISTENTE_SOCIAL");
		List<GrantedAuthority> authorityListEnfermeiro = AuthorityUtils.createAuthorityList("ROLE_ENFERMEIRO");
		List<GrantedAuthority> authorityListTecnico = AuthorityUtils.createAuthorityList("ROLE_TECNICO");

		List<GrantedAuthority> userAuthorityList;
		if (usuario.get().getTipoUsuario() == TipoUsuario.DIRETOR) {
			userAuthorityList = authorityListDiretor;
		} else if (usuario.get().getTipoUsuario() == TipoUsuario.ASSISTENTE_SOCIAL) {
			userAuthorityList = authorityListAssistenteSocial;
		} else if (usuario.get().getTipoUsuario() == TipoUsuario.ENFERMEIRO) {
			userAuthorityList = authorityListEnfermeiro;
		} else {
			userAuthorityList = authorityListTecnico;
		}

		return new org.springframework.security.core.userdetails.User(usuario.get().getNomeResumido(), usuario.get().getSenha(), userAuthorityList);
	}
}