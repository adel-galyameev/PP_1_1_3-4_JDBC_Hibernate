package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {


    public UserDaoJDBCImpl() throws ClassNotFoundException, SQLException {

    }

    public void createUsersTable()  {
       try (Connection con = Util.connect();
            Statement statement = con.createStatement()) {
           statement.executeUpdate("CREATE TABLE `users` (\n" +
                   "  `id` INT NOT NULL AUTO_INCREMENT,\n" +
                   "  `name` VARCHAR(45) NULL,\n" +
                   "  `lastName` VARCHAR(45) NULL,\n" +
                   "  `age` INT(3) NULL,\n" +
                   "  PRIMARY KEY (`id`))");
       } catch (SQLException ignore){

       }

    }

    public void dropUsersTable() {
        try (Connection con = Util.connect();
             Statement statement = con.createStatement()) {
            statement.executeUpdate("DROP TABLE users");
        } catch (SQLException ignore) {

        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try (Connection con = Util.connect();
             Statement statement = con.createStatement()) {
            statement.execute(String.format("INSERT INTO users (name,lastName,age) VALUES ('%s', '%s', '%d')",name,lastName,age));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void removeUserById(long id) {
        try (Connection con = Util.connect();
             Statement statement = con.createStatement()) {
            statement.executeUpdate(String.format("DELETE FROM users WHERE id=%d",id));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        try (Connection con = Util.connect();
             Statement statement = con.createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM users");
            while (resultSet.next()) {
                User user = new User();
                user.setId((long) resultSet.getInt("id"));
                user.setName(resultSet.getString("name"));
                user.setLastName(resultSet.getString("lastName"));
                user.setAge((byte) resultSet.getInt("age"));

                users.add(user);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return users;
    }

    public void cleanUsersTable() {
        try (Connection con = Util.connect();
             Statement statement = con.createStatement()) {
            statement.executeUpdate("TRUNCATE TABLE users");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
