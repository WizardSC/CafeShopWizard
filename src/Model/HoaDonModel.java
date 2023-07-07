package Model;

import java.util.Date;

public class HoaDonModel {
    private String MaHD;
    private Date NgayLap;
    private int TongTienTruocKM;
    private float TongTienSauKM;
    private String MaNV;
    private String MaKH;
    private String MaKM;

    public HoaDonModel(String maHD, Date ngayLap, int tongTienTruocKM, float tongTienSauKM, String maNV, String maKH, String maKM) {
        MaHD = maHD;
        NgayLap = ngayLap;
        TongTienTruocKM = tongTienTruocKM;
        TongTienSauKM = tongTienSauKM;
        MaNV = maNV;
        MaKH = maKH;
        MaKM = maKM;
    }

    public float getTongTienSauKM() {
        return TongTienSauKM;
    }

    public void setTongTienSauKM(float tongTienSauKM) {
        TongTienSauKM = tongTienSauKM;
    }

    public String getMaHD() {
        return MaHD;
    }

    public void setMaHD(String maHD) {
        MaHD = maHD;
    }

    public Date getNgayLap() {
        return NgayLap;
    }

    public void setNgayLap(Date ngayLap) {
        NgayLap = ngayLap;
    }

    public int getTongTienTruocKM() {
        return TongTienTruocKM;
    }

    public void setTongTienTruocKM(int tongTienTruocKM) {
        TongTienTruocKM = tongTienTruocKM;
    }

    public String getMaNV() {
        return MaNV;
    }

    public void setMaNV(String maNV) {
        MaNV = maNV;
    }

    public String getMaKH() {
        return MaKH;
    }

    public void setMaKH(String maKH) {
        MaKH = maKH;
    }

    public String getMaKM() {
        return MaKM;
    }

    public void setMaKM(String maKM) {
        MaKM = maKM;
    }
}
