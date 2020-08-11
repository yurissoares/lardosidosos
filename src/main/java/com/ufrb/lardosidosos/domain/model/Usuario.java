package com.ufrb.lardosidosos.domain.model;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.ufrb.lardosidosos.domain.model.enums.TipoUsuario;

import lombok.Data;

@Data
@Entity
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private @Email @Size(max=50) String email;
	private @NotBlank @Size(max=50) String senha;
	
	private @Enumerated(EnumType.STRING) TipoUsuario tipoUsuario;
	
	private @Size(max=50) String nomeCompleto;
	private @Size(max=20) String nomeResumido;
	
}
