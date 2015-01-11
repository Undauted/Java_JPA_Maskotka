package com.example.jeedemo.service;


import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.example.jeedemo.domain.Car;
import com.example.jeedemo.domain.Maskotka;
import com.example.jeedemo.domain.Person;
import com.example.jeedemo.domain.Producent;
import com.example.jeedemo.domain.Stroje;


@Stateless
public class MaskotkaManager {

	@PersistenceContext
	EntityManager masko;

	public void addMaskotka(Maskotka maskotka) {
		maskotka.setId(null);
		masko.persist(maskotka);
	}
	

	
	public void edycja(Maskotka maskotka) {
	masko.merge(maskotka);
	}


	
	public void deleteMaskotka(Maskotka maskotka) {
		maskotka = masko.find(Maskotka.class, maskotka.getId());
		masko.remove(maskotka);
	}

	@SuppressWarnings("unchecked")
	public List<Maskotka> getAllMaskotka() {
		return masko.createNamedQuery("maskotka.all").getResultList();
	}
	
	
	
	public List<Stroje> getOwnedStroje(Maskotka maskotka) {
		maskotka = masko.find(Maskotka.class, maskotka.getId());
		// lazy loading here - try this code without this (shallow) copying
		List<Stroje> stroje = new ArrayList<Stroje>(maskotka.getStroje());
		return stroje;
	}
	
	public void sellMaskotka(Long producentId, Long maskotkaId) {

		Producent producent = masko.find(Producent.class, producentId);
		Maskotka maskotka = masko.find(Maskotka.class, maskotkaId);
		maskotka.setSold(true);

		producent.getMaskotka().add(maskotka);
	}

	@SuppressWarnings("unchecked")
	public List<Maskotka> getAvailableMaskotka() {
		return masko.createNamedQuery("maskotka.unsold").getResultList();
	}

	public void disposeMaskotka(Producent producent, Maskotka maskotka) {

		producent = masko.find(Producent.class, producent.getId());
		maskotka = masko.find(Maskotka.class, maskotka.getId());

		Maskotka toRemove = null;
		// lazy loading here (person.getCars)
		for (Maskotka aMaskotka : producent.getMaskotka())
			if (aMaskotka.getId().compareTo(maskotka.getId()) == 0) {
				toRemove = aMaskotka;
				break;
			}

		if (toRemove != null)
			producent.getMaskotka().remove(toRemove);
		
		maskotka.setSold(false);
	}

	

}
