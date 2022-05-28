package com.davidtfg.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.davidtfg.entity.CuentaLoL;
import com.davidtfg.services.CuentaLoLService;

@Controller
public class Index {
	@Autowired
	CuentaLoLService cuentaService;

	
	@GetMapping("/index")
	public String getIndex(Model model, HttpServletRequest request) {
		model.addAttribute("usuario", request.getAttribute("usuario.nombre"));
		return "index";
	}
	
	//CREAR OFERTA
	@PostMapping("/index/guardarCuenta")
	public ResponseEntity<CuentaLoL> obtenerCuenta(@RequestBody CuentaLoL cuentalol) {	
		CuentaLoL addCuenta = cuentaService.addCuenta(cuentalol);
		
		ResponseEntity<CuentaLoL> resp = new ResponseEntity<CuentaLoL>(addCuenta, HttpStatus.OK);
		return resp;
    }

}
