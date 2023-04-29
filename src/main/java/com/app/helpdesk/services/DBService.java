package com.app.helpdesk.services;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.helpdesk.domain.Chamado;
import com.app.helpdesk.domain.Cliente;
import com.app.helpdesk.domain.Tecnico;
import com.app.helpdesk.enums.Perfil;
import com.app.helpdesk.enums.Prioridade;
import com.app.helpdesk.enums.Status;
import com.app.helpdesk.repositories.ChamadoRepository;
import com.app.helpdesk.repositories.ClienteRepository;
import com.app.helpdesk.repositories.TecnicoRepository;

@Service
public class DBService {

	@Autowired
	private TecnicoRepository tecnicoRepository;
	@Autowired
	private ClienteRepository clienteRepository;
	@Autowired
	private ChamadoRepository chamadoRepository;

	public void instanciaDB() {

		Tecnico t1 = new Tecnico(null, "Tecnico 1", "11111111111", "tec1@email.com", "123");
		t1.addPerfil(Perfil.TECNICO);

		Cliente c1 = new Cliente(null, "Cliente 1", "11111111112", "cli1@email.com", "123");
		Chamado ch1 = new Chamado(null, Prioridade.ALTA, Status.ABERTO, "Chamado 01", "obs1", c1, t1);

		tecnicoRepository.saveAll(Arrays.asList(t1));
		clienteRepository.saveAll(Arrays.asList(c1));
		chamadoRepository.saveAll(Arrays.asList(ch1));
	}

}
