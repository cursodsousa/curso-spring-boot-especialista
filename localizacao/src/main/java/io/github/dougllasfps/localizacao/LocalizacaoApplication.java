package io.github.dougllasfps.localizacao;

import io.github.dougllasfps.localizacao.domain.entity.Cidade;
import io.github.dougllasfps.localizacao.domain.repository.CidadeRepository;
import io.github.dougllasfps.localizacao.service.CidadeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.Transactional;

@SpringBootApplication
public class LocalizacaoApplication implements CommandLineRunner {

	@Autowired
	private CidadeService service;

	@Override
	public void run(String... args) throws Exception {
		var cidade = new Cidade(1L, "São Paulo", 100L);
		service.listarCidadesSpecsFiltroDinamico(cidade);
	}

	public static void main(String[] args) {
		SpringApplication.run(LocalizacaoApplication.class, args);
	}

}
