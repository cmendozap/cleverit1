package com.cleverit.springboottest.client.model.plate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="plates")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Plate {

    @Id
	private Integer id;
    @Column(name = "id_user") private String idUser;
    @Column(name = "license_plate") private String licensePlate;
    @Column(name = "vehicle_alias") private String alias;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getIdUser() {
		return idUser;
	}
	public void setIdUser(String idUser) {
		this.idUser = idUser;
	}
	public String getLicensePlate() {
		return licensePlate;
	}
	public void setLicensePlate(String licensePlate) {
		this.licensePlate = licensePlate;
	}
	public String getAlias() {
		return alias;
	}
	public void setAlias(String alias) {
		this.alias = alias;
	}
	
}
