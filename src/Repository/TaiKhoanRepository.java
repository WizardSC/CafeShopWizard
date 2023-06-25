package Repository;

import Model.TaiKhoan;

import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TaiKhoanRepository {
    private MySQLConnect mssql = new MySQLConnect();
    private Connection connection = mssql.getConnection();
    private ArrayList<TaiKhoan> dstaikhoan = new ArrayList<>();


    public ArrayList<TaiKhoan> getListTaiKhoan() {
        try {
            String sql = "select * from taikhoan";
            ResultSet rs = mssql.executeQuery(sql);
            while (rs.next()) {
                TaiKhoan taikhoan = new TaiKhoan(
                        rs.getString("MaNV"),
                        rs.getString("TenDangNhap"),
                        rs.getString("MatKhau"),
                        rs.getString("CauHoi"),
                        rs.getString("CauTraLoi"),
                        rs.getDate("NgayTao"));
                dstaikhoan.add(taikhoan);
            }
            return dstaikhoan;
        }  catch (SQLException ex) {
            Logger.getLogger(TaiKhoanRepository.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            mssql.Disconnect();
        }
        return null;

    }

    public void insertTaiKhoan(TaiKhoan taikhoan) {

        try {
            String sql = "Insert into taikhoan values(?,?,?,?,?,?)";
            PreparedStatement ps = connection.prepareStatement(sql);
            java.sql.Date NgayTao = new Date(taikhoan.getNgayTao().getTime());
            ps.setString(1, taikhoan.getMaNV());
            ps.setString(2, taikhoan.getTenDangNhap());
            ps.setString(3, taikhoan.getMatKhau());
            ps.setString(4, taikhoan.getCauHoi());
            ps.setString(5, taikhoan.getCauTraLoi());
            ps.setDate(6, NgayTao);
            ps.executeUpdate();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }
}
