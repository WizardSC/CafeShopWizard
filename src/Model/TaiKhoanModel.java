package Model;

import java.util.Date;

public class TaiKhoanModel {
    private String MaNV;
    private String TenDangNhap;
    private String MatKhau;
    private String CauHoi;
    private String CauTraLoi;
    private Date NgayTao;

    public TaiKhoanModel(String maNV, String tenDangNhap, String matKhau, String cauHoi, String cauTraLoi, Date ngayTao) {
        MaNV = maNV;
        TenDangNhap = tenDangNhap;
        MatKhau = matKhau;
        CauHoi = cauHoi;
        CauTraLoi = cauTraLoi;
        NgayTao = ngayTao;
    }

    public String getMaNV() {
        return MaNV;
    }

    public void setMaNV(String maNV) {
        MaNV = maNV;
    }

    public String getTenDangNhap() {
        return TenDangNhap;
    }

    public void setTenDangNhap(String tenDangNhap) {
        TenDangNhap = tenDangNhap;
    }

    public String getMatKhau() {
        return MatKhau;
    }

    public void setMatKhau(String matKhau) {
        MatKhau = matKhau;
    }

    public String getCauHoi() {
        return CauHoi;
    }

    public void setCauHoi(String cauHoi) {
        CauHoi = cauHoi;
    }

    public String getCauTraLoi() {
        return CauTraLoi;
    }

    public void setCauTraLoi(String cauTraLoi) {
        CauTraLoi = cauTraLoi;
    }

    public Date getNgayTao() {
        return NgayTao;
    }

    public void setNgayTao(Date ngayTao) {
        NgayTao = ngayTao;
    }
}
