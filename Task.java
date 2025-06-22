import java.sql.*;

public class Task {
    public static void create(int userId, String title, String desc) {
        try (Connection conn = DBConnection.getConnection()) {
            String sql = "INSERT INTO tasks (user_id, title, description) VALUES (?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, userId);
            stmt.setString(2, title);
            stmt.setString(3, desc);
            stmt.executeUpdate();
            System.out.println("Task created.");
        } catch (SQLException e) {
            System.out.println("Error creating task: " + e.getMessage());
        }
    }

    public static void view(int userId) {
        try (Connection conn = DBConnection.getConnection()) {
            String sql = "SELECT * FROM tasks WHERE user_id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();
            System.out.println("Your Tasks:");
            while (rs.next()) {
                System.out.println(rs.getInt("id") + ": " + rs.getString("title") + " - " + rs.getString("description"));
            }
        } catch (SQLException e) {
            System.out.println("Error fetching tasks: " + e.getMessage());
        }
    }

    public static void delete(int userId, int taskId) {
        try (Connection conn = DBConnection.getConnection()) {
            String sql = "DELETE FROM tasks WHERE id = ? AND user_id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, taskId);
            stmt.setInt(2, userId);
            int rows = stmt.executeUpdate();
            System.out.println(rows > 0 ? "Task deleted." : "Task not found.");
        } catch (SQLException e) {
            System.out.println("Error deleting task: " + e.getMessage());
        }
    }
}