package com.api.poltrona.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.api.poltrona.entidades.Foto;
import com.api.poltrona.entidades.Materiales;
import com.api.poltrona.entidades.Producto;
import com.api.poltrona.entidades.Usuario;
import com.api.poltrona.service.ImagenDAO;
import com.api.poltrona.service.MaterialService;
import com.api.poltrona.service.ProductoService;
import com.api.poltrona.service.QrDAO;

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
	@Autowired
	private ProductoService ps;

	/*
	 * @GetMapping("/") public String index() { return"index.html"; }
	 */

	@GetMapping("/admin")
	public String admin(ModelMap modelo, @RequestParam(required = false) String logout,
			@RequestParam(required = false) String error, HttpSession session) {
		/*
		 * String encriptada = new BCryptPasswordEncoder().encode("PoltronaPass!");
		 * System.out.println(encriptada);
		 */
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
	public String buscar(ModelMap modelo, HttpSession session) {
		
		
		List<Producto> productos = ps.buscarTodosLosProductos();
		modelo.addAttribute("list", productos);
		Usuario usuario = (Usuario) session.getAttribute("usuariosession");
		session.removeAttribute("entro");
		modelo.addAttribute("spinner", "spinner");
		modelo.addAttribute("totalprod", productos.size());
		return "buscar.html";
	}
	//@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping("crear")
	public String crearProducto() throws Exception {
		//ps.crearProductoBarraca();
		//ps.crearProductoRufino();
		ps.crearProductoPalmares();
		return "redirect:/";
	}
	
	@GetMapping("/stock")
	public String modificarStock() {
		try {
			ps.buscarStockRufino();
			ps.buscarStockBarracas();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "redirect:/";
	}
	@GetMapping("/buscar/{categoria}")
	public String buscarCategoria(@PathVariable String categoria, ModelMap modelo) {
		List<Producto> productos = new ArrayList<>();
		List<Producto> producto2 = new ArrayList<>();
		if (categoria.equals("MUEBLES")) {

			productos = ps.buscarProductoPorCategoria("MUEBLES");
			producto2 =ps.buscarProductoPorCategoria("MESAS");
			for (Producto materiales2 : producto2) {
				productos.add(materiales2);
			}
			producto2 = ps.buscarProductoPorCategoria("SILLONES");
			for (Producto materiales2 : producto2) {
				productos.add(materiales2);
			}
			producto2 = ps.buscarProductoPorCategoria("SILLAS");
			for (Producto materiales2 : producto2) {
				productos.add(materiales2);
			}
			producto2 = ps.buscarProductoPorCategoria("SOFAS");
			for (Producto materiales2 : producto2) {
				productos.add(materiales2);
			}
			producto2 = ps.buscarProductoPorCategoria("BANQUETAS");
			for (Producto materiales2 : producto2) {
				productos.add(materiales2);
			}
			modelo.addAttribute("sub", "sub");
		} else {
			productos = ps.buscarProductoPorCategoria(categoria);
			if (categoria.equals("MESAS") || categoria.equals("SILLONES") || categoria.equals("SILLAS")
					|| categoria.equals("SOFAS") || categoria.equals("BANQUETAS")) {
				modelo.addAttribute("sub", "sub");
			}
		}

		modelo.addAttribute("list", productos);
		modelo.addAttribute("totalprod", productos.size());
		return "buscar.html";
	}

	@PostMapping("/buscador")
	public String buscador(@RequestParam String buscarProducto, ModelMap modelo) {
		List<Producto> productos = new ArrayList<>();
		List<Producto> productos2 = new ArrayList<>();

		if (buscarProducto.toUpperCase().equals("MUEBLES") || buscarProducto.toUpperCase().equals("MUEBLE")) {

			productos = ps.buscarProductoPorCategoria("MUEBLES");
			productos2 = ps.buscarProductoPorCategoria("MESAS");
			for (Producto materiales2 : productos2) {
				productos.add(materiales2);
			}
			productos2 = ps.buscarProductoPorCategoria("SILLONES");
			for (Producto materiales2 : productos2) {
				productos.add(materiales2);
			}
			productos2 = ps.buscarProductoPorCategoria("SILLAS");
			for (Producto materiales2 : productos2) {
				productos.add(materiales2);
			}
			productos2 = ps.buscarProductoPorCategoria("SOFAS");
			for (Producto materiales2 : productos2) {
				productos.add(materiales2);
			}
			productos2 = ps.buscarProductoPorCategoria("BANQUETAS");
			for (Producto materiales2 : productos2) {
				productos.add(materiales2);
			}
			modelo.addAttribute("sub", "sub");
		} else {
			productos = ps.buscarProductoPorCategoria(buscarProducto);
			if (buscarProducto.toUpperCase().equals("MESAS") || buscarProducto.toUpperCase().equals("MESA")
					|| buscarProducto.toUpperCase().equals("SILLON") || buscarProducto.toUpperCase().equals("SILLONES")
					|| buscarProducto.toUpperCase().equals("SILLAS") || buscarProducto.toUpperCase().equals("SOFAS")
					|| buscarProducto.toUpperCase().equals("BANQUETAS")) {
				modelo.addAttribute("sub", "sub");
			}
		}
		if (productos.isEmpty()) {
			modelo.addAttribute("error", "No se encontraron resultados para su busqueda.");
		}
		modelo.addAttribute("list", productos);
		modelo.addAttribute("totalprod", productos.size());
		return "buscar.html";
	}

	@GetMapping("/producto/{codigomaterial}")
	public String producto(@PathVariable("codigomaterial") String codigoMaterial, ModelMap modelo,
			HttpSession session) {
		Producto materiales = ps.buscarProductoPorCodigo(codigoMaterial);
		modelo.addAttribute("material", materiales);
		modelo.addAttribute("codigo", materiales.getCodigoMaterial());
		modelo.addAttribute("codigofoto", materiales.getCodigoMaterial());
		if (session.getAttribute("usuariosession") == null) {
			return "producto.html";
		}
		return "productoadmin.html";
	}

	@GetMapping("/loadfoto/{codigomaterial}")
	public ResponseEntity<byte[]> imagen(@PathVariable String codigomaterial, HttpSession session) throws Exception {

		Foto prod = is.buscarFotoMaterialPorCodigo(codigomaterial);
		if (prod != null) {

			prod.setMime("image");

			MediaType content = checkContent(prod.getMime());

			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(content);
			session.setAttribute("foto", prod);
			return new ResponseEntity<>(prod.getContenido(), headers, HttpStatus.OK);

		}
		session.removeAttribute("foto");
		return null;

	}

	@GetMapping("/loadqr/{codigomaterial}")
	public ResponseEntity<byte[]> qr(@PathVariable String codigomaterial, HttpSession session) throws Exception {

		Foto foto = (Foto) qrs.buscarQrMaterialPorCodigo(codigomaterial);
		if (foto != null) {
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
