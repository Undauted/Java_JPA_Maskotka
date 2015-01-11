package com.example.jeedemo.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.example.jeedemo.domain.Producent;
import com.example.jeedemo.domain.Stroje;
import com.example.jeedemo.domain.Maskotka;


@Stateless
public class StrojeManager {

	@PersistenceContext
	EntityManager stro;

	
	public void addStroj(Stroje stroj) {
		stroj.setId(null);
		stro.persist(stroj);
	}

	public void deleteStroj(Stroje stroj) {
		stroj = stro.find(Stroje.class, stroj.getId());
		stro.remove(stroj);
	}
	
	public Stroje deleteStroj1(Stroje stroj) {
		stroj = stro.find(Stroje.class, stroj.getId());
		stro.remove(stroj);
		
		return stroj;
	}
	
	public void edycja(Stroje stroje) {
		stro.merge(stroje);
		}

	@SuppressWarnings("unchecked")
	public List<Stroje> getAllStroje() {
		return stro.createNamedQuery("stroje.all").getResultList();
	}
	
	
	public void sellStroje(Long maskotkaId, Long strojeId) {

		Maskotka maskotka = stro.find(Maskotka.class, maskotkaId);
		Stroje stroje = stro.find(Stroje.class, strojeId);
		stroje.setSold(true);

		maskotka.getStroje().add(stroje);
	}

	@SuppressWarnings("unchecked")
	public List<Stroje> getAvailableStroje() {
		return stro.createNamedQuery("stroje.unsold").getResultList();
	}

	public void disposeStroje(Maskotka maskotka, Stroje stroje) {

		maskotka = stro.find(Maskotka.class, maskotka.getId());
		stroje = stro.find(Stroje.class, stroje.getId());

		Stroje toRemove = null;
		// lazy loading here (person.getCars)
		for (Stroje aStroje : maskotka.getStroje())
			if (aStroje.getId().compareTo(stroje.getId()) == 0) {
				toRemove = aStroje;
				break;
			}

		if (toRemove != null)
			maskotka.getStroje().remove(toRemove);
		
		stroje.setSold(false);
	}
}
