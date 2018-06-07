package com.fermax.spuche.java.pruebas;

public class Modelo
{
	
	private int precio;
	
	
	//CONSTRUCTOR
	public Modelo()
	{
		setPrecio(8000);
	}
	
	
	
	
	// SETTERS
	
	public void setPrecio(int precio)
	{
		this.precio = precio;
	}
	
	
	
	
	
	
	
	
	
	
	//GETTERS
	
	public int getPrecio()
	{
		return precio;
	}
	
	/**
	 * Teoricamente deberia devolver la velocidad maxima asociada a este modelo de coche
	 * @return velocidadMax
	 */
	public int getVelocidadMax()
	{
		return 0;
	}
	
	/**
	 * Teoricamente deberia devolver el nombre del modelo de coche
	 * @return nombreModelo
	 */
	public String getNombreModelo()
	{
		return "";
	}
	
	/**
	 * Teoricamente deberia devolver el numero de asientos del modelo
	 * @return numAsientos
	 */
	public int getNumAsientos()
	{
		return 0;
	}
	
	/**
	 * Teoricamente deberia devolver un Id (probablemente cadena de letras
	 * y numeros) asociado a cada modelo de coche
	 * @return idModelo
	 */
	public String getIdModelo()
	{
		return "";
	}
	
	/**
	 * Teoricamente deberia devolver un numero 0-100 con las probabilidades
	 * de tener un accidente asociadas a este modelo
	 */
	public int getProbAccidente()
	{
		return 0;
	}
	
	
	
	

}
