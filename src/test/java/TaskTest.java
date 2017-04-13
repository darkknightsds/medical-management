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

public class TaskTest {

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void task_instantiatesCorrectly_true() {
    Patient testPatient = new Patient(1, "Bob", "Jones", "12/12/12", "222-222-2222", "111-11-1111", "female", "03/15/30", "Saskatchewan", "Hindu", "sports", "Legacy Emanuel", "Bob Ross, MD", "123-123-4567");
    testPatient.save();
    Task testTask = new Task(testPatient.getPatientId(), "Give Bob a shower.", "2017-04-04 03:30");
    assertTrue(testTask instanceof Task);
  }

  @Test
  public void save_savesTaskToDatabase_true() {
    Patient testPatient = new Patient(1, "Bob", "Jones", "12/12/12", "222-222-2222", "111-11-1111", "female", "03/15/30", "Saskatchewan", "Hindu", "sports", "Legacy Emanuel", "Bob Ross, MD", "123-123-4567");
    testPatient.save();
    Task testTask = new Task(testPatient.getPatientId(), "Give Bob a shower.", "2017-04-04 03:30");
    testTask.save();
    assertTrue(Task.all().get(0).equals(testTask));
  }

  @Test
  public void getTopTaks_returnsTasksOrderedByDateTime_true() {
    Patient testPatient = new Patient(1, "Bob", "Jones", "12/12/12", "222-222-2222", "111-11-1111", "female", "03/15/30", "Saskatchewan", "Hindu", "sports", "Legacy Emanuel", "Bob Ross, MD", "123-123-4567");
    testPatient.save();
    Task testTask1 = new Task(testPatient.getPatientId(), "Give Bob a shower.", "2017-04-04 14:30");
    testTask1.save();
    Task testTask2 = new Task(testPatient.getPatientId(), "Give Bob a shower.", "2017-04-04 13:30");
    testTask2.save();
    Task testTask3 = new Task(testPatient.getPatientId(), "Give Bob a shower.", "2017-04-04 11:30");
    testTask3.save();
    Task testTask4 = new Task(testPatient.getPatientId(), "Give Bob a shower.", "2017-04-04 22:30");
    testTask4.save();
    List<Task> topTasks = Task.getTopTasks();
    assertTrue(topTasks.get(0).equals(testTask3));
  }


}
