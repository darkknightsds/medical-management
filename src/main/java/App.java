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
       return new ModelAndView(model, layout2);
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

    post("/users/:userid/facilities/:facilityid/residents/new", (request, response) -> {
       Map<String, Object> model = new HashMap<String, Object>();
       User thisUser = User.find(Integer.parseInt(request.params(":userid")));
       FosterHome thisFacility = FosterHome.find(Integer.parseInt(request.params(":facilityid")));
       int foster_home_id = thisFacility.getId();
       String first_name = request.queryParams("first_name");
       String last_name = request.queryParams("last_name");
       String admit_date = request.queryParams("admit_date");
       String telephone = request.queryParams("telephone");
       String ssid = request.queryParams("ssid");
       String sex = request.queryParams("sex");
       String birth_date = request.queryParams("birth_date");
       String birth_place = request.queryParams("birth_place");
       String faith = request.queryParams("faith");
       String hobbies = request.queryParams("hobbies");
       String preferred_hospital = request.queryParams("preferred_hospital");
       String primary_care_name = request.queryParams("primary_care_name");
       String primary_phone = request.queryParams("primary_phone");
       Patient newPatient = new Patient(foster_home_id, first_name, last_name, admit_date, telephone, ssid, sex, birth_date, birth_place, faith, hobbies, preferred_hospital, primary_care_name, primary_phone);
       newPatient.save();
       String url = String.format("/users/" + thisUser.getId() + "/facilities/" + thisFacility.getId() + "/residents");
       response.redirect(url);
       return new ModelAndView(model, layout2);
    }, new VelocityTemplateEngine());

    get("/users/:userid/facilities/:facilityid/residents/:residentid", (request, response) -> {
       Map<String, Object> model = new HashMap<String, Object>();
       User user = request.session().attribute("user");
       User thisUser = User.find(Integer.parseInt(request.params(":userid")));
       FosterHome thisFacility = FosterHome.find(Integer.parseInt(request.params(":facilityid")));
       Patient thisResident = Patient.find(Integer.parseInt(request.params(":residentid")));
       model.put("user", thisUser);
       model.put("facility", thisFacility);
       model.put("resident", thisResident);
       model.put("template", "templates/resident.vtl");
       return new ModelAndView(model, layout2);
    }, new VelocityTemplateEngine());

    post("/users/:userid/facilities/:facilityid/residents/:residentid/tasks/new", (request, response) -> {
       Map<String, Object> model = new HashMap<String, Object>();
       User thisUser = User.find(Integer.parseInt(request.params(":userid")));
       FosterHome thisFacility = FosterHome.find(Integer.parseInt(request.params(":facilityid")));
       Patient thisResident = Patient.find(Integer.parseInt(request.params(":residentid")));
       int patient_id = thisResident.getPatientId();
       String description = request.queryParams("description");
       String date_time = request.queryParams("date_time");
       Task newTask = new Task(patient_id, description, date_time);
       newTask.save();
       String url = String.format("/users/" + thisUser.getId() + "/facilities/" + thisFacility.getId() + "/residents/" + thisResident.getPatientId());
       response.redirect(url);
       return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/users/:userid/facilities/:facilityid/residents/:residentid/medications/new", (request, response) -> {
       Map<String, Object> model = new HashMap<String, Object>();
       User user = request.session().attribute("user");
       User thisUser = User.find(Integer.parseInt(request.params(":userid")));
       FosterHome thisFacility = FosterHome.find(Integer.parseInt(request.params(":facilityid")));
       Patient thisResident = Patient.find(Integer.parseInt(request.params(":residentid")));
       model.put("user", thisUser);
       model.put("facility", thisFacility);
       model.put("resident", thisResident);
       model.put("template", "templates/medication-form.vtl");
       return new ModelAndView(model, layout2);
    }, new VelocityTemplateEngine());

    get("/users/:userid/facilities/:facilityid/residents/:residentid/medhistory/new", (request, response) -> {
       Map<String, Object> model = new HashMap<String, Object>();
       User user = request.session().attribute("user");
       User thisUser = User.find(Integer.parseInt(request.params(":userid")));
       FosterHome thisFacility = FosterHome.find(Integer.parseInt(request.params(":facilityid")));
       Patient thisResident = Patient.find(Integer.parseInt(request.params(":residentid")));
       model.put("user", thisUser);
       model.put("facility", thisFacility);
       model.put("resident", thisResident);
       model.put("template", "templates/medhistory-form.vtl");
       return new ModelAndView(model, layout2);
    }, new VelocityTemplateEngine());

    get("/users/:userid/facilities/:facilityid/residents/:residentid/insurance/new", (request, response) -> {
       Map<String, Object> model = new HashMap<String, Object>();
       User user = request.session().attribute("user");
       User thisUser = User.find(Integer.parseInt(request.params(":userid")));
       FosterHome thisFacility = FosterHome.find(Integer.parseInt(request.params(":facilityid")));
       Patient thisResident = Patient.find(Integer.parseInt(request.params(":residentid")));
       model.put("user", thisUser);
       model.put("facility", thisFacility);
       model.put("resident", thisResident);
       model.put("template", "templates/insurance-form.vtl");
       return new ModelAndView(model, layout2);
    }, new VelocityTemplateEngine());

    get("/users/:userid/facilities/:facilityid/residents/:residentid/guardian/new", (request, response) -> {
       Map<String, Object> model = new HashMap<String, Object>();
       User user = request.session().attribute("user");
       User thisUser = User.find(Integer.parseInt(request.params(":userid")));
       FosterHome thisFacility = FosterHome.find(Integer.parseInt(request.params(":facilityid")));
       Patient thisResident = Patient.find(Integer.parseInt(request.params(":residentid")));
       model.put("user", thisUser);
       model.put("facility", thisFacility);
       model.put("resident", thisResident);
       model.put("template", "templates/guardian-form.vtl");
       return new ModelAndView(model, layout2);
    }, new VelocityTemplateEngine());

    post("/users/:userid/facilities/:facilityid/residents/:residentid/medications/new", (request, response) -> {
       Map<String, Object> model = new HashMap<String, Object>();
       User thisUser = User.find(Integer.parseInt(request.params(":userid")));
       FosterHome thisFacility = FosterHome.find(Integer.parseInt(request.params(":facilityid")));
       Patient thisResident = Patient.find(Integer.parseInt(request.params(":residentid")));
       int patient_id = thisResident.getPatientId();
       String name = request.queryParams("name");
       String dosage = request.queryParams("dosage");
       String frequency = request.queryParams("frequency");
       Medication newMedication = new Medication(patient_id, name, dosage, frequency);
       newMedication.save();
       String url = String.format("/users/" + thisUser.getId() + "/facilities/" + thisFacility.getId() + "/residents/" + thisResident.getPatientId());
       return new ModelAndView(model, layout2);
    }, new VelocityTemplateEngine());

    // get("/users/:userid/facilities/:facilityid/residents/:residentid/medhistory/new", (request, response) -> {
    //    Map<String, Object> model = new HashMap<String, Object>();
    //    User user = request.session().attribute("user");
    //    User thisUser = User.find(Integer.parseInt(request.params(":userid")));
    //    FosterHome thisFacility = FosterHome.find(Integer.parseInt(request.params(":facilityid")));
    //    Patient thisResident = Patient.find(Integer.parseInt(request.params(":residentid")));
    //    model.put("user", thisUser);
    //    model.put("facility", thisFacility);
    //    model.put("resident", thisResident);
    //    model.put("template", "templates/medhistory-form.vtl");
    //    return new ModelAndView(model, layout2);
    // }, new VelocityTemplateEngine());
    //
    // get("/users/:userid/facilities/:facilityid/residents/:residentid/insurance/new", (request, response) -> {
    //    Map<String, Object> model = new HashMap<String, Object>();
    //    User user = request.session().attribute("user");
    //    User thisUser = User.find(Integer.parseInt(request.params(":userid")));
    //    FosterHome thisFacility = FosterHome.find(Integer.parseInt(request.params(":facilityid")));
    //    Patient thisResident = Patient.find(Integer.parseInt(request.params(":residentid")));
    //    model.put("user", thisUser);
    //    model.put("facility", thisFacility);
    //    model.put("resident", thisResident);
    //    model.put("template", "templates/insurance-form.vtl");
    //    return new ModelAndView(model, layout2);
    // }, new VelocityTemplateEngine());
    //
    // get("/users/:userid/facilities/:facilityid/residents/:residentid/guardian/new", (request, response) -> {
    //    Map<String, Object> model = new HashMap<String, Object>();
    //    User user = request.session().attribute("user");
    //    User thisUser = User.find(Integer.parseInt(request.params(":userid")));
    //    FosterHome thisFacility = FosterHome.find(Integer.parseInt(request.params(":facilityid")));
    //    Patient thisResident = Patient.find(Integer.parseInt(request.params(":residentid")));
    //    model.put("user", thisUser);
    //    model.put("facility", thisFacility);
    //    model.put("resident", thisResident);
    //    model.put("template", "templates/guardian-form.vtl");
    //    return new ModelAndView(model, layout2);
    // }, new VelocityTemplateEngine());

  }
}
