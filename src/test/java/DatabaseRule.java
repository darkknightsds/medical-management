import org.junit.rules.ExternalResource;
import org.sql2o.*;

public class DatabaseRule extends ExternalResource {

  @Override
  protected void before() {
    DB.sql2o = new Sql2o("jdbc:postgresql://localhost:5432/medical_app_test", null, null);
  }

  @Override
  protected void after() {
    try(Connection con = DB.sql2o.open()) {
      String deleteToDoQuery = "DELETE FROM tasks *;";
      con.createQuery(deleteToDoQuery).executeUpdate();
      String deleteInsuranceQuery = "DELETE FROM insurance *;";
      con.createQuery(deleteInsuranceQuery).executeUpdate();
      String deleteMedHistoryQuery = "DELETE FROM med_histories *;";
      con.createQuery(deleteMedHistoryQuery).executeUpdate();
      String deleteMedicationQuery = "DELETE FROM medications *;";
      con.createQuery(deleteMedicationQuery).executeUpdate();
      String deleteGuardianQuery = "DELETE FROM guardians *;";
      con.createQuery(deleteGuardianQuery).executeUpdate();
      String deletePatientQuery = "DELETE FROM patients *;";
      con.createQuery(deletePatientQuery).executeUpdate();
      String deleteFosterHomeQuery = "DELETE FROM foster_homes *;";
      con.createQuery(deleteFosterHomeQuery).executeUpdate();
      String deleteUsersQuery = "DELETE FROM users *;";
      con.createQuery(deleteUsersQuery).executeUpdate();
    }
  }
}
