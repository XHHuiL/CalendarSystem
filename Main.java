import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

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
            Scene scene = new Scene(pane);
            scene.getStylesheets().add(getClass().getResource("DarkStyle.css").toExternalForm());

            // set something necessary
            primaryStage.initStyle(StageStyle.UNDECORATED);
            primaryStage.setResizable(false);
            primaryStage.setScene(scene);
            primaryStage.show();
        }
    }
}
