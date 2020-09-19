//package com.ufrb.lardosidosos.domain.service;
//
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.AuthorityUtils;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Component;
//
//import com.ufrb.lardosidosos.domain.model.Usuario;
//import com.ufrb.lardosidosos.domain.model.enums.TipoUsuario;
//import com.ufrb.lardosidosos.domain.repository.IUsuarioRepository;
//
//@Component
//public class CustomUsuarioDetailService implements UserDetailsService {
//
//	private IUsuarioRepository usuarioRepository;
//
//	@Autowired
//	public CustomUsuarioDetailService(IUsuarioRepository usuarioRepository) {
//		this.usuarioRepository = usuarioRepository;
//	}
//
//	@Override
//	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//
//		Usuario usuario = usuarioRepository.findByNomeResumido(username)
//				.orElseThrow(() -> new UsernameNotFoundException("Usuário não existe."));
//
//		List<GrantedAuthority> authorityListDiretor = AuthorityUtils.createAuthorityList("ROLE_TECNICO", "ROLE_DIRETOR");
//		List<GrantedAuthority> authorityListTecnico = AuthorityUtils.createAuthorityList("ROLE_TECNICO");
//
//		return new User(usuario.getNomeResumido(), usuario.getSenha(),
//				usuario.getTipoUsuario() == TipoUsuario.DIRETOR ? authorityListDiretor : authorityListTecnico);
//	}
//}