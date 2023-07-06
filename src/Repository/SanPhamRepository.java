package Repository;

import Model.SanPhamModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SanPhamRepository {
    private MySQLConnect mySQLConnect = new MySQLConnect();
    private ObservableList<SanPhamModel> dsSanPham = FXCollections.observableArrayList();

    private Connection connection = mySQLConnect.getConnection();

    public ObservableList<SanPhamModel> getListSanPham(){
        try{
            String sql = "select * from sanpham";
            ResultSet rs = mySQLConnect.executeQuery(sql);
            while(rs.next()){
                SanPhamModel sanpham = new SanPhamModel(
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

    public ObservableList<SanPhamModel> getDataSanPham_Card(){
        try {
            String sql = "select * from sanpham";
            ResultSet rs = mySQLConnect.executeQuery(sql);
            while(rs.next()){
                SanPhamModel sp = new SanPhamModel(
                        rs.getString("MaSP"),
                        rs.getString("TenSP"),
                        rs.getInt("DonGia"),
                        rs.getString("IMG")
                );
                dsSanPham.add(sp);
            }
            return dsSanPham;
        } catch(Exception ex){
            ex.printStackTrace();
        } finally {
            mySQLConnect.Disconnect();
        }
        return null;
    }

    public Integer getSoLuongTonkho(String MaSP){
        try{
            String sql = "select soluong from sanpham where MaSP = '" + MaSP +"'" ;

            ResultSet rs = mySQLConnect.executeQuery(sql);
            if(rs.next()){
                int SL = rs.getInt("SoLuong");
                return SL;
            } else {
                return null;
            }
        } catch (Exception ex){
            ex.printStackTrace();
        } finally {
            mySQLConnect.Disconnect();
        }
        return null;
    }

    public void insertSanPham(SanPhamModel sp) throws SQLIntegrityConstraintViolationException {
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
            if(ex instanceof SQLIntegrityConstraintViolationException){
                throw (SQLIntegrityConstraintViolationException) ex;
            } else {
                Logger.getLogger(SanPhamRepository.class.getName()).log(Level.SEVERE, null, ex);

            }

        }
    }

    public void updateSanPham(SanPhamModel sp){
        try {
            String sql = "update sanpham set TenSP = ?, SoLuong = ?, DonGia = ?, MaLoai = ?, IMG = ? where MaSP = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1,sp.getTenSP());
            ps.setInt(2,sp.getSoLuong());
            ps.setInt(3,sp.getDonGia());
            ps.setString(4,sp.getMaLoai());
            ps.setString(5,sp.getIMG());
            ps.setString(6,sp.getMaSP());
            ps.executeUpdate();
        } catch(Exception e){
            e.printStackTrace();
        }

    }

    public void deleteSanPhan(String MaSP){
        try {
            String sql = "delete from sanpham where MaSP = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1,MaSP);
            ps.executeUpdate();
        } catch (Exception ex){
            ex.printStackTrace();
        }
    }
}
