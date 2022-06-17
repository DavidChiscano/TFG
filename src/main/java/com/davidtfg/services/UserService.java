package com.davidtfg.services;

import com.davidtfg.entity.User;

public interface UserService {
	User obtenerUsuarioPorNombre(String nombreUsuario);
	User crearUsuario(User user);
}
