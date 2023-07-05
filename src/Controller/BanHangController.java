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
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class BanHangController implements Initializable {
    @FXML
    private GridPane grpMenu;

    @FXML
    private ImageView imgSanPham;

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

    @FXML
    private TextField txtDonGia;

    @FXML
    private TextField txtMaSP;

    @FXML
    private TextField txtSoLuong;

    @FXML
    private TextField txtTenSP;

    @FXML
    private VBox vbThongTinSP;

    private ObservableList<SanPham> listSanPham_Card; //Dùng cho gridpane ds sản phẩm
    private SanPhamRepository sanPhamRepository;
    private SanPham_CardController sanPham_cardController;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        sanPhamRepository = new SanPhamRepository();
        sanPham_cardController  = new SanPham_CardController();

        listSanPham_Card = sanPhamRepository.getDataSanPham_Card();
        int column = 0;
        int row = 1;
        try {
            for (int i = 0; i < listSanPham_Card.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/View/SanPham_Card.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();
                SanPham_CardController sanPham_cardController = fxmlLoader.getController();
                sanPham_cardController.setData(listSanPham_Card.get(i));
                sanPham_cardController.setOnClickListener(sp -> setChosenSanPham(sp));

                if (column == 3) {
                    column = 0;
                    row++;
                }
                grpMenu.add(anchorPane, column++, row);
                //set grid width
                grpMenu.setMinWidth(Region.USE_COMPUTED_SIZE);
                grpMenu.setPrefWidth(Region.USE_COMPUTED_SIZE);
                grpMenu.setMaxWidth(Region.USE_COMPUTED_SIZE);
                //set grid weight
                grpMenu.setMinHeight(Region.USE_COMPUTED_SIZE);
                grpMenu.setPrefHeight(Region.USE_COMPUTED_SIZE);
                grpMenu.setMaxHeight(Region.USE_COMPUTED_SIZE);

                GridPane.setMargin(anchorPane, new Insets(-10, 20, 30, 20)); //trên, phải, dưới, trái
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void onClickListener(SanPham sp) {
        setChosenSanPham(sp);
    }

    public void setChosenSanPham(SanPham sp) {
        txtMaSP.setText(sp.getMaSP());
        int SoLuong = sanPhamRepository.getSoLuongTonkho(sp.getMaSP());
        txtSoLuong.setText(String.valueOf(SoLuong));
        txtTenSP.setText(sp.getTenSP());
        txtDonGia.setText(String.valueOf(sp.getDonGia()));
        String path = "File:" + sp.getIMG();
        Image img = new Image(path);
        imgSanPham.setImage(img);

    }


}
