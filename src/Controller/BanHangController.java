package Controller;

import Model.CTHoaDonModel;
import Model.HoaDonModel;
import Model.SanPhamModel;
import Repository.SanPhamRepository;
import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class BanHangController implements Initializable {
    @FXML
    private JFXButton btnThemVaoGio;

    @FXML
    private GridPane grpMenu;

    @FXML
    private ImageView imgSanPham;

    @FXML
    private Label lblMaHD;

    @FXML
    private Label lblMaKH;

    @FXML
    private Label lblMaKM;

    @FXML
    private Label lblMaNV;

    @FXML
    private Label lblNgayLap;

    @FXML
    private Label lblTongTienSauKM;

    @FXML
    private Label lblTongTienTruocKM;

    @FXML
    private ScrollPane scpMenu;

    @FXML
    private Spinner<Integer> spnSoLuong;

    @FXML
    private TableView<CTHoaDonModel> tblGioHang;

    @FXML
    private TableColumn<CTHoaDonModel, Integer> tcDonGia;

    @FXML
    private TableColumn<CTHoaDonModel,String> tcMaSP;

    @FXML
    private TableColumn<CTHoaDonModel, Integer> tcSoLuong;

    @FXML
    private TableColumn<CTHoaDonModel, String> tcTenSP;

    @FXML
    private TableColumn<CTHoaDonModel, Integer> tcThanhTien;

    @FXML
    private TextField txtDonGia;

    @FXML
    private TextField txtMaSP;

    @FXML
    private TextField txtSoLuong;

    @FXML
    private TextField txtTenSP;

    @FXML
    private VBox vbSoLuongCanThem;

    @FXML
    private VBox vbThongTinSP;

    private ObservableList<SanPhamModel> listSanPham_Card; //Dùng cho gridpane ds sản phẩm
    private SanPhamRepository sanPhamRepository;
    private SanPham_CardController sanPham_cardController;
    private int SpinnerMaxValue = 0;
    private int SoLuongTonKho = 0;
    private ObservableList<CTHoaDonModel> tempList;
    //Các hàm khởi tạo và phương thức initialize
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        sanPhamRepository = new SanPhamRepository();
        sanPham_cardController = new SanPham_CardController();
        tempList = FXCollections.observableArrayList();
        listSanPham_Card = sanPhamRepository.getDataSanPham_Card();
        btnThemVaoGio.setDisable(true);
        //Nếu mã sp trống thì tắt button thêm vào giỏ
        txtSoLuong.setText("0");
        checkDisablebtnThemVaoGio();

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

    public void checkDisablebtnThemVaoGio() {
        if (txtMaSP.getText().isEmpty()) {
            btnThemVaoGio.setDisable(true);
        } else if (!txtMaSP.getText().isEmpty() && Integer.parseInt(txtSoLuong.getText()) <= 0) {
            btnThemVaoGio.setDisable(true);
        } else {
            btnThemVaoGio.setDisable(false);
        }
    }

    public void updateSpinnerMaxValue() {
        SoLuongTonKho= Integer.parseInt(txtSoLuong.getText());

        if (SoLuongTonKho != 0) {
            SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, SoLuongTonKho, 1);
            spnSoLuong.setValueFactory(valueFactory);
            spnSoLuong.setDisable(false);
        } else {
            SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 0, 1);
            spnSoLuong.setValueFactory(valueFactory);
            spnSoLuong.getEditor().setText("");
            spnSoLuong.setDisable(true);
        }


    }

    public void onClickListener(SanPhamModel sp) {
        setChosenSanPham(sp);

    }

    //Các hàm hỗ trợ
    public void setChosenSanPham(SanPhamModel sp) {
        txtSoLuong.textProperty().addListener((observableValue, s, t1) -> {
            updateSpinnerMaxValue();
            checkDisablebtnThemVaoGio();

        });
        txtMaSP.textProperty().addListener((observableValue, oldValue, newValue) -> {

            checkDisablebtnThemVaoGio();
        });
        txtMaSP.setText(sp.getMaSP());
        int SoLuong = sanPhamRepository.getSoLuongTonkho(sp.getMaSP());

        txtSoLuong.setText(String.valueOf(SoLuong));
        txtTenSP.setText(sp.getTenSP());
        txtDonGia.setText(String.valueOf(sp.getDonGia()));
        String path = "File:" + sp.getIMG();
        Image img = new Image(path);
        imgSanPham.setImage(img);




    }

    public void loadDataTotblGioHang(){

        tcMaSP.setCellValueFactory(new PropertyValueFactory<>("maSP"));
        tcTenSP.setCellValueFactory(new PropertyValueFactory<>("tenSP"));
        tcSoLuong.setCellValueFactory(new PropertyValueFactory<>("soLuong"));
        tcDonGia.setCellValueFactory(new PropertyValueFactory<>("donGia"));
        tcThanhTien.setCellValueFactory(new PropertyValueFactory<>("thanhTien"));

        tblGioHang.setItems(tempList);
    }
    @FXML
    void btnThemVaoGioMouseClicked(ActionEvent event) {
        String MaHD = lblMaHD.getText();
        String MaSP = txtMaSP.getText();
        String TenSP = txtTenSP.getText();
        int SoLuong = spnSoLuong.getValue();
        int DonGia = Integer.parseInt(txtDonGia.getText());
        int ThanhTien = SoLuong * DonGia;

        //Kiểm tra sản phẩm đã đang có trong list giỏ hàng chưa
        boolean flag = true;
        for(CTHoaDonModel cthd : tempList){

            if(cthd.getMaSP().equals(MaSP)){
                int SoLuongCu = cthd.getSoLuong();
                cthd.setSoLuong(SoLuongCu + SoLuong);

                cthd.setThanhTien(cthd.getSoLuong() * DonGia);
                flag = false;
                break;
            }
        }
        if(flag){
            tempList.add(new CTHoaDonModel(MaHD,MaSP,TenSP,SoLuong,DonGia,ThanhTien));


        }
        tblGioHang.refresh();
        loadDataTotblGioHang();
        int TongTien = 0;
        for(CTHoaDonModel cthd : tempList){
            TongTien = TongTien + cthd.getThanhTien();
            lblTongTienTruocKM.setText(String.valueOf(TongTien));
        }







    }

}
