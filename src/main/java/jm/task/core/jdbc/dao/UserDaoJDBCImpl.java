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
    public void createUsersTable()  {
        String commandNewTable = "CREATE TABLE IF NOT EXISTS users " +
                "(id BIGINT PRIMARY KEY AUTO_INCREMENT, name VARCHAR(255), lastname VARCHAR(255), age INT)";
        try (PreparedStatement prst = conn.prepareStatement(commandNewTable)) {
            prst.executeUpdate()
        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }
    public void dropUsersTable() {
        String commandDropTable = "DROP TABLE IF EXISTS users";
        try (PreparedStatement prst = conn.prepareStatement(commandDropTable)) {
            prst.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        String commandAddUser = "INSERT INTO users (name, lastName, age) VALUES (?, ?, ?)";
        try (PreparedStatement prst = conn.prepareStatement(commandAddUser)) {
            prst.setString(1, name);
            prst.setString(2, lastName);
            prst.setByte(3, age);
            prst.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }

    public void removeUserById(long id) {
        String removeId = "delete from users where id = ?";
        try (PreparedStatement prst = conn.prepareStatement(removeId)) {
            prst.setLong(1, id);
            prst.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }

    public List<User> getAllUsers() {
        List<User> listUsers = new ArrayList();
        String commandGet = "SELECT * FROM users";
        try (PreparedStatement prst = conn.prepareStatement(commandGet)) {
            ResultSet resultSet = prst.executeQuery();
            while(resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong("id"));
                user.setName(resultSet.getString("name"));
                user.setLastName(resultSet.getString("lastName"));
                user.setAge(resultSet.getByte("age"));
                listUsers.add(user);
            }
        } catch (SQLException e) {
            throw new RuntimeException();
        }
        return listUsers;
    }

    public void cleanUsersTable() {
        String commandCleanTable  = "TRUNCATE TABLE users";
        try (PreparedStatement prst = conn.prepareStatement(commandCleanTable)) {
            prst.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }
}
