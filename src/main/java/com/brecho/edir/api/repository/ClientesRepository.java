package com.brecho.edir.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.brecho.edir.api.model.ClienteModel;

public interface ClientesRepository extends JpaRepository<ClienteModel, Integer> {

}
