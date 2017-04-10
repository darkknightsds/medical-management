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

public class InsuranceTest {

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void insurance_instantiatesCorrectly_true() {
    Insurance testInsurance = new Insurance(1, "Kaiser Permanente", "239487dk", "237dhjkshjf8", "09345lkjdft", "9823749oiuhjdf");
    assertTrue(testInsurance instanceof Insurance);
  }

  @Test
  public void getPatientId_returnsPatientIdFromInsuranceObject_1() {
    Insurance testInsurance = new Insurance(1, "Kaiser Permanente", "239487dk", "237dhjkshjf8", "09345lkjdft", "9823749oiuhjdf");
    assertEquals(1, testInsurance.getPatientId());
  }

  @Test
  public void getInsuranceProvider_returnsInsuranceProviderFromInsuranceObject() {
    Insurance testInsurance = new Insurance(1, "Kaiser Permanente", "239487dk", "237dhjkshjf8", "09345lkjdft", "9823749oiuhjdf");
    assertEquals("Kaiser Permanente", testInsurance.getInsuranceProvider());
  }

  @Test
  public void getInsurancePolicy_returnsInsurancePolicyFromInsuranceObject() {
    Insurance testInsurance = new Insurance(1, "Kaiser Permanente", "239487dk", "237dhjkshjf8", "09345lkjdft", "9823749oiuhjdf");
    assertEquals("239487dk", testInsurance.getInsurancePolicy());
  }

  @Test
  public void getVaPolicy_returnsVaPolicyFromInsuranceObject() {
    Insurance testInsurance = new Insurance(1, "Kaiser Permanente", "239487dk", "237dhjkshjf8", "09345lkjdft", "9823749oiuhjdf");
    assertEquals("237dhjkshjf8", testInsurance.getVaPolicy());
  }

  @Test
  public void getMedicarePolicy_returnsMedicarePolicyFromInsuranceObject() {
    Insurance testInsurance = new Insurance(1, "Kaiser Permanente", "239487dk", "237dhjkshjf8", "09345lkjdft", "9823749oiuhjdf");
    assertEquals("09345lkjdft", testInsurance.getMedicarePolicy());
  }

  @Test
  public void getMedicaidPolicy_returnsMedicaidPolicyFromInsuranceObject() {
    Insurance testInsurance = new Insurance(1, "Kaiser Permanente", "239487dk", "237dhjkshjf8", "09345lkjdft", "9823749oiuhjdf");
    assertEquals("9823749oiuhjdf", testInsurance.getMedicaidPolicy());
  }

  @Test
  public void save_savesInsuranceObjectInDB_true() {
    Insurance testInsurance = new Insurance(1, "Kaiser Permanente", "239487dk", "237dhjkshjf8", "09345lkjdft", "9823749oiuhjdf");
    testInsurance.save();
    assertTrue(Insurance.all().get(0).equals(testInsurance));
  }

  @Test
  public void all_returnsAllInsuranceObjectsFromDB_true() {
    Insurance testInsurance1 = new Insurance(1, "Kaiser Permanente", "239487dk", "237dhjkshjf8", "09345lkjdft", "9823749oiuhjdf");
    testInsurance1.save();
    Insurance testInsurance2 = new Insurance(1, "Kaiser Permanente Diff", "qw2345gdg", "43wrsf", "345sdfdf", "332452345efgr");
    testInsurance2.save();
    assertTrue(Insurance.all().get(0).equals(testInsurance1));
    assertTrue(Insurance.all().get(1).equals(testInsurance2));
  }

  @Test
  public void equals_returnsTrueifTwoInsuranceObjectsAreTheSame() {
    Insurance testInsurance1 = new Insurance(1, "Kaiser Permanente", "239487dk", "237dhjkshjf8", "09345lkjdft", "9823749oiuhjdf");
    Insurance testInsurance2 = new Insurance(1, "Kaiser Permanente", "239487dk", "237dhjkshjf8", "09345lkjdft", "9823749oiuhjdf");
    assertTrue(testInsurance1.equals(testInsurance2));
  }

  @Test
  public void getInsuranceId_returnsAnId_true() {
    Insurance testInsurance = new Insurance(1, "Kaiser Permanente", "239487dk", "237dhjkshjf8", "09345lkjdft", "9823749oiuhjdf");
    testInsurance.save();
    assertTrue(testInsurance.getInsuranceId() > 0);
  }

  @Test
  public void find_returnsInsuranceWithSameId_Fargo() {
    Insurance testInsurance = new Insurance(1, "Kaiser Permanente", "239487dk", "237dhjkshjf8", "09345lkjdft", "9823749oiuhjdf");
    testInsurance.save();
    assertEquals(testInsurance, Insurance.find(testInsurance.getInsuranceId()));
  }

  @Test
  public void updateInsurance_updatesInsuranceProperties_true() {
    Insurance testInsurance = new Insurance(1, "Kaiser Permanente", "239487dk", "237dhjkshjf8", "09345lkjdft", "9823749oiuhjdf");
    testInsurance.save();
    testInsurance.updateInsurance(2, "Kaiser Permanente Diff", "qw2345gdg", "43wrsf", "345sdfdf", "332452345efgr");
    assertEquals(2, Insurance.find(testInsurance.getInsuranceId()).getPatientId());
    assertEquals(2, testInsurance.getPatientId());
    assertEquals("Kaiser Permanente Diff", Insurance.find(testInsurance.getInsuranceId()).getInsuranceProvider());
    assertEquals("Kaiser Permanente Diff", testInsurance.getInsuranceProvider());
    assertEquals("qw2345gdg", Insurance.find(testInsurance.getInsuranceId()).getInsurancePolicy());
    assertEquals("qw2345gdg", testInsurance.getInsurancePolicy());
    assertEquals("43wrsf", Insurance.find(testInsurance.getInsuranceId()).getVaPolicy());
    assertEquals("43wrsf", testInsurance.getVaPolicy());
    assertEquals("345sdfdf", Insurance.find(testInsurance.getInsuranceId()).getMedicarePolicy());
    assertEquals("345sdfdf", testInsurance.getMedicarePolicy());
    assertEquals("332452345efgr", Insurance.find(testInsurance.getInsuranceId()).getMedicaidPolicy());
    assertEquals("332452345efgr", testInsurance.getMedicaidPolicy());
  }

}
