package Controller;

import com.jfoenix.controls.JFXComboBox;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.awt.event.MouseEvent;
import java.net.URL;
import java.util.ResourceBundle;

public class SanPhamController implements Initializable {
    @FXML
    private Button btnThem;

    @FXML
    private TextField txtTimKiem;

    @FXML
    private HBox btnTimKiem;

    @FXML
    private AnchorPane pnSanPham;

    @FXML
    private VBox vbThongTinSanPham;

    @FXML
    private JFXComboBox<Label> cbxTimKiem;

    @FXML
    void btnThemMouseClicked(ActionEvent event) {


    }

    @FXML
    void cbxTimKiemMouseClicked(ActionEvent event) {
        Label selectedLabel = cbxTimKiem.getSelectionModel().getSelectedItem();
        String TuKhoa = selectedLabel.getText();
        System.out.println(TuKhoa);
    }

    //Thêm dữ liệu vào combobox
    public void addLabeltoComboBox(){
        cbxTimKiem.getItems().add(new Label("Mã SP"));
        cbxTimKiem.getItems().add(new Label("Tên SP"));
        cbxTimKiem.getItems().add(new Label("Mã loại"));
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        txtTimKiem.setOnMouseClicked(mouseEvent -> {


            btnTimKiem.getStyleClass().remove("search-btn");
            btnTimKiem.getStyleClass().add("click-search-btn");
        });
        txtTimKiem.setOnMouseExited(mouseEvent -> {
            btnTimKiem.getStyleClass().add("search-btn");
            btnTimKiem.getStyleClass().remove("click-search-btn");
        });

        addLabeltoComboBox();

    }
}
