package com.example.jeedemo.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.validation.constraints.Pattern;

@Entity

@NamedQueries({ 
	@NamedQuery(name = "producent.all", query = "Select p from Producent p"),
	
})
public class Producent {
	
	private Long id;
	private String nazwa;
	private int regon;
	private String miejscowosc;
	
	private List<Maskotka> maskotka = new ArrayList<Maskotka>();
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNazwa() {
		return nazwa;
	}
	public void setNazwa(String nazwa) {
		this.nazwa = nazwa;
	}
	
	
	public int getRegon() {
		return regon;
	}
	public void setRegon(int regon) {
		this.regon = regon;
	}
	public String getMiejscowosc() {
		return miejscowosc;
	}
	public void setMiejscowosc(String miejscowosc) {
		this.miejscowosc = miejscowosc;
	}
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	public List<Maskotka> getMaskotka() {
		return maskotka;
	}
	public void setMaskotka(List<Maskotka> maskotka) {
		this.maskotka = maskotka;
	}
}
