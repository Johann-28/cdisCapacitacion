package com.cdisEjemploJJVA.springboot.app.services;

import java.util.List;

import com.cdisEjemploJJVA.springboot.app.models.entity.Cuenta;

public interface ICuentaService {
	public Cuenta getById(Long id, List<Cuenta> lista);
}
