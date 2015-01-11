package com.example.jeedemo.web;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.faces.model.ListDataModel;
import javax.inject.Inject;
import javax.inject.Named;

import com.example.jeedemo.domain.Producent;
import com.example.jeedemo.domain.Stroje;
import com.example.jeedemo.domain.Maskotka;
import com.example.jeedemo.service.MaskotkaManager;
import com.example.jeedemo.service.StrojeManager;

@SessionScoped
@Named("strojeBean")
public class StrojeFormBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private Stroje stroje = new Stroje();
	private ListDataModel<Stroje> strojenie = new ListDataModel<Stroje>();
	
	private Stroje strojeToShow = new Stroje();
	
	@Inject
	private StrojeManager sm;

	@Inject
	private MaskotkaManager pm;

	public Stroje getStroje() {
		return stroje;
	}
	public void setStroje(Stroje stroje) {
		this.stroje = stroje;
	}
	
	public ListDataModel<Stroje> getAllStroje() {
		strojenie.setWrappedData(sm.getAllStroje());
		return strojenie;
	}
	
	
	// Actions
		public String addStroje() {
			sm.addStroj(stroje);
			return "showStroje";
			//return null;
		}

		public String deleteStroj() {
			Stroje strojeToDelete = strojenie.getRowData();
			sm.deleteStroj(strojeToDelete);
			return null;
		}
		
		
		
		public String showDetails() {
			strojeToShow = strojenie.getRowData();
			return "details";
		}
		
		public String edycja() {
			Stroje strojeToedit = strojenie.getRowData();
			setStroje(strojeToedit);
			return "edycjaStroj";
		}
		
		public String update(){
			sm.edycja(stroje);
			return "showStroje";
		}
	
	
	private Long strojeId;
	private Long maskotkaId;
	
	public Long getStrojeId() {
		return strojeId;
	}
	public void setStrojeId(Long strojeId) {
		this.strojeId = strojeId;
	}
	public Long getMaskotkaId() {
		return maskotkaId;
	}
	public void setMaskotkaId(Long maskotkaId) {
		this.maskotkaId = maskotkaId;
	}

	public List<Stroje> getAvailableStroje() {
		return sm.getAvailableStroje();
	}

	public List<Maskotka> getAllMaskotka() {
		return pm.getAllMaskotka();
	}

	public String sellStroje() {
		if(maskotkaId == null || strojeId == null)
		{
			
		}
		else
		{
		sm.sellStroje(maskotkaId, strojeId);
		
		}
		return null;
		
	}
}
