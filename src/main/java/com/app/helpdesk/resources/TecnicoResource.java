package com.app.helpdesk.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.app.helpdesk.domain.Tecnico;
import com.app.helpdesk.domain.dtos.TecnicoDTO;
import com.app.helpdesk.services.TecnicoService;

@RestController
@RequestMapping("/tecnicos")
public class TecnicoResource {

	@Autowired
	private TecnicoService tecnicoService;

	@GetMapping("/{id}")
	private ResponseEntity<TecnicoDTO> findById(@PathVariable Integer id) {
		Tecnico obj = tecnicoService.findById(id);
		return ResponseEntity.ok().body(new TecnicoDTO(obj));
	}

	@GetMapping
	private ResponseEntity<List<TecnicoDTO>> findAll() {
		List<Tecnico> list = tecnicoService.findAll();

		// Using STREAM
		List<TecnicoDTO> listDTO = list.stream().map(tec -> new TecnicoDTO(tec)).collect(Collectors.toList());

		// Using FOR
//		List<TecnicoDTO> listDTO = new ArrayList<>();
//		for (Tecnico tecnico : list) {
//			listDTO.add(new TecnicoDTO(tecnico));
//		}

		return ResponseEntity.ok().body(listDTO);
	}

	@PostMapping
	private ResponseEntity<TecnicoDTO> create(@Valid @RequestBody TecnicoDTO tecnicoDTO) {

		// Case 1
		Tecnico tecnico = tecnicoService.create(tecnicoDTO);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(tecnico.getId()).toUri();

		// Case 2
//		Tecnico tecnico = new Tecnico();
//		tecnico.setDataCriacao(LocalDate.now());
//		tecnico.setCpf(tecnicoDTO.getCpf());
//		tecnico.setEmail(tecnicoDTO.getEmail());
//		tecnico.setNome(tecnicoDTO.getNome());
//		tecnico.setSenha(tecnicoDTO.getSenha());
//		tecnico.addPerfil(Perfil.TECNICO);

		// Case 3
//		Tecnico tecnico = new Tecnico();
//		BeanUtils.copyProperties(tecnicoDTO, tecnico);
		
		return ResponseEntity.created(uri).build();
	}
	
	@PutMapping
	private ResponseEntity<TecnicoDTO> update(@Valid @RequestBody TecnicoDTO tecnicoDTO) {
		Tecnico tecnico = tecnicoService.update(tecnicoDTO);
		return ResponseEntity.ok(new TecnicoDTO(tecnico));
	}

}
