package com.api.poltrona.entidades;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;

@Entity
public class Producto {
	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	private Integer IdMaterial;
	private String CodigoMaterial;
	private String Material;
	private String NombreCompleto;
	private String Stock;
	private String Especificaciones;
	private Integer Precio_unitario;
	private byte[] Qr;
	private byte[] Foto;
	private String visible;
	
	public Integer getIdMaterial() {
		return IdMaterial;
	}
	public void setIdMaterial(Integer idMaterial) {
		IdMaterial = idMaterial;
	}
	public String getCodigoMaterial() {
		return CodigoMaterial;
	}
	public void setCodigoMaterial(String codigoMaterial) {
		CodigoMaterial = codigoMaterial;
	}
	public String getMaterial() {
		return Material;
	}
	public void setMaterial(String material) {
		Material = material;
	}
	public String getNombreCompleto() {
		return NombreCompleto;
	}
	public void setNombreCompleto(String nombreCompleto) {
		NombreCompleto = nombreCompleto;
	}
	public String getStock() {
		return Stock;
	}
	public void setStock(String stock) {
		Stock = stock;
	}
	public String getEspecificaciones() {
		return Especificaciones;
	}
	public void setEspecificaciones(String especificaciones) {
		Especificaciones = especificaciones;
	}

	public Integer getPrecio_unitario() {
		return Precio_unitario;
	}
	public void setPrecio_unitario(Integer precio_unitario) {
		Precio_unitario = precio_unitario;
	}
	public byte[] getQr() {
		return Qr;
	}
	public void setQr(byte[] qr) {
		Qr = qr;
	}
	public String getVisible() {
		return visible;
	}
	public void setVisible(String visible) {
		this.visible = visible;
	}
	public byte[] getFoto() {
		return Foto;
	}
	public void setFoto(byte[] foto) {
		Foto = foto;
	}
	

	
}
