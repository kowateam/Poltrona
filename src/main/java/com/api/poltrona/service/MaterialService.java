package com.api.poltrona.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.api.poltrona.entidades.Materiales;

@Lazy
@Service
public class MaterialService {

	 @Autowired
	 @Qualifier("jdbcTemplate2")
	 private JdbcTemplate jdbcTemplate2;
	 @Autowired
	 @Qualifier("jdbcTemplate3")
	 private JdbcTemplate jdbcTemplate3;
	 @Autowired
	 @Qualifier("jdbcTemplate4")
	 private JdbcTemplate jdbcTemplate4;
	 
	public List<Materiales>getAll(){
		 String sql = "SELECT codigoMaterial,material FROM Materiales WHERE precioUnitarioVtaTipoMoneda > 0";
		 List<Materiales> materiales= jdbcTemplate2.query(sql, BeanPropertyRowMapper.newInstance(Materiales.class));
		 	
		 return materiales;
	 };
	 
	 public List<Materiales> buscarMateriales(){
		 String sql = "SELECT codigoMaterial,material,precioUnitarioVtaTipoMoneda FROM Materiales WHERE precioUnitarioVtaTipoMoneda > 0";
		 List<Materiales> materiales= jdbcTemplate2.query(sql, BeanPropertyRowMapper.newInstance(Materiales.class));
		 return materiales;
	 }
	 
	 public List<Materiales> buscarMaterial(String codigoMaterial){
		 String sql = "SELECT idMaterial,codigoMaterial,material,nombreCompleto,stock,especificaciones,precioUnitarioVtaTipoMoneda,qr,v_catalogo  FROM Materiales where codigoMaterial like '" +codigoMaterial+"'";
		 List<Materiales> materiales= jdbcTemplate2.query(sql, BeanPropertyRowMapper.newInstance(Materiales.class));
		 return materiales;
	 }
	 
	 
	 public List<Materiales> buscarMaterialPorNombre(String nombreMaterial){
		 String sql = "SELECT idMaterial,codigoMaterial,material,nombreCompleto,stock,especificaciones,precioUnitarioVtaTipoMoneda,qr FROM Materiales where material like '" +nombreMaterial+"'";
		 List<Materiales> materiales= jdbcTemplate2.query(sql, BeanPropertyRowMapper.newInstance(Materiales.class));
		 for (Materiales materiales2 : materiales) {
			
			 System.out.println(materiales2.getMaterial());
			 System.out.println(materiales2.getPrecioUnitarioVtaTipoMoneda());
			 
		}
		 return materiales;
	 }

	public List<Materiales> buscarMaterialesPorCategoria(String categoria) {
		 String sql = "SELECT codigoMaterial,material,precioUnitarioVtaTipoMoneda FROM Materiales where (nombreCompleto like '" +categoria+"%') AND (precioUnitarioVtaTipoMoneda > 0)";
		 List<Materiales> materiales= jdbcTemplate2.query(sql, BeanPropertyRowMapper.newInstance(Materiales.class));
		 return materiales;
	} 
	
	public List<Materiales> buscadorMaterial(String categoria) {
		 String sql = "SELECT codigoMaterial,material,precioUnitarioVtaTipoMoneda FROM Materiales where (precioUnitarioVtaTipoMoneda > 0) AND ((nombreCompleto like '" +categoria+"%') OR (material like '" +categoria+"%')) ";
		 List<Materiales> materiales= jdbcTemplate2.query(sql, BeanPropertyRowMapper.newInstance(Materiales.class));
		 return materiales;
	}

	public List<Materiales> buscarMaterialesParaPortal() {
		 String sql = "SELECT idMaterial,codigoMaterial,material,nombreCompleto,stock,especificaciones,precioUnitarioVtaTipoMoneda,v_Catalogo FROM Materiales WHERE precioUnitarioVtaTipoMoneda > 0";
		 List<Materiales> materiales= jdbcTemplate2.query(sql, BeanPropertyRowMapper.newInstance(Materiales.class));
		 return materiales;
	}

	public List<Materiales> buscarFotoMaterial() {
		 String sql = "SELECT foto FROM Materiales WHERE precioUnitarioVtaTipoMoneda > 0";
		 List<Materiales> materiales= jdbcTemplate2.query(sql, BeanPropertyRowMapper.newInstance(Materiales.class));
		 return materiales;
		
	}

	public List<Materiales> buscarMaterialBarraca() {
		String sql = "SELECT idMaterial,codigoMaterial,material,nombreCompleto,stock,especificaciones,precioUnitarioVtaTipoMoneda,v_Catalogo FROM Materiales WHERE precioUnitarioVtaTipoMoneda > 0";
		 List<Materiales> materiales= jdbcTemplate3.query(sql, BeanPropertyRowMapper.newInstance(Materiales.class));
		 return materiales;
	}

	public List<Materiales> buscarMaterialPalmares() {
		String sql = "SELECT idMaterial,codigoMaterial,material,nombreCompleto,stock,especificaciones,precioUnitarioVtaTipoMoneda,v_Catalogo FROM Materiales WHERE precioUnitarioVtaTipoMoneda > 0";
		 List<Materiales> materiales= jdbcTemplate4.query(sql, BeanPropertyRowMapper.newInstance(Materiales.class));
		 return materiales;
	}
	


	
}
