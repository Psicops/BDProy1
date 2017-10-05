package logic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import java.sql.Statement;
import java.util.ArrayList;
import ui.UI;

public class Conexion {
    
    public static Connection ejecutar;
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
            JOptionPane.showMessageDialog(null, "No se pudo establecer conexión. Revisar Driver " + e.getMessage(),
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
    
    public static ResultSet ejecutaQuery(String op) throws SQLException{
          ejecutar = getConexion();
          PreparedStatement comando;
          comando = ejecutar.prepareStatement(op);
          ResultSet rs = comando.executeQuery();
          return rs;
    }

    public static void ejecutaInstruccion(String op) throws SQLException{
        ejecutar = getConexion();
        PreparedStatement comando;
        comando = ejecutar.prepareStatement(op);
        comando.execute();
    }
    
    public static ArrayList<ResultSet> getPermanentes(){
        ArrayList <ResultSet> rs = new ArrayList();
        return rs;
    }
    
    public static ArrayList<ResultSet> getTemporales(){
        ArrayList <ResultSet> rs = new ArrayList();
        return rs;
    }
    
}
