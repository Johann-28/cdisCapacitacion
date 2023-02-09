package com.cdisEjemploJJVA.springboot.app.models.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.exception.DataException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.cdisEjemploJJVA.springboot.app.errors.DataBaseBancoException;
import com.cdisEjemploJJVA.springboot.app.models.entity.Tarjeta;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;



@Repository
public class TarjetaDaoImpl implements ITarjetaDao {

	@PersistenceContext
	private EntityManager em;
	
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	@Override
	public List<Tarjeta> findAll() {
		return em.createQuery("from Tarjeta").getResultList();
	}
	
	@Override
	@Transactional(readOnly = true)
	public void save(Tarjeta tarjeta) throws DataBaseBancoException{
		if(tarjeta.getId() != null && tarjeta.getId() > 0) {
			try {
				em.merge(tarjeta);
			} catch (DataException e) {
				throw new DataBaseBancoException();
			}
				
		}else {
			try {
				em.persist(tarjeta);
			} catch (DataException e) {
				throw new DataBaseBancoException();
			}
		}
	}

	@Override
	@Transactional(readOnly = true)
	public Tarjeta findOne(Long Id) {
		return em.find(Tarjeta.class, Id);
	}

	@Override
	@Transactional
	public void delete(Long Id) {
		em.remove(findOne(Id));
	}

	@Override
	@Transactional(readOnly = true)
	public List<Tarjeta> findByCuentaId(String term) {
		List<Tarjeta> tarjetas = new ArrayList<Tarjeta>();
		for(Tarjeta tarjeta : this.findAll()) {
			if(tarjeta.getCuenta().getId().toString().equals(term)) {
				tarjetas.add(tarjeta);
			}
		}
		return tarjetas;
	}

}
