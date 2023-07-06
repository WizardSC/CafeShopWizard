package Repository;

import Model.TaiKhoanModel;

import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TaiKhoanRepository {
    private MySQLConnect mssql = new MySQLConnect();
    private Connection connection = mssql.getConnection();
    private ArrayList<TaiKhoanModel> dstaikhoan = new ArrayList<>();


    public ArrayList<TaiKhoanModel> getListTaiKhoan() {
        try {
            String sql = "select * from taikhoan";
            ResultSet rs = mssql.executeQuery(sql);
            while (rs.next()) {
                TaiKhoanModel taikhoan = new TaiKhoanModel(
                        rs.getString("MaNV"),
                        rs.getString("TenDangNhap"),
                        rs.getString("MatKhau"),
                        rs.getString("CauHoiModel"),
                        rs.getString("CauTraLoi"),
                        rs.getDate("NgayTao"));
                dstaikhoan.add(taikhoan);
            }
            return dstaikhoan;
        } catch (SQLException ex) {
            Logger.getLogger(TaiKhoanRepository.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            mssql.Disconnect();
        }
        return null;

    }

    public void insertTaiKhoan(TaiKhoanModel taikhoan) {

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

    public boolean Login(String TenDangNhap, String MatKhau) {
        try {
            String sql = "select * from taikhoan where TenDangNhap = '" + TenDangNhap + "' and MatKhau = '" + MatKhau + "'";
            ResultSet rs = mssql.executeQuery(sql);
            if (rs.next()) {
                return true;
            } else {
                return false;
            }

        } catch (SQLException ex) {
            Logger.getLogger(TaiKhoanRepository.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            mssql.Disconnect();
        }
        return false;
    }

    public void updateMatKhau(String TenDangNhap, String MatKhau) {
        try {
            String sql = "update taikhoan set MatKhau = ? where TenDangNhap = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, MatKhau);
            ps.setString(2, TenDangNhap);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(TaiKhoanRepository.class.getName()).log(Level.SEVERE, null, ex);


        }
    }
}
