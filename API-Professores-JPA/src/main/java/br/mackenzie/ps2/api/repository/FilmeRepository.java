package br.mackenzie.ps2.api.repository;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import br.mackenzie.ps2.api.entity.Filme;

public interface FilmeRepository extends CrudRepository<Filme, Long> {
	List<Filme> findByNomeContaining(String nome);
}
