package com.davidtfg.model;

import org.springframework.data.jpa.repository.JpaRepository;

import com.davidtfg.entity.Rol;

public interface RolDAO extends JpaRepository<Rol, Long> {

}
