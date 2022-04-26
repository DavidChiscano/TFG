package com.davidtfg.services;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.davidtfg.entity.Rol;
import com.davidtfg.entity.User;
import com.davidtfg.model.RolDAO;
import com.davidtfg.model.UserDAO;

@Transactional
@Service
public class UserServiceImpl implements UserService{
	@Autowired 
	UserDAO userDao;
	@Autowired
	RolDAO rolDao;
	@Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Override
	public User obtenerUsuarioPorNombre(String nombre) {
		return userDao.findByNombreUsuario(nombre);
	}

	@Override
	public User crearUsuario(User user) {
		Rol rolUser = rolDao.getById(1L);
		user.addRol(rolUser);
		user.setPasswd(bCryptPasswordEncoder.encode(user.getPasswd()));
		return userDao.save(user);
	}

}
