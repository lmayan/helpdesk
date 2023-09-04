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
		Tecnico t2 = new Tecnico(null, "Tecnico 2", "11111111113", "tec2@email.com", "123");
		t2.addPerfil(Perfil.TECNICO);
		Tecnico t3 = new Tecnico(null, "Tecnico 3", "11111111115", "tec3@email.com", "123");
		t3.addPerfil(Perfil.TECNICO);
		Tecnico t4 = new Tecnico(null, "Tecnico 4", "11111111117", "tec4@email.com", "123");
		t3.addPerfil(Perfil.TECNICO);

		Cliente c1 = new Cliente(null, "Cliente 1", "11111111112", "cli1@email.com", "123");
		Cliente c2 = new Cliente(null, "Cliente 2", "11111111114", "cli2@email.com", "123");
		Cliente c3 = new Cliente(null, "Cliente 3", "11111111116", "cli3@email.com", "123");
		Cliente c4 = new Cliente(null, "Cliente 4", "11111111118", "cli4@email.com", "123");

		Chamado ch1 = new Chamado(null, Prioridade.ALTA, Status.ABERTO, "Chamado 01", "obs1", c1, t1);
		Chamado ch2 = new Chamado(null, Prioridade.MEDIA, Status.ANDAMENTO, "Chamado 02", "obs2", c2, t2);
		Chamado ch3 = new Chamado(null, Prioridade.BAIXA, Status.ABERTO, "Chamado 03", "obs3", c3, t3);

		tecnicoRepository.saveAll(Arrays.asList(t1,t2,t3,t4));
		clienteRepository.saveAll(Arrays.asList(c1,c2,c3,c4));
		chamadoRepository.saveAll(Arrays.asList(ch1,ch2,ch3));
	}

}
