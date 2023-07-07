package Model;

public class CTHoaDonModel {
    private String MaHD;
    private String MaSP;
    private String TenSP;
    private int SoLuong;
    private int DonGia;
    private int ThanhTien;

    public CTHoaDonModel(String maHD, String maSP, String tenSP, int soLuong, int donGia, int thanhTien) {
        MaHD = maHD;
        MaSP = maSP;
        TenSP = tenSP;
        SoLuong = soLuong;
        DonGia = donGia;
        ThanhTien = thanhTien;
    }

    public String getMaHD() {
        return MaHD;
    }

    public void setMaHD(String maHD) {
        MaHD = maHD;
    }

    public String getMaSP() {
        return MaSP;
    }

    public void setMaSP(String maSP) {
        MaSP = maSP;
    }

    public String getTenSP() {
        return TenSP;
    }

    public void setTenSP(String tenSP) {
        TenSP = tenSP;
    }

    public int getSoLuong() {
        return SoLuong;
    }

    public void setSoLuong(int soLuong) {
        SoLuong = soLuong;
    }

    public int getDonGia() {
        return DonGia;
    }

    public void setDonGia(int donGia) {
        DonGia = donGia;
    }

    public int getThanhTien() {
        return ThanhTien;
    }

    public void setThanhTien(int thanhTien) {
        ThanhTien = thanhTien;
    }
}
