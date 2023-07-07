package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.time.LocalDate;

public class Main extends Application {
    public static final String CURRENCY = "VNĐ";
    public static int day;
    public static int month;
    public static int year;
    @Override
    public void start(Stage primaryStage) throws Exception{
        LocalDate currentDate = LocalDate.now();
        day = currentDate.getDayOfMonth();
        month = currentDate.getMonthValue();
        year = currentDate.getYear();
        Parent root = FXMLLoader.load(getClass().getResource("../View/GiaoDien.fxml"));
        primaryStage.setTitle("CafeWizard");
        Image icon = new Image(getClass().getResourceAsStream("/img/icons8-cafe-100 (1).png"));
        primaryStage.getIcons().add(icon);
        primaryStage.initStyle(StageStyle.UNDECORATED);

        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
