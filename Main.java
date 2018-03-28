import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import java.io.File;

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

    private static final String DISPLAY_FXML = "Display.fxml";
    private static final String DARK_STYLE_CSS = "DarkStyle.css";

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader();
        if (!sourceFileExists(DISPLAY_FXML)) {
            System.out.println("Error: Display.fxml is not found");
            System.exit(1);
        }
        loader.setLocation(Main.class.getResource(DISPLAY_FXML));
        Pane pane = loader.load();
        if (pane != null) {
            // get the scene and implement movable window
            new Display(pane);
            Scene scene = new Scene(pane);
            if (sourceFileExists(DARK_STYLE_CSS))
                scene.getStylesheets().add(getClass().getResource(DARK_STYLE_CSS).toExternalForm());
            else
                System.out.println("Waring: DarkStyle.css is not found");
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

    private boolean sourceFileExists(String path) {
        return new File(path).exists();
    }
}
