package Repository;

import Model.CauHoi;
import Model.SanPham;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
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

    public void insertSanPham(SanPham sp){
        try {
            String sql = "insert into sanpham values(?,?,?,?,?,?)";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1,sp.getMaSP());
            ps.setString(2,sp.getTenSP());
            ps.setInt(3,sp.getSoLuong());
            ps.setInt(4,sp.getDonGia());
            ps.setString(5,sp.getMaLoai());
            ps.setString(6,sp.getIMG());
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(SanPhamRepository.class.getName()).log(Level.SEVERE, null, ex);

        }
    }
}
