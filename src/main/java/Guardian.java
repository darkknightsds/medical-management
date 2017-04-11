import org.sql2o.*;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.sql.Timestamp;

public class Guardian  {
  private int guardian_id;
  private int patient_id;
  private String first_name;
  private String last_name;
  private String relationship;
  private String address;
  private String city;
  private String state;
  private int zip;
  private String telephone;


  // Constructor
  public Guardian(int patient_id, String first_name, String last_name, String relationship, String address, String city, String state, int zip, String telephone) {
    this.first_name = first_name;
    this.last_name = last_name;
    this.relationship = relationship;
    this.address = address;
    this.city = city;
    this.state = state;
    this.zip = zip;
    this.telephone = telephone;
    this.patient_id = patient_id;
  }
  // Getters
  public int getGuardianId(){
    return guardian_id;
  }

  public int getPatientId(){
    return patient_id;
  }
  public String getFirstName(){
    return first_name;
  }
  public String getLastName(){
    return last_name;
  }
  public String getRelationship(){
    return relationship;
  }
  public String getAddress(){
    return address;
  }
  public String getCity(){
    return city;
  }
  public String getState(){
    return state;
  }
  public int getZip(){
    return zip;
  }
  public String getTelephone(){
    return telephone;
  }

  // Functions
  public static List<Guardian> all(){
    String sql = "SELECT * FROM guardians;";
    try(Connection con = DB.sql2o.open()){
      return con.createQuery(sql)
      .throwOnMappingFailure(false)
      .executeAndFetch(Guardian.class);
    }
  }

  public void save(){
      try (Connection con = DB.sql2o.open()) {
        String sql = "INSERT INTO guardians ( patient_id, first_name, last_name, relationship, address, city, state, zip, telephone ) VALUES (:patient_id, :first_name, :last_name, :relationship, :address, :city, :state, :zip, :telephone);";
        this.guardian_id = (int) con.createQuery(sql, true)
          .addParameter("patient_id", this.patient_id)
          .addParameter("first_name", this.first_name)
          .addParameter("last_name", this.last_name)
          .addParameter("relationship", this.relationship)
          .addParameter("address", this.address)
          .addParameter("city", this.city)
          .addParameter("state", this.state)
          .addParameter("zip", this.zip)
          .addParameter("telephone", this.telephone)
          .executeUpdate()
          .getKey();
    }
  }

  @Override
  public boolean equals(Object otherGuardian) {
    if (!(otherGuardian instanceof Guardian)) {
      return false;
    } else {
      Guardian newGuardian = (Guardian) otherGuardian;
      return this.getPatientId() == newGuardian.getPatientId() &&
             this.getFirstName().equals(newGuardian.getFirstName()) &&
             this.getLastName().equals(newGuardian.getLastName()) &&
             this.getGuardianId() == newGuardian.getGuardianId();
    }
  }

  public static Guardian find(int guardian_id) {
    try (Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM guardians WHERE guardian_id = :guardian_id;";
      return con.createQuery(sql)
        .addParameter("guardian_id", guardian_id)
        .executeAndFetchFirst(Guardian.class);
    }
  }

  public void updateGuardian (int patient_id, String first_name, String last_name, String relationship, String address, String city, String state, int zip, String telephone) {
    this.patient_id = patient_id;
    this.first_name = first_name;
    this.last_name = last_name;
    this.relationship = relationship;
    this.address = address;
    this.city = city;
    this.state = state;
    this.zip = zip;
    this.telephone = telephone;

    try (Connection con = DB.sql2o.open()) {
      String sql = "UPDATE guardians SET (patient_id, first_name, last_name, relationship, address, city, state, zip, telephone) = ( :patient_id, :first_name, :last_name, :relationship, :address, :city, :state, :zip, :telephone ) WHERE guardian_id = :guardian_id;";
      con.createQuery(sql)
        .addParameter("guardian_id", this.guardian_id)
        .addParameter("patient_id", patient_id)
        .addParameter("first_name", first_name)
        .addParameter("last_name", last_name)
        .addParameter("relationship", relationship)
        .addParameter("address", address)
        .addParameter("city", city)
        .addParameter("state", state)
        .addParameter("zip", zip)
        .addParameter("telephone", telephone)
        .executeUpdate();
    }
  }

  public void deleteGuardian() {
    try (Connection con = DB.sql2o.open()) {
      String sql = "DELETE FROM guardians WHERE guardian_id = :guardian_id;";
      con.createQuery(sql)
        .addParameter("guardian_id", this.guardian_id)
        .executeUpdate();
    }
  }



}
