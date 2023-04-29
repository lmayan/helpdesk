package com.app.helpdesk.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.helpdesk.domain.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

}
