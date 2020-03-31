
public class SoloaventyrMVC {
    public static void main(String[] args) {
    SoloaventyrView theView = new SoloaventyrView();
    SoloaventyrModel theModel = new SoloaventyrModel();
    SoloaventyrController theController = new SoloaventyrController(theView, theModel);
    theView.setVisible(true);
    }
}