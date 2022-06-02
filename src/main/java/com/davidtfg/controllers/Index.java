package com.davidtfg.controllers;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.davidtfg.entity.CuentaLoL;
import com.davidtfg.entity.User;
import com.davidtfg.services.CuentaLoLService;

@Controller
public class Index {
	@Autowired
	CuentaLoLService cuentaService;

	@GetMapping("/index")
	public String getIndex(@SessionAttribute("usuario") User usuario, Model model, HttpServletRequest request) {
		request.getSession().setAttribute("usuario",usuario);
		return "index";
	}

	// Guardar cuenta en base de datos
	@PostMapping("/index/guardarCuenta")
	public ResponseEntity<CuentaLoL> obtenerCuenta(@RequestBody Map<String, String> json) {
		CuentaLoL cuenta = new CuentaLoL();
		cuenta.setUsuario(json.get("usuario"));
		cuentaService.addCuenta(cuenta);
		ResponseEntity<CuentaLoL> resp = new ResponseEntity<CuentaLoL>(cuenta, HttpStatus.OK);
		return resp;
	}

}
