package Model;

public class CauHoi {
    private String MaCH;
    private String TenCH;
    private String CauTL;

    public CauHoi(String maCH, String tenCH, String cauTL) {
        MaCH = maCH;
        TenCH = tenCH;
        CauTL = cauTL;
    }

    public String getMaCH() {
        return MaCH;
    }

    public void setMaCH(String maCH) {
        MaCH = maCH;
    }

    public String getTenCH() {
        return TenCH;
    }

    public void setTenCH(String tenCH) {
        TenCH = tenCH;
    }

    public String getCauTL() {
        return CauTL;
    }

    public void setCauTL(String cauTL) {
        CauTL = cauTL;
    }
}
