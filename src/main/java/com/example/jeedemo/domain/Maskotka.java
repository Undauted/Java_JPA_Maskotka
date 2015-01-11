package com.example.jeedemo.domain;


import java.util.ArrayList;
import java.util.Date;




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
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;


@Entity
@NamedQueries({ 
	@NamedQuery(name = "maskotka.all", query = "Select m from Maskotka m"),
	@NamedQuery(name = "maskotka.unsold", query = "Select m from Maskotka m where m.sold = false"),
	@NamedQuery(name = "maskotka.imie", query = "Select m from Maskotka m where m.imie = :imie")
})
public class Maskotka {

	private Long id;

	private String imie = "";
	private String rodzaj = "";
	private String material = "";
	private Date dateOfBirth = new Date();
	private String zdjecie="";
	private Boolean sold = false;
	
	private List<Stroje> stroje = new ArrayList<Stroje>();
	

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	@Pattern(regexp = "[a-zA-Z]{4,}" , message="Musza być więcej niż cztery litery")
	public String getImie() {
		return imie;
	}
	public void setImie(String imie) {
		this.imie = imie;
	}
	
	
	public String getRodzaj() {
		return rodzaj;
	}
	public void setRodzaj(String rodzaj) {
		this.rodzaj = rodzaj;
	}
	
	@Past(message="Data musi byc z przeszłości :P")
	public Date getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	
	public String getMaterial() {
		return material;
	}
	public void setMaterial(String material) {
		this.material = material;
	}
	
	public String getZdjecie() {
		return zdjecie;
	}
	public void setZdjecie(String zdjecie) {
		this.zdjecie = zdjecie;
	}
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	public List<Stroje> getStroje() {
		return stroje;
	}
	public void setStroje(List<Stroje> stroje) {
		this.stroje = stroje;
	}
	public Boolean getSold() {
		return sold;
	}
	public void setSold(Boolean sold) {
		this.sold = sold;
	}
}
