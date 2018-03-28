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
    /*
    * variables used to implement a movable window
    * */
    private double mouseX;
    private double mouseY;
    private double stageX;
    private double stageY;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("Display.fxml"));
        Pane pane = loader.load();
        if (pane != null){
            // get the scene and implement movable window
            new Display(pane);
            Scene scene = new Scene(pane);
            scene.getStylesheets().add(getClass().getResource("DarkStyle.css").toExternalForm());
            scene.setOnMousePressed(event -> {
                mouseX = event.getScreenX();
                mouseY = event.getScreenY();
                stageX = primaryStage.getX();
                stageY = primaryStage.getY();
            });
            scene.setOnMouseDragged(event -> {
                primaryStage.setX(stageX + event.getScreenX() - mouseX);
                primaryStage.setY(stageY + event.getScreenY() - mouseY);
            });

            // set something necessary about stage
            primaryStage.initStyle(StageStyle.UNDECORATED);
            primaryStage.setResizable(false);
            primaryStage.setScene(scene);
            primaryStage.show();
        }
    }
}
