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



    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() throws SQLException {
       try (Connection con = Util.connect()) {
           try (Statement statement = con.createStatement()) {
               statement.executeUpdate("CREATE TABLE IF NOT EXISTS `users` (\n" +
                       "  `id` INT NOT NULL AUTO_INCREMENT,\n" +
                       "  `name` VARCHAR(45) NULL,\n" +
                       "  `lastName` VARCHAR(45) NULL,\n" +
                       "  `age` INT(3) NULL,\n" +
                       "  PRIMARY KEY (`id`))");
               con.commit();
           } catch (SQLException e){
               con.rollback();
               e.printStackTrace();
           }
       }

    }

    public void dropUsersTable() throws SQLException {
        try (Connection con = Util.connect()) {
            try (Statement statement = con.createStatement()) {
                statement.executeUpdate("DROP TABLE IF EXISTS users");
                con.commit();
            } catch (SQLException e) {
                con.rollback();
                e.printStackTrace();
            }
        }
    }

    public void saveUser(String name, String lastName, byte age) throws SQLException {
        try (Connection con = Util.connect()) {
            try (Statement statement = con.createStatement()) {
                statement.execute(String.format("INSERT INTO users (name,lastName,age) VALUES ('%s', '%s', '%d')", name, lastName, age));
                con.commit();
            } catch (SQLException e) {
                con.rollback();
                e.printStackTrace();
            }
        }
    }

    public void removeUserById(long id) throws SQLException {
        try (Connection con = Util.connect()) {
            try (Statement statement = con.createStatement()) {
                statement.executeUpdate(String.format("DELETE FROM users WHERE id=%d", id));
                con.commit();
            } catch (SQLException e) {
                con.rollback();
                e.printStackTrace();
            }
        }
    }

    public List<User> getAllUsers() throws SQLException {
        List<User> users = new ArrayList<>();
        try (Connection con = Util.connect()) {
            try (Statement statement = con.createStatement()) {
                ResultSet resultSet = statement.executeQuery("SELECT * FROM users");
                while (resultSet.next()) {
                    User user = new User();
                    user.setId((long) resultSet.getInt("id"));
                    user.setName(resultSet.getString("name"));
                    user.setLastName(resultSet.getString("lastName"));
                    user.setAge((byte) resultSet.getInt("age"));

                    users.add(user);
                }
                con.commit();
            } catch (SQLException e) {
                con.rollback();
                e.printStackTrace();
            }
        }
        return users;
    }

    public void cleanUsersTable() throws SQLException {
        try (Connection con = Util.connect()) {
            try (Statement statement = con.createStatement()) {
                statement.executeUpdate("TRUNCATE TABLE users");
                con.commit();
            } catch (SQLException e) {
                con.rollback();
                e.printStackTrace();
            }
        }
    }
}
