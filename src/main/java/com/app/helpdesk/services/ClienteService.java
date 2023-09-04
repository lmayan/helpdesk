package com.app.helpdesk.services;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.helpdesk.domain.Cliente;
import com.app.helpdesk.domain.Pessoa;
import com.app.helpdesk.domain.dtos.ClienteDTO;
import com.app.helpdesk.repositories.ClienteRepository;
import com.app.helpdesk.repositories.PessoaRepository;
import com.app.helpdesk.services.exceptions.DataIntegrityViolationException;
import com.app.helpdesk.services.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository clienteRepository;
	@Autowired
	private PessoaRepository pessoaRepository;

	public Cliente findById(Integer id) {
		Optional<Cliente> obj = clienteRepository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Cliente not found. (ID:" + id + ")"));
	}

	public List<Cliente> findAll() {
		return clienteRepository.findAll();
	}
	
	public Cliente create(ClienteDTO clienteDTO) {
		clienteDTO.setId(null);
		validForCPFAndEmail(clienteDTO);
		Cliente cliente = new Cliente(clienteDTO);
		return clienteRepository.save(cliente);
	}

	private void validForCPFAndEmail(ClienteDTO clienteDTO) {
		Optional<Pessoa> obj = pessoaRepository.findByCpf(clienteDTO.getCpf());
		if(obj.isPresent() && obj.get().getId() != clienteDTO.getId()) {
			throw new DataIntegrityViolationException("CPF ("+clienteDTO.getCpf()+") is already in use.");
		}
		
		obj = pessoaRepository.findByEmail(clienteDTO.getEmail());
		if(obj.isPresent() && obj.get().getId() != clienteDTO.getId()) {
			throw new DataIntegrityViolationException("EMAIL ("+clienteDTO.getEmail()+") is already in use.");
		}
	}

	public Cliente update(@Valid ClienteDTO clienteDTO) {
		Cliente cliente = findById(clienteDTO.getId());
		validForCPFAndEmail(clienteDTO);
		cliente = new Cliente(clienteDTO);
		return clienteRepository.save(cliente);
	}

	public void delete(Integer id) {
		Cliente cliente = findById(id);
		if(cliente.getChamados().size() > 0) {
			throw new DataIntegrityViolationException("Cliente have one or more bind. (chamados)");
		}
		clienteRepository.delete(cliente);
	}

}
