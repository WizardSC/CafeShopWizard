package Controller;

import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.util.Duration;

import java.net.URL;
import java.util.ResourceBundle;

public class DashboardController implements Initializable {

    @FXML
    private Label menuLabel;
    private TranslateTransition translateTransition;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Tạo hiệu ứng chuyển động
        translateTransition = new TranslateTransition(Duration.seconds(0.3), menuLabel);
        translateTransition.setToX(10); // Điểm bắt đầu của hiệu ứng chuyển động
        translateTransition.setCycleCount(2);
        translateTransition.setAutoReverse(true);

        // Hover effect
        menuLabel.setOnMouseEntered(event -> translateTransition.play());
        menuLabel.setOnMouseExited(event -> translateTransition.stop());
    }
}
