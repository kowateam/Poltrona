package com.api.poltrona.service;

import java.sql.Blob;

import org.springframework.stereotype.Service;

import com.api.poltrona.entidades.Foto;
@Service
public class QrDAO extends DAO{

	
	public Foto buscarQrMaterialPorCodigo(String codigomaterial) throws Exception {
		try {
			Foto img = new Foto();
			String sql = "SELECT * FROM producto where codigo_material like '" +codigomaterial+"'";
			consultarBase(sql);
			byte[] recuperado = null;
			while(resultado.next()) {
				
			Blob qr = resultado.getBlob("qr");
			if(qr != null) {
			recuperado= qr.getBytes(1, (int) qr.length());
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
