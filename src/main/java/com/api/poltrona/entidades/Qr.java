package com.api.poltrona.entidades;

public class Qr {

	private String Id;
	private String mime;
	private byte[] contenido;
	
	
	public Qr() {
		super();
	}
	
	public Qr(String id, String mime, byte[] contenido) {
		super();
		Id = id;
		this.mime = mime;
		this.contenido = contenido;
	}

	public String getId() {
		return Id;
	}
	public void setId(String id) {
		Id = id;
	}
	public String getMime() {
		return mime;
	}
	public void setMime(String mime) {
		this.mime = mime;
	}
	public byte[] getContenido() {
		return contenido;
	}
	public void setContenido(byte[] bs) {
		this.contenido = bs;
	}
	
	
}
