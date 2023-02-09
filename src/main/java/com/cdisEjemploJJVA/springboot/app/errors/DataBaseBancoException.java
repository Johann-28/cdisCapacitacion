package com.cdisEjemploJJVA.springboot.app.errors;

public class DataBaseBancoException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8800551278858523051L;
	
	public DataBaseBancoException() {
		super("Contacte con la administracion, hubo un error con la base de datos");
	}
}
