package com.api.poltrona.service;

import java.sql.Blob;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.api.poltrona.entidades.Materiales;
import com.api.poltrona.entidades.Producto;

@Service
public class ProductoService extends DAO {
	@Autowired
	@Qualifier("jdbcTemplate1")
	private JdbcTemplate jdbcTemplate1;

	@Autowired
	private MaterialService ms;
	@Autowired
	private DAO2 ds;
	@Autowired(required = true)
	private DAO3 ds3;
	@Autowired(required = true)
	private DAO4 ds4;

	@Transactional
	public void crearProductoRufino() throws Exception {

		List<Materiales> materiales = ms.buscarMaterialesParaPortal();
		String codigo = null;
		String sqlproduc = "SELECT * FROM producto ";
		List<String> codigos = new ArrayList<>();
		List<Materiales> codigosGuardados = new ArrayList<>();
		List<Materiales> codigosNuevos = new ArrayList<>();

		consultarBase(sqlproduc);
		while (resultado.next()) {
			codigo = resultado.getString("codigo_material");
			// System.out.println("lalala");
			codigos.add(codigo);
			for (Materiales materiales2 : materiales) {

				if (codigo.equals(materiales2.getCodigoMaterial())) {
					System.out.println("los codigos q vamos agregando a guardado :" + materiales2.getCodigoMaterial());
					codigosGuardados.add(materiales2);
				}
			}
		}
		
		//desconectarBase();
		codigosNuevos = materiales.stream().filter(f -> !codigosGuardados.contains(f)).collect(Collectors.toList());
		System.out.println(codigosNuevos);

		System.out.println(codigosNuevos.size());
		codigosNuevos.forEach((n) -> System.out.println(n.getCodigoMaterial()));
		System.out.println("los codigos nuevos son :" + codigosGuardados.size());
		//ds.conectarBase();
		for (Materiales string : codigosNuevos) {
			//System.out.println("los codigos nuevos son :" + string.getCodigoMaterial() + string.getMaterial());
			System.out.println("los codigos nuevos son :" + codigosNuevos.size());
			crearProductoNuevoRufino(string);
		}
		for (Materiales string : codigosGuardados) {
			//System.out.println("los codigos guardados son :" + string.getCodigoMaterial() + string.getMaterial());
			System.out.println("los codigos guardados son :" + codigosGuardados.size());
			modificarProductoRufino(string);
		}
	}

	private void modificarProductoRufino(Materiales string) throws Exception {
		//Foto img = new Foto();
		String sql = "SELECT qr,foto FROM materiales where codigoMaterial like '" + string.getCodigoMaterial() + "'";
		ds.consultarBase(sql);
		//byte[] recuperado = null;
		while (ds.resultado.next()) {
			Blob foto = ds.resultado.getBlob("foto");
			Blob qr = ds.resultado.getBlob("qr");
			String sql2 = "UPDATE producto SET codigo_material = ?, material = ? , nombre_completo = ?, stock_rufino = ?, especificaciones =?, precio_unitario = ? , qr = ?, visible = ? , foto = ? WHERE codigo_material = ? ";
			PreparedStatement ps = conexion.prepareStatement(sql2);
			ps.setString(1, string.getCodigoMaterial());
			ps.setString(2, string.getMaterial());
			ps.setString(3, string.getNombreCompleto());
			ps.setString(4, string.getStock());
			ps.setString(5, string.getEspecificaciones());
			ps.setInt(6, string.getPrecioUnitarioVtaTipoMoneda());
			ps.setBlob(7, qr);
			ps.setString(8, string.getV_Catalogo());
			ps.setBlob(9, foto);
			ps.setString(10, string.getCodigoMaterial());
			ps.executeUpdate();
		}
	
	}

	private void crearProductoNuevoRufino(Materiales string) throws Exception {

		String sql = "SELECT qr,foto FROM materiales where codigoMaterial like '" + string.getCodigoMaterial() + "'";
		ds.consultarBase(sql);
		while (ds.resultado.next()) {
			Blob foto = ds.resultado.getBlob("foto");
			Blob qr = ds.resultado.getBlob("qr");
			String sql2 = "INSERT INTO `producto`( `codigo_material`, `material`, `nombre_completo`, `stock_rufino`, `especificaciones`, `precio_unitario`, `qr`, `visible`,`foto`) VALUES (?,?,?,? ,?,?,?,?,?)";
			PreparedStatement ps = conexion.prepareStatement(sql2);
			ps.setString(1, string.getCodigoMaterial());
			ps.setString(2, string.getMaterial());
			ps.setString(3, string.getNombreCompleto());
			ps.setString(4, string.getStock());
			ps.setString(5, string.getEspecificaciones());
			ps.setInt(6, string.getPrecioUnitarioVtaTipoMoneda());
			ps.setBlob(7, qr);
			ps.setString(8, string.getV_Catalogo());
			ps.setBlob(9, foto);
			ps.executeUpdate();
		}
	}

	public void crearProductoBarraca() throws Exception {

		List<Materiales>materiales=ms.buscarMaterialBarraca();
		System.out.println(materiales.size());
		String codigo = null;
		String sqlproduc = "SELECT * FROM producto ";
		//List<String> codigos = new ArrayList<>();
		List<Materiales> codigosGuardados = new ArrayList<>();
		List<Materiales> codigosNuevos = new ArrayList<>();

		consultarBase(sqlproduc);
		while (resultado.next()) {
			codigo = resultado.getString("codigo_material");
			//codigos.add(codigo);
			for (Materiales materiales2 : materiales) {

				if (codigo.equals(materiales2.getCodigoMaterial())) {
					System.out.println("los codigos q vamos agregando a guardado :" + materiales2.getCodigoMaterial());
					codigosGuardados.add(materiales2);
				}
			}
		}
		//desconectarBase();
		codigosNuevos = materiales.stream().filter(f -> !codigosGuardados.contains(f)).collect(Collectors.toList());
		System.out.println(codigosNuevos);

		System.out.println(codigosNuevos.size());
		codigosNuevos.forEach((n) -> System.out.println(n.getCodigoMaterial()));
		System.out.println("los codigos nuevos son :" + codigosGuardados.size());
		//ds.conectarBase();
		for (Materiales string : codigosNuevos) {
			//System.out.println("los codigos nuevos son :" + string.getCodigoMaterial() + string.getMaterial());
			System.out.println("los codigos nuevos son :" + codigosNuevos.size());
			crearProductoNuevoBarraca(string);
		}
		for (Materiales string : codigosGuardados) {
			//System.out.println("los codigos guardados son :" + string.getCodigoMaterial() + string.getMaterial());
			System.out.println("los codigos guardados son :" + codigosGuardados.size());
			modificarProductoBarraca(string);
		}
		desconectarBase();
	}

	private void modificarProductoBarraca(Materiales string) throws Exception {
		//Foto img = new Foto();
		String sql = "SELECT qr,foto FROM materiales where codigoMaterial like '" + string.getCodigoMaterial() + "'";
		ds3.consultarBase(sql);
		//byte[] recuperado = null;
		while (ds3.resultado.next()) {
			Blob foto = ds3.resultado.getBlob("foto");
			Blob qr = ds3.resultado.getBlob("qr");
			String sql2 = "UPDATE producto SET codigo_material = ?, material = ? , nombre_completo = ?, stock_barracas = ?, especificaciones =?, precio_unitario = ? , qr = ?, visible = ? , foto = ? WHERE codigo_material = ? ";
			//conectarBase();
			PreparedStatement ps = conexion.prepareStatement(sql2);
			ps.setString(1, string.getCodigoMaterial());
			ps.setString(2, string.getMaterial());
			ps.setString(3, string.getNombreCompleto());
			ps.setString(4, string.getStock());
			ps.setString(5, string.getEspecificaciones());
			ps.setInt(6, string.getPrecioUnitarioVtaTipoMoneda());
			ps.setBlob(7, qr);
			ps.setString(8, string.getV_Catalogo());
			ps.setBlob(9, foto);
			ps.setString(10, string.getCodigoMaterial());
			ps.executeUpdate();
		}
	}
	private void crearProductoNuevoBarraca(Materiales string) throws Exception {


		String sql = "SELECT qr,foto FROM materiales where codigoMaterial like '" + string.getCodigoMaterial() + "'";
		ds3.consultarBase(sql);
		while (ds3.resultado.next()) {
			Blob foto = ds3.resultado.getBlob("foto");
			Blob qr = ds3.resultado.getBlob("qr");
			String sql2 = "INSERT INTO `producto`( `codigo_material`, `material`, `nombre_completo`, `stock_barracas`, `especificaciones`, `precio_unitario`, `qr`, `visible`,`foto`) VALUES (?,?,?,? ,?,?,?,?,?)";
			PreparedStatement ps = conexion.prepareStatement(sql2);
			ps.setString(1, string.getCodigoMaterial());
			ps.setString(2, string.getMaterial());
			ps.setString(3, string.getNombreCompleto());
			ps.setString(4, string.getStock());
			ps.setString(5, string.getEspecificaciones());
			ps.setInt(6, string.getPrecioUnitarioVtaTipoMoneda());
			ps.setBlob(7, qr);
			ps.setString(8, string.getV_Catalogo());
			ps.setBlob(9, foto);
			ps.executeUpdate();
		}
	}

	@Transactional
	public void crearProductoPalmares() throws Exception {

		List<Materiales> materiales = ms.buscarMaterialPalmares();
		String codigo = null;
		String sqlproduc = "SELECT * FROM producto ";
		//List<String> codigos = new ArrayList<>();
		List<Materiales> codigosGuardados = new ArrayList<>();
		List<Materiales> codigosNuevos = new ArrayList<>();

		consultarBase(sqlproduc);
		while (resultado.next()) {
			codigo = resultado.getString("codigo_material");
			// System.out.println("lalala");
			//codigos.add(codigo);
			for (Materiales materiales2 : materiales) {

				if (codigo.equals(materiales2.getCodigoMaterial())) {
					System.out.println("los codigos q vamos agregando a guardado :" + materiales2.getCodigoMaterial());
					codigosGuardados.add(materiales2);
				}
			}
		}
		codigosNuevos = materiales.stream().filter(f -> !codigosGuardados.contains(f)).collect(Collectors.toList());
		System.out.println(codigosNuevos);

		System.out.println(codigosNuevos.size());
		codigosNuevos.forEach((n) -> System.out.println(n.getCodigoMaterial()));
		System.out.println("los codigos nuevos son :" + codigosGuardados.size());
		for (Materiales string : codigosNuevos) {
			//System.out.println("los codigos nuevos son :" + string.getCodigoMaterial() + string.getMaterial());
			System.out.println("los codigos nuevos son :" + codigosNuevos.size());
			crearProductoNuevoPalmares(string);
		}
		for (Materiales string : codigosGuardados) {
			//System.out.println("los codigos guardados son :" + string.getCodigoMaterial() + string.getMaterial());
			System.out.println("los codigos guardados son :" + codigosGuardados.size());
			modificarProductoPalmares(string);
		}
	}

	private void modificarProductoPalmares(Materiales string) throws Exception {
		//Foto img = new Foto();
		String sql = "SELECT qr,foto FROM materiales where codigoMaterial like '" + string.getCodigoMaterial() + "'";
		ds4.consultarBase(sql);
		//byte[] recuperado = null;
		while (ds4.resultado.next()) {
			Blob foto = ds4.resultado.getBlob("foto");
			Blob qr = ds4.resultado.getBlob("qr");
			String sql2 = "UPDATE producto SET codigo_material = ?, material = ? , nombre_completo = ?, stock_rufino = ?, especificaciones =?, precio_unitario = ? , qr = ?, visible = ? , foto = ? WHERE codigo_material = ? ";
			//conectarBase();
			PreparedStatement ps = conexion.prepareStatement(sql2);
			ps.setString(1, string.getCodigoMaterial());
			ps.setString(2, string.getMaterial());
			ps.setString(3, string.getNombreCompleto());
			ps.setString(4, string.getStock());
			ps.setString(5, string.getEspecificaciones());
			ps.setInt(6, string.getPrecioUnitarioVtaTipoMoneda());
			ps.setBlob(7, qr);
			ps.setString(8, string.getV_Catalogo());
			ps.setBlob(9, foto);
			ps.setString(10, string.getCodigoMaterial());
			ps.executeUpdate();
		}
	}

	private void crearProductoNuevoPalmares(Materiales string) throws Exception {

		String sql = "SELECT qr,foto FROM materiales where codigoMaterial like '" + string.getCodigoMaterial() + "'";
		ds4.consultarBase(sql);
		while (ds4.resultado.next()) {
			Blob foto = ds4.resultado.getBlob("foto");
			Blob qr = ds4.resultado.getBlob("qr");
			String sql2 = "INSERT INTO `producto`( `codigo_material`, `material`, `nombre_completo`, `stock_rufino`, `especificaciones`, `precio_unitario`, `qr`, `visible`,`foto`) VALUES (?,?,?,? ,?,?,?,?,?)";
			PreparedStatement ps = conexion.prepareStatement(sql2);
			ps.setString(1, string.getCodigoMaterial());
			ps.setString(2, string.getMaterial());
			ps.setString(3, string.getNombreCompleto());
			ps.setString(4, string.getStock());
			ps.setString(5, string.getEspecificaciones());
			ps.setInt(6, string.getPrecioUnitarioVtaTipoMoneda());
			ps.setBlob(7, qr);
			ps.setString(8, string.getV_Catalogo());
			ps.setBlob(9, foto);
			ps.executeUpdate();
		}
	}

	public void modificarStock() {
		
	}
	@Transactional
	public List<Producto> buscarTodosLosProductos() {
		String sql = "SELECT id_Material,codigo_material,material,nombre_completo,stock_rufino,stock_barracas,stock_palmares,especificaciones,precio_unitario,visible FROM producto";
		List<Producto> productos = jdbcTemplate1.query(sql, BeanPropertyRowMapper.newInstance(Producto.class));
		System.out.println("buscamos los productos" + productos);
		return productos;
	}

	public Producto buscarProductoPorCodigo(String codigomaterial) {
		String sql = "SELECT id_Material,codigo_material,material,nombre_completo,stock_palmares,stock_rufino,stock_barracas,especificaciones,precio_unitario,visible FROM producto WHERE codigo_material like '"
				+ codigomaterial + "'";
		List<Producto> productos = jdbcTemplate1.query(sql, BeanPropertyRowMapper.newInstance(Producto.class));
		Producto prod = productos.get(0);
		return prod;
	}

	public List<Producto> buscarProductoPorCategoria(String categoria) {
		String sql = "SELECT codigo_material,material,precio_unitario FROM producto where (nombre_completo like '"
				+ categoria + "%') AND (precio_unitario > 0)";
		List<Producto> productos = jdbcTemplate1.query(sql, BeanPropertyRowMapper.newInstance(Producto.class));
		return productos;
	}

	public void buscarStockBarracas() throws Exception {
		String sql = "SELECT codigoMaterial,stock FROM Materiales";
	
		ds3.consultarBase(sql);
		List<Materiales> pp = new ArrayList<>();
		while (ds3.resultado.next()) {
			System.out.println("el resultado es " + ds3.resultado.getString(1));
			Materiales mat = new Materiales();
			mat.setCodigoMaterial(ds3.resultado.getString(1));
			mat.setStock(ds3.resultado.getString(2));
			pp.add(mat);
			System.out.println("el resultado es " + ds3.resultado.getString(2));
			
		}
		ds3.desconectarBase();
		conectarBase();
		System.out.println("pasamos a barracas");
		for (Materiales materiales : pp) {
			String sql2 = "UPDATE producto SET stock_barracas = ? WHERE codigo_material = ? ";
			PreparedStatement ps = conexion.prepareStatement(sql2);
			ps.setString(1, materiales.getStock());
			ps.setString(2, materiales.getCodigoMaterial());
			ps.executeUpdate();
			System.out.println("modiciamos : "+materiales.getCodigoMaterial());
			
		}
		System.out.println("el total de los productos es : " + pp.size());
		desconectarBase();
	}
	public void buscarStockRufino() throws Exception {
		String sql = "SELECT codigoMaterial,stock FROM Materiales";
		ds.consultarBase(sql);
		List<Materiales> pp = new ArrayList<>();
		while (ds.resultado.next()) {
			Materiales mat = new Materiales();
			mat.setCodigoMaterial(ds.resultado.getString(1));
			mat.setStock(ds.resultado.getString(2));
			
			pp.add(mat);
			System.out.println("el resultado es " + ds.resultado.getString(2));		
		}
		ds.desconectarBase();
		conectarBase();
		for (Materiales materiales : pp) {
			String sql2 = "UPDATE producto SET stock_rufino = ? WHERE codigo_material = ? ";
			
			PreparedStatement ps = conexion.prepareStatement(sql2);
			ps.setString(1, materiales.getStock());
			ps.setString(2, materiales.getCodigoMaterial());
			ps.executeUpdate();	
			System.out.println("modiciamos : "+materiales.getCodigoMaterial());
		}
		
		
		System.out.println("el total de los productos es : " + pp.size());
		desconectarBase();
	}
}
