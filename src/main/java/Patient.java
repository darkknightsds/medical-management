import org.sql2o.*;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.sql.Timestamp;

public class Patient  {
  private String first_name;
  private String last_name;
  private Date admit_date;
  private String telephone;
  private String ssid;
  private String sex;
  private Date birth_date;
  private String birth_place;
  private String faith;
  private String hobbies;
  private String preferred_hospital;
  private String primary_care_name;
  private String primary_phone;
  private int id;
  private int foster_home_id;

  public Patient(int foster_home_id, String first_name, String last_name, Date admit_date, String telephone, String ssid, String sex, Date birth_date, String birth_place, String faith, String hobbies, String preferred_hospital, String primary_care_name, String primary_phone) {
    this.foster_home_id = foster_home_id;
    this.first_name = first_name;
    this.last_name = last_name;
    this.admit_date = admit_date;
    this.telephone = telephone;
    this.ssid = ssid;
    this.sex = sex;
    this.birth_date = birth_date;
    this.birth_place = birth_place;
    this.faith = faith;
    this.hobbies = hobbies;
    this.preferred_hospital = preferred_hospital;
    this.primary_care_name = primary_care_name;
    this.primary_phone = primary_phone;
  }

  public int getFosterHomeId() {
    return foster_home_id;
  }

  public String getFirstName() {
    return first_name;
  }

  public String getLastName() {
    return last_name;
  }

  public Date getAdmitDate() {
    return admit_date;
  }

  public String getTelephone() {
    return telephone;
  }

  public String getSsid() {
    return ssid;
  }

  public String getSex() {
    return sex;
  }

  public Date getBirthDate() {
    return birth_date;
  }

  public String getBirthPlace() {
    return birth_place;
  }

  public String getFaith() {
    return faith;
  }

  public String getHobbies() {
    return hobbies;
  }

  public String getPreferredHospital() {
    return preferred_hospital;
  }

  public String getPrimaryCareName() {
    return primary_care_name;
  }

  public String getPrimaryPhone() {
    return primary_phone;
  }

}
