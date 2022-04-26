package com.davidtfg.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.davidtfg.entity.CuentaLoL;
import com.davidtfg.model.CuentaDAO;

@Transactional
@Service
public class CuentaLoLServiceImpl implements CuentaLoLService {
	@Autowired
	CuentaDAO cuentaDao;
	
	@Override
	public List<CuentaLoL> obtenerTodas() {
		return cuentaDao.findAll();
	}

}
