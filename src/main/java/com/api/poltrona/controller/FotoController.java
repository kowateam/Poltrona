package com.api.poltrona.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.poltrona.entidades.Foto;
import com.api.poltrona.service.ImagenDAO;

@RestController
@RequestMapping("/foto")
public class FotoController {
	@Autowired
	private ImagenDAO is;

    @GetMapping(path ="/loadfoto/{codigofoto}")
    public ResponseEntity<byte[]> foto(@PathVariable String codigofoto) throws Exception {
    	Foto foto = is.buscarFotoMaterialPorCodigo(codigofoto);
    	foto.setMime("image");
    	
        MediaType content = checkContent(foto.getMime());

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(content);

        return new ResponseEntity<>(foto.getContenido(), headers, HttpStatus.OK);
	
    }
    
    
    private MediaType checkContent(String mime) {

         if (mime.contains("image")) {  

            if (mime.contains("png")) {
                return (MediaType.IMAGE_PNG);
            } else {
                return (MediaType.IMAGE_JPEG);
            }
        
    }
         return MediaType.MULTIPART_FORM_DATA;
}
}
