import org.sql2o.*;
import org.junit.*;
import static org.junit.Assert.*;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.sql.Timestamp;

public class PatientTest {

Date testDate1 = new Date (102, 9, 22);

  private Patient testPatient;

  @Before
  public void setUp() {
    testPatient = new Patient(1, "Bob", "Jones", testDate1, "222-222-2222", "111-11-1111", "female", testDate1, "Saskatchewan", "Hindu", "sports", "Legacy Emanuel", "Bob Ross, MD", "123-123-4567");
  }
  // 
  // @Rule
  // public DatabaseRule database = new DatabaseRule();

  @Test
  public void patient_instantiatesCorrectly_true() {
    assertTrue(testPatient instanceof Patient);
  }

}
