package com.example.jeedemo.web;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.faces.model.ListDataModel;
import javax.inject.Inject;
import javax.inject.Named;

import com.example.jeedemo.domain.Maskotka;
import com.example.jeedemo.domain.Producent;
import com.example.jeedemo.domain.Stroje;
import com.example.jeedemo.service.MaskotkaManager;
import com.example.jeedemo.service.ProducentManager;
import com.example.jeedemo.service.StrojeManager;



@SessionScoped
@Named("producentBean")
public class ProducentFormBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private Producent producent = new Producent();
	private ListDataModel<Producent> producenci = new ListDataModel<Producent>();
	
	private Producent producentToShow = new Producent();
	private ListDataModel<Maskotka> ownedMaskotka = new ListDataModel<Maskotka>();


	@Inject
	private ProducentManager pm;
	@Inject
	private MaskotkaManager sm;

	
	public Producent getProducent() {
		return producent;
	}
	public void setProducent(Producent producent) {
		this.producent = producent;
	}
	
	public ListDataModel<Producent> getAllProducent() {
		producenci.setWrappedData(pm.getAllProducent());
		return producenci;
	}
	public ListDataModel<Maskotka> getOwnedMaskotka() {
		ownedMaskotka.setWrappedData(pm.getOwnedMaskotka(producentToShow));
		return ownedMaskotka;
	}
	
	
	// Actions
	public String addProducent() {
		pm.addProducent(producent);
		return "showProducent";
		//return null;
	}

	public String deleteProducent() {
		Producent producentToDelete = producenci.getRowData();
		pm.deleteProducent(producentToDelete);
		return null;
	}
	
	public String edycja() {
		Producent producentToedit = producenci.getRowData();
		setProducent(producentToedit);
		return "edycjaProducent";
	}
	
	public String update(){
		pm.edycja(producent);
		return "showProducent";
	}
	
	public String showDetails() {
		producentToShow = producenci.getRowData();
		return "szczegolyProducenta";
	}
	
	public String disposeMaskotka(){
		Maskotka maskotkaToDispose = ownedMaskotka.getRowData();
		sm.disposeMaskotka(producentToShow, maskotkaToDispose);
		return null;
	}
	
	
	
}
