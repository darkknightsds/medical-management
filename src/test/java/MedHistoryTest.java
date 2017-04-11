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

public class MedHistoryTest {
Date testDate1 = new Date (102, 9, 22);
Date testDate2 = new Date (103,10,23);

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void MedHistory_instantiatesCorrectly_true(){
    MedHistory testMedHistory=new MedHistory(3, "recent patient", "Patient 0", testDate1, "tylenol", true);
    assertEquals(true, testMedHistory instanceof MedHistory);
  }

  @Test
  public void get_returnsAllPropertiesOfMedHistory_true(){
    MedHistory testMedHistory = new MedHistory(3, "recent patient", "Patient 0", testDate1, "tylenol", true);
    assertEquals(3, testMedHistory.getPatientId());
    assertEquals("recent patient", testMedHistory.getType());
    assertEquals("Patient 0", testMedHistory.getName());
    assertEquals(testDate1, testMedHistory.getDate());
    assertEquals("tylenol", testMedHistory.getMedications());
    assertEquals( true, testMedHistory.getCurrent());
  }

  @Test
  public void equals_retursTrueIfMedHistoriesAreTheSame_true(){
    MedHistory testMedHistory1 = new MedHistory(3, "recent patient", "Patient 0", testDate1, "tylenol", true);
    MedHistory testMedHistory2 = new MedHistory(3, "recent patient", "Patient 0", testDate1, "tylenol", true);
    assertTrue(testMedHistory1.equals(testMedHistory2));
  }

  @Test
  public void save_savesMedHistoryObjectInDB_true(){
    MedHistory medHistory = new MedHistory(2, "longtime patient", "Patient 4", testDate2, "advil", true);
    medHistory.save();
    assertTrue(MedHistory.all().get(0).equals(medHistory));
  }

  @Test
  public void all_returnsAllMedHistoriesFromDB_true(){
    MedHistory testMedHistory1 = new MedHistory(3, "recent patient", "Patient 0", testDate1, "tylenol", true);
    testMedHistory1.save();
    MedHistory testMedHistory2 = new MedHistory(2, "longtime patient", "Patient 4", testDate2, "advil", false);
    testMedHistory2.save();
    assertTrue(MedHistory.all().get(0).equals(testMedHistory1));
    assertTrue(MedHistory.all().get(1).equals(testMedHistory2));
  }

  @Test
  public void MedHistory_instantiatesWithMedHistoryId_int(){
    MedHistory medHistory = new MedHistory(2, "longtime patient", "Patient 4", testDate2, "advil", true);
    medHistory.save();
    assertTrue(MedHistory.all().get(0).getMedHistoryId() > 0);
  }

  @Test
  public void find_returnsMedHistoryWithSameId(){
    MedHistory testMedHistory = new MedHistory(2, "longtime patient", "Patient 4", testDate2, "advil", true);
    testMedHistory.save();
    assertEquals(testMedHistory, MedHistory.find(testMedHistory.getMedHistoryId()));
  }

}
