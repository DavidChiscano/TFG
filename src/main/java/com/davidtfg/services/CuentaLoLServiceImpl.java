package com.davidtfg.services;

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
	public CuentaLoL addCuenta(CuentaLoL cuenta) {
		return cuentaDao.save(cuenta);
	}
	

}
