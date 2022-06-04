package com.davidtfg.services;
import java.util.Set;
import com.davidtfg.entity.CuentaLoL;

public interface CuentaLoLService {
	CuentaLoL addCuenta(CuentaLoL cuenta);
	Set<CuentaLoL> getCuentas(Long idUsuario);
}
