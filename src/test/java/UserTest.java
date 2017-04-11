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

public class UserTest {

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void testUser_Instatiates() {
    User testUser = new User("biglove69", "xxx123");
    assertTrue(testUser instanceof User);
  }

  @Test
  public void testUser_allGettersWork() {
    User testUser = new User("biglove69", "xxx123");
    assertEquals("biglove69", testUser.getUsername());
    assertEquals("xxx123", testUser.getPassword());
  }

  @Test
  public void overrideEquals_Works() {
    User testUser = new User("biglove69", "xxx123");
    User testUser2 = new User("biglove69", "xxx123");
    assertTrue(testUser.equals(testUser2));
  }

  @Test
  public void savesAllHomesToDatabase_True() {
    User testUser = new User("biglove69", "xxx123");
    User testUser2 = new User("biglove69", "xxx123");
    testUser.save();
    testUser2.save();
    assertTrue(testUser2.equals(User.all().get(1)));
  }

  @Test
  public void clientsAreCreatedWithDatabaseId_True() {
   User testUser = new User("biglove69", "xxx123");
   testUser.save();
   assertTrue(testUser.getId() > 0);
  }

  @Test
  public void findsUserById_ReturnsSecondUser() {
    User newUser1 = new User("biglove69", "xxx123");
    User newUser2 = new User("biglove69", "xxx123");
    newUser1.save();
    newUser2.save();
    assertEquals(User.find(newUser2.getId()), newUser2);
  }

  @Test
  public void updatesUserInformation_True() {
    User newUser1 = new User("biglove69", "xxx123");
    newUser1.save();
    newUser1.update("biglove69", "xxx124"); assertEquals("xxx124", User.find(newUser1.getId()).getPassword());
  }

  // @Test
  // public void deletesUser_Null() {
  //   User newUser1 = new User("biglove69", "xxx123");
  //   newUser1.save();
  //   int user_id = newUser1.getId();
  //   newUser1.delete();
  //   assertEquals(null, User.find(user_id));
  // }

  @Test
  public void retrievesFosterHomeByUserId_True() {
    User testUser = new User("biglove69", "xxx123");
    testUser.save();
    FosterHome newFosterHome1 = new FosterHome(testUser.getId(), "Emma's Foster Home", "Emma", "Plesa", "123 Alphabet St.", "Paisley Park", "Your Heart", 71999, "777-777-1234");
    newFosterHome1.save();
    FosterHome newFosterHome2 = new FosterHome(testUser.getId(), "Emma's Foster Home", "Emma", "Plesa", "123 Alphabet St.", "Paisley Park", "Your Heart", 71999, "777-777-1234");
    newFosterHome2.save();
    FosterHome[] fosterHomes = new FosterHome[] {newFosterHome1, newFosterHome2};
    assertTrue(testUser.getFosterHomes().containsAll(Arrays.asList(fosterHomes)));
  }

}
