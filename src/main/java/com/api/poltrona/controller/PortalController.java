package com.api.poltrona.controller;



import java.sql.Blob;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.api.poltrona.entidades.Foto;
import com.api.poltrona.entidades.Materiales;
import com.api.poltrona.entidades.Usuario;
import com.api.poltrona.service.ImagenDAO;
import com.api.poltrona.service.MaterialService;
import com.api.poltrona.service.QrDAO;
import com.api.poltrona.service.UsuarioService;

@SuppressWarnings("unused")
@Controller
@RequestMapping("/")
public class PortalController {
	@Lazy
	@Autowired
	private MaterialService ms;
	@Autowired
	private ImagenDAO is;
	@Autowired
	private QrDAO qrs;
	
	/*@GetMapping("/")
	public String index() {
		return"index.html";
	}*/
	
	@GetMapping("/admin")
	public String admin( ModelMap modelo, @RequestParam(required = false) String logout, @RequestParam(required = false) String error,HttpSession session) {
		/* String encriptada = new BCryptPasswordEncoder().encode("PoltronaPass!");
		 System.out.println(encriptada);*/
	    if (error != null && !error.isEmpty()) {
            if (error.equals("error")) {
                modelo.addAttribute("error", "Mail o Clave incorrectos");
            } else {
                modelo.addAttribute("error", error);
            }
            return "admin.html";
        }

        if (logout != null) {
            modelo.addAttribute("logout", "Has salido de la plataforma exitosamente.");
        }
        
        return checkLogueado(session);
    }


    private String checkLogueado(HttpSession session) {
        if (session.getAttribute("usuariosession") != null) {
            return "redirect:/";
        }

        return "admin.html";
    }
	

	@GetMapping("/")
	public String buscar(ModelMap modelo,HttpSession session) {
		List<Materiales>materiales=ms.buscarMateriales();
		List<Materiales>mater = new ArrayList<>();
		for (int i = 0; i < 100; i++) {
			mater.add(materiales.get(i));
		}

		System.out.println(mater);
		 modelo.addAttribute("list", mater);
		 Usuario usuario =(Usuario) session.getAttribute("usuariosession");
		 session.removeAttribute("entro");
		 modelo.addAttribute("spinner", "spinner");
		 modelo.addAttribute("totalprod", materiales.size());
		return "buscar.html";
	}
	@GetMapping("/page/{page}")
	public String buscarPaginador(ModelMap modelo,HttpSession session,@PathVariable Integer page) {
		List<Materiales>materiales=ms.buscarMateriales();
		List<Materiales>mater = new ArrayList<>();
		Integer desde = null,hasta=null;
		for (int i = 0; i < page*100; i++) {
			mater.add(materiales.get(i));
		}
		System.out.println(mater);
		 modelo.addAttribute("list", mater);
		 modelo.addAttribute("totalprod", materiales.size());
		 Usuario usuario =(Usuario) session.getAttribute("usuariosession");
		return "buscar.html";
	}
	
	@GetMapping("/buscar/{categoria}")
	public String buscarCategoria(@PathVariable String categoria ,ModelMap modelo) {
		List<Materiales>materiales= new ArrayList<>();
		List<Materiales>materialestemp= new ArrayList<>();
		System.out.println(categoria);		
		if(categoria.equals("MUEBLES")) {
			
			materiales= ms.buscarMaterialesPorCategoria("MUEBLES");
			materialestemp= ms.buscarMaterialesPorCategoria("MESAS");
			for (Materiales materiales2 : materialestemp) {
				materiales.add(materiales2);
			}
			materialestemp= ms.buscarMaterialesPorCategoria("SILLONES");
			for (Materiales materiales2 : materialestemp) {
				materiales.add(materiales2);
			}
			materialestemp= ms.buscarMaterialesPorCategoria("SILLAS");
			for (Materiales materiales2 : materialestemp) {
				materiales.add(materiales2);
			}
			materialestemp= ms.buscarMaterialesPorCategoria("SOFAS");
			for (Materiales materiales2 : materialestemp) {
				materiales.add(materiales2);
			}
			materialestemp= ms.buscarMaterialesPorCategoria("BANQUETAS");
			for (Materiales materiales2 : materialestemp) {
				materiales.add(materiales2);
			}
			modelo.addAttribute("sub","sub");
			System.out.println("los productos:" +materiales);
		}else {
			materiales = ms.buscarMaterialesPorCategoria(categoria);
			if(categoria.equals("MESAS")|| categoria.equals("SILLONES")|| categoria.equals("SILLAS")|| categoria.equals("SOFAS")|| categoria.equals("BANQUETAS")) {
				modelo.addAttribute("sub","sub");	
			}	
		}
		
		modelo.addAttribute("list",materiales);
		 modelo.addAttribute("totalprod", materiales.size());
		return"buscar.html";
	}
	
	@PostMapping("/buscador")
	public String buscador(@RequestParam String buscarProducto ,ModelMap modelo) {
		List<Materiales>materiales = new ArrayList<>();
		List<Materiales>materialestemp= new ArrayList<>();
	
		if(buscarProducto.toUpperCase().equals("MUEBLES")||buscarProducto.toUpperCase().equals("MUEBLE")) {
			
			materiales= ms.buscarMaterialesPorCategoria("MUEBLES");
			materialestemp= ms.buscarMaterialesPorCategoria("MESAS");
			for (Materiales materiales2 : materialestemp) {
				materiales.add(materiales2);
			}
			materialestemp= ms.buscarMaterialesPorCategoria("SILLONES");
			for (Materiales materiales2 : materialestemp) {
				materiales.add(materiales2);
			}
			materialestemp= ms.buscarMaterialesPorCategoria("SILLAS");
			for (Materiales materiales2 : materialestemp) {
				materiales.add(materiales2);
			}
			materialestemp= ms.buscarMaterialesPorCategoria("SOFAS");
			for (Materiales materiales2 : materialestemp) {
				materiales.add(materiales2);
			}
			materialestemp= ms.buscarMaterialesPorCategoria("BANQUETAS");
			for (Materiales materiales2 : materialestemp) {
				materiales.add(materiales2);
			}
			modelo.addAttribute("sub","sub");
			System.out.println("los productos:" +materiales);
		}else {
			materiales = ms.buscarMaterialesPorCategoria(buscarProducto);
			if(buscarProducto.toUpperCase().equals("MESAS")|| buscarProducto.toUpperCase().equals("MESA")||buscarProducto.toUpperCase().equals("SILLON")||buscarProducto.toUpperCase().equals("SILLONES")|| buscarProducto.toUpperCase().equals("SILLAS")|| buscarProducto.toUpperCase().equals("SOFAS")|| buscarProducto.toUpperCase().equals("BANQUETAS")) {
				modelo.addAttribute("sub","sub");	
			}	
		}
		if(materiales.isEmpty()) {
			modelo.addAttribute("error","No se encontraron resultados para su busqueda.");
		}	
		modelo.addAttribute("list",materiales);
		modelo.addAttribute("totalprod", materiales.size());
		return"buscar.html";
	}
	
	@GetMapping("/producto/{codigomaterial}")
	public String producto(@PathVariable("codigomaterial") String codigoMaterial,ModelMap modelo,HttpSession session) {
		List<Materiales>materiales=ms.buscarMaterial(codigoMaterial);
		modelo.addAttribute("material", materiales);
		for (Materiales materiales2 : materiales) {
			
			modelo.addAttribute("codigo", materiales2.getCodigoMaterial());
			modelo.addAttribute("codigofoto", materiales2.getCodigoMaterial());
		}
		 if (session.getAttribute("usuariosession") == null) {
	            return "producto.html";
	        }
		return"productoadmin.html";
	}
	

	 @GetMapping("/loadfoto/{codigomaterial}")
	    public ResponseEntity<byte[]> imagen(@PathVariable String codigomaterial,HttpSession session) throws Exception {
	    	
	    	Foto foto = (Foto) is.buscarFotoMaterialPorCodigo(codigomaterial);
	    	
	    	if(foto != null) {
	    		
	    		foto.setMime("image");
	    		
	    		
	    		MediaType content = checkContent(foto.getMime());
	    		
	    		HttpHeaders headers = new HttpHeaders();
	    		headers.setContentType(content);
	    		session.setAttribute("foto", foto);
	    		return new ResponseEntity<>(foto.getContenido(), headers, HttpStatus.OK);
	    		
	    	}
	    	session.removeAttribute("foto");
	    	System.out.println("la foto "+session.getAttribute("foto"));
	    	return null;
		
	    }
	    @GetMapping("/loadqr/{codigomaterial}")
	    public ResponseEntity<byte[]> qr(@PathVariable String codigomaterial,HttpSession session) throws Exception {
	    	
	    	Foto foto = (Foto) qrs.buscarQrMaterialPorCodigo(codigomaterial);
	    	if(foto != null) {
	    	foto.setMime("image");
	    	
	    	
	    	MediaType content = checkContent(foto.getMime());
	    	
	    	HttpHeaders headers = new HttpHeaders();
	    	headers.setContentType(content);
	    	session.setAttribute("qr", foto);
	    	return new ResponseEntity<>(foto.getContenido(), headers, HttpStatus.OK);
	    	}

	    	return null;
	    	
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






