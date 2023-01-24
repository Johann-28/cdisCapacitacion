package com.cdisEjemploJJVA.springboot.app.models.entity;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "cliente")
public class Cliente implements Serializable {

	
	private static final long serialVersionUID = -8140520579369535007L;

	@Id
	@NotEmpty
	private String idCliente;
	
	@NotEmpty
	@Column
	private String nombre;
	
	@NotEmpty
	@Column
	private String apellido;
	
	@NotEmpty
	@Column(name = "numero_telefonico")
	private String numeroTelefonico;
	
	@NotEmpty
	@Column
	private String email;
	
	public String getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(String idCliente) {
		this.idCliente = idCliente;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getNumeroTelefonico() {
		return numeroTelefonico;
	}

	public void setNumeroTelefonico(String numeroTelefonico) {
		this.numeroTelefonico = numeroTelefonico;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public long getIdCuenta() {
		return idCuenta;
	}

	public void setIdCuenta(long idCuenta) {
		this.idCuenta = idCuenta;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Column
	private long idCuenta;
	
		
}
