package Controller;

import Model.SanPham;
import Repository.SanPhamRepository;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class BanHangController implements Initializable {
    @FXML
    private GridPane grpMenu;

    @FXML
    private ScrollPane scpMenu;

    @FXML
    private TableView<?> tblGioHang;

    @FXML
    private TableColumn<?, ?> tcDonGia;

    @FXML
    private TableColumn<?, ?> tcMaSP;

    @FXML
    private TableColumn<?, ?> tcSoLuong;

    @FXML
    private TableColumn<?, ?> tcTenSP;

    @FXML
    private TableColumn<?, ?> tcThanhTien;

    private ObservableList<SanPham> listSanPham_Card; //Dùng cho gridpane ds sản phẩm
    private SanPhamRepository sanPhamRepository;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        sanPhamRepository = new SanPhamRepository();
        listSanPham_Card = sanPhamRepository.getDataSanPham_Card();
//        listSanPham_Card.addAll(sanPhamRepository.getDataSanPham_Card());
        int column = 0;
        int row = 1;
        try {
            for (int i = 0; i < listSanPham_Card.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/View/SanPham_Card.fxml"));
                AnchorPane anchorPane =fxmlLoader.load();
                SanPham_CardController sanPham_cardController = fxmlLoader.getController();
                sanPham_cardController.setData(listSanPham_Card.get(i));
                if(column == 3){
                    column =0;
                    row++;
                }
                grpMenu.add(anchorPane,column++,row);
                //set grid width
                grpMenu.setMinWidth(Region.USE_COMPUTED_SIZE);
                grpMenu.setPrefWidth(Region.USE_COMPUTED_SIZE);
                grpMenu.setMaxWidth(Region.USE_COMPUTED_SIZE);
                //set grid weight
                grpMenu.setMinHeight(Region.USE_COMPUTED_SIZE);
                grpMenu.setPrefHeight(Region.USE_COMPUTED_SIZE);
                grpMenu.setMaxHeight(Region.USE_COMPUTED_SIZE);

                GridPane.setMargin(anchorPane,new Insets(-10,20,30,20)); //trên, phải, dưới, trái
            }
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}
