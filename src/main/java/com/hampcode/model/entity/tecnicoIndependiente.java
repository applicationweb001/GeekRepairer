package com.hampcode.model.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "tecnicoIndependientes")
public class tecnicoIndependiente {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idTecInd;
	private String nombre;
	private int telefono;
	private double montoHora;
	private String estado;
	
	public Long getIdTecInd() {
		return idTecInd;
	}
	public void setIdTecInd(Long idTecInd) {
		this.idTecInd = idTecInd;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public int getTelefono() {
		return telefono;
	}
	public void setTelefono(int telefono) {
		this.telefono = telefono;
	}
	public double getMontoHora() {
		return montoHora;
	}
	public void setMontoHora(double montoHora) {
		this.montoHora = montoHora;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	
	
	
	
	
}
