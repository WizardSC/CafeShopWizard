package Controller;

import Model.SanPhamModel;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import sample.Main;

import java.util.function.Consumer;

public class SanPham_CardController {
    @FXML
    private ImageView imgSanPham;

    @FXML
    private Label lblDonGia;

    @FXML
    private Label lblMaSP;

    @FXML
    private Label lblTenSP;

    private SanPhamModel sanpham;
    private Consumer<SanPhamModel> onClickListener;
    public void setOnClickListener(Consumer<SanPhamModel> onClickListener){
        this.onClickListener = onClickListener;
    }
    public void setData(SanPhamModel sanpham) {
        this.sanpham = sanpham;
        lblMaSP.setText(sanpham.getMaSP());
        lblTenSP.setText(sanpham.getTenSP());
        lblDonGia.setText(sanpham.getDonGia() + " " + Main.CURRENCY);
        String path = "File:" + sanpham.getIMG();
        Image image = new Image(path);
        imgSanPham.setImage(image);
    }


    @FXML
    void SPMouseClicked(MouseEvent event) {
         if(onClickListener != null){
             onClickListener.accept(sanpham);
         }
    }
}
