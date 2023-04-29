package com.app.helpdesk.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.helpdesk.domain.Chamado;

public interface ChamadoRepository extends JpaRepository<Chamado, Integer>{

}
