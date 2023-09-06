package com.app.helpdesk.services;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.helpdesk.domain.Chamado;
import com.app.helpdesk.domain.Cliente;
import com.app.helpdesk.domain.Tecnico;
import com.app.helpdesk.domain.dtos.ChamadoDTO;
import com.app.helpdesk.enums.Prioridade;
import com.app.helpdesk.enums.Status;
import com.app.helpdesk.repositories.ChamadoRepository;
import com.app.helpdesk.services.exceptions.ObjectNotFoundException;

@Service
public class ChamadoService {

	@Autowired
	private ChamadoRepository chamadoRepository;
	@Autowired
	private TecnicoService tecnicoService;
	@Autowired
	private ClienteService clienteService;

	public Chamado findById(Integer id) {
		Optional<Chamado> obj = chamadoRepository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Chamado not found. (ID:" + id + ")"));
	}

	public List<Chamado> findAll() {
		return chamadoRepository.findAll();
	}

	public Chamado create(@Valid ChamadoDTO chamadoDTO) {
		return chamadoRepository.save(newChamado(chamadoDTO));
	}
	
	private Chamado newChamado(ChamadoDTO chamadoDTO) {
		Tecnico tecnico = new Tecnico();
		Cliente cliente = new Cliente();
		
		tecnico = tecnicoService.findById(chamadoDTO.getTecnico());
		cliente = clienteService.findById(chamadoDTO.getCliente());
		
		Chamado chamado = new Chamado();
		if(chamadoDTO.getId() != null) {
			chamado.setId(chamadoDTO.getId());
		}
		chamado.setCliente(cliente);
		chamado.setTecnico(tecnico);
		chamado.setTitulo(chamadoDTO.getTitulo());
		chamado.setObservacao(chamadoDTO.getObservacao());
		chamado.setPrioridade(Prioridade.toEnum(chamadoDTO.getPrioridade()));
		chamado.setStatus(Status.toEnum(chamadoDTO.getStatus()));
		return chamado;
	}

}
