package com.davidtfg.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class Perfil {
	@GetMapping("/perfil/{id}")
	public String getPerfil() {
		return "perfil";
	}
}
