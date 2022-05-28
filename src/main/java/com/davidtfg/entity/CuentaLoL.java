package com.davidtfg.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "cuentaslol")
public class CuentaLoL implements Serializable{

	private static final long serialVersionUID = 1L;
	
	// ATRIBUTOS
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_cuenta")
	private Long id_cuenta;
	
	private String usuario;
	@ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@JoinTable(name = "usuario_cuentas", 
	joinColumns = @JoinColumn(name = "id_cuenta"), 
	inverseJoinColumns = @JoinColumn(name = "id_usuario"))
	@JsonIgnore
	private Set<User> users = new HashSet<>();
	
	// CONSTRUCTORES
	public CuentaLoL() {
		super();
	}

	public CuentaLoL(long id, String usuarios) {
		super();
		this.id_cuenta = id;
		this.usuario = usuarios;
	}
	public CuentaLoL(String usuarios) {
		super();
		this.usuario = usuarios;
	}
	// GETTERS & SETTERS
	
	public String getUsuario() {
		return usuario;
	}

	public Set<User> getUsers() {
		return users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
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
	
	public void addUser(User user) {
		this.users.add(user);
		user.getCuentaslol().add(this);
	}
	public void eliminarUser(User user) {
		this.users.remove(user);
	}
	
	
}
