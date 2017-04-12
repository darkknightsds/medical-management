import org.sql2o.*;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.sql.Timestamp;

public class User
// implements DatabaseManagement
 {
   private int user_id;
   private String username;
   private String password;

  public User(String username, String password) {
    this.username = username;
    this.password = password;
  }

  public int getId() {
    return user_id;
  }

  public String getUsername() {
    return username;
  }

  public String getPassword() {
    return password;
  }

  @Override
  public boolean equals(Object otherUser) {
    if(!(otherUser instanceof User)) {
      return false;
    } else {
      User testUser = (User) otherUser;
      return this.getUsername().equals(testUser.getUsername());
    }
  }

  public static List<User> all() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM users;";
      return con.createQuery(sql)
      .executeAndFetch(User.class);
    }
  }

  public void save() {
    try(Connection con = DB.sql2o.open()) {
    String sql = "INSERT INTO users (username, password) VALUES (:username, :password);";
    this.user_id = (int) con.createQuery(sql, true)
      .addParameter("username", this.username)
      .addParameter("password", this.password)
      .executeUpdate()
      .getKey();
    }
  }

  public static User find(int user_id) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM users where user_id = :user_id;";
      User newUser = con.createQuery(sql)
        .addParameter("user_id", user_id)
        .executeAndFetchFirst(User.class);
      return newUser;
    }
  }

  public static User findByUsername(String username) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM users where username = :username;";
      User newUser = con.createQuery(sql)
        .addParameter("username", username)
        .executeAndFetchFirst(User.class);
      // if (newUser == null) {
      //   throw new IllegalArgumentException("That user doesn't exist. Please create a new account to proceed.");
      // }
      return newUser;
    }
  }

  public void update(String username, String password) {
    this.username = username;
    this.password = password;

    try(Connection con = DB.sql2o.open()) {
      String sql = "UPDATE users SET (username, password) = (:username, :password) WHERE user_id = :user_id;";
      con.createQuery(sql)
        .addParameter("user_id", this.user_id)
        .addParameter("username", username)
        .addParameter("password", password)
        .executeUpdate();
    }
  }

  // public void delete() {
  // try (Connection con = DB.sql2o.open()) {
  //     String sql = "DELETE FROM users WHERE user_id = :user_id;";
  //     con.createQuery(sql)
  //       .addParameter("user_id", this.user_id)
  //       .executeUpdate();
  //   }
  // }

  public List<FosterHome> getFosterHomes() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM foster_homes WHERE user_id = :user_id";
      return con.createQuery(sql)
        .addParameter("user_id", this.user_id)
        .executeAndFetch(FosterHome.class);
    }
  }

  public void getSession() {

  }

}
