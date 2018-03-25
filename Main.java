import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/*
 * Start here!
 *
 * */
public class Main extends Application {
    public static void main(String[] args) {
        /*
        System.out.println(DateUtil.getCurrentYear());
        System.out.println(DateUtil.getCurrentMonth());
        System.out.println(DateUtil.getCurrentDay());
        */
        //todo  We will run this class to test your codes.
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("Display.fxml"));
        Pane pane = loader.load();
        if (pane != null){
            new Display(pane);

            primaryStage.setTitle("Calendar");
            primaryStage.setScene(new Scene(pane));
            primaryStage.show();
        }
    }
}
