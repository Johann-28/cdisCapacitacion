package com.cdisEjemploJJVA.springboot.app.models.dao;

import java.util.List;

import com.cdisEjemploJJVA.springboot.app.models.entity.Cliente;

public interface IClienteDao {

	public List<Cliente> findAll();
	
	public void save(Cliente cliente);
}
