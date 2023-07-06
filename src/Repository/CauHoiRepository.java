package Repository;

import Model.CauHoiModel;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CauHoiRepository {
    private MySQLConnect mySQLConnect = new MySQLConnect();
    private ArrayList<CauHoiModel> dscauhoi = new ArrayList<>();
    private Connection connection = mySQLConnect.getConnection();

    public ArrayList<CauHoiModel> getListCauHoi(){
        try{
            String sql = "select * from cauhoi";
            ResultSet rs = mySQLConnect.executeQuery(sql);
            while (rs.next()){
                CauHoiModel cauhoi = new CauHoiModel(
                        rs.getString("MaCH"),
                        rs.getString("TenCH")
                );
                dscauhoi.add(cauhoi);
            }
            return dscauhoi;
        } catch (SQLException ex) {
            Logger.getLogger(CauHoiRepository.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            mySQLConnect.Disconnect();
        }
        return null;

    }
}
