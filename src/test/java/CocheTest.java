import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;

import com.fermax.spuche.java.pruebas.Coche;
import com.fermax.spuche.java.pruebas.Modelo;
import com.fermax.spuche.java.pruebas.Persona;

public class CocheTest
{

	private Coche coche;
	private Persona duenyo;
	private Modelo modeloCoche;
	String matricula;
	
	/**
	 * Inicializacion de objetos y variables usados con frecuancia.
	 * duenyo y modeloCoche mockeados, necesarios para la instanciacion de un Coche
	 */
	@Before
	public void before()
	{
		duenyo = mock(Persona.class);
		modeloCoche = mock(Modelo.class);
		
	}

	/**
	 * Se comprueba que la matricula tenga la longitud deseada
	 */
	@Test
	public void matriculaShouldBeLength7()
	{
		coche = new Coche(duenyo, modeloCoche);
		matricula = coche.getMatricula();
		
		assertEquals("La matricula no tiene una longitud de 7 " + matricula, 7, matricula.length());

	}

	/**
	 * Se comprueba que los tres ultimos caracteres de la matricula sean letras mayusculas del 
	 * rango A-Z
	 */
	@Test
	public void matriculaShouldHave3CapLetters()
	{
		coche = new Coche(duenyo, modeloCoche);
		matricula = coche.getMatricula();
		
		boolean error = false;
		String letrasMatricula = matricula.substring(4, 7);
		char[] arrayLetras = letrasMatricula.toCharArray();

		//Comprobacion de que son letras A-Z (correspondencias 65-90 en codigo ASCII)
		for (char letra : arrayLetras)
		{
			if (((int) letra) < 65 || ((int) letra) > 90)
			{
				error = true;
			}
		}

		assertFalse("Alguno de los ultimos 3 caracteres de la matricula no es " + "una letra mayuscula: " + matricula,
				error);
	}

	/**
	 * Se comprueba que los 4 primeros caracteres de la matricula sean números del rango 0-9
	 */
	@Test
	public void matriculaShouldHave4Numbers()
	{
		coche = new Coche(duenyo, modeloCoche);
		matricula = coche.getMatricula();
		
		boolean error = false;
		String numerosMatricula = matricula.substring(0, 4);
		char[] arrayNumeros = numerosMatricula.toCharArray();

		for (char numero : arrayNumeros)
		{
			
			//Comprobacion de que son numeros 0-9 (correspondencias 48-57 en codigo ASCII)
			if (((int) numero) < 48 || ((int) numero) > 57)
			{
				error = true;
			}
		}

		assertFalse("Alguno de los primeros 4 caracteres de la matricula no es " + "un numero: " + matricula, error);
	}

	/**
	 * Se comprueba que el calculo de la velocidad maxima sea correcto para un caso de
	 * ejecucion normal con parametros esperados
	 */
	@Test
	public void velocidadMaxShouldBeCool()
	{
		try
		{
			coche = new Coche(duenyo, modeloCoche);
			
			when(duenyo.getPersonalidad()).thenReturn(3); //Mockeo de personalidad tipo 3
			when(modeloCoche.getVelocidadMax()).thenReturn(150); //Mockeado modelo de coche con velocidad maxima 180km/h
			int velocidad = coche.getVelocidadMax();
			assertEquals(180, velocidad);
		}
		catch (Exception e)
		{
			fail("No deberia saltar una excepcion aqui");
		}
	}
	
	
	/**
	 * Se comprueba que el calculo de la velocidad maxima lance una excepcion cuando se trata
	 * de calcular la velocidad sin antes haber configurado los metodos necesarios en los objetos
	 * mock (duenyo.getPersonalidad() y modeloCoche.getVelocidadMax() )
	 */
	@Test
	public void velocidadMaxShouldFail()
	{
		try 
		{
			coche = new Coche(duenyo, modeloCoche);
			
			coche.getVelocidadMax();
			fail("Deberia haber saltado una excepcion");
		}
		catch(Exception e)
		{
			assertNotNull(e);
		}
	}
	
	/**
	 * Se comprueba que el calculo de la velocidad maxima lance una excepcion cuando se intenta
	 * proporcionar una personalidad incorrecta
	 */
	@Test
	public void velocidadMaxShouldFail2()
	{
		try 
		{
			coche = new Coche(duenyo, modeloCoche);
			
			when(duenyo.getPersonalidad()).thenReturn(4); //Mockeo de personalidad tipo 4
			when(modeloCoche.getVelocidadMax()).thenReturn(150); //Mockeado modelo de coche con velocidad maxima 180km/h
			coche.getVelocidadMax();
			fail("Deberia haber saltado una excepcion");
		}
		catch(Exception e)
		{
			assertNotNull(e);
		}
	}
	
	/**
	 * Se comprueba que el riesgo de accidente sea correcto si se introducen parametros
	 * dentro de los rangos esperados
	 */
	@Test
	public void accidentRiskShouldBeCool()
	{
		// Mockeamos un coche de un modelo viejo y poco seguro 
		// con 200 km/h vel max y un conductor borracho y temerario
		when(duenyo.getPersonalidad()).thenReturn(3);
		when(duenyo.isDrunk()).thenReturn(true);
		when(modeloCoche.getVelocidadMax()).thenReturn(200);
		when(modeloCoche.getProbAccidente()).thenReturn(40);
		
		coche = new Coche(duenyo, modeloCoche);
		
		//El coche se pone a 500 km/h (menos si este numero supera la vel maxima)
		for(int i=0;i<100;i++)
		{
			coche.increaseVel5();
		}
		
		
		assertEquals(100, coche.calcProbAccidente());
	}
	
	/**
	 * Se comprueba que el riesgo de accidente sea correcto si se introducen parametros
	 * dentro de los rangos esperados
	 */
	@Test
	public void accidentRiskShouldBeCool2()
	{
		// Mockeamos un coche de un modelo muy seguro con 
		// 110 km/h vel max y un conductor sobrio y tranquilo
		when(duenyo.getPersonalidad()).thenReturn(1);
		when(duenyo.isDrunk()).thenReturn(false);
		when(modeloCoche.getVelocidadMax()).thenReturn(110);
		when(modeloCoche.getProbAccidente()).thenReturn(5);
		
		coche = new Coche(duenyo, modeloCoche);
		
		//El coche se pone a 75 km/h
		for(int i=0;i<15;i++)
		{
			coche.increaseVel5();
		}
		
		// Hechos a mano los calculos resultan:
		// 5 (riesgo base del modelo) + 0 (riesgo añadido por velocidad) = 5
		assertEquals(5, coche.calcProbAccidente());
	}
	
	/**
	 * Comprueba que al calcular la probabilidad de accidente se necesita saber
	 * si el conductor esta borracho, su personalidad y la probabilidad base de accidente
	 * del modelo de coche
	 */
	@Test
	public void accidentCalculatorShouldInvoke()
	{
		coche = new Coche(duenyo, modeloCoche);
		coche.calcProbAccidente();
		
		verify(duenyo,atLeast(1)).isDrunk();
		verify(duenyo,atLeast(1)).getPersonalidad();
		verify(modeloCoche,atLeast(1)).getProbAccidente();
	}
	
	/**
	 * Si el coche recibe dos hostias, su valor cae a la cuarta parte
	 */
	@Test
	public void valueShouldBeDecreased()
	{
		try 
		{
			when(duenyo.getPersonalidad()).thenReturn(1);
			coche = new Coche(duenyo, modeloCoche);
			
			int valorInit = coche.getValor();
			
			coche.darHostiaAlCoche();
			coche.darHostiaAlCoche();
			
			assertEquals(valorInit/4, coche.getValor());
		}
		
		catch (Exception e) {
			fail();
		}

	}
	
	/**
	 * Si el coche recibe demasiadas hostias, pasa a no valer nada
	 */
	@Test
	public void valueShouldBeDecreased2()
	{
		try 
		{
			when(duenyo.getPersonalidad()).thenReturn(3);
			coche = new Coche(duenyo, modeloCoche);
						
			for(int i=0;i<100;i++)
			{
				coche.darHostiaAlCoche();
			}
			assertEquals(0, coche.getValor());
		}
		
		catch (Exception e) {
			fail();
		}

	}
	
	/**
	 * Si el coche recibe varias hostias, cambia de estado
	 */
	@Test
	public void estadoShouldBeX()
	{
		try 
		{
			when(duenyo.getPersonalidad()).thenReturn(2);
			coche = new Coche(duenyo, modeloCoche);
						
			for(int i=0;i<100;i++)
			{
				coche.darHostiaAlCoche();
			}
			
			assertEquals("Basura", coche.getEstado());
		}
		
		catch (Exception e) {
			fail();
		}

	}
	
	
}
