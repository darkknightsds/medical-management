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
    String layout2 = "templates/layout-2.vtl";

    get("/", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      model.put("template", "templates/index.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/users/new", (request, response) -> {
       Map<String, Object> model = new HashMap<String, Object>();
       model.put("template", "templates/user-form.vtl");
       return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      String username = request.queryParams("username");
      String password = request.queryParams("password");
      User user = User.findByUsername(username);
      // try {
      //   user = User.findByUsername(username);
      // } catch(IllegalArgumentException exception) {
      //   String url = String.format("/invalid");
      //   response.redirect(url);
      // }
      if ((user = User.findByUsername(username)) == null) {
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

    post("/users/new", (request, response) -> {
       Map<String, Object> model = new HashMap<String, Object>();
       String username = request.queryParams("username");
       String password = request.queryParams("password");
       User newUser = new User(username, password);
       newUser.save();
       String url = String.format("/users/" + newUser.getId());
       response.redirect(url);
       return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/invalid", (request, response) -> {
       Map<String, Object> model = new HashMap<String, Object>();
       model.put("template", "templates/invalid.vtl");
       return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/users/:userid", (request, response) -> {
       Map<String, Object> model = new HashMap<String, Object>();
       User user = request.session().attribute("user");
       User thisUser = User.find(Integer.parseInt(request.params(":userid")));
       model.put("user", thisUser);
       model.put("template", "templates/user.vtl");
       return new ModelAndView(model, layout2);
    }, new VelocityTemplateEngine());

    post("/users/:userid/facilities/new", (request, response) -> {
       Map<String, Object> model = new HashMap<String, Object>();
       User thisUser = User.find(Integer.parseInt(request.params(":userid")));
       int user_id = thisUser.getId();
       String facility_name = request.queryParams("facility_name");
       String primary_first = request.queryParams("primary_first");
       String primary_last = request.queryParams("primary_last");
       String address = request.queryParams("address");
       String city = request.queryParams("city");
       String state = request.queryParams("state");
       int zip = Integer.parseInt(request.queryParams("zip"));
       String telephone = request.queryParams("telephone");
       FosterHome newFacility = new FosterHome(user_id, facility_name, primary_first, primary_last, address, city, state, zip, telephone);
       newFacility.save();
       String url = String.format("/users/" + thisUser.getId());
       response.redirect(url);
       return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/users/:userid/facilities/:facilityid", (request, response) -> {
       Map<String, Object> model = new HashMap<String, Object>();
       User user = request.session().attribute("user");
       User thisUser = User.find(Integer.parseInt(request.params(":userid")));
       FosterHome thisFacility = FosterHome.find(Integer.parseInt(request.params(":facilityid")));
       model.put("user", thisUser);
       model.put("facility", thisFacility);
       model.put("template", "templates/facility.vtl");
       return new ModelAndView(model, layout2);
    }, new VelocityTemplateEngine());

    get("/users/:userid/facilities/:facilityid/residents", (request, response) -> {
       Map<String, Object> model = new HashMap<String, Object>();
       User user = request.session().attribute("user");
       User thisUser = User.find(Integer.parseInt(request.params(":userid")));
       FosterHome thisFacility = FosterHome.find(Integer.parseInt(request.params(":facilityid")));
       model.put("user", thisUser);
       model.put("facility", thisFacility);
       model.put("template", "templates/residents.vtl");
       return new ModelAndView(model, layout2);
    }, new VelocityTemplateEngine());
  }
}
