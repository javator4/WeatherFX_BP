package pl.sda.weather;


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
        System.out.println( "Hello World!" );
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        Parent root = FXMLLoader.load(getClass().getResource("/layout.fxml"));
        primaryStage.setScene(new Scene(root));
        primaryStage.show();

    }
}
