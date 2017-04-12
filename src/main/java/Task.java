import org.sql2o.*;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.sql.Timestamp;


public class Task
// implements DatabaseManagement
 {

  private int task_id;
  private int patient_id;
  private String description;
  private Timestamp timestamp_date_time;
  private String date_time;
  private String patient_name;
  private String formatted;


  public Task(int patient_id, String description, String date_time) {
    this.patient_id = patient_id;
    this.description = description;
    this.date_time = date_time;
    this.patient_name = Patient.find(patient_id).getLastName() + ", " + Patient.find(patient_id).getFirstName();
    this.timestamp_date_time = stringToTimestamp(date_time);
  }

  public int getTaskId() {
    return task_id;
  }

  public int getPatientId() {
    return patient_id;
  }

  public String getDescription() {
    return description;
  }

  public String getDateTime() {
    return date_time;
  }

  public String getPatientName() {
    return patient_name;
  }

  public Timestamp getTimestampDateTime() {
    return timestamp_date_time;
  }

  public Timestamp stringToTimestamp(String input) {
    formatted = input + ":00";
    return Timestamp.valueOf(formatted);
  }

  @Override
  public boolean equals(Object otherTask) {
    if (!(otherTask instanceof Task)) {
      return false;
    } else {
      Task newTask = (Task) otherTask;
      return this.getPatientId() == newTask.getPatientId() &&
             this.getDateTime().equals(newTask.getDateTime()) &&
             this.getTaskId() == newTask.getTaskId();
    }
  }

  public void save() {
    try (Connection con = DB.sql2o.open()) {
      String sql = "INSERT INTO tasks (patient_id, description, date_time, patient_name, timestamp_date_time) VALUES (:patient_id, :description, :date_time, :patient_name, :timestamp_date_time);";
      this.task_id = (int) con.createQuery(sql, true)
        .addParameter("patient_id", this.patient_id)
        .addParameter("description", this.description)
        .addParameter("date_time", this.date_time)
        .addParameter("patient_name", this.patient_name)
        .addParameter("timestamp_date_time", this.timestamp_date_time)
        .executeUpdate()
        .getKey();
    }
  }

  public static List<Task> all() {
    try (Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM tasks;";
      return con.createQuery(sql)
        .executeAndFetch(Task.class);
    }
  }

  public static List<Task> getTopTasks() {
    try (Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM tasks ORDER BY timestamp_date_time asc;";
      return con.createQuery(sql)
        .executeAndFetch(Task.class);
    }
  }



}
