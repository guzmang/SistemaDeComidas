package main;

import static spark.Spark.*;

import org.uqbarproject.jpa.java8.extras.PerThreadEntityManagers;
import spark.template.handlebars.HandlebarsTemplateEngine;

import controllers.HomeController;

public class Routes {

	public static void main(String[] args) {
		System.out.println("Iniciando servidor");


		HomeController home = new HomeController();
		HandlebarsTemplateEngine engine = new HandlebarsTemplateEngine();

		port(8080);

		staticFileLocation("/public");

		get("/", home::mostrar, engine);
		get("/index.html", (request, response) -> {
			response.redirect("/");
			return null;
		});

	}

}
