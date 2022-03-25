package com.api.poltrona.entidades;

public class Materiales {

	private Integer IdMaterial;
	private String CodigoMaterial;
	private String Material;
	private String NombreCompleto;
	private String Stock;
	private String Especificaciones;
	private Integer PrecioUnitarioVtaTipoMoneda;
	private Byte[] Qr;
	


	public Byte[] getQr() {
		return Qr;
	}
	public void setQr(Byte[] qr) {
		Qr = qr;
	}
	public String getNombreCompleto() {
		return NombreCompleto;
	}
	public void setNombreCompleto(String nombreCompleto) {
		NombreCompleto = nombreCompleto;
	}
	public String getCodigoMaterial() {
		return CodigoMaterial;
	}
	public void setCodigoMaterial(String codigoMaterial) {
		CodigoMaterial = codigoMaterial;
	}


	public Integer getPrecioUnitarioVtaTipoMoneda() {
		return PrecioUnitarioVtaTipoMoneda;
	}
	public void setPrecioUnitarioVtaTipoMoneda(Integer precioUnitarioVtaTipoMoneda) {
		PrecioUnitarioVtaTipoMoneda = precioUnitarioVtaTipoMoneda;
	}
	public String getStock() {
		return Stock;
	}
	public void setStock(String stock) {
		Stock = stock;
	}
	public Integer getIdMaterial() {
		return IdMaterial;
	}
	public void setIdMaterial(Integer idMaterial) {
		IdMaterial = idMaterial;
	}

	public String getMaterial() {
		return Material;
	}
	public void setMaterial(String material) {
		Material = material;
	}
	public String getEspecificaciones() {
		return Especificaciones;
	}
	public void setEspecificaciones(String especificaciones) {
		Especificaciones = especificaciones;
	}

	
}
