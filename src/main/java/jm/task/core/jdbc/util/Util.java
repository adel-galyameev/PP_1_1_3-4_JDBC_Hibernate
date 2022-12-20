package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Util {
    private static final String NAME_USER = "root";
    private static final String PASSWORD = "adelfitnesinfo";
    private static final String URL = "jdbc:mysql://localhost:3306/dbtest";

    private static SessionFactory sessionFactory;


    public static Connection connect()  {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (Exception e) {
            e.getStackTrace();
        }

        try {
            Connection con = DriverManager.getConnection(URL,NAME_USER,PASSWORD);
            con.setAutoCommit(false);
            return con;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static SessionFactory getSessionFactory() {
        try {
            Configuration configuration = new Configuration();

            Properties settings = new Properties();
            settings.put(Environment.DRIVER, "com.mysql.cj.jdbc.Driver");
            settings.put(Environment.URL, "jdbc:mysql://localhost:3306/dbtest?useSSL=false");
            settings.put(Environment.USER, NAME_USER);
            settings.put(Environment.PASS, PASSWORD);
            settings.put(Environment.SHOW_SQL, "true");
            

            configuration.setProperties(settings);

            configuration.addAnnotatedClass(User.class);

            ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                    .applySettings(configuration.getProperties()).build();

            sessionFactory = configuration.buildSessionFactory(serviceRegistry);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return sessionFactory;
    }
    // реализуйте настройку соеденения с БД
}
