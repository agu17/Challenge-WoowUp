/*package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Calendar;
import java.util.Date;

import org.junit.jupiter.api.Test;

import modelo.*;

class TestSistema {

	@Test
	void testRegistrarUnUsuario() {
		Sistema sistema = new Sistema();
		sistema.registrarUnUsuario("Robertino");
		sistema.registrarUnUsuario("Juan");

		assertEquals(2,sistema.getUsuarios().size());
	}

	@Test
	void testRegistrarUnTema() {
		Sistema sistema = new Sistema();
		
		sistema.registrarUnTema("Java");
		assertEquals(1,sistema.getTemas().size());
	}

	@Test
	void testAgregarUnTemaParaUnUsuario() {
		Sistema sistema = new Sistema();
		sistema.registrarUnUsuario("Robertino");
		sistema.registrarUnTema("Java");
		
		Usuario u1 = sistema.getUsuarios().get(0);
		Tema t1 = sistema.getTemas().get(0);
		
		sistema.agregarUnTemaParaUnUsuario(t1, u1);
		
		assertEquals(1,u1.getTemas().size());
		assertEquals(1,t1.getUsuariosConEsteTema().size());
	}

	@Test
	void testEnviarAlertaInformativa() {
		Sistema sistema = new Sistema();
		sistema.registrarUnUsuario("Robertino");
		sistema.registrarUnUsuario("Juan");
		sistema.registrarUnTema("Java");
		sistema.registrarUnTema("POO");
		
		Usuario u1 = sistema.getUsuarios().get(0);
		Usuario u2 = sistema.getUsuarios().get(1);
		Tema t1 = sistema.getTemas().get(0);
		Tema t2 = sistema.getTemas().get(1);
		Alerta a1 = new AlertaInformativa(t1);
		Alerta a2 = new AlertaInformativa(t2);
		
		u1.agregarUnTema(t1);
		u1.agregarUnTema(t2);
		u2.agregarUnTema(t1);
		
		sistema.enviarAlerta(a1);
		assertEquals(1,u1.getAlertas().size());
		assertEquals(1,u2.getAlertas().size());
		
		sistema.enviarAlerta(a2);
		assertEquals(2,u1.getAlertas().size());
		assertEquals(1,u2.getAlertas().size());
		
	}
	@Test
	void testEnviarAlertaUrgente() {
		Sistema sistema = new Sistema();
		sistema.registrarUnUsuario("Robertino");
		sistema.registrarUnUsuario("Juan");
		sistema.registrarUnTema("Java");
		sistema.registrarUnTema("POO");
		
		Usuario u1 = sistema.getUsuarios().get(0);
		Usuario u2 = sistema.getUsuarios().get(1);
		Tema t1 = sistema.getTemas().get(0);
		Tema t2 = sistema.getTemas().get(1);
		
		Alerta a1 = new AlertaUrgente(t1);
		Alerta a2 = new AlertaUrgente(t2);
		
		sistema.enviarAlerta(a1);
		assertEquals(1,u1.getAlertas().size());
		assertEquals(1,u2.getAlertas().size());
		
		sistema.enviarAlerta(a2);
		assertEquals(2,u2.getAlertas().size());
	}

	@Test
	void testEnviarAlertaA() {
		Sistema sistema = new Sistema();
		sistema.registrarUnUsuario("Robertino");
		sistema.registrarUnUsuario("Juan");
		sistema.registrarUnTema("Java");
		
		
		Usuario u1 = sistema.getUsuarios().get(0);
		Usuario u2 = sistema.getUsuarios().get(1);
		
		Tema t1 = sistema.getTemas().get(0);
		
		Alerta a1 = new AlertaInformativa(t1);

		
		u1.agregarUnTema(t1);
		u2.agregarUnTema(t1);
		
		sistema.enviarAlertaA(u1, a1);
		assertEquals(1,u1.getAlertas().size());
		assertEquals(0,u2.getAlertas().size());
		
	}

	@Test
	void testVerLasAlertasDeUnUsuario() {
		Sistema sistema = new Sistema();
		sistema.registrarUnUsuario("Robertino");
		sistema.registrarUnTema("Java");
		
		Usuario u1 = sistema.getUsuarios().get(0);
		Tema t1 = sistema.getTemas().get(0);
		
		
		Calendar fecha1 = Calendar.getInstance();
		fecha1.set(Calendar.YEAR, 2022);
		fecha1.set(Calendar.MONTH, Calendar.DECEMBER);
		fecha1.set(Calendar.DAY_OF_MONTH, 17);
		Date fechaNoExpirada = fecha1.getTime();
		
		Calendar fecha2 = Calendar.getInstance();
		fecha2.set(Calendar.YEAR, 2021);
		fecha2.set(Calendar.MONTH, Calendar.DECEMBER);
		fecha2.set(Calendar.DAY_OF_MONTH, 17);
		Date fechaExpirada = fecha2.getTime();
		
		Alerta a1 = new AlertaInformativa(t1,fechaNoExpirada);
		sistema.enviarAlertaA(u1, a1);
		assertEquals(1,sistema.verLasAlertasDeUnUsuario(u1).size());
		
		Alerta a2 = new AlertaInformativa(t1,fechaExpirada);
		sistema.enviarAlertaA(u1, a2);
		assertEquals(1,sistema.verLasAlertasDeUnUsuario(u1).size());

	}
	
	@Test
	void testMarcarAlertaComoLeidaParaUnUsuario() {
		Sistema sistema = new Sistema();
		
		sistema.registrarUnUsuario("Robertino");
		sistema.registrarUnTema("POO");
		
		Usuario u1 = sistema.getUsuarios().get(0);
		Tema t1 = sistema.getTemas().get(0);
		Alerta a1 = new AlertaInformativa(t1);
		
		u1.agregarUnTema(t1);
		
		sistema.enviarAlerta(a1);
		assertEquals(false,u1.getAlertas().get(0).getLeida());
		
		u1.marcarAlertaComoLeida(a1);
		assertEquals(true,u1.getAlertas().get(0).getLeida());
	}
	
	@Test
	void testVerAlertasNoLeidasDeUnUsuario() {
		Sistema sistema = new Sistema();
		sistema.registrarUnUsuario("Robertino");
		sistema.registrarUnTema("POO");
		
		Usuario u1 = sistema.getUsuarios().get(0);
		Tema t1 = sistema.getTemas().get(0);
		
		Calendar fechaExpMasVieja = Calendar.getInstance();
		fechaExpMasVieja.set(Calendar.YEAR, 2023);
		fechaExpMasVieja.set(Calendar.MONTH, Calendar.DECEMBER);
		fechaExpMasVieja.set(Calendar.DAY_OF_MONTH, 17);
		Date fechaMasVieja = fechaExpMasVieja.getTime();
		
		Calendar fechaExpMenosVieja = Calendar.getInstance();
		fechaExpMenosVieja.set(Calendar.YEAR, 2022);
		fechaExpMenosVieja.set(Calendar.MONTH, Calendar.DECEMBER);
		fechaExpMenosVieja.set(Calendar.DAY_OF_MONTH, 17);
		Date fechaMenosVieja = fechaExpMenosVieja.getTime();
		
		Alerta a1 = new AlertaInformativa(t1,fechaMasVieja);
		Alerta a2 = new AlertaInformativa(t1,fechaMenosVieja);
		Alerta a3 = new AlertaInformativa(t1,fechaMasVieja);
		
		u1.agregarUnTema(t1);
		
		sistema.enviarAlerta(a1);
		sistema.enviarAlerta(a2);
		assertEquals(2,u1.getAlertas().size());
		assertEquals(2,u1.obternerAlertasNoLeidas().size());
		
		u1.marcarAlertaComoLeida(a1);
		assertEquals(1,u1.obternerAlertasNoLeidas().size());
		
		//La primer alerta que me devuelve es la segunda que guarde porque esta en orden de menos antigua a mas antigua
		sistema.enviarAlerta(a3);
		assertEquals(a2,u1.obternerAlertasNoLeidas().get(0));
	}

	@Test
	void testObtenerAlertasDeUnTema() {
		Sistema sistema = new Sistema();
		sistema.registrarUnUsuario("Robertino");
		sistema.registrarUnTema("POO");
		sistema.registrarUnTema("JAVA");
		
		Usuario u1 = sistema.getUsuarios().get(0);
		Tema t1 = sistema.getTemas().get(0);
		Tema t2 = sistema.getTemas().get(1);
		u1.agregarUnTema(t1);
		u1.agregarUnTema(t2);
		
		Calendar fechaExpMasVieja = Calendar.getInstance();
		fechaExpMasVieja.set(Calendar.YEAR, 2023);
		fechaExpMasVieja.set(Calendar.MONTH, Calendar.DECEMBER);
		fechaExpMasVieja.set(Calendar.DAY_OF_MONTH, 17);
		Date fechaMasVieja = fechaExpMasVieja.getTime();
		
		Calendar fechaExpMenosVieja = Calendar.getInstance();
		fechaExpMenosVieja.set(Calendar.YEAR, 2022);
		fechaExpMenosVieja.set(Calendar.MONTH, Calendar.DECEMBER);
		fechaExpMenosVieja.set(Calendar.DAY_OF_MONTH, 17);
		Date fechaMenosVieja = fechaExpMenosVieja.getTime();
		
		Alerta a1 = new AlertaInformativa(t1,fechaMasVieja);
		Alerta a2 = new AlertaInformativa(t2);
		Alerta a3 = new AlertaInformativa(t1,fechaMenosVieja);
		Alerta a4 = new AlertaInformativa(t1,fechaMasVieja);
		
		sistema.enviarAlerta(a1);
		sistema.enviarAlerta(a2);
		sistema.enviarAlerta(a3);
		sistema.enviarAlerta(a4);
		
		assertEquals(3, sistema.obtenerAlertasDeUnTema(t1).size());
		assertEquals(1, sistema.obtenerAlertasDeUnTema(t2).size());
		
		//La primer alerta que me devuelve es la segunda que guarde para este tema porque esta en orden de menos antigua a mas antigua
		assertEquals(a3, sistema.obtenerAlertasDeUnTema(t1).get(0));
		
		//CONFIRMA QUE ESTA ALERTA FUE PARA TODOS LOS QUE TIENEN ESTE TEMA
		assertEquals(true, sistema.obtenerAlertasDeUnTema(t1).get(0).getAlertaParaTodos());
		
		//CONFIRMA QUE ESTA ALERTA FUE PARA UN USUARIO ESPECIFICO
		Alerta a5 = new AlertaInformativa(t1,fechaMasVieja);
		sistema.enviarAlertaA(u1,a5);
		assertEquals(false, sistema.obtenerAlertasDeUnTema(t1).get(3).getAlertaParaTodos());
	}

}*/