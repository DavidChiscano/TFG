package com.davidtfg.services;

import java.util.HashSet;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.davidtfg.entity.Rol;
import com.davidtfg.entity.User;
import com.davidtfg.model.UserDAO;


@Transactional
@Service
public class CustomUserDetailsService implements UserDetailsService{
	@Autowired
	private UserDAO userDao;
	@Override
	public UserDetails loadUserByUsername(String nombre) throws UsernameNotFoundException {
		Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
		User user = userDao.findByNombreUsuario(nombre);
		if(user != null) {
			for (Rol rol : user.getRoles()) {
				grantedAuthorities.add(new SimpleGrantedAuthority(rol.getNombre_rol()));
			}
			return new org.springframework.security.core.userdetails.User(user.getNombre(), user.getPasswd(),
					grantedAuthorities);
		}else {
			 throw new UsernameNotFoundException("El usuario no existe");
		}
	}
}
