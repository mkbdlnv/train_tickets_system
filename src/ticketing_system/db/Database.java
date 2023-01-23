package ticketing_system.db;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import org.postgresql.core.SqlCommand;

import java.sql.ResultSet;

public class Database {
    private final String connectionUrl = "jdbc:postgresql://localhost:5432/train_ticketing_system";
    private Connection con = null;
    private ResultSet rs = null;
    private Statement stmt = null;
    public Database(){
        try{
            Class.forName("org.postgresql.Driver");
            con = DriverManager.getConnection(connectionUrl, "user", "0000");
            stmt = con.createStatement();
            rs = stmt.executeQuery("select * from trains");
        } catch(Exception e){
            System.out.println(e);
        }
        finally{
            System.out.println("Database successfully connected!");
        }
        
        
        
    }
    public ResultSet getData(String table){
        try{
            Class.forName("org.postgresql.Driver");
            con = DriverManager.getConnection(connectionUrl, "user", "0000");
            stmt = con.createStatement();
            rs = stmt.executeQuery("select * from "+table);
        } catch(Exception e){
            System.out.println(e);
        }
        return rs;
    }

    public void close(){
        try{
            rs.close();
            stmt.clearBatch();
            con.close();
        } catch (SQLException throwables){
            throwables.printStackTrace();
        }

    }
    
}
