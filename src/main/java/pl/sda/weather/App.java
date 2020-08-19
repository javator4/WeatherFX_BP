package pl.sda.weather;

import com.sun.javafx.scene.SceneHelper;
import com.sun.javafx.scene.SceneUtils;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application
{
    public static void main( String[] args )
    {
        launch(args);
        System.out.println( "JavaFX closed" );
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        Parent root = FXMLLoader.load(getClass().getResource("res/layout.fxml"));
        Scene s = new Scene(root);
        primaryStage.setTitle("Weather FX");
        primaryStage.setScene(s);

        primaryStage.show();

    }
}
