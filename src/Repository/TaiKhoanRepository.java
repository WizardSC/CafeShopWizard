package Repository;

import Model.TaiKhoan;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

public class TaiKhoanRepository {
    private MySQLConnect mssql = new MySQLConnect();
    private Connection connection = mssql.getConnection();
    private ArrayList<TaiKhoan> dstaikhoan = new ArrayList<>();


    public A
    public void insertTaiKhoan(TaiKhoan taikhoan) {

        try {
            String sql = "Insert into taikhoan values(?,?,?,?,?,?)";
            PreparedStatement ps = connection.prepareStatement(sql);
            java.sql.Date NgayTao = new Date(taikhoan.getNgayTao().getTime());
            ps.setString(1,taikhoan.getMaNV());
            ps.setString(2,taikhoan.getTenDangNhap());
            ps.setString(3, taikhoan.getMatKhau());
            ps.setString(4,taikhoan.getCauHoi());
            ps.setString(5,taikhoan.getCauTraLoi());
            ps.setDate(6, NgayTao);
            ps.executeUpdate();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }
}
