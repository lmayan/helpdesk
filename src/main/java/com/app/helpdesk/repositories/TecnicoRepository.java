package com.app.helpdesk.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.helpdesk.domain.Tecnico;

public interface TecnicoRepository extends JpaRepository<Tecnico, Integer>{

}
