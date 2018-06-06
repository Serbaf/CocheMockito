package com.fermax.spuche.java.pruebas;

import java.util.*;

public class Coche
{

	private String matricula;
	private Modelo modelo;
	private int nAsientos;
	private Persona duenyo;

	public Coche(Persona duenyo, Modelo modelo)
	{

		this.duenyo = duenyo;
		this.modelo = modelo;

		matricula = generateMatricula();
	}

	/**
	 * Método interno Genera una matricula aleatoria con la siguiente forma: XXXXYYY
	 * X=numero del 0 al 9 Y=letra mayuscula de la A a la Z
	 * 
	 * @return matricula generada
	 */
	private String generateMatricula()
	{
		String auxMatricula;
		Random randomizer = new Random();
		int nMatricula = randomizer.nextInt(9999);
		auxMatricula = "" + nMatricula;

		if (auxMatricula.length() < 4)
		{
			int aux = 4 - auxMatricula.length();
			for (int i = 0; i < aux; i++)
			{
				auxMatricula = "0" + auxMatricula;
			}
		}

		for (int i = 0; i < 3; i++)
		{
			char lMatricula = (char) (randomizer.nextInt(25) + 65);
			auxMatricula += lMatricula;
		}

		return auxMatricula;
	}

	/**
	 * Calcula la velocidad maxima de un vehiculo a partir de parametros internos
	 * sobre el modelo y la temeridad de su dueño
	 * 
	 * @return velocidad maxima del vehiculo
	 * @throws Exception
	 *             si el objeto Persona no tiene personalidad definida dentro de los
	 *             limites
	 */
	public int getVelocidadMax() throws Exception
	{
		if (duenyo.getPersonalidad() == 1)
		{
			return modelo.getVelocidadMax() - 30;
		}
		else if (duenyo.getPersonalidad() == 2)
		{
			return modelo.getVelocidadMax();
		}
		else if (duenyo.getPersonalidad() == 3)
		{
			return modelo.getVelocidadMax() + 30;
		}
		else
		{
			throw new Exception("Fallo en la personalidad del dueño");
		}
	}

	// GETTERS

	/**
	 * @return matricula del vehiculo
	 */
	public String getMatricula()
	{
		return matricula;
	}

	/**
	 * 
	 * @return numero de asientos del vehiculo
	 */
	public int getNumAsientos()
	{
		return nAsientos;
	}

	/*
	 * public static void main(String[] args) { Coche cochecito = new Coche();
	 * System.out.println("La matricula del coche es: " + cochecito.getMatricula());
	 * 
	 * }
	 */

}
