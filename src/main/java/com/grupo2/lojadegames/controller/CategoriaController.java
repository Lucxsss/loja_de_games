package com.grupo2.lojadegames.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
	public ResponseEntity<List<Categoria>> getAll() {
		List<Categoria> list = repository.findAll();
		if (list.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Perdeu!");
		} else {
			return ResponseEntity.ok(repository.findAll());
		}
	}

	@GetMapping("/select/{id}")

	// Patch == caminho/remendo // variable == variavel
	// responseEntity para trabalhar minha resposta HTTP

	public ResponseEntity<Categoria> findById(@PathVariable("id") Long idvariavel) {

		// idvariavel é nome que quer de variavel

		return repository.findById(idvariavel).map(resp -> ResponseEntity.status(200).body(resp)).orElseGet(() -> {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "NONONO");
		});
	}

	// OUTRA OPÇÃO DE RETURN - return
	// ResponseEntity.status(HttpStatus.OK).body(repository.findById(idvariavel));

	@PostMapping("/save")
	public ResponseEntity<Categoria> save(@RequestBody Categoria categoria){
		return ResponseEntity.status(201).body(repository.save(categoria));
		
	}
	
	@PutMapping("/alterar/{id}")
	public ResponseEntity<Categoria> putCategoria(@RequestBody Categoria cat){
		return repository.findById(cat.getId()).map(resp -> ResponseEntity.status(200).body(repository.save(cat)))
				.orElseGet(()->{
					throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "ID não encontrado");
				});
	}
	
	//DELETE
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteById (@PathVariable Long id){
		Optional<Categoria> optional = repository.findById(id);
		if (optional.isPresent()) {
			
			repository.deleteById(id);
			return ResponseEntity.status(200).build();
			
		} else {
			
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Id não encontrado!");
			
		}
	}
	
}
