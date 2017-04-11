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

public class FosterHomeTest {
  Date testDate1 = new Date (102, 9, 22);
  Date testDate2 = new Date (30, 3, 12);

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void testFosterHome_createsNewInstance() {
    FosterHome testFosterHome = new FosterHome(1, "Emma's Foster Home", "Emma", "Plesa", "123 Alphabet St.", "Paisley Park", "Minnesota", 71999, "777-777-1234");
    assertTrue(testFosterHome instanceof FosterHome);
  }

  @Test
  public void testFosterHome_allGettersWork() {
    FosterHome testFosterHome = new FosterHome(1, "Emma's Foster Home", "Emma", "Plesa", "123 Alphabet St.", "Paisley Park", "Minnesota", 71999, "777-777-1234");
    assertEquals(1, testFosterHome.getUserId());
    assertEquals("Emma's Foster Home", testFosterHome.getFacilityName());
    assertEquals("Emma", testFosterHome.getPrimaryFirst());
    assertEquals("Plesa", testFosterHome.getPrimaryLast());
    assertEquals("123 Alphabet St.", testFosterHome.getAddress());
    assertEquals("Paisley Park", testFosterHome.getCity());
    assertEquals("Minnesota", testFosterHome.getState());
    assertEquals(71999, testFosterHome.getZip());
    assertEquals("777-777-1234", testFosterHome.getTelephone());
  }

  @Test
  public void overrideEquals_Works() {
    FosterHome testFosterHome = new FosterHome(1, "Emma's Foster Home", "Emma", "Plesa", "123 Alphabet St.", "Paisley Park", "Minnesota", 71999, "777-777-1234");
    FosterHome testFosterHome2 = new FosterHome(1, "Emma's Foster Home", "Emma", "Plesa", "123 Alphabet St.", "Paisley Park", "Minnesota", 71999, "777-777-1234");
    assertTrue(testFosterHome.equals(testFosterHome2));
  }

  @Test
  public void savesAllHomesToDatabase_True() {
    FosterHome testFosterHome = new FosterHome(1, "Emma's Foster Home", "Emma", "Plesa", "123 Alphabet St.", "Paisley Park", "Minnesota", 71999, "777-777-1234");
    FosterHome testFosterHome2 = new FosterHome(1, "Emma's Foster Home", "Emma", "Plesa", "123 Alphabet St.", "Paisley Park", "Minnesota", 71999, "777-777-1234");
    testFosterHome.save();
    testFosterHome2.save();
    assertTrue(testFosterHome2.equals(FosterHome.all().get(1)));
  }

  @Test
  public void clientsAreCreatedWithDatabaseId_True() {
   FosterHome testFosterHome = new FosterHome(1, "Emma's Foster Home", "Emma", "Plesa", "123 Alphabet St.", "Paisley Park", "Minnesota", 71999, "777-777-1234");
    testFosterHome.save();
    assertTrue(testFosterHome.getId() > 0);
  }

  @Test
  public void findsFosterHomeById_ReturnsSecondFosterHome() {
    FosterHome newFosterHome1 = new FosterHome(1, "Emma's Foster Home", "Emma", "Plesa", "123 Alphabet St.", "Paisley Park", "Minnesota", 71999, "777-777-1234");
    FosterHome newFosterHome2 = new FosterHome(1, "Emma's Foster Home", "Emma", "Plesa", "123 Alphabet St.", "Paisley Park", "Minnesota", 71999, "777-777-1234");
    newFosterHome1.save();
    newFosterHome2.save();
    assertEquals(FosterHome.find(newFosterHome2.getId()), newFosterHome2);
  }

  @Test
  public void updatesFosterHomeInformation_True() {
    FosterHome newFosterHome1 = new FosterHome(1, "Emma's Foster Home", "Emma", "Plesa", "123 Alphabet St.", "Paisley Park", "Minnesota", 71999, "777-777-1234");
    newFosterHome1.save();
    newFosterHome1.update("Emma's Foster Home", "Emma", "Plesa", "123 Alphabet St.", "Paisley Park", "Your Heart", 71999, "777-777-1234");
    assertEquals("Your Heart", FosterHome.find(newFosterHome1.getId()).getState());
  }

  // @Test
  // public void deletesFosterHome_Null() {
  //   FosterHome newFosterHome1 = new FosterHome(1, "Emma's Foster Home", "Emma", "Plesa", "123 Alphabet St.", "Paisley Park", "Your Heart", 71999, "777-777-1234");
  //   newFosterHome1.save();
  //   int foster_home_id = newFosterHome1.getId();
  //   newFosterHome1.delete();
  //   assertEquals(null, FosterHome.find(foster_home_id));
  // }

  @Test
  public void retrievesPatientByFHId_True() {
    FosterHome newFosterHome1 = new FosterHome(1, "Emma's Foster Home", "Emma", "Plesa", "123 Alphabet St.", "Paisley Park", "Your Heart", 71999, "777-777-1234");
    newFosterHome1.save();
    Patient testPatient1 = new Patient(newFosterHome1.getId(), "Bob", "Jones", testDate1, "222-222-2222", "111-11-1111", "female", testDate2, "Saskatchewan", "Hindu", "sports", "Legacy Emanuel", "Bob Ross, MD", "123-123-4567");
    testPatient1.save();
    Patient testPatient2 = new Patient(newFosterHome1.getId(), "Bob", "Jones", testDate1, "222-222-2222", "111-11-1111", "female", testDate2, "Saskatchewan", "Hindu", "sports", "Legacy Emanuel", "Bob Ross, MD", "123-123-4567");
    testPatient2.save();
    Patient[] patients = new Patient[] {testPatient1, testPatient2};
    assertTrue(newFosterHome1.getPatients().containsAll(Arrays.asList(patients)));
  }

}
