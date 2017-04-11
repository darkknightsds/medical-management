import org.sql2o.*;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.sql.Timestamp;


public class Medication
// implements DatabaseManagement
 {
   private int medication_id;
   private int patient_id;
   private String name;
   private String dosage;
   private String frequency;

  public Medication(int patient_id, String name, String dosage, String frequency) {
    this.patient_id = patient_id;
    this.name = name;
    this.dosage = dosage;
    this.frequency = frequency;
  }

  public int getId() {
    return medication_id;
  }

  public int getPatientId() {
    return patient_id;
  }

  public String getName() {
    return name;
  }

  public String getDosage() {
    return dosage;
  }

  public String getFrequency() {
    return frequency;
  }

  @Override
  public boolean equals(Object otherMedication) {
    if(!(otherMedication instanceof Medication)) {
      return false;
    } else {
      Medication testMedication = (Medication) otherMedication;
      return this.getName().equals(testMedication.getName());
    }
  }

  public static List<Medication> all() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM medications;";
      return con.createQuery(sql)
      .executeAndFetch(Medication.class);
    }
  }

  public void save() {
    try(Connection con = DB.sql2o.open()) {
    String sql = "INSERT INTO medications (patient_id, name, dosage, frequency) VALUES (:patient_id, :name, :dosage, :frequency);";
    this.medication_id = (int) con.createQuery(sql, true)
      .addParameter("patient_id", this.patient_id)
      .addParameter("name", this.name)
      .addParameter("dosage", this.dosage)
      .addParameter("frequency", this.frequency)
      .executeUpdate()
      .getKey();
    }
  }

  public static Medication find(int medication_id) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM medications where medication_id = :medication_id;";
      Medication newMedication = con.createQuery(sql)
        .addParameter("medication_id", medication_id)
        .executeAndFetchFirst(Medication.class);
      return newMedication;
    }
  }

  public void update(String name, String dosage, String frequency) {
    this.name = name;
    this.dosage = dosage;
    this.frequency = frequency;

    try(Connection con = DB.sql2o.open()) {
      String sql = "UPDATE medications SET (name, dosage, frequency) = (:name, :dosage, :frequency) WHERE medication_id = :medication_id;";
      con.createQuery(sql)
        .addParameter("medication_id", this.medication_id)
        .addParameter("name", name)
        .addParameter("dosage", dosage)
        .addParameter("frequency", frequency)
        .executeUpdate();
    }
  }

  public void delete() {
  try (Connection con = DB.sql2o.open()) {
      String sql = "DELETE FROM medications WHERE medication_id = :medication_id;";
      con.createQuery(sql)
        .addParameter("medication_id", this.medication_id)
        .executeUpdate();
    }
  }

}
