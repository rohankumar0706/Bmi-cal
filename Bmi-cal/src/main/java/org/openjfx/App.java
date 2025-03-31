package org.openjfx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class App extends Application {

    private static Scene scene;

    /**
     * BMI Kalkulátor "színpad felállítása"
     * @param stage színpad
     * @throws IOException kivétel
     */
    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("bmi"), 640, 400);
        stage.setScene(scene);
        stage.show();
        stage.setTitle("BMI Kalkulátor");
    }

    /**
     * FXML fájl betöltése
     * @param fxml betölteni kívánt FXML neve
     * @return fxmlLoader.load();
     * @throws IOException kivétel
     */
/*    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }
*/
    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }

}