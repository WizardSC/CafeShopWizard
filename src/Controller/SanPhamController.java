package Controller;

import Model.SanPham;
import Repository.SanPhamRepository;
import com.jfoenix.controls.JFXComboBox;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;

import java.awt.event.MouseEvent;
import java.io.File;
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
    private VBox vbThongTinSanPham;

    @FXML
    private JFXComboBox<Label> cbxTimKiem;

    @FXML
    private TableView<SanPham> tblDSSP;

    @FXML
    private TableColumn<SanPham, Integer> tcDonGia;

    @FXML
    private TableColumn<SanPham, String> tcIMG;

    @FXML
    private TableColumn<SanPham, String> tcMaLoai;

    @FXML
    private TableColumn<SanPham, String> tcMaSP;

    @FXML
    private TableColumn<SanPham, Integer> tcSoLuong;

    @FXML
    private TableColumn<SanPham, String> tcTenSP;

    private ObservableList<SanPham> dsSanPham;
    private SanPhamRepository sanPhamRepository;

    //Các hàm khởi tạo và phương thức initialize

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        txtTimKiem.setOnMouseClicked(mouseEvent -> {


            btnTimKiem.getStyleClass().remove("search-btn");
            btnTimKiem.getStyleClass().add("click-search-btn");
        });
        txtTimKiem.setOnMouseExited(mouseEvent -> {
            btnTimKiem.getStyleClass().add("search-btn");
            btnTimKiem.getStyleClass().remove("click-search-btn");
        });


        addLabeltoComboBox();
        sanPhamRepository = new SanPhamRepository();
        dsSanPham = sanPhamRepository.getListSanPham();
        customizeRowHeight();

        loadDatatoDSSPTable();
    }
    //Các hàm hỗ trợ
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
        tblDSSP.setRowFactory(tv -> new TableRow<SanPham>() {
            private static final int ROW_HEIGHT = 30; // Chiều cao mong muốn cho hàng

            @Override
            protected void updateItem(SanPham item, boolean empty) {
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
            TableRow<SanPham> row = new TableRow<>();

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

    public void importImage(){
        FileChooser openFileChooser = new FileChooser();
        openFileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("File hình ảnh", "*png", "*jpg"));
        File file = openFileChooser.showOpenDialog(pnSanPham.getScene().getWindow());
        if(file != null){

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
    void btnThemMouseClicked(ActionEvent event) {


    }

}
