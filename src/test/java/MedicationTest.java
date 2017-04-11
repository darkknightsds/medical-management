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
  public void testMedication_allGettersWork() {
    Medication testMedication = new Medication(1, "Tylenol", "200mg", "Mornings at 8");
    assertEquals(1, testMedication.getPatientId());
    assertEquals("Tylenol", testMedication.getName());
    assertEquals("200mg", testMedication.getDosage());
    assertEquals("Mornings at 8", testMedication.getFrequency());
  }

  @Test
  public void overrideEquals_Works() {
    Medication testMedication = new Medication(1, "Tylenol", "200mg", "Mornings at 8");
    Medication testMedication2 = new Medication(1, "Tylenol", "200mg", "Mornings at 8");
    assertTrue(testMedication.equals(testMedication2));
  }

  @Test
  public void savesAllHomesToDatabase_True() {
    Medication testMedication = new Medication(1, "Tylenol", "200mg", "Mornings at 8");
    Medication testMedication2 = new Medication(1, "Tylenol", "200mg", "Mornings at 8");
    testMedication.save();
    testMedication2.save();
    assertTrue(testMedication2.equals(Medication.all().get(1)));
  }

  @Test
  public void clientsAreCreatedWithDatabaseId_True() {
   Medication testMedication = new Medication(1, "Tylenol", "200mg", "Mornings at 8");
    testMedication.save();
    assertTrue(testMedication.getId() > 0);
  }

  @Test
  public void findsMedicationById_ReturnsSecondMedication() {
    Medication newMedication1 = new Medication(1, "Tylenol", "200mg", "Mornings at 8");
    Medication newMedication2 = new Medication(1, "Tylenol", "200mg", "Mornings at 8");
    newMedication1.save();
    newMedication2.save();
    assertEquals(Medication.find(newMedication2.getId()), newMedication2);
  }

  @Test
  public void updatesMedicationInformation_True() {
    Medication newMedication1 = new Medication(1, "Tylenol", "200mg", "Mornings at 8");
    newMedication1.save();
    newMedication1.update("Tylenol", "200mg", "Mornings at 9"); assertEquals("Mornings at 9", Medication.find(newMedication1.getId()).getFrequency());
  }

  @Test
  public void deletesMedication_Null() {
    Medication newMedication1 = new Medication(1, "Tylenol", "200mg", "Mornings at 8");
    newMedication1.save();
    int medication_id = newMedication1.getId();
    newMedication1.delete();
    assertEquals(null, Medication.find(medication_id));
  }

}
