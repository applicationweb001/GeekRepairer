package com.hampcode.model.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "tecnicoIndependiente")
public class tecnicoIndependiente {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	
	private long idTecInd;
	
	public long getIdTecInd() {
		return idTecInd;
	}
	public void setIdTecInd(long idTecInd) {
		this.idTecInd = idTecInd;
	}
	String Nombre;
	public String getNombre() {
		return Nombre;
	}
	public void setNombre(String nombre) {
		Nombre = nombre;
	}
	int telefono;
	public int getTelefono() {
		return telefono;
	}
	public void setTelefono(int telefono) {
		this.telefono = telefono;
	}
	double montoHora;
	public double getMontoHora() {
		return montoHora;
	}
	public void setMontoHora(double montoHora) {
		this.montoHora = montoHora;
	}
	String estado;
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}

}
