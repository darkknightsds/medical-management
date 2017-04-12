import java.util.Map;
import java.util.HashMap;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;
import static spark.Spark.*;

public class App {
  public static void main(String[] args) {
    // Use this setting only for development
    externalStaticFileLocation(String.format("%s/src/main/resources/public", System.getProperty("user.dir")));

    staticFileLocation("/public");
    String layout = "templates/layout.vtl";

    get("/", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      model.put("template", "templates/index.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      String username = request.queryParams("username");
      String password = request.queryParams("password");
      User user = User.findByUsername(username);
      try {
        user = User.findByUsername(username);
      } catch(IllegalArgumentException exception) {
        String url = String.format("/invalid");
        response.redirect(url);
      }
      if (user.getPassword().equals(password)) {
        request.session().attribute("user", user);
        String url = String.format("/users/" + user.getId());
        response.redirect(url);
      } else {
        String url = String.format("/invalid");
        response.redirect(url);
      }
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    // User user = request.session().attribute("user");

    get("/patients/new", (request, response) -> {
       Map<String, Object> model = new HashMap<String, Object>();
       model.put("template", "templates/patient-form.vtl");
       return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());
  }
}
