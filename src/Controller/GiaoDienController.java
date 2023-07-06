package Controller;

import Model.MenuItemModel;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class GiaoDienController implements Initializable {
    @FXML
    private HBox hbTool;
    @FXML
    private HBox btnExit;
    @FXML
    private HBox formBanHang;
    @FXML
    private HBox formSanPham;
    @FXML
    private HBox formTrangChu;

    @FXML
    private VBox paneMenu;

    @FXML
    private AnchorPane paneRoot;

    private boolean isFormBanHangHovered = false;
    private ArrayList<MenuItemModel> menuItemModels = new ArrayList<>();



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        MenuItemModel trangChuItem = new MenuItemModel(formTrangChu,"../View/TrangChu.fxml");
        MenuItemModel banHangItem = new MenuItemModel(formBanHang,"../View/BanHang.fxml");
        MenuItemModel sanPhamItem = new MenuItemModel(formSanPham,"../View/SanPham.fxml");
        menuItemModels.add(trangChuItem);
        menuItemModels.add(banHangItem);
        menuItemModels.add(sanPhamItem);

        for(MenuItemModel item : menuItemModels){
            item.getHbox().setOnMouseClicked(mouseEvent -> handleMenuItemClick(item));
        }
        showDefaultFXMl();

    }

    private void handleMenuItemClick(MenuItemModel selectedItem){
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
        for(MenuItemModel item : menuItemModels){
            if(item == selectedItem){
                item.getHbox().getStyleClass().add("hbox-hover");
            } else {
                item.getHbox().getStyleClass().remove("hbox-hover");
            }
        }
    }

    // Hiển thị giao diện mặc định
    private void showDefaultFXMl(){
        MenuItemModel defaultItem = menuItemModels.get(1);
        handleMenuItemClick(defaultItem);
    }

    @FXML
    void btnExitMouseClicked() {
        Platform.exit();
    }

}
