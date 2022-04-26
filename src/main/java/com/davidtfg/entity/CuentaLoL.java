package com.davidtfg.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "cuentaslol")
public class CuentaLoL implements Serializable{

	private static final long serialVersionUID = 1L;
	
	// ATRIBUTOS
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id_cuenta;
	
	private String usuario;
	
	@ManyToOne(fetch = FetchType.LAZY) 
    @JoinColumn(name = "id_usuario")
	private User cuentaslol;
	
	// CONSTRUCTORES
	public CuentaLoL() {
		super();
	}

	public CuentaLoL(long id, String usuarios) {
		super();
		this.id_cuenta = id;
		this.usuario = usuarios;
	}
	// GETTERS & SETTERS
	
	
	
	public User getCuentaslol() {
		return cuentaslol;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public void setCuentaslol(User cuentaslol) {
		this.cuentaslol = cuentaslol;
	}
	

	public Long getId_cuenta() {
		return id_cuenta;
	}

	public void setId_cuenta(Long id_cuenta) {
		this.id_cuenta = id_cuenta;
	}

	public String getUsuarios() {
		return usuario;
	}

	public void setUsuarios(String usuarios) {
		this.usuario = usuarios;
	}
	
}
