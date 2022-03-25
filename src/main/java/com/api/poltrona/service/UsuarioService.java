package com.api.poltrona.service;


import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.api.poltrona.entidades.Usuario;



@Transactional
@Repository
public class UsuarioService implements UserDetailsService{
 
 @Autowired
 @Qualifier("jdbcTemplate1")
 private JdbcTemplate jdbcTemplate1;
 
 @Autowired
 @Qualifier("jdbcTemplate2")
 private JdbcTemplate jdbcTemplate2;

 public List<Usuario> buscarUsuario(String mail) {
	 String sql1 = "select * from usuario where mail like '"+mail+"'";
	 List<Usuario> usuario= jdbcTemplate1.query(sql1, BeanPropertyRowMapper.newInstance(Usuario.class));
	 return usuario;
 }


 @Override
 public UserDetails loadUserByUsername(String mail) {
     
	 String sql1 = "select * from usuario where mail like '"+mail+"'";
	 List<Usuario> usuarios= jdbcTemplate1.query(sql1, BeanPropertyRowMapper.newInstance(Usuario.class));
	 Usuario user= null;
	 for (Usuario usuario2 : usuarios) {
		user = usuario2;
	}
	 
     if (user != null) {
         
         List<GrantedAuthority> permisos = new ArrayList<>();
         GrantedAuthority p1 = new SimpleGrantedAuthority("ROLE_" + user.getRole().toString());
         permisos.add(p1);      
         ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
         HttpSession session = attr.getRequest().getSession(true);
         session.setAttribute("usuariosession", user);
         session.setAttribute("entro", "entro");
         return new User(user.getMail(), user.getPassword(), permisos);
     } else {
         return null;
     }
 }

}