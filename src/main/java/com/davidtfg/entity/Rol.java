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

@Entity
@Table(name = "roles")
public class Rol implements Serializable {

	private static final long serialVersionUID = 1L;

	// ATRIBUTOS
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id_rol;
	private String nombre_rol;
	
	
	@ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@JoinTable(name = "usuario_rol", 
	joinColumns = @JoinColumn(name = "id_rol"), 
	inverseJoinColumns = @JoinColumn(name = "id_usuario"))
	private Set<User> usuarios = new HashSet<>();
	
	
	
	//CONSTRUCTORES
	public Rol() {
		super();
	}
	public Rol(long id, String nombre_rol) {
		super();
		this.id_rol = id;
		this.nombre_rol = nombre_rol;
	}
	
	//GETTERS & SETTERS
	public String getNombre_rol() {
		return nombre_rol;
	}
	public void setNombre_rol(String nombre_rol) {
		this.nombre_rol = nombre_rol;
	}
	public Long getId_rol() {
		return id_rol;
	}
	public void setId_rol(Long id_rol) {
		this.id_rol = id_rol;
	}
	public Set<User> getUsuarios() {
		return usuarios;
	}
	public void setUsuarios(Set<User> usuarios) {
		this.usuarios = usuarios;
	}
	public void addUsuario(User usuario) {
		this.usuarios.add(usuario);
		//usuario.getRoles().add(this);
	}
	public void eliminarUsuario(User usuario) {
		this.usuarios.remove(usuario);
	}
	
	
	

}
