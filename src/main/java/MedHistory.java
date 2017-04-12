import org.sql2o.*;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.sql.Timestamp;


public class MedHistory
// implements DatabaseManagement
{
    private int med_history_id;
    private int patient_id;
    private String type;
    private String name;
    private Date date;
    private String medications;
    private boolean current;

  public MedHistory( int patient_id, String type, String name, Date date, String medications, boolean current ) {
    this.patient_id = patient_id;
    this.type = type;
    this.name = name;
    this.date = date;
    this.medications = medications;
    this.current=current;
  }
  // Getters
  public int getMedHistoryId(){
    return med_history_id;
  }

  public int getPatientId(){
    return patient_id;
  }

  public String getType(){
    return type;
  }

  public String getName(){
    return name;
  }

  public Date getDate(){
    return date;
  }

  public String getMedications(){
    return medications;
  }

  public boolean getCurrent(){
    return current;
  }

  // Functions
  public static List<MedHistory> all(){
    String sql = "SELECT * FROM med_histories;";
    try(Connection con = DB.sql2o.open()){
      return con.createQuery(sql)
      .throwOnMappingFailure(false)
      .executeAndFetch(MedHistory.class);
    }
  }

  public void save(){
    try (Connection con = DB.sql2o.open()) {
      String sql = "INSERT INTO med_histories (patient_id, type, name, date, medications, current) VALUES (:patient_id, :type, :name, :date, :medications, :current);";
      this.med_history_id = (int) con.createQuery(sql, true)
        .addParameter("patient_id", this.patient_id)
        .addParameter("type", this.type)
        .addParameter("name", this.name)
        .addParameter("date", this.date)
        .addParameter("medications", this.medications)
        .addParameter("current", this.current)
        .executeUpdate()
        .getKey();
    }
  }

  @Override
  public boolean equals (Object otherMedHistory){
    if (!(otherMedHistory instanceof MedHistory)){
      return false;
    } else {
      MedHistory newMedHistory = (MedHistory) otherMedHistory;
      return this.getPatientId() == newMedHistory.getPatientId() &&
             this.getName().equals(newMedHistory.getName()) &&
             this.getType().equals(newMedHistory.getType()) &&
             this.getDate().equals(newMedHistory.getDate());
    }
  }

  public static MedHistory find(int med_history_id) {
    try (Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM med_histories WHERE med_history_id = :med_history_id;";
      return con.createQuery(sql)
        .addParameter("med_history_id", med_history_id)
        .executeAndFetchFirst(MedHistory.class);
    }
  }

  public void updateMedHistory(int patient_id, String type, String name, Date date, String medications, boolean current) {
    this.patient_id = patient_id;
    this.type = type;
    this.name = name;
    this.date = date;
    this.medications = medications;
    this.current=current;

    try (Connection con = DB.sql2o.open()) {
      String sql = "UPDATE med_histories SET (patient_id, type, name, date, medications, current) = (:patient_id, :type, :name, :date, :medications, :current) WHERE med_history_id = :med_history_id;";
      con.createQuery(sql)
        .addParameter("med_history_id", this.med_history_id)
        .addParameter("patient_id",patient_id )
        .addParameter("type",type )
        .addParameter("name", name)
        .addParameter("date", date)
        .addParameter("medications", medications)
        .addParameter("current", current)
        .executeUpdate();
    }
  }

  public void deleteMedHistory() {
    try (Connection con = DB.sql2o.open()) {
      String sql = "DELETE FROM med_histories WHERE med_history_id = :med_history_id;";
      con.createQuery(sql)
        .addParameter("med_history_id", this.med_history_id)
        .executeUpdate();
    }
  }
}
