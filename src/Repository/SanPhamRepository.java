package Repository;

import Model.CauHoi;
import Model.SanPham;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SanPhamRepository {
    private MySQLConnect mySQLConnect = new MySQLConnect();
    private ObservableList<SanPham> dsSanPham = FXCollections.observableArrayList();

    private Connection connection = mySQLConnect.getConnection();

    public ObservableList<SanPham> getListSanPham(){
        try{
            String sql = "select * from sanpham";
            ResultSet rs = mySQLConnect.executeQuery(sql);
            while(rs.next()){
                SanPham sanpham = new SanPham(
                        rs.getString("MaSP"),
                        rs.getString("TenSP"),
                        rs.getInt("SoLuong"),
                        rs.getInt("DonGia"),
                        rs.getString("MaLoai"),
                        rs.getString("IMG")
                );
                dsSanPham.add(sanpham);
            }
            return dsSanPham;
        }catch (SQLException ex) {
            Logger.getLogger(SanPhamRepository.class.getName()).log(Level.SEVERE, null, ex);

        } finally {
            mySQLConnect.Disconnect();
        }
        return null;
    }
}
