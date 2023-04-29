package com.app.helpdesk.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.helpdesk.domain.Pessoa;

public interface PessoaRepository extends JpaRepository<Pessoa, Integer> {

}
