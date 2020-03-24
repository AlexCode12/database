import java.sql.*;
import java.util.Scanner;

public class Soloaventyr {
    public static void main(String[] args) {
        try {
            // Set up connection to database
            Connection conn = DriverManager.getConnection(
                    "jdbc:mysql://"+DatabaseLoginData.DBURL + ":" + DatabaseLoginData.port + "/" + DatabaseLoginData.DBname +
                            "? allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=UTC",
                    DatabaseLoginData.user, DatabaseLoginData.password);

            // Setup statement
            Statement stmt = conn.createStatement();
            int story_id = 1;
            int target_id = 1;
            // Create query and execute
            String strSelect = "select body from story where id = " + story_id;
            System.out.println("Skriv in ID:t för att gå vidare (t.ex skriv 3 om du vill gå till 3");
            ResultSet rset = stmt.executeQuery(strSelect);

            // Loop through the result set and print
            while(rset.next()) {
                String body = rset.getString("body");
                System.out.println(body);
            }

            while (target_id != 53) {
                String strSelect2 = "select target_id, description from links where story_id = " + story_id;
                ResultSet rset2 = stmt.executeQuery(strSelect2);

                while (rset2.next()) {
                    int target_ids = rset2.getInt("target_id");
                    String description = rset2.getString("description");
                    System.out.println(target_ids + ": " + description);
                }

                Scanner in = new Scanner(System.in);
                target_id = in.nextInt();
                story_id = target_id;

                if (target_id >= 53) {
                    System.out.println("Välj ett annat tal.");
                    target_id = in.nextInt();
                }
                
                String strSelect3 = "select body from story where id = " + target_id;
                ResultSet rset3 = stmt.executeQuery(strSelect3);

                while (rset3.next()) {
                    String body = rset3.getString("body");
                    System.out.println(body);
                }
            }
            // Close conn and stmt
            conn.close();
            stmt.close();
        } catch(SQLException ex) {
            ex.printStackTrace();
        }
    }
}