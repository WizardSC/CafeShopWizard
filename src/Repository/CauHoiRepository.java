package Repository;

import Model.CauHoi;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CauHoiRepository {
    private MySQLConnect mySQLConnect = new MySQLConnect();
    private ArrayList<CauHoi> dscauhoi = new ArrayList<>();
    private Connection connection = mySQLConnect.getConnection();

    public ArrayList<CauHoi> getListCauHoi(){
        try{
            String sql = "select * from cauhoi";
            ResultSet rs = mySQLConnect.executeQuery(sql);
            while (rs.next()){
                CauHoi cauhoi = new CauHoi(
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
