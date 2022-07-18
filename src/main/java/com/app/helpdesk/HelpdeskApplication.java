package com.app.helpdesk;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.app.helpdesk.domain.Chamado;
import com.app.helpdesk.domain.Cliente;
import com.app.helpdesk.domain.Tecnico;
import com.app.helpdesk.domain.enums.Perfil;
import com.app.helpdesk.domain.enums.Prioridade;
import com.app.helpdesk.domain.enums.Status;
import com.app.helpdesk.repositories.ChamadoRepository;
import com.app.helpdesk.repositories.ClienteRepository;
import com.app.helpdesk.repositories.TecnicoRepository;

@SpringBootApplication
public class HelpdeskApplication implements CommandLineRunner {

	@Autowired
	private TecnicoRepository tecnicoRepository;
	@Autowired
	private ClienteRepository clienteRepository;
	@Autowired
	private ChamadoRepository chamadoRepository;

	public static void main(String[] args) {
		SpringApplication.run(HelpdeskApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		Tecnico tec1 = new Tecnico(null, "Valdir Cezar", "63653230268", "valdir@mail.com", "123");
		tec1.addPerfil(Perfil.TECNICO);

		Cliente cli1 = new Cliente(null, "Linus Torvalds", "80527954780", "torvalds@email.com", "123");

		Chamado c1 = new Chamado(null, Prioridade.MEDIA, Status.ANDAMENTO, "Chamado 01", "Primeiro chamado", tec1,
				cli1);

		tecnicoRepository.saveAll(List.of(tec1));
		clienteRepository.saveAll(List.of(cli1));
		chamadoRepository.saveAll(List.of(c1));

	}

}
