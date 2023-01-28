package com.cdisEjemploJJVA.springboot.app.models.dao;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.cdisEjemploJJVA.springboot.app.models.entity.Tarjeta;

import jakarta.persistence.EntityManager;



@Repository
public class TarjetaDaoImpl implements ITarjetaDao {

	private EntityManager em;
	
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	@Override
	public List<Tarjeta> findAll() {
		// TODO Auto-generated method stub
		return em.createQuery("from Tarjeta").getResultList();
	}
	
	@Transactional(readOnly = true)
	@Override
	public void save(Tarjeta tarjeta){
		if(tarjeta.getId() != null && tarjeta.getId() > 0) {
				em.merge(tarjeta);
		}else {
			em.persist(tarjeta);
		}
		// TODO Auto-generated method stub

	}

	@Override
	@Transactional(readOnly = true)
	public Tarjeta findOne(Long Id) {
		// TODO Auto-generated method stub
		return em.find(Tarjeta.class, Id);
	}

	@Override
	public void delete(Long Id) {
		em.remove(findOne(Id));
	}

}
