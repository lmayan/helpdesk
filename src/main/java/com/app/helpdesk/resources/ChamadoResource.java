package com.app.helpdesk.resources;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.helpdesk.domain.Chamado;
import com.app.helpdesk.domain.dtos.ChamadoDTO;
import com.app.helpdesk.services.ChamadoService;

@RestController
@RequestMapping("/chamados")
public class ChamadoResource {

	@Autowired
	private ChamadoService chamadoService;

	@GetMapping("/{id}")
	private ResponseEntity<ChamadoDTO> findById(@PathVariable Integer id) {
		Chamado obj = chamadoService.findById(id);
		return ResponseEntity.ok().body(new ChamadoDTO(obj));
	}
	
	@GetMapping
	private ResponseEntity<List<ChamadoDTO>> findAll(){
		List<Chamado> list = chamadoService.findAll();
		List<ChamadoDTO> listDTO = list.stream().map(chamado -> new ChamadoDTO(chamado)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDTO);
	}
	

}
