package com.davidtfg.model;

import org.springframework.data.jpa.repository.JpaRepository;

import com.davidtfg.entity.CuentaLoL;
import com.davidtfg.entity.User;

public interface CuentaDAO extends JpaRepository<CuentaLoL, Long> {
	CuentaLoL findByUsuario(String usuario);
}
