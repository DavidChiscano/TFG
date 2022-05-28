package com.davidtfg.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "usuarios")
public class User implements Serializable{
	private static final long serialVersionUID = 1L;
	
	// ATRIBUTOS
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id_usuario;
	@Column(name = "nombre")
	private String nombreUsuario;
	@Column(name = "passwd")
	private String passwd;
	@Column(name = "email")
	private String email;
	@Column(name = "fecha_nacimiento")
	private String fechaNacimiento;
	
	@ManyToMany(fetch = FetchType.EAGER, cascade = { CascadeType.PERSIST, CascadeType.MERGE },mappedBy="usuarios")
	private Set<Rol> roles = new HashSet<>();
	@JsonIgnore
	@ManyToMany(fetch = FetchType.EAGER, cascade = { CascadeType.PERSIST, CascadeType.MERGE },mappedBy = "users")
	private Set<CuentaLoL> cuentaslol = new HashSet<>();

	//CONSTRUCTORES
	public User(Long id, String nombre, String passwd, String email,String fechaNacimiento) {
		super();
		this.id_usuario = id;
		this.nombreUsuario = nombre;
		this.email = email;
		this.passwd = passwd;
		this.fechaNacimiento = fechaNacimiento;
	}
	
	public String getNombreUsuario() {
		return nombreUsuario;
	}

	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}

	public User() {
		super();
	}

	// GETTERS & SETTERS
	
	public Set<CuentaLoL> getCuentasLoL() {
		return cuentaslol;
	}

	public void setCuentaLoLUser(Set<CuentaLoL> cuentaslol) {
		this.cuentaslol = cuentaslol;
	}
	
	public String getNombre() {
		return nombreUsuario;
	}

	public Long getId_usuario() {
		return id_usuario;
	}

	public void setId_usuario(Long id_usuario) {
		this.id_usuario = id_usuario;
	}

	public void setNombre(String nombre) {
		this.nombreUsuario = nombre;
	}
	
	public Set<Rol> getRoles() {
		return roles;
	}

	public void setRoles(Set<Rol> roles) {
		this.roles = roles;
	}

	public Set<CuentaLoL> getCuentaslol() {
		return cuentaslol;
	}

	public void setCuentaslol(Set<CuentaLoL> cuentaslol) {
		this.cuentaslol = cuentaslol;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPasswd() {
		return passwd;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}

	public String getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(String fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}
	
	public boolean addRol(Rol rol) {
	    rol.addUsuario(this);
		return getRoles().add(rol);
	}

	public void eliminarRol(Rol rol) {
		this.roles.remove(rol);
		rol.getUsuarios().remove(this);
	}
	
	public boolean addCuenta(CuentaLoL cuenta) {
		cuenta.addUser(this);
		return getCuentasLoL().add(cuenta);
	}
	
	public void eliminarCuenta(CuentaLoL cuenta) {
		this.cuentaslol.remove(cuenta);
		cuenta.getUsers().remove(this);
	}
	

}
