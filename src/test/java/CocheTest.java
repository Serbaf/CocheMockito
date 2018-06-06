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

	@Before
	public void before()
	{
		duenyo = mock(Persona.class);
		modeloCoche = mock(Modelo.class);
		coche = new Coche(duenyo, modeloCoche);
		matricula = coche.getMatricula();
	}

	@Test
	public void matriculaShouldBeLength7()
	{
		assertEquals("La matricula no tiene una longitud de 7 " + matricula, 7, matricula.length());

	}

	@Test
	public void matriculaShouldHave3CapLetters()
	{
		boolean error = false;
		String letrasMatricula = matricula.substring(4, 7);
		char[] arrayLetras = letrasMatricula.toCharArray();

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

	@Test
	public void matriculaShouldHave4Numbers()
	{
		boolean error = false;
		String numerosMatricula = matricula.substring(0, 4);
		char[] arrayNumeros = numerosMatricula.toCharArray();

		for (char numero : arrayNumeros)
		{
			if (((int) numero) < 48 || ((int) numero) > 57)
			{
				error = true;
			}
		}

		assertFalse("Alguno de los primeros 4 caracteres de la matricula no es " + "un numero: " + matricula, error);
	}

	@Test
	public void velocidadMaxShouldBe()
	{
		try
		{
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
	
	@Test
	public void velocidadMaxShouldFail()
	{
		try 
		{
			coche.getVelocidadMax();
			fail("Deberia haber saltado una excepcion");
		}
		catch(Exception e)
		{
			assertNotNull(e);
		}
	}

}
