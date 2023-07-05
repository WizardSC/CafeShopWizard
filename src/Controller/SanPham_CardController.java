package Controller;

import Model.SanPham;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import sample.Main;

public class SanPham_CardController {
    @FXML
    private ImageView imgSanPham;

    @FXML
    private Label lblDonGia;

    @FXML
    private Label lblMaSP;

    @FXML
    private Label lblTenSP;

    private SanPham sanpham;

    public void setData(SanPham sanpham) {
        this.sanpham = sanpham;
        lblMaSP.setText(sanpham.getMaSP());
        lblTenSP.setText(sanpham.getTenSP());
        lblDonGia.setText(sanpham.getDonGia() + " " + Main.CURRENCY);
        String path = "File:" + sanpham.getIMG();
        Image image = new Image(path);
        imgSanPham.setImage(image);
    }
}
