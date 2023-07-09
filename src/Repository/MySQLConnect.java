package Repository;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MySQLConnect {
    private String user = "root";
    private String password = "";
    private String url = "jdbc:mysql://localhost:3306/cafeshopwizard?useUnicode=true&characterEncoding=UTF-8&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=Asia/Ho_Chi_Minh&zeroDateTimeBehavior=convertToNull&autoReconnect=true";
    private Connection connection = null;
    private Statement statement = null;
    private PreparedStatement pstatement = null;
    public void Connect() {
        try {
            connection = DriverManager.getConnection(url, user, password);
        } catch (SQLException ex) {
            Logger.getLogger(MySQLConnect.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void Disconnect() {
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException ex) {
                Logger.getLogger(MySQLConnect.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        if (connection != null){
            try {
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(MySQLConnect.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public ResultSet executeQuery(String sql){
        ResultSet rs = null;
        try {
            Connect();
            statement = connection.createStatement();
            rs = statement.executeQuery(sql);
        } catch (SQLException ex) {
            Logger.getLogger(MySQLConnect.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs;
    }
    public Connection getConnection(){
        Connect();
        return connection;
    }

}
