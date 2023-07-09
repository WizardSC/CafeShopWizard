package Repository;

import Model.CTHoaDonModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CTHoaDonRepository {
    private MySQLConnect mssql = new MySQLConnect();
    private ObservableList<CTHoaDonModel> dsCTHD = FXCollections.observableArrayList();
    private Connection connection = mssql.getConnection();

    public void insertCTHD(CTHoaDonModel cthd){
        try {
            String sql = "Insert into cthoadon values(?,?,?,?,?,?)";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, cthd.getMaHD());
            ps.setString(2,cthd.getMaSP());
            ps.setString(3,cthd.getTenSP());
            ps.setInt(4,cthd.getSoLuong());
            ps.setInt(5,cthd.getDonGia());
            ps.setInt(6,cthd.getThanhTien());
            ps.executeUpdate();
        } catch (SQLException e){
            e.printStackTrace();
        }
    }
}
