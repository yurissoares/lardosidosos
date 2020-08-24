package com.ufrb.lardosidosos.doctransfer.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

import com.ufrb.lardosidosos.domain.model.DocumentoMorador;
import com.ufrb.lardosidosos.domain.model.DocumentoRegistroSaude;

import lombok.Data;

@Data
@Entity
public class Arquivo {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY )
	private long id;
	
	@ManyToOne
	private DocumentoMorador documentoMorador;
	
	@ManyToOne
	private DocumentoRegistroSaude documentoRegistroSaude;
	
	private String tipoArquivo;
	
	private @Lob byte[] data;
	
	public Arquivo() {}
	
	public Arquivo(DocumentoMorador documentoMorador, DocumentoRegistroSaude documentoRegistroSaude,
				   String tipoArquivo, byte[] data)
	{
		this.documentoMorador = documentoMorador;
		this.documentoRegistroSaude = documentoRegistroSaude;
		this.tipoArquivo = tipoArquivo;
		this.data = data;
	}
}
