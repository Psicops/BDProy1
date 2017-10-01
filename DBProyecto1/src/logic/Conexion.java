package logic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import java.sql.Statement;

public class Conexion {
    
    static Connection contacto = null;
    public static String usser;
    public static String pass;
    public static boolean status = false;
    
    public static Connection getConexion(){
        status = false;
        String url = "jdbc:sqlserver://GAWLLET\\SQLEXPRESS:1433;databaseName=bdproy1";
        
        try{
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        }catch (ClassNotFoundException e){
            JOptionPane.showMessageDialog(null, "No se pudo establecer conección. Revisar Driver " + e.getMessage(),
            "Error de Conexion", JOptionPane.ERROR_MESSAGE);
        }
        try{
            contacto = DriverManager.getConnection(url, Conexion.usser, Conexion.pass);
            status = true;
        }catch (SQLException e){
            JOptionPane.showMessageDialog(null, e.getMessage(),
            "Error de Conexion", JOptionPane.ERROR_MESSAGE);
        }
        return contacto;
    }
    
    public static boolean getStatus(){
        return status;
    }
    
    public static void setCuenta(String usuario, String password){
        Conexion.usser = usuario;
        Conexion.pass = password;
    }
    
    public static ResultSet Consulta(String consulta){
        Connection con = getConexion();
        Statement declara;
        try{
            declara = con.createStatement();
            ResultSet respuesta = declara.executeQuery(consulta);
            return respuesta;
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Error " + e.getMessage(),
                "Error de Conexion", JOptionPane.ERROR_MESSAGE);
        }
        return null;
    }
    
}
