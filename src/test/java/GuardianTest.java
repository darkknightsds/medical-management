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

  // @Test
  // public void all_returnsAllInstancesOfGuardian_true() {
  //   Guardian firstGuardian = new Guardian(2,"John","Doe","Father","123 Sesame St.", "Portland","OR",92701,"503-123-4321");
  //   firstGuardian.save();
  //   Guardian secondGuardian = new Guardian(3,"John","Doe","Father","123 Sesame St.", "Portland","WA",92702,"503-123-4321");
  //   secondGuardian.save();
  //   assertEquals(true, Guardian.all().get(0).equals(firstGuardian));
  //   assertEquals(true, Guardian.all().get(1).equals(secondGuardian));
  // }



}
