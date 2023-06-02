package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {

    Connection conn = Util.getConnection();
    public UserDaoJDBCImpl() {

    }

//"CREATE TABLE IF NOT EXISTS users " + "(id BIGINT PRIMARY KEY AUTO_INCREMENT, name VARCHAR(255), last_name VARCHAR(255), age INT)"
    public void createUsersTable()  {
        String commandNewTable = "CREATE TABLE IF NOT EXISTS users " +
                "(id BIGINT PRIMARY KEY AUTO_INCREMENT, name VARCHAR(255), lastname VARCHAR(255), age INT)";
        try (Statement state = conn.createStatement()) {
            state.executeUpdate(commandNewTable);
            System.out.println("Table was created");
        } catch (SQLException e) {
            System.out.println(e);

        }


    }

    public void dropUsersTable() {
        String commandDropTable = "DROP TABLE IF EXISTS users";
        try (Statement state = conn.createStatement()) {
            state.executeUpdate(commandDropTable);
            System.out.println("Table was deleted");
        } catch (SQLException e) {
            System.out.println(e);

        }
    }

    public void saveUser(String name, String lastName, byte age) {
        String commandAddUser = "INSERT INTO users (name, lastName, age) VALUES (?, ?, ?)";
        User user = new User(name, lastName, age);
        try (PreparedStatement prst = conn.prepareStatement(commandAddUser)) {
            prst.setString(1, name);
            prst.setString(2, lastName);
            prst.setByte(3, age);
            prst.executeUpdate();
            System.out.println("User с именем – " + user.getName() + " добавлен в базу данных");

        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public void removeUserById(long id) {
        String removeId = "delete from users where id = ?";
        try (PreparedStatement prst = conn.prepareStatement(removeId)) {
            prst.setLong(1, id);
            prst.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public List<User> getAllUsers() {
        List<User> listUsers = new ArrayList();
        String commandGet = "SELECT * FROM users";
        try (Statement state = conn.createStatement()) {
            ResultSet resultSet = state.executeQuery(commandGet);
            while(resultSet.next()) {
                User user = new User();
                user.setName(resultSet.getString("name"));
                user.setLastName(resultSet.getString("lastName"));
                user.setAge(resultSet.getByte("age"));
                listUsers.add(user);
                System.out.println("Все строки получены");
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return listUsers;
    }

    public void cleanUsersTable() {
        String commandCleanTable  = "TRUNCATE TABLE users";
        try (Statement  state = conn.createStatement()) {
            state.executeUpdate(commandCleanTable);
            System.out.println("Table was cleaned");
        } catch (SQLException e) {
            System.out.println(e);

        }
    }
}
