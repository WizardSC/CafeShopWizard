package Model;

public class CauHoiModel {
    private String MaCH;
    private String TenCH;


    public CauHoiModel(String maCH, String tenCH) {
        MaCH = maCH;
        TenCH = tenCH;

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

}
