package com.fermax.spuche.java.pruebas;

import java.util.*;

public class Coche
{

	private String matricula;
	private Modelo modelo;
	private int nAsientos;
	private Persona duenyo;
	private int velMax;
	private int velAct;
	private String estado;
	private int valor;

	// CONSTRUCTORES

	/**
	 * Constructor del coche. Necesita un duenyo y un modelo de coche. Este metodo
	 * carga todas las caracteristicas basicas del objeto (modelo, velocidad
	 * 
	 * @param duenyo
	 * @param modelo
	 */
	public Coche(Persona duenyo, Modelo modelo)
	{
		try
		{
			this.duenyo = duenyo;
			this.modelo = modelo;

			matricula = generateMatricula();
			nAsientos = modelo.getNumAsientos();
			velMax = getVelocidadMax();
			velAct = 0;
			estado = "Nuevo";
			valor = modelo.getPrecio();

		}

		catch (Exception e)
		{

		}
	}

	// METODOS INTERNOS

	/**
	 * Genera una matricula aleatoria con la siguiente forma: XXXXYYY X=numero del 0
	 * al 9 Y=letra mayuscula de la A a la Z
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

	// METODOS PUBLICOS

	/**
	 * Aumenta la velocidad en 5 km/h si la maxima velocidad posible no se ha
	 * alcanzado
	 */
	public void increaseVel5()
	{
		if (velAct + 5 <= velMax)
		{
			velAct += 5;
		}
	}

	/**
	 * Frena la velocidad en 5 km/h si el coche no está parado (o casi)
	 */
	public void decreaseVel5()
	{
		if (velAct >= 5)
		{
			velAct -= 5;
		}
	}

	/**
	 * Calcula las probabilidades que tiene este coche de sufrir un accidente.
	 * 
	 * @return probabilidadAccidente
	 */
	public int calcProbAccidente()
	{
		int base, total;
		base = modelo.getProbAccidente();

		// Las probabilidades de accidente aumentan con la velocidad del vehiculo
		if (velAct < 5)
		{
			total = 0;
		}
		else if (velAct < 40)
		{
			total = base - 40;
		}
		else if (velAct < 90)
		{
			total = base;
		}
		else if (velAct < 130)
		{
			total = base + 20;
		}
		else
		{
			total = base + 50;
		}

		// Si el conductor esta borracho las probabilidades de accidente se triplican
		if (duenyo.isDrunk())
		{
			total *= 3;
		}

		if (total > 100)
			total = 100; // Las probabilidades no pueden ser superiores al 100%

		return total;
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

	/**
	 * El coche
	 * @throws Exception
	 */
	public void darHostiaAlCoche() throws Exception
	{
		if (estado.equals("Nuevo"))
		{
			estado = "Rayado";
			valor /= 2;
		}
		else if (estado.equals("Rayado"))
		{
			estado = "Con desperfectos";
			valor /= 2;
		}
		else if (estado.equals("Con desperfectos"))
		{
			estado = "Muy deteriorado";
			valor /= 4;
		}
		else if (estado.equals("Muy deteriorado"))
		{
			estado = "Chatarra andante";
			valor /= 5;
		}
		else if (estado.equals("Chatarra andante"))
		{
			estado = "Basura";
			valor = 0;
		}
		else if (estado.equals("Basura"))
		{
			// Dar una hostia a la basura no la convierte en otra cosa
		}
		else
		{
			throw new Exception("El coche necesita un estado");
		}

	}

	// SETTERS

	// GETTERS

	/**
	 * @return velocidad actual del vehiculo
	 */
	public int getVelActual()
	{
		return velAct;
	}

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

	/**
	 * 
	 * @return valor actual del coche
	 */
	public int getValor()
	{
		return valor;
	}
	
	/**
	 * 
	 * @return estado actual del coche (String)
	 */
	public String getEstado()
	{
		return estado;
	}

	/*
	 * public static void main(String[] args) { Coche cochecito = new Coche();
	 * System.out.println("La matricula del coche es: " + cochecito.getMatricula());
	 * 
	 * }
	 */

}
