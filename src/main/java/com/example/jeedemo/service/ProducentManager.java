package com.example.jeedemo.service;


import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.example.jeedemo.domain.Producent;
import com.example.jeedemo.domain.Maskotka;

import com.example.jeedemo.domain.Stroje;

@Stateless
public class ProducentManager {

	@PersistenceContext
	EntityManager produ;

	public void addProducent(Producent producent) {
		producent.setId(null);
		produ.persist(producent);
	}
	
	public void edycja(Producent producent) {
		produ.merge(producent);
		}

	public void deleteProducent(Producent producent) {
		producent = produ.find(Producent.class, producent.getId());
		produ.remove(producent);
	}
	
	public Producent deleteProducent1(Producent producent) {
		producent = produ.find(Producent.class, producent.getId());
		produ.remove(producent);
		
		return producent;
	}

	@SuppressWarnings("unchecked")
	public List<Producent> getAllProducent() {
		return produ.createNamedQuery("producent.all").getResultList();
	}
	
	public List<Maskotka> getOwnedMaskotka(Producent producent) {
		producent = produ.find(Producent.class, producent.getId());
		// lazy loading here - try this code without this (shallow) copying
		List<Maskotka> maskotka = new ArrayList<Maskotka>(producent.getMaskotka());
		return maskotka;
	}

	

}
