package com.utndds.tests;

import com.utndds.creadores.CreadorProducto;
import com.utndds.creadores.CreadorReceta;
import com.utndds.creadores.CreadorUsuario;
import com.utndds.personas.Usuario;
import com.utndds.recetas.ComponenteReceta;
import com.utndds.recetas.RecetaCompuesta;
import com.utndds.recetas.RecetaSimple;
import com.utndds.recetas.Recetario;
import org.junit.Test;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.test.AbstractPersistenceTest;

import javax.persistence.Query;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class TestPersistencia extends AbstractPersistenceTest implements
        WithGlobalEntityManager {

    @Test
    public void unUsuarioSePersisteCorrectamente() {
        Query query = createQuery("SELECT u FROM Usuario u WHERE u =:user");
        Usuario unUsuario = new CreadorUsuario().build();
        Usuario otroUsuario = new CreadorUsuario().build();
        persist(otroUsuario);
        persist(unUsuario);
        assertEquals(unUsuario, query.setParameter("user", unUsuario)
                .getSingleResult());

    }

    @Test
    public void unaRecetaCompuestaSePersisteCorrectamente() {
        Query query = createQuery("SELECT r FROM ComponenteReceta r WHERE r =:receta");
        RecetaSimple recetaSimple = new CreadorReceta().build();
        RecetaCompuesta recetaCompuesta = new RecetaCompuesta();
        recetaCompuesta.agregarSubreceta(recetaSimple);
        persist(recetaSimple);
        persist(recetaCompuesta);
        assertEquals(recetaSimple, query.setParameter("receta", recetaSimple).getSingleResult());
        assertEquals(recetaCompuesta, query.setParameter("receta", recetaCompuesta).getSingleResult());
    }

    @Test
    public void unGrupoSePersiste() {
    } // TODO


    @Test
    public void cuandoLeAsignoAUnaRecetaUnUsuarioPersisteAmbos() {
    }

    @Test
    public void sePersisteBienLaReceta() {
        withTransaction(() -> {
            persist(new CreadorReceta()
                    .setNombre("Cocolatada")
                    .addIngrediente(
                            new CreadorProducto().setNombre("Coco").build())
                    .addCondimento(
                            new CreadorProducto().setNombre("Cacao").build()).build());});

        List<ComponenteReceta> recetas = Recetario.instancia.listarTodas();
        assertEquals("Cocolatada", recetas.get(0).getNombre());


    }
}
