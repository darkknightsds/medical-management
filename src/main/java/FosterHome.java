import org.sql2o.*;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.sql.Timestamp;

public class FosterHome
// implements DatabaseManagement
{
  private int foster_home_id;
  private int user_id;
  private String facility_name;
  private String primary_first;
  private String primary_last;
  private String address;
  private String city;
  private String state;
  private int zip;
  private String telephone;

  public FosterHome(int user_id, String facility_name, String primary_first, String primary_last, String address, String city, String state, int zip, String telephone) {
    this.foster_home_id = foster_home_id;
    this.user_id = user_id;
    this.facility_name = facility_name;
    this.primary_first = primary_first;
    this.primary_last = primary_last;
    this.address = address;
    this.city = city;
    this.state = state;
    this.zip = zip;
    this.telephone = telephone;
  }

  public int getId() {
    return foster_home_id;
  }

  public int getUserId() {
    return user_id;
  }

  public String getFacilityName() {
    return facility_name;
  }

  public String getPrimaryFirst() {
    return primary_first;
  }

  public String getPrimaryLast() {
    return primary_last;
  }

  public String getAddress() {
    return address;
  }

  public String getCity() {
    return city;
  }

  public String getState() {
    return state;
  }

  public int getZip() {
    return zip;
  }

  public String getTelephone() {
    return telephone;
  }

  @Override
  public boolean equals(Object otherFosterHome) {
    if(!(otherFosterHome instanceof FosterHome)) {
      return false;
    } else {
      FosterHome testFosterHome = (FosterHome) otherFosterHome;
      return this.getFacilityName().equals(testFosterHome.getFacilityName());
    }
  }

}
