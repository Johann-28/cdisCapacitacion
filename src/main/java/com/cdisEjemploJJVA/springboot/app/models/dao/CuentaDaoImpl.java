package com.cdisEjemploJJVA.springboot.app.models.dao;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.cdisEjemploJJVA.springboot.app.models.entity.Cuenta;
import com.cdisEjemploJJVA.springboot.app.models.entity.Tarjeta;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Repository
public class CuentaDaoImpl implements ICuentaDao {

	@PersistenceContext
	private EntityManager em;
		
	
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	@Override
	public List<Cuenta> findAll() {
		// TODO Auto-generated method stub
		return em.createQuery("from Cuenta").getResultList();
	}
	
	@Transactional(readOnly = true)
	@Override
	public void save(Cuenta cuenta) {
		// TODO Auto-generated method stub
		if(cuenta.getId() != null && cuenta.getId() > 0) {
			em.merge(cuenta);
		}else {
			em.persist(cuenta);
		}
	}

	@Override
	public Cuenta findOne(Long id) {
		return em.find(Cuenta.class,id);
	}

	@Override
	public void delete(Long id) {
		em.remove(findOne(id));
	}
	
	
	

}
