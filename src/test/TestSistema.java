package test;

import java.time.LocalDateTime;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import modelo.*;

class TestSistema {

	@Test
	void testRegistrarUsuario() {
		Sistema sistema = Sistema.getInstance();
		Usuario u1= new Usuario("Agustín"); 
		Usuario u2= new Usuario("Lionel"); 
		sistema.registrarUsuario(u1);
		sistema.registrarUsuario(u2);

		assertEquals(2,sistema.getUsuarios().size());
	}

	@Test
	void testRegistrarTema() {
		Sistema sistema = Sistema.getInstance();
		Tema t1= new Tema("Futbol");
		Tema t2= new Tema("Futsal");
		sistema.registrarTema(t1);
		sistema.registrarTema(t2);
		assertEquals(2,sistema.getTemas().size());
	}
	
	@Test
	void testagregarUnTemaAlUsuario() {
		Sistema sistema = Sistema.getInstance();
		Usuario u1= new Usuario("Agustín"); 
		Tema t1= new Tema("Futbol");
		sistema.registrarUsuario(u1);
		sistema.registrarTema(t1);
		sistema.agregarUnTemaAlUsuario(u1, t1);

		
		assertEquals(1,u1.getTemas().size());
	}
	
	@Test
	void testEnviarAlerta() { 
		Sistema sistema = Sistema.getInstance();

		Usuario u1= new Usuario("Agustín");
		Usuario u2= new Usuario("Lionel"); 
		sistema.registrarUsuario(u1);
		sistema.registrarUsuario(u2);

		Tema t1= new Tema("Futbol");
		Tema t2= new Tema("Futsal");
		sistema.registrarTema(t1);
		sistema.registrarTema(t2);

		sistema.agregarUnTemaAlUsuario(u1, t1);
		sistema.agregarUnTemaAlUsuario(u1, t2);
		sistema.agregarUnTemaAlUsuario(u2, t2);
		
		LocalDateTime fecha1 = LocalDateTime.of(2023, 01, 01, 01, 01, 01);
		LocalDateTime fecha2 = LocalDateTime.of(2023, 01, 01, 01, 01, 01);

		Alerta a1 = new Informativa(fecha1, t1);
		Alerta a2 = new Urgente(fecha2, t2);
		
		


		sistema.enviarAlerta(a1);
		

		assertEquals(1,u1.getAlertas().size());
		assertEquals(0,u2.getAlertas().size());
		
		sistema.enviarAlerta(a2);
		assertEquals(2,u1.getAlertas().size());
		assertEquals(1,u2.getAlertas().size());
		
	}
	
	@Test
	void testEnviarAlertaAUnUsuario() {
		Sistema sistema = Sistema.getInstance();

		Usuario u1= new Usuario("Agustín");
		Usuario u2= new Usuario("Lionel"); 
		sistema.registrarUsuario(u1);
		sistema.registrarUsuario(u2);

		Tema t1= new Tema("Futbol");
		Tema t2= new Tema("Futsal");
		sistema.registrarTema(t1);
		sistema.registrarTema(t2);

		sistema.agregarUnTemaAlUsuario(u1, t1);
		sistema.agregarUnTemaAlUsuario(u1, t2);
		sistema.agregarUnTemaAlUsuario(u2, t2);

		LocalDateTime fecha1 = LocalDateTime.of(2023, 01, 01, 01, 01, 01);
		Alerta a1 = new Urgente(fecha1, t1);

		
		sistema.enviarAlertaAUnUsuario(a1, u1);
		assertEquals(1,u1.getAlertas().size());
		assertEquals(0,u2.getAlertas().size());

		Alerta a2 = new Informativa(fecha1, t1);

		// en este caso el usuario no posee el tema, por lo que no se registra la alerta.
		sistema.enviarAlertaAUnUsuario(a2, u2);
		assertEquals(0,u2.getAlertas().size());

		
	}
 
	@Test
	void testAlertasDeUnUsuario() {
		Sistema sistema = Sistema.getInstance();

		Usuario u1= new Usuario("Agustín");
		sistema.registrarUsuario(u1);

		Tema t1= new Tema("Futbol");
		Tema t2= new Tema("Futsal");
		sistema.registrarTema(t1);
		sistema.registrarTema(t2);

		sistema.agregarUnTemaAlUsuario(u1, t1);
		

		LocalDateTime fecha1 = LocalDateTime.of(2023, 01, 01, 01, 01, 01);
		Alerta a1 = new Urgente(fecha1, t1);
		sistema.enviarAlertaAUnUsuario(a1, u1);



		assertEquals(1,sistema.obtenerLasAlertasDeUnUsuario(u1).size());
		
		LocalDateTime fecha2 = LocalDateTime.of(2023, 01, 01, 01, 01, 01);
		Alerta a2 = new Urgente(fecha2, t2);
		sistema.agregarUnTemaAlUsuario(u1, t2);
		sistema.enviarAlertaAUnUsuario(a2, u1);
		assertEquals(2,sistema.obtenerLasAlertasDeUnUsuario(u1).size());

	}

	
	@Test
	void testalertaLeida() {
		Sistema sistema = Sistema.getInstance();
		
		Usuario u1= new Usuario("Agustín");
		sistema.registrarUsuario(u1);
		
		Tema t1= new Tema("Futbol");
		Tema t2= new Tema("Futsal");
		sistema.registrarTema(t1);
		sistema.registrarTema(t2);

		sistema.agregarUnTemaAlUsuario(u1, t1);
		sistema.agregarUnTemaAlUsuario(u1, t2);


		LocalDateTime fecha1 = LocalDateTime.of(2023, 01, 01, 01, 01, 01);
		Alerta a1 = new Urgente(fecha1, t1);
		
		sistema.enviarAlerta(a1);
		assertEquals(false,u1.getAlertas().get(0).getLeida());
		
		u1.leerAlerta(a1);
		assertEquals(true,u1.getAlertas().get(0).getLeida());
	}

	 
	@Test
	void testObtenerAlertasNoLeidasNoExpiradas() {
		Sistema sistema = Sistema.getInstance();
		Usuario u1= new Usuario("Agustín");
		sistema.registrarUsuario(u1);

		Tema t1= new Tema("Futbol");
		Tema t2= new Tema("Futsal");
		sistema.registrarTema(t1);
		sistema.registrarTema(t2);
		
		sistema.agregarUnTemaAlUsuario(u1, t1);
		sistema.agregarUnTemaAlUsuario(u1, t2);

		LocalDateTime noexpirada = LocalDateTime.of(2023, 01, 01, 01, 01, 01);
		LocalDateTime expirada = LocalDateTime.of(2017, 01, 01, 01, 01, 01);
		Alerta a1 = new Urgente(noexpirada, t1);
		Alerta a2 = new Urgente(expirada, t1);
		

		sistema.enviarAlerta(a1);
		sistema.enviarAlerta(a2);
		assertEquals(2,u1.getAlertas().size());
		assertEquals(1,u1.obtenerAlertasNoLeidasNiExpiradas().size());
		
		u1.leerAlerta(a1);
		assertEquals(0,u1.obtenerAlertasNoLeidasNiExpiradas().size());
	
	}

	 
	@Test
	void testObtenerAlertasNoExpiradasDeUnTema() {
		Sistema sistema = Sistema.getInstance();

		Usuario u1= new Usuario("Agustín");
		sistema.registrarUsuario(u1);

		Tema t1= new Tema("Futbol");
		Tema t2= new Tema("Futsal");
		sistema.registrarTema(t1);
		sistema.registrarTema(t2);
		
		sistema.agregarUnTemaAlUsuario(u1, t1);
		sistema.agregarUnTemaAlUsuario(u1, t2);
		

		
		LocalDateTime noexpirada = LocalDateTime.of(2023, 01, 01, 01, 01, 01);
		LocalDateTime noexpirada2 = LocalDateTime.of(2024, 01, 01, 01, 01, 01);
		LocalDateTime noexpirada3 = LocalDateTime.of(2025, 01, 01, 01, 01, 01);
		LocalDateTime expirada = LocalDateTime.of(2017, 01, 01, 01, 01, 01);

		Alerta a5 = new Urgente(noexpirada3, t1);
		Alerta a1 = new Urgente(noexpirada, t1);
		Alerta a2 = new Urgente(expirada, t1);
		Alerta a3 = new Urgente(noexpirada2, t1);
		Alerta a4 = new Informativa(noexpirada, t2);

		

		sistema.enviarAlerta(a1);
		sistema.enviarAlerta(a2);
		sistema.enviarAlerta(a3);
		sistema.enviarAlerta(a4);
		sistema.enviarAlerta(a5);

		assertEquals(3, sistema.obtenerAlertasNoExpiradasDeUnTema(t1).size());
		assertEquals(1, sistema.obtenerAlertasNoExpiradasDeUnTema(t2).size());
		
		//La primer alerta que devuelve es la más cercana a expirarse
		assertEquals(a1, sistema.obtenerAlertasNoExpiradasDeUnTema(t1).get(0));
		
		//Alerta de tipo urgente.
		assertEquals("Urgente", sistema.obtenerAlertasNoExpiradasDeUnTema(t1).get(0).getTipo());
		
		//Alerta de tipo Informativa.

		
		assertEquals("Informativa", sistema.obtenerAlertasNoExpiradasDeUnTema(t2).get(0).getTipo());
	}
}