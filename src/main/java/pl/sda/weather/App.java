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

<<<<<<< HEAD
<<<<<<< HEAD
        Parent root = FXMLLoader.load(getClass().getResource("res/layout.fxml"));
=======
        Parent root = FXMLLoader.load(getClass().getResource("/layout.fxml"));
>>>>>>> update
        Scene s = new Scene(root);
        primaryStage.setTitle("Weather FX");
        primaryStage.setScene(s);

=======
        Parent root = FXMLLoader.load(getClass().getResource("/layout.fxml"));
        primaryStage.setScene(new Scene(root));
>>>>>>> 7bc9f7f37b9166336a30117b48ac23da009fb132
        primaryStage.show();

    }
}
