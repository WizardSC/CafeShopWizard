package Controller;

import Model.MenuItem;
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

import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
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
    private ArrayList<MenuItem> menuItems = new ArrayList<>();
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
        MenuItem banHangItem = new MenuItem(formBanHang,"../View/BanHang.fxml");
        MenuItem sanPhamItem = new MenuItem(formSanPham,"../View/SanPham.fxml");

        menuItems.add(banHangItem);
        menuItems.add(sanPhamItem);

        for(MenuItem item : menuItems){
            item.getHbox().setOnMouseClicked(mouseEvent -> handleMenuItemClick(item));
        }
        showDefaultFXMl();

    }

    private void handleMenuItemClick(MenuItem selectedItem){
        paneRoot.getChildren().clear();  // Xóa giao diện hiện tại
        try {
            // Tải giao diện từ file FXML tương ứng
            FXMLLoader loader = new FXMLLoader(getClass().getResource(selectedItem.getFxmlPath()));
            Parent root = loader.load();

            // Hiển thị giao diện trong paneRoot
            paneRoot.getChildren().add(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
        // Gán pseudo-class active cho HBox được chọn
        for(MenuItem item : menuItems){
            if(item == selectedItem){
                item.getHbox().getStyleClass().add("hbox-hover");
            } else {
                item.getHbox().getStyleClass().remove("hbox-hover");
            }
        }
    }

    // Hiển thị giao diện mặc định
    private void showDefaultFXMl(){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../View/TrangChu.fxml"));
            Parent root = loader.load();
            paneRoot.getChildren().add(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
