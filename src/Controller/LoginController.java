package Controller;

import Model.CauHoi;
import Model.TaiKhoan;
import Repository.CauHoiRepository;
import Repository.TaiKhoanRepository;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

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
    private ComboBox<String> cbxQuestion;

    @FXML
    private Hyperlink hplForgot;

    @FXML
    private PasswordField pwdPasswordLogin;

    @FXML
    private PasswordField pwdPasswordRegister;

    @FXML
    private TextField txtAnswerRegister;

    @FXML
    private TextField txtUsernameLogin;

    @FXML
    private TextField txtUsernameRegister;

    @FXML
    private Label lblCreateAccount;

    private CauHoiRepository cauHoiRepository;
    private TaiKhoanRepository taiKhoanRepository;
    private Alert alert;

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

                slider.setOnFinished((ActionEvent e) -> {
                    lblCreateAccount.setVisible(true);
                    btnCreateNewAccount.setText("Tạo tài khoản mới");
                });
                slider.play();
            }

        }
    }
    @FXML
    void btnDangKyMouseClicked(ActionEvent event) {
        if (txtUsernameRegister.getText().isEmpty() || pwdPasswordRegister.getText().isEmpty() || txtAnswerRegister.getText().isEmpty()
                || cbxQuestion.getSelectionModel().getSelectedItem() == null) {
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Lỗi");
            alert.setHeaderText(null);
            alert.setContentText("Vui lòng điền đầy đủ thông tin");
            alert.showAndWait();
        } else {
            taiKhoanRepository = new TaiKhoanRepository();
            String MaNV= "NV02";
            String TenDangNhap = txtUsernameRegister.getText();
            String MatKhau = pwdPasswordRegister.getText();
            String CauHoi = cbxQuestion.getSelectionModel().getSelectedItem();
            String CauTraLoi = txtAnswerRegister.getText();
            Date NgayTao = new java.util.Date();
            NgayTao.getTime();
            TaiKhoan taikhoan = new TaiKhoan(MaNV,TenDangNhap,MatKhau,CauHoi,CauTraLoi,NgayTao);
            taiKhoanRepository.insertTaiKhoan(taikhoan);

            Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
            alert1.setTitle("Thông báo");
            alert1.setHeaderText(null);
            alert1.setContentText("Tạo tài khoản thành công");
            alert1.showAndWait();

            TranslateTransition slider = new TranslateTransition();
            slider.setNode(DangKyContainer);
            slider.setToX(0);
            slider.setDuration(Duration.seconds(0.5));
            slider.play();

            txtUsernameRegister.setText("");
            txtAnswerRegister.setText("");
            pwdPasswordRegister.setText("");

            cbxQuestion.getSelectionModel().clearSelection();




        }
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        cauHoiRepository = new CauHoiRepository();
        ArrayList<CauHoi> dscauhoi = cauHoiRepository.getListCauHoi();
        for (CauHoi cauhoi : dscauhoi) {
            String TenCH = cauhoi.getTenCH();
            cbxQuestion.getItems().add(TenCH);
        }
    }
}



