package com.davidtfg.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.davidtfg.entity.CuentaLoL;

@Controller
public class ObtenerDatosPerfil {
	@ResponseBody
	@GetMapping("https://euw1.api.riotgames.com/lol/summoner/v4/summoners/by-name/0kame")
	public String getDatos(Model model, HttpServletRequest request) {
		return "index";
	}
}
