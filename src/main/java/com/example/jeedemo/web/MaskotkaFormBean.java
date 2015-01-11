package com.example.jeedemo.web;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.faces.model.ListDataModel;
import javax.inject.Inject;
import javax.inject.Named;

import com.example.jeedemo.domain.Car;
import com.example.jeedemo.domain.Maskotka;
import com.example.jeedemo.domain.Producent;
import com.example.jeedemo.domain.Stroje;
import com.example.jeedemo.service.MaskotkaManager;
import com.example.jeedemo.service.ProducentManager;
import com.example.jeedemo.service.SellingManager;
import com.example.jeedemo.service.StrojeManager;


@SessionScoped
@Named("maskotkaBean")
public class MaskotkaFormBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private Maskotka maskotka = new Maskotka();
	private ListDataModel<Maskotka> maskotki = new ListDataModel<Maskotka>();
	
	private Maskotka maskotkaToShow = new Maskotka();
	private ListDataModel<Stroje> ownedStroje = new ListDataModel<Stroje>();


	@Inject
	private MaskotkaManager pm;
	@Inject
	private StrojeManager sm;
	@Inject
	private ProducentManager pro;
	
	public Maskotka getMaskotka() {
		return maskotka;
	}
	public void setMaskotka(Maskotka maskotka) {
		this.maskotka = maskotka;
	}
	
	public ListDataModel<Maskotka> getAllMaskotki() {
		maskotki.setWrappedData(pm.getAllMaskotka());
		return maskotki;
	}
	
	
	
	public ListDataModel<Stroje> getOwnedStroje() {
		ownedStroje.setWrappedData(pm.getOwnedStroje(maskotkaToShow));
		return ownedStroje;
	}
	
	
	// Actions
	public String addMaskotka() {
		pm.addMaskotka(maskotka);
		return "showMaskotka";
		//return null;
	}
	


	public String deleteMaskotka() {
		Maskotka maskotkaToDelete = maskotki.getRowData();
		pm.deleteMaskotka(maskotkaToDelete);
		return null;
	}
	
	public String edycja() {
		Maskotka maskotkaToedit = maskotki.getRowData();
		setMaskotka(maskotkaToedit);
		return "edycja";
	}
	
	public String update(){
		pm.edycja(maskotka);
		return "showMaskotka";
	}
	
	
	public String showDetails() {
		maskotkaToShow = maskotki.getRowData();
		return "szczegoly";
	}
	
	public String disposeStroje(){
		Stroje strojeToDispose = ownedStroje.getRowData();
		sm.disposeStroje(maskotkaToShow, strojeToDispose);
		return null;
	}
	
	private Long maskotkaId;
	private Long producentId;
	
	public Long getMaskotkaId() {
		return maskotkaId;
	}
	public void setMaskotkaId(Long maskotkaId) {
		this.maskotkaId = maskotkaId;
	}
	public Long getProducentId() {
		return producentId;
	}
	public void setProducentId(Long producentId) {
		this.producentId = producentId;
	}

	public List<Maskotka> getAvailableMaskotka() {
		return pm.getAvailableMaskotka();
	}

	public List<Producent> getAllProducent() {
		return pro.getAllProducent();
	}

	public String sellMaskotka() {
		if(producentId == null || maskotkaId == null)
		{
			
		}
		else
		{
		pm.sellMaskotka(producentId, maskotkaId);
		}
		return null;
	}
	
	
}
