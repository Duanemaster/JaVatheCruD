import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDao {
    private String url = ""; // Ikaw na dito syempre
    private String Name = ""; // pati ito
    private String password = ""; // pati rin ito

    public void createUser(User user) {
        String sql = "{call sp_InserUser(?, ?, ?)}";
        try (Connection conn = DriverManager.getConnection(url, Name, password);
                CallableStatement mt = conn.prepareCall(sql)) {
            mt.setString(1, user.getName());
            mt.setString(2, user.getEmail());
            mt.setInt(3, user.getAge());
            mt.execute();

            System.out.println("We just created a new user freakining duane");

        } catch (SQLException e) {
            System.out.println("Error creating user in the database.");
            e.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        String sql = "{call sp_GetAllUsers()}";

        try (Connection conn = DriverManager.getConnection(url, Name, password);
                CallableStatement mt = conn.prepareCall(sql);
                ResultSet rs = mt.executeQuery()) {

            while (rs.next()) {
                users.add(new User(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getInt("age")));
            }
        } catch (SQLException e) {
            System.out.println("Error sa pagkuha ng listahan:");
            e.printStackTrace();
        }
        return users;
    }

    public void updateUser(User user) {
        String sql = "{call sp_UpdateUser(?, ?, ?, ?)}";
        try (Connection conn = DriverManager.getConnection(url, Name, password);
                CallableStatement mt = conn.prepareCall(sql)) {
            mt.setInt(1, user.getId());
            mt.setString(2, user.getName());
            mt.setString(3, user.getEmail());
            mt.setInt(4, user.getAge());
            mt.execute();
            System.out.println("User updated successfully.");
        } catch (SQLException e) {
            System.out.println("Error updating user in the database.");
            e.printStackTrace();
        }
    }

    public void deleteUser(int id) {
        String sql = "{call sp_DeleteUser(?)}";
        try (Connection conn = DriverManager.getConnection(url, Name, password);
                CallableStatement mt = conn.prepareCall(sql)) {
            mt.setInt(1, id);
            mt.execute();
            System.out.println("User deleted successfully.");
        } catch (SQLException e) {
            System.out.println("Error deleting user in the database.");
            e.printStackTrace();
        }
    }
}
