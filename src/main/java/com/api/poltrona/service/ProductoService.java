package com.api.poltrona.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.api.poltrona.entidades.Foto;
import com.api.poltrona.entidades.Materiales;
import com.api.poltrona.entidades.Producto;

@Service
public class ProductoService {
	@Autowired
	@Qualifier("jdbcTemplate1")
	private JdbcTemplate jdbcTemplate1;

	@Autowired
	private MaterialService ms;

	@Transactional
	public void crearProducto() {

		List<Materiales> materiales = ms.buscarMaterialesParaPortal();
		for (Materiales materiales2 : materiales) {
			String sql = "INSERT INTO `producto`( `codigo_material`, `material`, `nombre_completo`, `stock`, `especificaciones`, `precio_unitario`, `qr`, `visible`,`foto`) VALUES ('"
					+ materiales2.getCodigoMaterial() + "','" + materiales2.getMaterial() + "','"
					+ materiales2.getNombreCompleto() + "','" + materiales2.getStock() + "','"
					+ materiales2.getEspecificaciones() + "','" + materiales2.getPrecioUnitarioVtaTipoMoneda() + "','"
					+ materiales2.getQr() + "','" + materiales2.getV_Catalogo() + "','" + materiales2.getFoto() + "')";
			jdbcTemplate1.execute(sql);
		}
	}

	@Transactional
	public List<Producto> buscarTodosLosProductos() {
		String sql = "SELECT id_Material,codigo_material,material,nombre_completo,foto,stock,especificaciones,precio_unitario,qr,visible FROM producto";
		List<Producto> productos = jdbcTemplate1.query(sql, BeanPropertyRowMapper.newInstance(Producto.class));
		System.out.println("buscamos los productos" + productos);
		return productos;
	}
	/*
	 * private List<Producto> buscarProducto(String codigo) { String sql =
	 * "SELECT idMaterial,codigoMaterial,material,nombreCompleto,stock,especificaciones,precioUnitarioVtaTipoMoneda,qr FROM Materiales where codigoMaterial like '"
	 * + codigo + "'"; List<Producto> productos = jdbcTemplate1.query(sql,
	 * BeanPropertyRowMapper.newInstance(Producto.class)); return productos; }
	 */

	public Producto buscarProductoPorCodigo(String codigomaterial) {
		String sql = "SELECT id_Material,codigo_material,material,nombre_completo,foto,stock,especificaciones,precio_unitario,qr,visible FROM producto";
		List<Producto> productos = jdbcTemplate1.query(sql, BeanPropertyRowMapper.newInstance(Producto.class));
		Producto prod =productos.get(0);
		return prod;
	}

}
