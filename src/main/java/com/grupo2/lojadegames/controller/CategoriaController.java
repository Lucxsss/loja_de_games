package com.grupo2.lojadegames.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.grupo2.lojadegames.model.Categoria;
import com.grupo2.lojadegames.repository.CategoriaRepository;

@RestController
@RequestMapping("/categoria")
@CrossOrigin("*")
public class CategoriaController {
	
	@Autowired
	private CategoriaRepository repository;
	
	@GetMapping("/all")
	public ResponseEntity<List<Categoria>> getAll(){
		List<Categoria> list = repository.findAll();
		if(list.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Perdeu!");
		} 
		else {
			return ResponseEntity.ok(repository.findAll());
	}
}
	
		
	@GetMapping("/select/{id}") 
	
		// Patch == caminho/remendo // variable == variavel
		//responseEntity para trabalhar minha resposta HTTP
   
	public ResponseEntity<Categoria> findById(@PathVariable("id") Long idvariavel ) { 
		
		// idvariavel é nome que quer de variavel
		       
           return repository.findById(idvariavel).map(resp -> ResponseEntity.status(200)
        		   .body(resp))
        		   .orElseGet(() -> { 
        			   throw new ResponseStatusException(HttpStatus.NOT_FOUND, "NONONO");
        		  });
    } 
	
	 //OUTRA OPÇÃO DE RETURN -  return ResponseEntity.status(HttpStatus.OK).body(repository.findById(idvariavel));
	
}
