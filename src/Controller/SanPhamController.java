package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;

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
    void btnThemMouseClicked(ActionEvent event) {


    }




    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        txtTimKiem.setOnMouseClicked(mouseEvent -> {

            System.out.println("Xin chào");
            btnTimKiem.getStyleClass().remove("search-btn");
            btnTimKiem.getStyleClass().add("click-search-btn");
        });
        txtTimKiem.setOnMouseExited(mouseEvent -> {
            btnTimKiem.getStyleClass().add("search-btn");
            btnTimKiem.getStyleClass().remove("click-search-btn");
        });
    }
}
