package br.mackenzie.ps2.api.resource;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import br.mackenzie.ps2.api.entity.Filme;
import br.mackenzie.ps2.api.repository.FilmeRepository;

@RestController
public class FilmeResource {
	
	@Autowired
	private FilmeRepository repository;
	
	public FilmeResource() {}
	
	@PostMapping("/api/filmes")
	public Filme create(@RequestBody Filme prof) {
		try {
			return this.repository.save(prof);
		}catch(Exception ex) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Dados inválidos", ex);
		}
	}
	
	@GetMapping("/api/filmes")
	public Iterable<Filme> readAll(@RequestParam(required = false) String nome) {
		if (nome == null) { 
			return this.repository.findAll();
		}
		
		return this.repository.findByNomeContaining(nome);
	}
	
	@GetMapping("/api/filmes/{id}")
	public Filme readById(@PathVariable long id) {
		Optional<Filme> op = this.repository.findById(id);
		if(op.isPresent()) {
			return op.get();
		}
		throw new ResponseStatusException(HttpStatus.NOT_FOUND);
	}
	
	@PutMapping("/api/filmes/{id}")
	public Filme update(@RequestBody Filme film, @PathVariable long id) {
		Optional<Filme> op = this.repository.findById(id);
		if(op.isPresent()) {
			Filme f = op.get();
			
			String nome = film.getNome();
			int lancamento = film.getLancamento();
			String categoria =film.getCategoria();
			String descri= film.getDescri();
			double nota = film.getNota();

			if(nome != null) f.setNome(nome);
			if(lancamento != 0) f.setLancamento(lancamento);
			if(categoria != null) f.setCategoria(categoria);
			if(nota != 0) f.setNota(nota);
			if(descri != null) f.setDescri(descri);
			this.repository.save(f);
			return f;
		}
		throw new ResponseStatusException(HttpStatus.NOT_FOUND);
	}
	
	@DeleteMapping(value="/api/filmes/{id}", produces = "application/json")
	public Filme delete(@PathVariable long id) {
		
		try {
			Optional<Filme> op = this.repository.findById(id);
			if(op.isPresent()) {
				this.repository.deleteById(id);
				return op.get();
			}
		}catch(Exception ex) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "ID precisa ser válido", ex);
		}
		throw new ResponseStatusException(HttpStatus.NOT_FOUND);
	}
}
