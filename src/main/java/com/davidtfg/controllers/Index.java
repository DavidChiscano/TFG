package com.davidtfg.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.davidtfg.entity.CuentaLoL;
import com.davidtfg.services.CuentaLoLService;

@Controller
public class Index {
	@Autowired
	CuentaLoLService cuentaService;
	
	@GetMapping("/index")
	public String getIndex(Model model, HttpServletRequest request) {
		model.addAttribute("usuario", request.getAttribute("usuario.nombre"));
		List<CuentaLoL> ListaCuentas = cuentaService.obtenerTodas();
		model.addAttribute("ListaCuentas", ListaCuentas);
		return "index";
	}

}
