package com.api.poltrona.service;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.springframework.stereotype.Service;
@Service
public  class DAO3 {

	protected Connection conexion = null;
	protected ResultSet resultado = null;
	protected Statement sentencia = null;
	
	
	private final String USER = "usersgim";
	private final String PASSWORD = "passsgim";
	private final String DRIVER = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
	
	
	protected void conectarBase() throws Exception {
		
		try {
			Class.forName(DRIVER);
			String urlBaseDeDatos = "jdbc:sqlserver://190.15.203.50:8235;databaseName=SGIM3;sslProtocol=TLSv1.2;";
			conexion = DriverManager.getConnection(urlBaseDeDatos,USER,PASSWORD);
			
			
		} catch (ClassNotFoundException | SQLException ex) {
		 throw ex;
		}
	}
	
	
	protected void desconectarBase() throws Exception {
		try {
			if(resultado != null) {
				resultado.close();
			}
			if(sentencia != null) {
				sentencia.close();
			}
			if(conexion != null) {
				conexion.close();
			}
		}catch (Exception e) {
			throw e;
		}
	}
	
	protected void consultarBase (String sql ) throws Exception {
		try {
			conectarBase();
			sentencia = conexion.createStatement();
			resultado = sentencia.executeQuery(sql);
		} catch (Exception ex) {
			throw ex;
	}}
}
