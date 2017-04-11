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
Date testDate2 = new Date (30, 3, 12);

  private Patient testPatient;

  @Before
  public void setUp() {
    testPatient = new Patient(1, "Bob", "Jones", testDate1, "222-222-2222", "111-11-1111", "female", testDate2, "Saskatchewan", "Hindu", "sports", "Legacy Emanuel", "Bob Ross, MD", "123-123-4567");
    testPatient.save();
  }

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void patient_instantiatesCorrectly_true() {
    assertTrue(testPatient instanceof Patient);
  }

   @Test
   public void getters_returnPatientVariables() {
     assertEquals(1, testPatient.getFosterHomeId());
     assertEquals("Bob",testPatient.getFirstName());
     assertEquals("Jones", testPatient.getLastName());
     assertEquals(testDate1, testPatient.getAdmitDate());
     assertEquals("222-222-2222", testPatient.getTelephone());
     assertEquals("111-11-1111", testPatient.getSsid());
     assertEquals("female", testPatient.getSex());
     assertEquals(testDate2, testPatient.getBirthDate());
     assertEquals("Saskatchewan", testPatient.getBirthPlace());
     assertEquals("Hindu", testPatient.getFaith());
     assertEquals("sports", testPatient.getHobbies());
     assertEquals("Legacy Emanuel", testPatient.getPreferredHospital());
     assertEquals("Bob Ross, MD", testPatient.getPrimaryCareName());
     assertEquals("123-123-4567", testPatient.getPrimaryPhone());
   }

   @Test
   public void equals_returnsTrueIfValuesAreSame() {
     Patient otherPatient = new Patient(1, "Bob", "Jones", testDate1, "222-222-2222", "111-11-1111", "female", testDate2, "Saskatchewan", "Hindu", "sports", "Legacy Emanuel", "Bob Ross, MD", "123-123-4567");
     assertTrue(otherPatient.equals(testPatient));
   }

   @Test
   public void save_savesPatientToDatabase_true() {
     assertTrue(Patient.all().get(0).equals(testPatient));
   }

   @Test
   public void save_assignsIdToPatient() {
     Patient savedPatient = Patient.all().get(0);
     assertEquals(testPatient.getPatientId(),savedPatient.getPatientId());
   }

   @Test
   public void find_findsPatientByPatientId() {
     Patient secondPatient = new Patient(1, "Deborah", "Marques", testDate1, "222-222-2222", "333-33-3333", "female", testDate2, "Vienna", "Wiccan", "literature", "Providence", "Bob Ross, MD", "123-123-4567");
     secondPatient.save();
     assertEquals(secondPatient, Patient.find(secondPatient.getPatientId()));
   }

}
