package com.davidtfg.services;

import java.util.Set;

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
		CuentaLoL cLoL = cuentaDao.findByUsuario(cuenta.getUsuario());
		if(cLoL == null) {
			UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			User authUser = userDao.findByNombreUsuario(userDetails.getUsername());
			user = userDao.findById(authUser.getId_usuario()).orElse(null);
			user.addCuenta(cuenta);
			cuenta.addUser(user);
			return cuentaDao.save(cuenta);
		}
		return null;
	}
	@Override
	public Set<CuentaLoL> getCuentas(Long idUsuario) {
		User user = userDao.findById(idUsuario).orElse(null);
		return user.getCuentaslol();
	}
	@Override
	public void borrarCuenta(Long id) {
		cuentaDao.deleteById(id);
	}
}
