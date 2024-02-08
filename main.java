import java.sql.*;

public class main {
    private static final String CONNECTION_URL = "jdbc:postgresql://localhost:5432/postgres";
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "LUFFYtaroo111&&&";

    public static void main(String[] args) {
        retrieveAndPrintManhwas();
        addNewManhwa(6,"Прирожденный наёмник","Боевик,школа,драма", 175, 9);
    }

    private static void retrieveAndPrintManhwas() {
        try (Connection con = DriverManager.getConnection(CONNECTION_URL, USERNAME, PASSWORD);
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM favourite_manhwas")) {

            System.out.println("Список любимых манхв:");
            while (rs.next()) {
                System.out.println(rs.getInt("id") + " "
                        + rs.getString("manhwa_name") + " "
                        + rs.getString("genre") + " "
                        + rs.getInt("chapters") + " "
                        + rs.getInt("rate"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void addNewManhwa(int id,String name, String genre, int chapters, int rate) {
        try (Connection con = DriverManager.getConnection(CONNECTION_URL, USERNAME, PASSWORD)) {
            String query = "INSERT INTO favourite_manhwas (id,manhwa_name, genre, chapters, rate) VALUES (?,?, ?, ?, ?)";
            try (PreparedStatement pstmt = con.prepareStatement(query)) {
                pstmt.setInt(1,id);
                pstmt.setString(2, name);
                pstmt.setString(3, genre);
                pstmt.setInt(4, chapters);
                pstmt.setInt(5, rate);
                int rowsAffected = pstmt.executeUpdate();
                if (rowsAffected > 0) {
                    System.out.println("Новая запись успешно добавлена.");
                } else {
                    System.out.println("Не удалось добавить новую запись.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
