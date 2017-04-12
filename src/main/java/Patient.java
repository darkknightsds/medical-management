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
  private String admit_date;
  private String telephone;
  private String ssid;
  private String sex;
  private String birth_date;
  private String birth_place;
  private String faith;
  private String hobbies;
  private String preferred_hospital;
  private String primary_care_name;
  private String primary_phone;
  private int patient_id;
  private int foster_home_id;

  public Patient(int foster_home_id, String first_name, String last_name, String admit_date, String telephone, String ssid, String sex, String birth_date, String birth_place, String faith, String hobbies, String preferred_hospital, String primary_care_name, String primary_phone) {
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

  public String getAdmitDate() {
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

  public String getBirthDate() {
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

  public int getPatientId() {
    return patient_id;
  }

  @Override
  public boolean equals(Object otherPatient) {
    if (!(otherPatient instanceof Patient)) {
      return false;
    } else {
      Patient newPatient = (Patient) otherPatient;
      return this.getFosterHomeId() == newPatient.getFosterHomeId()
        && this.getFirstName().equals(newPatient.getFirstName())
        && this.getLastName().equals(newPatient.getLastName())
        && this.getAdmitDate().equals(newPatient.getAdmitDate())
        && this.getTelephone().equals(newPatient.getTelephone())
        && this.getSsid().equals(newPatient.getSsid())
        && this.getSex().equals(newPatient.getSex())
        && this.getBirthDate().equals(newPatient.getBirthDate())
        && this.getBirthPlace().equals(newPatient.getBirthPlace())
        && this.getFaith().equals(newPatient.getFaith())
        && this.getHobbies().equals(newPatient.getHobbies())
        && this.getPreferredHospital().equals(newPatient.getPreferredHospital())
        && this.getPrimaryCareName().equals(newPatient.getPrimaryCareName())
        && this.getPrimaryPhone().equals(newPatient.getPrimaryPhone());
    }
  }

  public void save() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "INSERT INTO patients (foster_home_id, first_name, last_name, admit_date, telephone, ssid, sex, birth_date, birth_place, faith, hobbies, preferred_hospital, primary_care_name, primary_phone) VALUES (:foster_home_id, :first_name, :last_name, :admit_date, :telephone, :ssid, :sex, :birth_date, :birth_place, :faith, :hobbies, :preferred_hospital, :primary_care_name, :primary_phone);";
      this.patient_id = (int) con.createQuery(sql, true)
        .addParameter("foster_home_id", this.foster_home_id)
        .addParameter("first_name", this.first_name)
        .addParameter("last_name", this.last_name)
        .addParameter("admit_date", this.admit_date)
        .addParameter("telephone", this.telephone)
        .addParameter("ssid", this.ssid)
        .addParameter("sex", this.sex)
        .addParameter("birth_date", this.birth_date)
        .addParameter("birth_place", this.birth_place)
        .addParameter("faith", this.faith)
        .addParameter("hobbies", this.hobbies)
        .addParameter("preferred_hospital", this.preferred_hospital)
        .addParameter("primary_care_name", this.primary_care_name)
        .addParameter("primary_phone", this.primary_phone)
        .executeUpdate()
        .getKey();
    }
  }

  public static List<Patient> all() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM patients;";
      return con.createQuery(sql)
        .executeAndFetch(Patient.class);
    }
  }

  public static Patient find(int patient_id) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM patients WHERE patient_id = :patient_id;";
      Patient result  = con.createQuery(sql)
        .addParameter("patient_id", patient_id)
        .executeAndFetchFirst(Patient.class);
      return result;
    }
  }

  public void update(String first_name, String last_name, String admit_date, String telephone, String ssid, String sex, String birth_date, String birth_place, String faith, String hobbies, String preferred_hospital, String primary_care_name, String primary_phone) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "UPDATE patients SET first_name = :first_name, last_name = :last_name, admit_date = :admit_date, telephone = :telephone, ssid = :ssid, sex = :sex, birth_date = :birth_date, birth_place = :birth_place, faith = :faith, hobbies = :hobbies, preferred_hospital = :preferred_hospital, primary_care_name = :primary_care_name, primary_phone = :primary_phone WHERE patient_id = :patient_id;";
      con.createQuery(sql)
      .addParameter("first_name", first_name)
      .addParameter("last_name", last_name)
      .addParameter("admit_date", admit_date)
      .addParameter("telephone", telephone)
      .addParameter("ssid", ssid)
      .addParameter("sex", sex)
      .addParameter("birth_date", birth_date)
      .addParameter("birth_place", birth_place)
      .addParameter("faith", faith)
      .addParameter("hobbies", hobbies)
      .addParameter("preferred_hospital", preferred_hospital)
      .addParameter("primary_care_name", primary_care_name)
      .addParameter("primary_phone", primary_phone)
      .addParameter("patient_id", this.patient_id)
      .executeUpdate();
    }
  }

  public List<Insurance> getInsurances() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM insurance WHERE patient_id = :patient_id";
      return con.createQuery(sql)
        .addParameter("patient_id", this.patient_id)
        .executeAndFetch(Insurance.class);
    }
  }

  public List<Guardian> getGuardians() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM guardians WHERE patient_id = :patient_id";
      return con.createQuery(sql)
        .addParameter("patient_id", this.patient_id)
        .executeAndFetch(Guardian.class);
    }
  }

  public List<Medication> getMedications() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM medications WHERE patient_id = :patient_id";
      return con.createQuery(sql)
        .addParameter("patient_id", this.patient_id)
        .executeAndFetch(Medication.class);
    }
  }

  public List<MedHistory> getMedHistories() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM med_histories WHERE patient_id = :patient_id";
      return con.createQuery(sql)
        .addParameter("patient_id", this.patient_id)
        .executeAndFetch(MedHistory.class);
    }
  }

  public List<Task> getTasks() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM tasks WHERE patient_id = :patient_id";
      return con.createQuery(sql)
        .addParameter("patient_id", this.patient_id)
        .executeAndFetch(Task.class);
    }
  }


}
