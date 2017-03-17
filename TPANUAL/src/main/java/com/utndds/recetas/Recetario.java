package com.utndds.recetas;

import java.util.Collection;
import java.util.List;

import org.uqbarproject.jpa.java8.extras.EntityManagerOps;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;

import com.utndds.excepciones.ConsultaNoValidaException;
import com.utndds.excepciones.DificultadIncorrectaException;
import org.uqbarproject.jpa.java8.extras.transaction.TransactionalOps;

public class Recetario implements WithGlobalEntityManager, TransactionalOps, EntityManagerOps {
	public static final Recetario instancia = new Recetario();

	public void agregar(ComponenteReceta receta) {

		persist(receta);
	};

	public void quitar(ComponenteReceta receta) {

		 remove(receta);
	};

	public List<ComponenteReceta> listarTodas() {
		return createQuery("from ComponenteReceta",
				ComponenteReceta.class).getResultList();
	};

	public ComponenteReceta buscar(long id) throws DificultadIncorrectaException,
			ConsultaNoValidaException {
		return find(ComponenteReceta.class, id);
	}

	public Collection<ComponenteReceta> buscarPorCampo(String field, String value) {
		String query = "from ComponenteReceta where " + field + " like :busq";
		return createQuery(query, ComponenteReceta.class)
				.setParameter("busq","%"+value+"%").getResultList();
	}
}
