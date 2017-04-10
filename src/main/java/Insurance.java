import org.sql2o.*;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.sql.Timestamp;

public class Insurance {
  private int insurance_id;
  private int patient_id;
  private String insurance_provider;
  private String insurance_policy;
  private String va_policy;
  private String medicare_policy;
  private String medicaid_policy;

  public Insurance(int patient_id, String insurance_provider, String insurance_policy, String va_policy, String medicare_policy, String medicaid_policy) {
    this.patient_id = patient_id;
    this.insurance_provider = insurance_provider;
    this.insurance_policy = insurance_policy;
    this.va_policy = va_policy;
    this.medicare_policy = medicare_policy;
    this.medicaid_policy = medicaid_policy;
  }

  public int getInsuranceId() {
    return insurance_id;
  }
  public int getPatientId() {
    return patient_id;
  }

  public String getInsuranceProvider() {
    return insurance_provider;
  }

  public String getInsurancePolicy() {
    return insurance_policy;
  }

  public String getVaPolicy() {
    return va_policy;
  }

  public String getMedicarePolicy() {
    return medicare_policy;
  }

  public String getMedicaidPolicy() {
    return medicaid_policy;
  }

  public void save() {
    try (Connection con = DB.sql2o.open()) {
      String sql = "INSERT INTO insurance (patient_id, insurance_provider, insurance_policy, va_policy, medicare_policy, medicaid_policy) VALUES (:patient_id, :insurance_provider, :insurance_policy, :va_policy, :medicare_policy, :medicaid_policy);";
      this.insurance_id = (int) con.createQuery(sql, true)
        .addParameter("patient_id", this.patient_id)
        .addParameter("insurance_provider", this.insurance_provider)
        .addParameter("insurance_policy", this.insurance_policy)
        .addParameter("va_policy", this.va_policy)
        .addParameter("medicare_policy", this.medicare_policy)
        .addParameter("medicaid_policy", this.medicaid_policy)
        .executeUpdate()
        .getKey();
    }
  }

  public static List<Insurance> all() {
    try (Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM insurance;";
      return con.createQuery(sql)
        .executeAndFetch(Insurance.class);
    }
  }

  @Override
  public boolean equals(Object otherInsurance) {
    if (!(otherInsurance instanceof Insurance)) {
      return false;
    } else {
      Insurance newInsurance = (Insurance) otherInsurance;
      return this.getPatientId() == newInsurance.getPatientId() &&
             this.getInsuranceProvider().equals(newInsurance.getInsuranceProvider()) &&
             this.getInsurancePolicy().equals(newInsurance.getInsurancePolicy()) &&
             this.getVaPolicy().equals(newInsurance.getVaPolicy()) &&
             this.getMedicarePolicy().equals(newInsurance.getMedicarePolicy()) &&
             this.getMedicaidPolicy().equals(newInsurance.getMedicaidPolicy()) &&
             this.getInsuranceId() == newInsurance.getInsuranceId();
    }
  }

  public static Insurance find(int insurance_id) {
    try (Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM insurance WHERE insurance_id = :insurance_id;";
      return con.createQuery(sql)
        .addParameter("insurance_id", insurance_id)
        .executeAndFetchFirst(Insurance.class);
    }
  }

  public void updateInsurance(int patient_id, String insurance_provider, String insurance_policy, String va_policy, String medicare_policy, String medicaid_policy) {
    this.patient_id = patient_id;
    this.insurance_provider = insurance_provider;
    this.insurance_policy = insurance_policy;
    this.va_policy = va_policy;
    this.medicare_policy = medicare_policy;
    this.medicaid_policy = medicaid_policy;

    try (Connection con = DB.sql2o.open()) {
      String sql = "UPDATE insurance SET (patient_id, insurance_provider, insurance_policy, va_policy, medicare_policy, medicaid_policy) = (:patient_id, :insurance_provider, :insurance_policy, :va_policy, :medicare_policy, :medicaid_policy) WHERE insurance_id = :insurance_id;";
      con.createQuery(sql)
        .addParameter("insurance_id", this.insurance_id)
        .addParameter("patient_id", patient_id)
        .addParameter("insurance_provider", insurance_provider)
        .addParameter("insurance_policy", insurance_policy)
        .addParameter("va_policy", va_policy)
        .addParameter("medicare_policy", medicare_policy)
        .addParameter("medicaid_policy", medicaid_policy)
        .executeUpdate();
    }
  }


}
