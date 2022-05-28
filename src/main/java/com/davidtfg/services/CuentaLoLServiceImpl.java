package com.davidtfg.services;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.davidtfg.entity.CuentaLoL;
import com.davidtfg.entity.User;
import com.davidtfg.model.CuentaDAO;
import com.davidtfg.model.UserDAO;

@Transactional
@Service
public class CuentaLoLServiceImpl implements CuentaLoLService {
	@Autowired
	CuentaDAO cuentaDao;
	@Autowired
	UserDAO userDao;

	@Override
	public CuentaLoL addCuenta(CuentaLoL cuenta) {
		User user = new User();

		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		User authUser = userDao.findByNombreUsuario(userDetails.getUsername());

		user = userDao.findById(authUser.getId_usuario()).orElse(null);

		user.addCuenta(cuenta);
		cuenta.addUser(user);
		return cuentaDao.save(cuenta);
	}

}
