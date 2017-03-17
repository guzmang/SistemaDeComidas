package com.utndds.tests;

import static org.junit.Assert.assertEquals;

import org.junit.rules.ExpectedException;
import org.junit.Before;
import org.junit.Test;
import org.junit.Rule;

import java.time.LocalDate;
import java.util.HashSet;

import com.utndds.condiciones.*;
import com.utndds.creadores.CreadorUsuario;
import com.utndds.personas.Usuario;
import com.utndds.personas.RepositorioDeUsuarios;
import com.utndds.excepciones.NoEncontroUsuarioException;

public class TestRepositorioDeRecetas {

	private Usuario usuario;
	private Usuario usuario1;
	private Usuario usuario2;
	private Usuario usuario3;
	private Usuario usuario4;
	private Usuario usuario5;
	
	public HashSet<Condicion> condiciones1;
	public HashSet<Condicion> condiciones2;
	public HashSet<Condicion> condiciones3;
	public HashSet<Condicion> condiciones4;
	
	public Condicion veganismo;
	public Condicion celiaquia;
	public Condicion diabetes;	
	
	public RepositorioDeUsuarios repo;

	
	@Before
	public void setup() {

		veganismo = new Vegano();
		celiaquia = new Celiaco();
		diabetes = new Diabetico();		
		
		condiciones1 = new HashSet<Condicion>();
		condiciones2 = new HashSet<Condicion>();
		condiciones3 = new HashSet<Condicion>();
		condiciones4 = new HashSet<Condicion>();
		
		condiciones1.add(veganismo);
		condiciones1.add(diabetes);
		
		condiciones2.add(diabetes);
		condiciones3.add(celiaquia);
		
		condiciones4.add(veganismo);
		condiciones4.add(diabetes);
		
		usuario = new CreadorUsuario().setNombre("Leonardo").build();		
		usuario1 = new CreadorUsuario().setNombre("Leonardo").setAsHombre().setNacimiento(LocalDate.of(2000, 02, 02)).setCondiciones(condiciones1).build();
		usuario2 = new CreadorUsuario().setNombre("Donatello").setAsHombre().setNacimiento(LocalDate.of(2000, 02, 02)).setCondiciones(condiciones2).build();
		usuario3 = new CreadorUsuario().setNombre("Miguel Angelo").setAsHombre().setNacimiento(LocalDate.of(2000, 02, 02)).setCondiciones(condiciones3).build();
		usuario4 = new CreadorUsuario().setNombre("Rafael").setAsHombre().setNacimiento(LocalDate.of(2000, 02, 02)).setCondiciones(condiciones4).build();
		usuario5 = new CreadorUsuario().setNombre("Leonardo").setAsHombre().setNacimiento(LocalDate.of(2000, 02, 02)).setCondiciones(condiciones4).build();

		repo = new RepositorioDeUsuarios();
		repo.addPendiente(usuario1);
		repo.addPendiente(usuario2);
		repo.addPendiente(usuario3);
		repo.addPendiente(usuario4);
		
	}
	
	@Test
	public void TestAgregoUsuariosYVoyRemoviendoDeLosPendientes() {
		repo.add(usuario1);
		repo.add(usuario4);
		assertEquals(repo.getUsuariosPendientes().size(),2);
		assertEquals(repo.getUsuariosAceptados().size(),2);
	}
	
	@Test
	public void TestRechazoUsuariosYVoyRemoviendoDeLosPendientes() {
		repo.remove(usuario2);
		repo.remove(usuario3);
		assertEquals(repo.getUsuariosRechazados().indexOf(usuario2),0);
		assertEquals(repo.getUsuariosRechazados().get(0),usuario2);
		assertEquals(repo.getMotivosRechazados().get(0),"Rechazado");
	}
	
	@Test
	public void TestDevolvemeUnaListaDeUsuariosConMismoNombreYCondiciones() {
		repo.add(usuario1);
		repo.add(usuario2);
		repo.add(usuario3);
		repo.add(usuario4);
		repo.add(usuario5);
		assertEquals(repo.list(usuario1).size(),2);
		assertEquals(repo.list(usuario1).get(0),usuario1);
		assertEquals(repo.list(usuario1).get(1),usuario5);
	}
	
	@Test
	public void TestDevolvemeUnUsuarioConEseNombre() {
		repo.add(usuario4);
		repo.add(usuario3);
		repo.add(usuario1);
		repo.add(usuario2);
		assertEquals(repo.get(usuario),usuario1);
	}	
	
    @Rule
    public ExpectedException thrown= ExpectedException.none();

    @Test
    public void TestDeExcepcionCuandoNoSeEncuentraUsuario() {
        thrown.expect(NoEncontroUsuarioException.class);
        throw new NoEncontroUsuarioException();
    }	
    

	
}
