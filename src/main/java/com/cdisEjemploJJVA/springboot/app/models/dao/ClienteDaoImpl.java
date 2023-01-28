package com.cdisEjemploJJVA.springboot.app.models.dao;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.cdisEjemploJJVA.springboot.app.models.entity.Cliente;

import jakarta.persistence.EntityManager;

@Repository
public class ClienteDaoImpl implements IClienteDao {

	private EntityManager em;
	
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	@Override
	public List<Cliente> findAll() {
		// TODO Auto-generated method stub
		return em.createQuery("from Cliente").getResultList();
	}
	
	@Transactional(readOnly = true)
	@Override
	public void save(Cliente cliente) {
		// TODO Auto-generated method stub
		
	}

}
