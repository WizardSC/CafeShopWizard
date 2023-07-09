package Repository;

import Model.HoaDonModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.time.ZoneId;

public class HoaDonRepository {
    private MySQLConnect mssql = new MySQLConnect();
    private ObservableList<HoaDonModel> dsHoaDon = FXCollections.observableArrayList();

    private Connection connection = mssql.getConnection();

    public ObservableList<HoaDonModel> getListHoaDon(){
        try {
            ObservableList<HoaDonModel> dsHoaDon = FXCollections.observableArrayList();
            String sql = "select * from hoadon";
            ResultSet rs = mssql.executeQuery(sql);
            while(rs.next()){
                HoaDonModel hd = new HoaDonModel(
                       rs.getString("MaHD"),
                        rs.getDate("NgayLap"),
                        rs.getInt("TongTienTruocKM"),
                        rs.getInt("TongTienSauKM"),
                        rs.getString("MaNV"),
                        rs.getString("MaKH"),
                        rs.getString("MaKM")
                );
                dsHoaDon.add(hd);
            }
            return dsHoaDon;
        } catch (SQLException e){
            e.printStackTrace();
        } finally {
            mssql.Disconnect();
        }
        return null;
    }
    public void insertHoaDon(HoaDonModel hd){
        try {
            String sql = "Insert into hoadon values(?,?,?,?,?,?,?)";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1,hd.getMaHD());
            ps.setDate(2, new java.sql.Date(hd.getNgayLap().getTime()));
            ps.setInt(3,hd.getTongTienTruocKM());
            ps.setInt(4,hd.getTongTienSauKM());
            ps.setString(5,hd.getMaNV());
            ps.setString(6,hd.getMaKH());
            ps.setString(7,hd.getMaKM());
            ps.executeUpdate();
        } catch (SQLException e){
            e.printStackTrace();
        }
    }


}
