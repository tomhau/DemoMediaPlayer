package sample;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

/**
 *
 * @author tha
 */
public class DB {
    private static Connection con;
    private static PreparedStatement ps;
    private static ResultSet rs;
    private static String port;
    private static String databaseName;
    private static String userName;
    private static String password;

    public static final String NOMOREDATA ="|ND|";
    private static int numberOfColumns;
    private static int currentColumnNumber=1;

    /**
     * STATES
     */
    private static boolean moreData=false;  // from Resultset
    private static boolean pendingData=false; // from select statement
    private static boolean terminated = false;

    private DB(){
    }
    /**
     * Static initializer - no object construction
     */
    static {
        Properties props = new Properties();
        String fileName = "db.properties";
        InputStream input;
        try{
            input = new FileInputStream(fileName);
            props.load(input);
            port = props.getProperty("port","1433");
            databaseName = props.getProperty("databaseName");
            userName=props.getProperty("userName", "sa");
            password=props.getProperty("password");
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            System.out.println("Database Ready");

        }catch(Exception e){
            System.err.println(e.getMessage());
        }
    }
    private static void connect(){
        try {
            con = DriverManager.getConnection("jdbc:sqlserver://localhost:"+port+";databaseName="+databaseName,userName,password);
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

    }
    private static void disconnect(){
        try {
            con.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    /**
     *
     * @param sql the sql string to be executed in SQLServer
     */
    public static void selectSQL(String sql){
        if (terminated){
            System.exit(0);
        }
        try{
            if (ps!=null){
                ps.close();
            }
            if (rs!=null){
                rs.close();
            }
            connect();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            pendingData=true;
            moreData = rs.next();
            ResultSetMetaData rsmd = rs.getMetaData();
            numberOfColumns = rsmd.getColumnCount();
        }catch (Exception e){
            System.err.println("Error in the sql parameter, please test this in SQLServer first");
            System.err.println(e.getMessage());
        }
    }
    /**
     *
     * @return The next single value (formatted) from previous select
     */
    public static String getDisplayData(){
        if (terminated){
            System.exit(0);
        }
        if (!pendingData){
            terminated=true;
            throw new RuntimeException("ERROR! No previous select, communication with the database is lost!");
        }else if (!moreData){
            disconnect();
            pendingData=false;
            return NOMOREDATA;
        }else {
            return getNextValue(true);
        }
    }

    public static int getNumberOfColumns(){
        return numberOfColumns;
    }
    /**
     *
     * @return The next single value (without formatting) from previous select
     */
    public static String getData(){
        if (terminated){
            System.exit(0);
        }
        if (!pendingData){
            terminated=true;
            throw new RuntimeException("ERROR! No previous select, communication with the database is lost!");
        }else if (!moreData){
            disconnect();
            pendingData=false;
            return NOMOREDATA;
        }else {
            return getNextValue(false).trim();
        }
    }

    private static String getNextValue(boolean view){
        StringBuilder value= new StringBuilder();
        try{
            value.append(rs.getString(currentColumnNumber));
            if (currentColumnNumber>=numberOfColumns){
                currentColumnNumber=1;
                if (view){
                    value.append("\n");
                }
                moreData = rs.next();
            }else{
                if (view){
                    value.append(" ");
                }
                currentColumnNumber++;
            }
        }catch (SQLException e){
            System.err.println(e.getMessage());
        }
        return value.toString();
    }

    public static boolean insertSQL(String sql){
        return executeUpdate(sql);
    }

    public static boolean updateSQL(String sql){
        return executeUpdate(sql);
    }

    public static boolean deleteSQL(String sql){
        return executeUpdate(sql);
    }

    private static boolean executeUpdate(String sql){
        if (terminated){
            System.exit(0);
        }
        if (pendingData){
            terminated=true;
            throw new RuntimeException("ERROR! There were pending data from previous select, communication with the database is lost! ");
        }
        try{
            if (ps!=null){
                ps.close();
            }
            connect();
            ps = con.prepareStatement(sql);
            int rows = ps.executeUpdate();
            ps.close();
            if (rows>0){
                return true;
            }
        }catch (Exception e){
            System.err.println(e.getMessage());
        } finally{
            disconnect();
        }
        return false;
    }
}

