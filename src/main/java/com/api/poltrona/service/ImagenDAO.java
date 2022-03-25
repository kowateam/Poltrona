package com.api.poltrona.service;

import java.sql.Blob;

import org.springframework.stereotype.Service;

import com.api.poltrona.entidades.Foto;

@Service
public class ImagenDAO extends DAO {
	
	 
	public Foto buscarFotoMaterialPorCodigo(String codigomaterial) throws Exception {
		try {
			Foto img = new Foto();
			String sql = "SELECT Foto FROM Materiales where codigoMaterial like '" +codigomaterial+"'";
			consultarBase(sql);
			byte[] recuperado = null;
			while(resultado.next()) {
			Blob foto = resultado.getBlob("Foto");
			if(foto != null) {
			recuperado= foto.getBytes(1, (int) foto.length());
			img.setContenido(recuperado);
			return img;
			}}
			return null;
		} catch (Exception e) {
			throw e;
			}finally {
				desconectarBase();
			}
		
	}
	
}
