package Controller;

import Model.CauHoiModel;
import Model.TaiKhoanModel;
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
    private AnchorPane CauHoi;

    @FXML
    private AnchorPane DangKy;

    @FXML
    private AnchorPane DangKyContainer;

    @FXML
    private AnchorPane DangNhap;

    @FXML
    private AnchorPane DoiMatKhau;

    @FXML
    private Button btnCreateNewAccount;

    @FXML
    private Button btnDoiMKPass;

    @FXML
    private Button btnLogin;

    @FXML
    private Button btnQuayLaiChangedPass;

    @FXML
    private Button btnQuayLaiForgot;

    @FXML
    private Button btnRegister;

    @FXML
    private Button btnXacNhanForgot;

    @FXML
    private ComboBox<String> cbxQuestion;

    @FXML
    private ComboBox<String> cbxQuestionForgot;

    @FXML
    private Hyperlink hplForgot;

    @FXML
    private Label lblCreateAccount;

    @FXML
    private PasswordField pwdNewPassword;

    @FXML
    private PasswordField pwdOldPassword;

    @FXML
    private PasswordField pwdPasswordLogin;

    @FXML
    private PasswordField pwdPasswordRegister;

    @FXML
    private TextField txtAnswerForgot;


    @FXML
    private TextField txtAnswerRegister;

    @FXML
    private TextField txtUsernameForgot;

    @FXML
    private TextField txtUsernameLogin;

    @FXML
    private TextField txtUsernameRegister;

    private CauHoiRepository cauHoiRepository;
    private TaiKhoanRepository taiKhoanRepository;
    private Alert alert;
    private String MaCH;
    private ArrayList<TaiKhoanModel> dstaikhoan;
    private ArrayList<CauHoiModel> dscauhoi;
    private String TenDangNhapRegister;
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
            String MaNV = "NV02";
            String TenDangNhap = txtUsernameRegister.getText();
            String MatKhau = pwdPasswordRegister.getText();
            String CauHoi = cbxQuestion.getSelectionModel().getSelectedItem();
            String CauTraLoi = txtAnswerRegister.getText();
            Date NgayTao = new java.util.Date();
            NgayTao.getTime();
            if (MatKhau.length() < 8) {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Lỗi");
                alert.setHeaderText(null);
                alert.setContentText("Mật khẩu phải trên 8 kí tự");
                alert.showAndWait();
                return;
            }
//            dstaikhoan = taiKhoanRepository.getListTaiKhoan();
            boolean isDuplicate = false;
            for (TaiKhoanModel tk : dstaikhoan) {
                if (TenDangNhap.equals(tk.getTenDangNhap())) {
                    isDuplicate = true;
                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Lỗi");
                    alert.setHeaderText(null);
                    alert.setContentText("Tên tài khoản đã có trong hệ thống");
                    alert.showAndWait();

                    break;
                }
            }
            if (!isDuplicate) {
//                dscauhoi = cauHoiRepository.getListCauHoi();
                for (CauHoiModel ch : dscauhoi) {
                    if (ch.getTenCH().equals(CauHoi)) {
                        MaCH = ch.getMaCH();
                    }
                }
                TaiKhoanModel taikhoan = new TaiKhoanModel(MaNV, TenDangNhap, MatKhau, MaCH, CauTraLoi, NgayTao);
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

    }

    @FXML
    void btnLoginMouseClicked(ActionEvent event) {
        String TenDangNhap = txtUsernameLogin.getText();
        String MatKhau = pwdPasswordLogin.getText();
        if (TenDangNhap.equals("") || MatKhau.equals("")) {
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Lỗi");
            alert.setHeaderText(null);
            alert.setContentText("Vui lòng điền đầy đủ tên đăng nhập & mật khẩu");
            alert.showAndWait();
            return;
        }
        boolean Check = taiKhoanRepository.Login(TenDangNhap, MatKhau);
        if (Check) {
            System.out.println("Thành công");
        } else {
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Lỗi");
            alert.setHeaderText(null);
            alert.setContentText("Sai tên tài khoản hoặc mật khẩu");
            alert.showAndWait();
            return;
        }
    }

    //Quên mật khẩu
    @FXML
    void hplForgotMouseClicked(ActionEvent event) {
        DangKy.setVisible(false);
        CauHoi.setVisible(true);
        lblCreateAccount.setVisible(false);
        btnCreateNewAccount.setVisible(false);
        if (!txtUsernameLogin.getText().equals("")) {
            txtUsernameForgot.setText(txtUsernameLogin.getText());
        }


    }

    @FXML
    void btnQuayLaiForgotMouseClicked(ActionEvent event) {
        DangKy.setVisible(true);
        CauHoi.setVisible(false);
        lblCreateAccount.setVisible(true);
        btnCreateNewAccount.setVisible(true);

    }

    @FXML
    void btnXacNhanForgotMouseClicked(ActionEvent event) {
        String TenCH = cbxQuestionForgot.getSelectionModel().getSelectedItem();
        TenDangNhapRegister = txtUsernameForgot.getText();
        String CauTraLoi = txtAnswerForgot.getText();
        if (TenDangNhapRegister.equals("")) {
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Lỗi");
            alert.setHeaderText(null);
            alert.setContentText("Vui lòng điền tên đăng nhập");
            alert.showAndWait();
            return;
        }
        if (CauTraLoi.equals("")) {
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Lỗi");
            alert.setHeaderText(null);
            alert.setContentText("Vui lòng điền đầy đủ thông tin");
            alert.showAndWait();
            return;
        }

//       dscauhoi = cauHoiRepository.getListCauHoi();
        for (CauHoiModel ch : dscauhoi) {
            if (ch.getTenCH().equals(TenCH)) {
                MaCH = ch.getMaCH();
            }
        }
//        dstaikhoan = taiKhoanRepository.getListTaiKhoan();
        boolean foundCauTL = false;
        boolean foundCauHoi = false;
        for (TaiKhoanModel tk : dstaikhoan) {
            if (tk.getTenDangNhap().equals(TenDangNhapRegister) && tk.getCauHoi().equals(MaCH)){
                foundCauHoi = true;
                if(tk.getCauTraLoi().equals(CauTraLoi)){
                    foundCauTL = true;
                    break;
                }
            }
        }
        if(!foundCauHoi){
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Lỗi");
            alert.setHeaderText(null);
            alert.setContentText("Câu hỏi bảo mật không khớp");
            alert.showAndWait();
            return;
        }
        else if(!foundCauTL){
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Lỗi");
            alert.setHeaderText(null);
            alert.setContentText("Câu trả lời không chính xác");
            alert.showAndWait();
            return;
        } else {
            CauHoi.setVisible(false);
            DoiMatKhau.setVisible(true);
        }
    }

    @FXML
    void txtAnswerForgotMouseClicked(ActionEvent event) {
        if (txtUsernameForgot.getText().equals("")) {
            System.out.println("Điền đầy đủ thông tin");
        }
    }


    @FXML
    void btnDoiMKPassMouseClicked(ActionEvent event) {
        String MatKhauCu = pwdOldPassword.getText();
        String MatKhauMoi = pwdNewPassword.getText();
        if(MatKhauCu.equals("")){
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Lỗi");
            alert.setHeaderText(null);
            alert.setContentText("Không được để trống thông tin");
            alert.showAndWait();
            return;
        }
        if (MatKhauMoi.length() < 8) {
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Lỗi");
            alert.setHeaderText(null);
            alert.setContentText("Mật khẩu mới phải trên 8 kí tự");
            alert.showAndWait();
            return;
        }
        boolean checkMKCu = false;
        for(TaiKhoanModel tk : dstaikhoan){
            if(tk.getMatKhau().equals(MatKhauCu)){
                checkMKCu = true;
                taiKhoanRepository.updateMatKhau(TenDangNhapRegister,MatKhauMoi);
                alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Thông báo");
                alert.setHeaderText(null);
                alert.setContentText("Đổi mật khẩu thành công");
                alert.showAndWait();
                break;
            }
        }
        if(!checkMKCu){
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Lỗi");
            alert.setHeaderText(null);
            alert.setContentText("Mật khẩu cũ không chính xác");
            alert.showAndWait();
            return;
        }

    }

    @FXML
    void btnQuayLaiChangedPassMouseClicked(ActionEvent event) {
        CauHoi.setVisible(true);
        DoiMatKhau.setVisible(false);
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        cauHoiRepository = new CauHoiRepository();
        taiKhoanRepository = new TaiKhoanRepository();
        dscauhoi = cauHoiRepository.getListCauHoi();
        dstaikhoan = taiKhoanRepository.getListTaiKhoan();
        for (CauHoiModel cauhoi : dscauhoi) {
            String TenCH = cauhoi.getTenCH();
            cbxQuestion.getItems().add(TenCH);
            cbxQuestionForgot.getItems().add(TenCH);
        }
    }
}



