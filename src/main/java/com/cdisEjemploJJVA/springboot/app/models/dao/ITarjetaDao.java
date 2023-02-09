package com.cdisEjemploJJVA.springboot.app.models.dao;

import java.util.List;

import com.cdisEjemploJJVA.springboot.app.models.entity.Tarjeta;

public interface ITarjetaDao {
	
	public List<Tarjeta> findAll();
	
	public void save(Tarjeta tarjeta);
	
	public Tarjeta findOne(Long Id);
	
	public void delete(Long Id);
	
	public List<Tarjeta> findByCuentaId(String term);
}
