import java.sql.*;

public class User {
    public static boolean register(String username, String password) {
        try (Connection conn = DBConnection.getConnection()) {
            String sql = "INSERT INTO users (username, password) VALUES (?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, username);
            stmt.setString(2, password);
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Username already exists or DB error: " + e.getMessage());
            return false;
        }
    }

    public static int login(String username, String password) {
        try (Connection conn = DBConnection.getConnection()) {
            String sql = "SELECT id FROM users WHERE username = ? AND password = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, username);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) return rs.getInt("id");
        } catch (SQLException e) {
            System.out.println("Login failed: " + e.getMessage());
        }
        return -1;
    }
}