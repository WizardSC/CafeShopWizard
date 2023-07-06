package Model;

import java.util.Date;

public class HoaDonModel {
    private String MaHD;
    private Date NgayLap;
    private int TongTien;
    private String MaNV;
    private String MaKH;
    private String MaKM;

    public HoaDonModel(String maHD, Date ngayLap, int tongTien, String maNV, String maKH, String maKM) {
        MaHD = maHD;
        NgayLap = ngayLap;
        TongTien = tongTien;
        MaNV = maNV;
        MaKH = maKH;
        MaKM = maKM;
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

    public int getTongTien() {
        return TongTien;
    }

    public void setTongTien(int tongTien) {
        TongTien = tongTien;
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
