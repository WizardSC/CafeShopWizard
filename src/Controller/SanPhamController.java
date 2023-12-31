package Controller;

import Model.SanPhamModel;
import Repository.SanPhamRepository;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;

import java.io.*;
import java.net.URL;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.*;


public class SanPhamController implements Initializable {
    @FXML
    private JFXButton btnImportImage;

    @FXML
    private JFXButton btnSua;

    @FXML
    private JFXButton btnThem;

    @FXML
    private HBox btnTimKiem;

    @FXML
    private JFXButton btnXoa;

    @FXML
    private JFXComboBox<Label> cbxTimKiem;

    @FXML
    private ImageView imgSanPham;

    @FXML
    private AnchorPane pnSanPham;


    @FXML
    private TableView<SanPhamModel> tblDSSP;

    @FXML
    private TableColumn<SanPhamModel, Integer> tcDonGia;

    @FXML
    private TableColumn<SanPhamModel, String> tcIMG;

    @FXML
    private TableColumn<SanPhamModel, String> tcMaLoai;

    @FXML
    private TableColumn<SanPhamModel, String> tcMaSP;

    @FXML
    private TableColumn<SanPhamModel, Integer> tcSoLuong;

    @FXML
    private TableColumn<SanPhamModel, String> tcTenSP;

    @FXML
    private TextField txtDonGia;

    @FXML
    private TextField txtMaLoai;

    @FXML
    private TextField txtMaSP;

    @FXML
    private TextField txtSoLuong;

    @FXML
    private TextField txtTenSP;

    @FXML
    private TextField txtTimKiem;

    @FXML
    private VBox vbThongTinSanPham;

    @FXML
    private ImageView btnReset;

    private ObservableList<SanPhamModel> dsSanPham;
    private ArrayList<SanPhamModel> dssp;
    private SanPhamRepository sanPhamRepository;
    private Image img;
    private String path;
    private String imgName;
    //Các hàm khởi tạo và phương thức initialize

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //Hành động
        txtTimKiem.setOnMouseClicked(mouseEvent -> {


            btnTimKiem.getStyleClass().remove("search-btn");
            btnTimKiem.getStyleClass().add("click-search-btn");
        });
        txtTimKiem.setOnMouseExited(mouseEvent -> {
            btnTimKiem.getStyleClass().add("search-btn");
            btnTimKiem.getStyleClass().remove("click-search-btn");
        });

        txtTenSP.textProperty().addListener((observableValue, s, t1) ->{
            if(t1.equals("")){
                btnImportImage.setDisable(true);
            } else {
                btnImportImage.setDisable(false);
            }
        });
        txtMaSP.setDisable(true);
        btnSua.setDisable(true);
        btnXoa.setDisable(true);




        addLabeltoComboBox();
        sanPhamRepository = new SanPhamRepository();
        dsSanPham = sanPhamRepository.getListSanPham();

        customizeRowHeight();

        loadDatatoDSSPTable();
        loadDataMaSP();
    }

    //Các hàm hỗ trợ
    public void refreshForm() {
        dsSanPham = sanPhamRepository.getListSanPham();
        clear();
        loadDatatoDSSPTable();
        loadDataMaSP();
    }
    public void clear(){
        tblDSSP.getSelectionModel().clearSelection();

        txtMaSP.setText("");
        txtTenSP.setText("");
        txtMaLoai.setText("");
        txtDonGia.setText("");
        txtSoLuong.setText("");
        imgSanPham.setImage(null);
        btnSua.setDisable(true);
        btnXoa.setDisable(true);
        btnThem.setDisable(false);
        loadDataMaSP();
    }
    //Thêm dữ liệu vào combobox
    public void addLabeltoComboBox() {
        cbxTimKiem.getItems().add(new Label("Mã SP"));
        cbxTimKiem.getItems().add(new Label("Tên SP"));
        cbxTimKiem.getItems().add(new Label("Mã loại"));
    }

    public void loadDatatoDSSPTable() {


        tcMaSP.setCellValueFactory(new PropertyValueFactory<>("maSP"));
        tcTenSP.setCellValueFactory(new PropertyValueFactory<>("tenSP"));
        tcSoLuong.setCellValueFactory(new PropertyValueFactory<>("soLuong"));
        tcDonGia.setCellValueFactory(new PropertyValueFactory<>("donGia"));
        tcMaLoai.setCellValueFactory(new PropertyValueFactory<>("maLoai"));
        tcIMG.setCellValueFactory(new PropertyValueFactory<>("IMG"));

        tblDSSP.setItems(dsSanPham);

    }

    private void customizeRowHeight() {
        tblDSSP.setRowFactory(tv -> new TableRow<SanPhamModel>() {
            private static final int ROW_HEIGHT = 30; // Chiều cao mong muốn cho hàng

            @Override
            protected void updateItem(SanPhamModel item, boolean empty) {
                super.updateItem(item, empty);
//                if (empty || item == null) {
//                    setStyle(""); // Đặt style cho hàng rỗng
//                    setPrefHeight(ROW_HEIGHT); // Đặt chiều cao cho hàng
//                } else {

                getStyleClass().add("high-price-row");

                setPrefHeight(ROW_HEIGHT); // Đặt chiều cao cho hàng
//                }
            }
        });
    }

    private void setRowClickEvent() {
        tblDSSP.setRowFactory(tv -> {
            TableRow<SanPhamModel> row = new TableRow<>();

            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 1 && !row.isEmpty()) {
                    // Xóa màu nền của các hàng khác
                    tblDSSP.getSelectionModel().clearSelection();

                    // Đặt màu nền cho hàng được nhấp chuột
                    row.setStyle("-fx-background-color: #FFF7D4;");
                    tblDSSP.getSelectionModel().select(row.getItem());
                }
            });

            return row;
        });
    }

    //Hàm load mã SP mới nhất lên form
    public String findMissingMaSP(ObservableList<SanPhamModel> dssp){
        Set<String> maSPSet = new HashSet<>();
        for(SanPhamModel sp : dssp){
            maSPSet.add(sp.getMaSP());
        }
        for(int i=1 ; i <= dssp.size() + 1; i++){
            String MaSP = String.format("SP%03d",i);
            if(!maSPSet.contains(MaSP)){
                return MaSP;
            }
        }
        return null;
    }



    public void loadDataMaSP(){
        String missingMaSP = findMissingMaSP(dsSanPham);
        if (missingMaSP != null) {
            txtMaSP.setText(missingMaSP);
        } else {
            if(dsSanPham.isEmpty()){
                txtMaSP.setText("SP001");
            } else {
                SanPhamModel lastSP = dsSanPham.get(dsSanPham.size()-1);
                String MaSP = lastSP.getMaSP();
                int sum = Integer.parseInt(MaSP.substring(3)) +1;
                String newMaSP = String.format("SP%03d",sum);
                txtMaSP.setText(newMaSP);
            }
        }

    }

    //Các hàm xử lý sự kiện
    @FXML
    void cbxTimKiemMouseClicked(ActionEvent event) {
        Label selectedLabel = cbxTimKiem.getSelectionModel().getSelectedItem();
        String TuKhoa = selectedLabel.getText();
        System.out.println(TuKhoa);
    }


    @FXML
    void btnImportImageMouseClicked(ActionEvent event) {

        FileChooser fileChooser = new FileChooser();
        File initialDirectory = new File("./src/img/SanPhamModel");
        fileChooser.setInitialDirectory(initialDirectory);
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("File hình ảnh", "*.png", "*.jpg"));
        File file = fileChooser.showOpenDialog(pnSanPham.getScene().getWindow());
        if (file != null) {
            path = file.getAbsolutePath();
            img = new Image(file.toURI().toString(), 145, 150, false, true);
            imgSanPham.setImage(img);
        }
    }

    @FXML
    void btnSuaMouseClicked(ActionEvent event) {
        String MaSP = txtMaSP.getText();
        String TenSP = txtTenSP.getText();
        int SoLuong = Integer.parseInt(txtSoLuong.getText());
        int DonGia = Integer.parseInt(txtDonGia.getText());
        String MaLoai = txtMaLoai.getText();
        String IMG;
        if(path == null){
            IMG = imgName;
        } else {
            imgName = path.replace("//","////");
            IMG = imgName;
        }
        SanPhamModel sp = new SanPhamModel(MaSP, TenSP, SoLuong, DonGia, MaLoai, IMG);
        sanPhamRepository.updateSanPham(sp);
        tblDSSP.getItems().clear();
        refreshForm();
    }

    @FXML
    void btnThemMouseClicked(ActionEvent event) {


        if (txtMaSP.getText().equals("") || txtTenSP.getText().equals("") || txtMaLoai.getText().equals("") || txtSoLuong.getText().equals("") || txtDonGia.getText().equals("")) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Lỗi");
            alert.setHeaderText(null);
            alert.setContentText("Vui lòng điền đầy đủ thông tin sản phẩm");
            alert.showAndWait();
            return;
        }
        String MaSP = txtMaSP.getText();
        String TenSP = txtTenSP.getText();
        int SoLuong = Integer.parseInt(txtSoLuong.getText());
        int DonGia = Integer.parseInt(txtDonGia.getText());
        String MaLoai = txtMaLoai.getText();
        imgName = path.replace("\\","\\\\");
        String IMG = imgName;
        SanPhamModel sp = new SanPhamModel(MaSP, TenSP, SoLuong, DonGia, MaLoai, IMG);
        try {
            sanPhamRepository.insertSanPham(sp);
            tblDSSP.getItems().clear(); //Xóa dữ liệu hiện tại trong tableview
            refreshForm();

        } catch (SQLIntegrityConstraintViolationException ex) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Lỗi");
            alert.setHeaderText(null);
            alert.setContentText("Trùng mã sản phẩm. Hãy thử lại với mã sản phẩm khác");
            alert.showAndWait();
        }



    }

    @FXML
    void btnXoaMouseClicked(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Xác nhận");
        alert.setHeaderText(null);
        alert.setContentText("Bạn thực sự muốn xóa sản phẩm");
        Optional<ButtonType> result = alert.showAndWait();
        if(result.get() == ButtonType.OK){
            String MaSP = txtMaSP.getText();
            sanPhamRepository.deleteSanPhan(MaSP);
            tblDSSP.getItems().clear();
            refreshForm();

            Alert thanhcong = new Alert(Alert.AlertType.INFORMATION);
            thanhcong.setTitle("Thông báo");
            thanhcong.setContentText("Xóa sản phẩm thành công!");
            thanhcong.setHeaderText(null);
            thanhcong.showAndWait();
            return;
        }
    }
//    @FXML
//    void btnXoaClicked(ActionEvent event) {
//        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
//        alert.setTitle("Xác nhận");
//        alert.setHeaderText("Bạn có muốn xóa không?");
//        ButtonType Yes = new ButtonType("Có", ButtonBar.ButtonData.YES);
//        ButtonType No = new ButtonType("Không",ButtonBar.ButtonData.NO);
//        alert.getButtonTypes().setAll(Yes,No);
//        Optional<ButtonType> result = alert.showAndWait();
//        if(result.get() == Yes){
//            Alert thanhcong = new Alert(Alert.AlertType.INFORMATION);
//            thanhcong.setTitle("Thông báo");
//            thanhcong.setContentText("Bạn đã xóa thành công");
//            thanhcong.setHeaderText(null);
//            thanhcong.showAndWait();
//        } else if (result.get()==No){
//            System.out.println("Khong");
//        }
//
//    }

    @FXML
    void tblDSSPMouseClicked() {
        btnThem.setDisable(true);
        btnSua.setDisable(false);
        btnXoa.setDisable(false);

        SanPhamModel sp = tblDSSP.getSelectionModel().getSelectedItem();
        int k = tblDSSP.getSelectionModel().getSelectedIndex();
        if((k-1) < -1) return;
        txtMaSP.setText(sp.getMaSP());
        txtTenSP.setText(sp.getTenSP());
        txtSoLuong.setText(String.valueOf(sp.getSoLuong()));
        txtDonGia.setText(String.valueOf(sp.getDonGia()));
        txtMaLoai.setText(sp.getMaLoai());
        imgName = sp.getIMG();

        Image image1 = new Image( "File:"+imgName, 145, 150, false, true);
        imgSanPham.setImage(image1);
    }

    @FXML
    void btnResetMouseClicked() {
        clear();
    }
}
