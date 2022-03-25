package com.api.poltrona.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.poltrona.entidades.Materiales;
import com.api.poltrona.service.MaterialService;


@RestController
@RequestMapping("/buscar")
public class BuscarMaterialController {
	
	@Autowired
	private MaterialService ms;
	

	@GetMapping(path="/{nombre}", produces = "application/json")
	public List<Materiales> buscarMaterialesPorNombre(@PathVariable String nombre, ModelMap modelo,HttpSession session) {
			List<Materiales>materiales=ms.buscarMaterialPorNombre(nombre);
			 modelo.addAttribute("materiales", materiales);
			 session.removeAttribute("entro");
			return materiales;
		}

}
