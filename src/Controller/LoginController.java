package Controller;

import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

import java.net.URL;
import java.util.ResourceBundle;

public class LoginController {

    @FXML
    private AnchorPane DangKy;

    @FXML
    private AnchorPane DangKyContainer;

    @FXML
    private AnchorPane DangNhap;

    @FXML
    private Button btnCreateNewAccount;

    @FXML
    private Button btnLogin;

    @FXML
    private Button btnRegister;

    @FXML
    private ComboBox<?> cbxQuestion;

    @FXML
    private Hyperlink hplForgot;

    @FXML
    private PasswordField pwdPasswordLogin;

    @FXML
    private PasswordField pwdPasswordRegister;

    @FXML
    private PasswordField txtAnswerRegister;

    @FXML
    private TextField txtUsernameLogin;

    @FXML
    private TextField txtUsernameRegister;

    @FXML
    private Label lblCreateAccount;

    @FXML
    public void switchForm(ActionEvent event) {
        TranslateTransition slider = new TranslateTransition();
        slider.setDuration(Duration.seconds(0.5));

        if (event.getSource() == btnCreateNewAccount) {
            if (btnCreateNewAccount.getText().equals("Tạo tài khoản mới")) {
                System.out.println("OK");
                slider.setNode(DangKyContainer);
                lblCreateAccount.setVisible(false);
                slider.setToX(400);
                slider.setOnFinished((ActionEvent e) -> {
                    btnCreateNewAccount.setText("Đã có tài khoản rồi");

                });
                slider.play();
            } else {
                slider.setNode(DangKyContainer);
                slider.setToX(0);

                 slider.setOnFinished((ActionEvent e) ->{
                     lblCreateAccount.setVisible(true);
                     btnCreateNewAccount.setText("Tạo tài khoản mới");
                 });
                slider.play();
            }

        }
    }
}



