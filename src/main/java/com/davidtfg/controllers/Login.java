package com.davidtfg.controllers;

import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.davidtfg.entity.CuentaLoL;
import com.davidtfg.entity.User;
import com.davidtfg.services.UserService;

@Controller
public class Login {
	
	@Autowired
	UserService userService;
	
	@RequestMapping(value = { "/","login"}, method = RequestMethod.GET)
	public String getLogin() {
		return "login";
	}
	@GetMapping("/registro")
	public String getRegistro() {
		return "registro";
	}
	@PostMapping("/registro")
	public String crearUsuario(@RequestParam String usuario, @RequestParam String password,@RequestParam String email,@RequestParam String fechaNacimiento) {
			User u = new User();
			u.setNombre(usuario);
			u.setPasswd(password);
			u.setEmail(email);
			u.setFechaNacimiento(fechaNacimiento);
			u.setCuentaslol(new HashSet<CuentaLoL>());
			userService.crearUsuario(u);
			return "redirect:/login";
	}
}
