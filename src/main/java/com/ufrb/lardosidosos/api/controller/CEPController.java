package com.ufrb.lardosidosos.api.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ufrb.lardosidosos.domain.exception.NegocioException;

@RestController
@RequestMapping("/cep")
@PreAuthorize("hasRole('ROLE_DIRETOR')")
public class CEPController {
	
	@GetMapping("/{cepNumero}")
	public ResponseEntity<Object> buscaCep(@PathVariable String cepNumero) {
		String apiLine = null;
		JSONObject jsonObj = null;
		
		try {
			
			String httpsURL = "https://viacep.com.br/ws/" + cepNumero + "/json/";
	        URL myUrl = new URL(httpsURL);
	        HttpsURLConnection conn = (HttpsURLConnection)myUrl.openConnection();
	        InputStream is = conn.getInputStream();
	        InputStreamReader isr = new InputStreamReader(is);
	        BufferedReader br = new BufferedReader(isr);
	        
	        StringBuilder jsonString = new StringBuilder();

	        while ((apiLine = br.readLine()) != null) {
	            jsonString.append(apiLine);
	        } 
	        
	        jsonObj = new JSONObject(jsonString.toString());
	        
	        br.close();	
	        
		} catch(IOException e) {
			throw new NegocioException("CEP inválido.", HttpStatus.BAD_REQUEST);
        }
		
		return ResponseEntity.ok(jsonObj.toMap());
	}
}