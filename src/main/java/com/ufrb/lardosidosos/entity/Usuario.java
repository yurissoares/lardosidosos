package com.ufrb.lardosidosos.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.ufrb.lardosidosos.entity.enums.TipoUsuario;

import lombok.Data;

@Data
@Entity
public class Usuario implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Email
	@Size(max = 50)
	private String email;

	@NotBlank
	private String senha;

	@Enumerated(EnumType.STRING)
	private TipoUsuario tipoUsuario;

	@Size(max = 50)
	private String nomeCompleto;

	@Size(max = 20)
	private String nomeResumido;

}
