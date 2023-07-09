package Repository;

import Model.HoaDonModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.ZoneId;

public class HoaDonRepository {
    private MySQLConnect mssql = new MySQLConnect();
    private ObservableList<HoaDonModel> dsHoaDon = FXCollections.observableArrayList();

    private Connection connection = mssql.getConnection();

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
