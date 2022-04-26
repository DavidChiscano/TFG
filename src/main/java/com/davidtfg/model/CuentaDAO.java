package com.davidtfg.model;

import org.springframework.data.jpa.repository.JpaRepository;

import com.davidtfg.entity.CuentaLoL;

public interface CuentaDAO extends JpaRepository<CuentaLoL, Long> {

}
