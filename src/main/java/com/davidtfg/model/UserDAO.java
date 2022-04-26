package com.davidtfg.model;

import org.springframework.data.jpa.repository.JpaRepository;

import com.davidtfg.entity.User;

public interface UserDAO extends JpaRepository<User, Long>{
	User findByNombreUsuario(String nombre);
}
