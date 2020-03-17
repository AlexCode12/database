import java.sql.*;

public class DatabaseExample {
    public static void main(String[] args) {
        try {
            // Set up connection to database
            Connection conn = DriverManager.getConnection(
                    "jdbc:mysql://"+DatabaseLoginData.DBURL + ":" + DatabaseLoginData.port + "/" + DatabaseLoginData.DBname +
                            "? allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=UTC",
                    DatabaseLoginData.user, DatabaseLoginData.password);

            // Setup statement
            Statement stmt = conn.createStatement();
            // Create query and execute
            String strSelect = "select * from story";
            System.out.println("The SQL statement is: " + strSelect + "\n");

            ResultSet rset = stmt.executeQuery(strSelect);

            // Loop through the result set and print

            System.out.println("The records selected are:");
            int rowCount = 0;
            while(rset.next()) {
                int    qty   = rset.getInt("id");
                String title = rset.getString("body");
                System.out.println(qty + "\t" + title);
                ++rowCount;
            }
            System.out.println("Total number of records = " + rowCount);

            // Close conn and stmt
            conn.close();
            stmt.close();
        } catch(SQLException ex) {
            ex.printStackTrace();
        }
    }
}
