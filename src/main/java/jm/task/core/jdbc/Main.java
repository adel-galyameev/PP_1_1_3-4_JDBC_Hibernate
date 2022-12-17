package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        UserService us = new UserServiceImpl();

        //us.createUsersTable();

        us.saveUser("Bob","Smeet",(byte) 20);
        us.saveUser("Tom","Black",(byte) 25);
        us.saveUser("Slim","Blue",(byte) 40);
        us.saveUser("Rob","Smail",(byte) 99);
//
        //us.getAllUsers();

        //us.cleanUsersTable();

       // us.dropUsersTable();
    }
}
