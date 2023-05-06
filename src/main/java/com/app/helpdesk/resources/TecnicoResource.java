package com.app.helpdesk.resources;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.helpdesk.domain.Tecnico;
import com.app.helpdesk.domain.dtos.TecnicoDTO;
import com.app.helpdesk.services.TecnicoService;

@RestController
@RequestMapping("/tecnicos")
public class TecnicoResource {

	@Autowired
	private TecnicoService tecnicoService;
	
	@GetMapping("/{id}")
	private ResponseEntity<TecnicoDTO> findById(@PathVariable Integer id){
		Tecnico obj = tecnicoService.findById(id);
		return ResponseEntity.ok().body(new TecnicoDTO(obj));
	}
	
	@GetMapping
	private ResponseEntity<List<TecnicoDTO>> findAll(){
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
	
}
