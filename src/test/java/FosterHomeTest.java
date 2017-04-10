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


}
