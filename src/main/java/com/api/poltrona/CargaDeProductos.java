package com.api.poltrona;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import com.api.poltrona.service.ProductoService;

@Configuration
@EnableScheduling
@EnableAsync
public class CargaDeProductos {

	@Autowired
	private ProductoService ps;

	@Async
	@Scheduled(cron = "0 30 15 * * *")
	public void timerCrearProductos() {
		try {
			ps.crearProductoBarraca();
			ps.crearProductoRufino();
			System.out.println("terminamos crear productos *************************************");
			// ps.crearProductoPalmares();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Async
	@Scheduled(fixedRate = 3600000)
	public void timerStock() {
		try {
			ps.buscarStockBarracas();
			ps.buscarStockRufino();
			System.out.println("terminamos el stock *******************************");
			// ps.buscarSockPalmares();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
