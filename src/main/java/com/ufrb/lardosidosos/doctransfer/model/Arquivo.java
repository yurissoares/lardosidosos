package com.ufrb.lardosidosos.doctransfer.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

import com.ufrb.lardosidosos.domain.model.Documento;

import lombok.Data;

@Data
@Entity
public class Arquivo {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY )
	private long id;
	
	private @ManyToOne Documento documento;
	
	private String tipoArquivo;
	
	private @Lob byte[] data;
	
	public Arquivo() {}
	
	public Arquivo(Documento documento, String tipoArquivo, byte[] data) {
		this.documento = documento;
		this.tipoArquivo = tipoArquivo;
		this.data = data;
	}
}
