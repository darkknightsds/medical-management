import org.sql2o.*;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.sql.Timestamp;

public class Guardian  {
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
  public List<Guardian> all(){
    String sql = "SELECT * FROM guardians;";
    try(Connection con = DB.sql2o.open()){
      return con.createQuery(sql)
      .throwOnMappingFailure(false)
      .executeAndFetch(Guardian.class);
    }
  }


}
