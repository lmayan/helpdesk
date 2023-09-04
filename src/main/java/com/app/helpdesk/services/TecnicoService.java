package com.app.helpdesk.services;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.helpdesk.domain.Pessoa;
import com.app.helpdesk.domain.Tecnico;
import com.app.helpdesk.domain.dtos.TecnicoDTO;
import com.app.helpdesk.repositories.PessoaRepository;
import com.app.helpdesk.repositories.TecnicoRepository;
import com.app.helpdesk.services.exceptions.DataIntegrityViolationException;
import com.app.helpdesk.services.exceptions.ObjectNotFoundException;

@Service
public class TecnicoService {

	@Autowired
	private TecnicoRepository tecnicoRepository;
	@Autowired
	private PessoaRepository pessoaRepository;

	public Tecnico findById(Integer id) {
		Optional<Tecnico> obj = tecnicoRepository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Tecnico not found. (ID:" + id + ")"));
	}

	public List<Tecnico> findAll() {
		return tecnicoRepository.findAll();
	}
	
	public Tecnico create(TecnicoDTO tecnicoDTO) {
		tecnicoDTO.setId(null);
		validForCPFAndEmail(tecnicoDTO);
		Tecnico tecnico = new Tecnico(tecnicoDTO);
		return tecnicoRepository.save(tecnico);
	}

	private void validForCPFAndEmail(TecnicoDTO tecnicoDTO) {
		Optional<Pessoa> obj = pessoaRepository.findByCpf(tecnicoDTO.getCpf());
		if(obj.isPresent() && obj.get().getId() != tecnicoDTO.getId()) {
			throw new DataIntegrityViolationException("CPF ("+tecnicoDTO.getCpf()+") is already in use.");
		}
		
		obj = pessoaRepository.findByEmail(tecnicoDTO.getEmail());
		if(obj.isPresent() && obj.get().getId() != tecnicoDTO.getId()) {
			throw new DataIntegrityViolationException("EMAIL ("+tecnicoDTO.getEmail()+") is already in use.");
		}
	}

	public Tecnico update(@Valid TecnicoDTO tecnicoDTO) {
		Tecnico tecnico = findById(tecnicoDTO.getId());
		validForCPFAndEmail(tecnicoDTO);
		tecnico = new Tecnico(tecnicoDTO);
		return tecnicoRepository.save(tecnico);
	}

	public void delete(Integer id) {
		Tecnico tecnico = findById(id);
		if(tecnico.getChamados().size() > 0) {
			throw new DataIntegrityViolationException("Tecnico have one or more bind. (chamados)");
		}
		tecnicoRepository.delete(tecnico);
	}

}
