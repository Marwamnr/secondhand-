package app;

import app.config.ThymeleafConfig;
import app.controllers.UserController;
import io.javalin.Javalin;
import io.javalin.rendering.template.JavalinThymeleaf;

public class Main {
    public static void main(String[] args)
    {

        // Initializing Javalin and Jetty webserver

        Javalin app = Javalin.create(config -> { //den starter hele javalin op
            config.staticFiles.add("/public");
            config.fileRenderer(new JavalinThymeleaf(ThymeleafConfig.templateEngine()));
        }).start(7070);

        // Routing

        app.get("/", ctx ->  ctx.render("index.html"));

        app.post("/login", ctx ->
        {
            UserController.login(ctx);
        });


        app.get("/welcome.html", ctx ->  ctx.render("welcome.html"));

        //get - bruges til at hente data
        //post - bruges til at sende data
    }
}
