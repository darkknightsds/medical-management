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

public class GuardianTest {

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void Guardian_instantiatesCorrectly_true(){
    Guardian guardian = new Guardian(2,"John","Doe","Father","123 Sesame St.", "Portland","OR",92701,"503-123-4321");
    assertEquals(true, guardian instanceof Guardian);
  }


  @Test
  public void Guardian_instantiatesWithPatientId_int(){
    Guardian guardian = new Guardian(2,"John","Doe","Father","123 Sesame St.", "Portland","OR",92701,"503-123-4321");
    assertEquals(2, guardian.getPatientId());
  }

  @Test
  public void Guardian_instantiatesWithFirstName_String(){
    Guardian guardian = new Guardian(2,"John","Doe","Father","123 Sesame St.", "Portland","OR",92701,"503-123-4321");
    assertEquals("John", guardian.getFirstName());
  }

  @Test
  public void Guardian_instantiatesWithLastName_String(){
    Guardian guardian = new Guardian(2,"John","Doe","Father","123 Sesame St.", "Portland","OR",92701,"503-123-4321");
    assertEquals("Doe", guardian.getLastName());
  }

  @Test
  public void Guardian_instantiatesWithRelationship_String(){
    Guardian guardian = new Guardian(2,"John","Doe","Father","123 Sesame St.", "Portland","OR",92701,"503-123-4321");
    assertEquals("Father", guardian.getRelationship());
  }

  @Test
  public void Guardian_instantiatesWithAddress_String(){
    Guardian guardian = new Guardian(2,"John","Doe","Father","123 Sesame St.", "Portland","OR",92701,"503-123-4321");
    assertEquals("123 Sesame St.", guardian.getAddress());
  }

  @Test
  public void Guardian_instantiatesWithCity_String(){
    Guardian guardian = new Guardian(2,"John","Doe","Father","123 Sesame St.", "Portland","OR",92701,"503-123-4321");
    assertEquals("Portland", guardian.getCity());
  }

  @Test
  public void Guardian_instantiatesWithState_String(){
    Guardian guardian = new Guardian(2,"John","Doe","Father","123 Sesame St.", "Portland","OR",92701,"503-123-4321");
    assertEquals("OR", guardian.getState());
  }

  @Test
  public void Guardian_instantiatesWithZip_int(){
    Guardian guardian = new Guardian(2,"John","Doe","Father","123 Sesame St.", "Portland","OR",92701,"503-123-4321");
    assertEquals(92701, guardian.getZip());
  }

  @Test
  public void Guardian_instantiatesWithTelephone_String(){
    Guardian guardian = new Guardian(2,"John","Doe","Father","123 Sesame St.", "Portland","OR",92701,"503-123-4321");
    assertEquals("503-123-4321", guardian.getTelephone());
  }

  @Test
  public void all_returnsAllInstancesOfGuardian_true() {
    Guardian firstGuardian = new Guardian(2,"John","Doe","Father","123 Sesame St.", "Portland","OR",92701,"503-123-4321");
    firstGuardian.save();
    Guardian secondGuardian = new Guardian(3,"John","Doe","Father","123 Sesame St.", "Portland","WA",92702,"503-123-4321");
    secondGuardian.save();
    assertEquals(true, Guardian.all().get(0).equals(firstGuardian));
    assertEquals(true, Guardian.all().get(1).equals(secondGuardian));
  }

  @Test
  public void save_savesGuardianObjectInDB_true(){
    Guardian guardian = new Guardian(2,"John","Doe","Father","123 Sesame St.", "Portland","OR",92701,"503-123-4321");
    guardian.save();
    assertTrue(Guardian.all().get(0).equals(guardian));
  }

  @Test
  public void Guardian_instantiatesWithGuardianId_int(){
    Guardian guardian = new Guardian(2,"John","Doe","Father","123 Sesame St.", "Portland","OR",92701,"503-123-4321");
    guardian.save();
    assertTrue(Guardian.all().get(0).getGuardianId() > 0);
  }


  @Test
  public void all_returnsAllGuardiansFromDB_true(){
    Guardian testGuardian1 = new Guardian(2,"John","Doe","Father","123 Sesame St.", "Portland","OR",92701,"503-123-4321");
    testGuardian1.save();
    Guardian testGuardian2 = new Guardian(3,"Jane","Doe","Mother","123 Sesame St.", "Medford","OR",92707,"503-123-4321");
    testGuardian2.save();
    assertTrue(Guardian.all().get(0).equals(testGuardian1));
    assertTrue(Guardian.all().get(1).equals(testGuardian2));
  }

  @Test
  public void find_returnsGuardianWithSameId(){
    Guardian testGuardian = new Guardian(2,"John","Doe","Father","123 Sesame St.", "Portland","OR",92701,"503-123-4321");
    testGuardian.save();
    assertEquals(testGuardian, Guardian.find(testGuardian.getGuardianId()));
  }

  @Test
  public void updateGuardian_updatesGuardianAttributes_true(){
    Guardian testGuardian = new Guardian(2,"John","Doe","Father","123 Sesame St.", "Portland","OR",92701,"503-123-1234");
    testGuardian.save();
    testGuardian.updateGuardian(3,"Jane","Doer","Aunt","321 Sesame St.", "Medford","WA",92707,"503-123-4321");
    assertEquals(3, Guardian.find(testGuardian.getGuardianId()).getPatientId());
    assertEquals(3,testGuardian.getPatientId());
    assertEquals("Jane", Guardian.find(testGuardian.getGuardianId()).getFirstName());
    assertEquals("Jane", testGuardian.getFirstName());
    assertEquals("Doer", Guardian.find(testGuardian.getGuardianId()).getLastName());
    assertEquals("Doer", testGuardian.getLastName());
    assertEquals("Aunt", Guardian.find(testGuardian.getGuardianId()).getRelationship());
    assertEquals("Aunt", testGuardian.getRelationship());
    assertEquals("321 Sesame St.", Guardian.find(testGuardian.getGuardianId()).getAddress());
    assertEquals("321 Sesame St.", testGuardian.getAddress());
    assertEquals("Medford", Guardian.find(testGuardian.getGuardianId()).getCity());
    assertEquals("Medford", testGuardian.getCity());
    assertEquals("WA", Guardian.find(testGuardian.getGuardianId()).getState());
    assertEquals("WA", testGuardian.getState());
    assertEquals(92707, Guardian.find(testGuardian.getGuardianId()).getZip());
    assertEquals(92707, testGuardian.getZip());
    assertEquals("503-123-4321", Guardian.find(testGuardian.getGuardianId()).getTelephone());
    assertEquals("503-123-4321", testGuardian.getTelephone());
  }

  @Test
 public void deleteGuardian_updatesGuardianProperties_true() {
   Guardian testGuardian = new Guardian(3,"Jane","Doer","Aunt","321 Sesame St.", "Medford","WA",92707,"503-123-4321");
   testGuardian.save();
   int testGuardianId = testGuardian.getGuardianId();
   testGuardian.deleteGuardian();
   assertEquals(null, Guardian.find(testGuardianId));
 }

}
