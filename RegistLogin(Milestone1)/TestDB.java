import util.DBConnection;
import java.sql.*;

public class TestDB {
    public static void main(String[] args) {
        try (Connection conn = DBConnection.getConnection()) {
            System.out.println("Connected to database!");

            // Test: Check if user exists
            String checkEmail = "SELECT * FROM users WHERE email = ?";
            PreparedStatement checkStmt = conn.prepareStatement(checkEmail);
            checkStmt.setString(1, "oarabile@example.com");
            ResultSet rs = checkStmt.executeQuery();

            if(rs.next()) {
                System.out.println("User exists: " + rs.getString("name"));
            } else {
                System.out.println("User not found.");
            }

            // Test: Insert new user (handle exceptions for duplicates)
            String insertSQL = "INSERT INTO users (student_number, name, surname, email, phone, password) VALUES (?, ?, ?, ?, ?, ?)";
            try (PreparedStatement insertStmt = conn.prepareStatement(insertSQL)) {
                insertStmt.setString(1, "202501235");
                insertStmt.setString(2, "Test");
                insertStmt.setString(3, "User");
                insertStmt.setString(4, "testuser@example.com");
                insertStmt.setString(5, "0712345678");
                insertStmt.setString(6, "hashed_password_here");
                insertStmt.executeUpdate();
                System.out.println("User inserted successfully.");
            } catch (SQLException e) {
                System.out.println("Insert error: " + e.getMessage());
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
