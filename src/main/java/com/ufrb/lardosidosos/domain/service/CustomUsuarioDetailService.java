package com.ufrb.lardosidosos.domain.service;

import java.util.List;
import java.util.Optional;

import com.ufrb.lardosidosos.domain.exception.NegocioException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.ufrb.lardosidosos.domain.model.Usuario;
import com.ufrb.lardosidosos.domain.model.enums.TipoUsuario;
import com.ufrb.lardosidosos.domain.repository.IUsuarioRepository;

@Component
public class CustomUsuarioDetailService implements UserDetailsService {

	private IUsuarioRepository usuarioRepository;

	@Autowired
	public CustomUsuarioDetailService(IUsuarioRepository usuarioRepository) {
		this.usuarioRepository = usuarioRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws InternalAuthenticationServiceException {
		Optional<Usuario> usuario = usuarioRepository.findByNomeResumido(username);
		if(usuario.isEmpty()) {
			//TODO: Faltando mapear essa exception corretamente 02-03-2021
			throw new InternalAuthenticationServiceException(usuario.get().getNomeResumido());
		}

		List<GrantedAuthority> authorityListDiretor = AuthorityUtils.createAuthorityList("ROLE_DIRETOR");
		List<GrantedAuthority> authorityListAssistenteSocial = AuthorityUtils.createAuthorityList("ROLE_ASSISTENTE_SOCIAL");
		List<GrantedAuthority> authorityListEnfermeiro = AuthorityUtils.createAuthorityList("ROLE_ENFERMEIRO");
		List<GrantedAuthority> authorityListTecnico = AuthorityUtils.createAuthorityList("ROLE_TECNICO");

		List<GrantedAuthority> userAuthorityList;
		if(usuario.isPresent()) {
			if (usuario.get().getTipoUsuario() == TipoUsuario.DIRETOR) {
				userAuthorityList = authorityListDiretor;
			} else if (usuario.get().getTipoUsuario() == TipoUsuario.ASSISTENTE_SOCIAL) {
				userAuthorityList = authorityListAssistenteSocial;
			} else if (usuario.get().getTipoUsuario() == TipoUsuario.ENFERMEIRO) {
				userAuthorityList = authorityListEnfermeiro;
			} else {
				userAuthorityList = authorityListTecnico;
			}
		} else {
			throw new NegocioException("Usuario sem permissões.", HttpStatus.BAD_REQUEST);
		}
		return new org.springframework.security.core.userdetails.User(usuario.get().getNomeResumido(), usuario.get().getSenha(), userAuthorityList);
	}
}