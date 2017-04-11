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

  public int getMedicationId() {
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

}
