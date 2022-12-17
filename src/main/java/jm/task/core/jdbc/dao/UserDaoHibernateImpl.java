package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

public class UserDaoHibernateImpl implements UserDao {

    public UserDaoHibernateImpl() {

    }


    @Override
    public void createUsersTable() {
        try (SessionFactory sessionFactory = Util.getSessionFactory();
             Session session =  sessionFactory.openSession()) {
            session.beginTransaction();
            session.createSQLQuery("CREATE TABLE IF NOT EXISTS `user` (\n" +
                    "  `id` INT AUTO_INCREMENT,\n" +
                    "  `name` VARCHAR(45) ,\n" +
                    "  `lastName` VARCHAR(45) ,\n" +
                    "  `age` INT(3) ,\n" +
                    "  PRIMARY KEY (`id`))").addEntity(User.class).executeUpdate();
            session.getTransaction().commit();
        } catch (Exception e) {
            e.getStackTrace();
        }
    }

    @Override
    public void dropUsersTable() {

    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        try (SessionFactory sessionFactory = Util.getSessionFactory();
             Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            User user = new User(name,lastName,age);
            session.save(user);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.getStackTrace();
        }
    }

    @Override
    public void removeUserById(long id) {
        try (SessionFactory sessionFactory = Util.getSessionFactory();
             Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            User user = session.get(User.class, id);
            session.delete(user);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.getStackTrace();
        }
    }

    @Override
    public List<User> getAllUsers() {
        return null;
    }

    @Override
    public void cleanUsersTable() {

    }
}
