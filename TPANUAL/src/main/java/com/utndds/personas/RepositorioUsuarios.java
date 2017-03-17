package com.utndds.personas;

import org.uqbarproject.jpa.java8.extras.EntityManagerOps;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.transaction.TransactionalOps;

import java.util.List;

public class RepositorioUsuarios implements WithGlobalEntityManager, TransactionalOps, EntityManagerOps {
    public static final RepositorioUsuarios repositorioUsuarios = new RepositorioUsuarios();

    public List<Usuario> listar() {
        return createQuery("Select r from Usuario",
                Usuario.class).getResultList();
    }

    public Usuario prueba() {
        return createQuery("Select r from Usuario r", Usuario.class).getSingleResult();
    }


}
