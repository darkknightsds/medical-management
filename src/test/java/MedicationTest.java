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

public class MedicationTest {

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void testMedication_Instatiates() {
    Medication testMedication = new Medication(1, "Tylenol", "200mg", "Mornings at 8");
    assertTrue(testMedication instanceof Medication);
  }

  @Test
  public void testFosterHome_allGettersWork() {
    Medication testMedication = new Medication(1, "Tylenol", "200mg", "Mornings at 8");
    assertEquals(1, testMedication.getPatientId());
    assertEquals("Tylenol", testMedication.getName());
    assertEquals("200mg", testMedication.getDosage());
    assertEquals("Mornings at 8", testMedication.getFrequency());
  }

}
