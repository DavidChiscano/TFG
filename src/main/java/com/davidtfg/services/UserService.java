package com.davidtfg.services;

import com.davidtfg.entity.User;

public interface UserService {
	public User obtenerUsuarioPorNombre(String nombreUsuario);
	public User crearUsuario(User user);
}
