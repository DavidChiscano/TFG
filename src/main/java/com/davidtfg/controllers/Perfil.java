package com.davidtfg.controllers;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import com.davidtfg.entity.CuentaLoL;
import com.davidtfg.services.CuentaLoLService;

@Controller
public class Perfil {
	@Autowired 
	CuentaLoLService cuentaLoLService;
	
	@GetMapping("/perfil/{id}")
	public String getPerfil(Model model, @PathVariable("id") Long id) {
		Set<CuentaLoL> ListaCuentas = cuentaLoLService.getCuentas(id);
		model.addAttribute("ListaCuentas", ListaCuentas);
		return "perfil";
	}
	@ResponseBody
	@GetMapping("/perfil/borrar/{id}")
	public void getBorrarIdCuenta(@PathVariable("id") long id) {
		cuentaLoLService.borrarCuenta(id);
	}

	
}
