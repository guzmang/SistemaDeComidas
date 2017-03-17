package main;

import com.utndds.creadores.CreadorProducto;
import com.utndds.creadores.CreadorReceta;
import com.utndds.creadores.CreadorUsuario;
import com.utndds.recetas.Recetario;
import org.uqbarproject.jpa.java8.extras.EntityManagerOps;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.transaction.TransactionalOps;

import java.time.LocalDate;

public class BeforeLaunch implements WithGlobalEntityManager, EntityManagerOps,
        TransactionalOps {

    public static void main(String[] args) {
        new BeforeLaunch().run();
    }

    public void run() {
        withTransaction(() -> {
            persist(new CreadorReceta()
                    .setNombre("Cocolatada")
                    .addIngrediente(
                            new CreadorProducto().setNombre("Coco").build())
                    .addCondimento(
                            new CreadorProducto().setNombre("Cacao").build()).build());
             persist(new CreadorUsuario().setAsMujer().setNombre("Bianca")
                    .setAsVegano().addPreferencia("Coco").setPeso(58.0)
                    .setAltura(1.70).setNacimiento(LocalDate.of(1995, 03, 05))
                    .setRutina("Activo").build());
        });
    }

}
