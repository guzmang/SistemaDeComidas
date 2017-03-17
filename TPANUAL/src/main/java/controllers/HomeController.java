package controllers;

import com.utndds.creadores.CreadorReceta;
import com.utndds.personas.RepositorioUsuarios;
import com.utndds.recetas.ComponenteReceta;
import com.utndds.recetas.Recetario;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.transaction.TransactionalOps;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.HashMap;
import java.util.List;

public class HomeController implements WithGlobalEntityManager, TransactionalOps {

    public ModelAndView mostrar(Request request, Response response) {
        HashMap<String, Object> recetasView = new HashMap<>();

        recetasView.put("user", RepositorioUsuarios.repositorioUsuarios.prueba());
        recetasView.put("users", RepositorioUsuarios.repositorioUsuarios.listar());
       List<ComponenteReceta> recetas = Recetario.instancia.listarTodas();
        recetas.add(new CreadorReceta().setNombre("prueba").setCalorias(250).build());
       recetasView.put("recetas", Recetario.instancia.listarTodas());
        return new ModelAndView(recetasView, "home.hbs");
    }

}
