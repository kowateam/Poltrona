package com.api.poltrona.entidades;

public class Producto {

	private Integer IdMaterial;
	private String CodigoMaterial;
	private String Material;
	private String NombreCompleto;
	private String StockRufino;
	private String StockBarracas;
	private String StockPalmares;
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

	public String getStockRufino() {
		return StockRufino;
	}
	public void setStockRufino(String stockRufino) {
		StockRufino = stockRufino;
	}
	public String getStockBarracas() {
		return StockBarracas;
	}
	public void setStockBarracas(String stockBarraca) {
		StockBarracas = stockBarraca;
	}
	public String getStockPalmares() {
		return StockPalmares;
	}
	public void setStockPalmares(String stockPalmares) {
		StockPalmares = stockPalmares;
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
