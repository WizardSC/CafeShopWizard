package Controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GiaoDienController implements Initializable {

    @FXML
    private HBox formBanHang;
    @FXML
    private HBox formSanPham;
    @FXML
    private VBox paneMenu;

    @FXML
    private AnchorPane paneRoot;

    private boolean isFormBanHangHovered = false;
    @FXML
    void formBanHangMouseClicked(MouseEvent event) {

        try {
            paneRoot.getChildren().clear();
            FXMLLoader newLoader = new FXMLLoader(getClass().getResource("../View/BanHang.fxml"));
            Parent newRoot = null;
            newRoot = newLoader.load();
            paneRoot.getChildren().add(newRoot);

            if (isFormBanHangHovered) {
                formBanHang.getStyleClass().add("hbox-hover");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../View/TrangChu.fxml"));
            Parent root = loader.load();
            paneRoot.getChildren().add(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
        formBanHang.setOnMouseEntered(event -> {
            isFormBanHangHovered = true;
            formBanHang.getStyleClass().add("hbox-hover");
        });

        formBanHang.setOnMouseExited(event -> {
            isFormBanHangHovered = false;
            formBanHang.getStyleClass().remove("hbox-hover");
        });

    }
}
