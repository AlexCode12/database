import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.Scanner;

public class SoloaventyrController {
    private SoloaventyrView theView;
    private SoloaventyrModel theModel;

    public SoloaventyrController(SoloaventyrView theView, SoloaventyrModel theModel){
       this.theView = theView;
       this.theModel = theModel;
        this.theView.addCalculateListener(new CalculateListener());
        try {
            Connection conn = DriverManager.getConnection(
                    "jdbc:mysql://"+DatabaseLoginData.DBURL + ":" + DatabaseLoginData.port + "/" + DatabaseLoginData.DBname +
                            "? allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=UTC",
                    DatabaseLoginData.user, DatabaseLoginData.password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private class CalculateListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            int story_id, target_id = 0;
            try{
                story_id = theView.getFirstNumber();
                target_id = theView.getSecondNumber();
                String strSelect = "select body from story where id = " + story_id;
            }
            catch(NumberFormatException ex){
                System.out.println(ex);
                theView.displayErrorMessage("You Need to Enter valid Integers");
            }
        }
    }
}
