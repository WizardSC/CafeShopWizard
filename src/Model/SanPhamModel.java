package Model;

public class SanPhamModel {
    private String MaSP;
    private String TenSP;
    private int SoLuong;
    private int DonGia;
    private String MaLoai;
    private String IMG;

    public SanPhamModel(String maSP, String tenSP, int soLuong, int donGia, String maLoai, String IMG) {
        MaSP = maSP;
        TenSP = tenSP;
        SoLuong = soLuong;
        DonGia = donGia;
        MaLoai = maLoai;
        this.IMG = IMG;
    }

    public SanPhamModel(String maSP, String tenSP, int donGia, String IMG) {
        MaSP = maSP;
        TenSP = tenSP;
        DonGia = donGia;
        this.IMG = IMG;
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

    public String getMaLoai() {
        return MaLoai;
    }

    public void setMaLoai(String maLoai) {
        MaLoai = maLoai;
    }

    public String getIMG() {
        return IMG;
    }

    public void setIMG(String IMG) {
        this.IMG = IMG;
    }
}
