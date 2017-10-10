package logic;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Conexion {
    
    public static Connection ejecutar;
    static Connection contacto = null;
    public static String usser;
    public static String pass;
    public static boolean status = false;
    
    private static final ArrayList<Tuple<String, String>> renames = new ArrayList();
    
    public static Connection getConexion()throws SQLException, ClassNotFoundException{
        status = false;
        String url = "jdbc:sqlserver://GAWLLET\\SQLEXPRESS:1433;databaseName=bdproy1";
        String urlAlejandro = "jdbc:sqlserver://LAPTOPALEJANDRO\\SQLEXPRESS01:1433;databaseName=bdproy1";
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        try{
            contacto = DriverManager.getConnection(url, Conexion.usser, Conexion.pass);
            status = true;
        }catch (SQLException e){
            contacto = DriverManager.getConnection(urlAlejandro, Conexion.usser, Conexion.pass);
            status = true;
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
    
    public static ResultSet Consulta(String consulta) throws SQLException, ClassNotFoundException{
        Connection con = getConexion();
        Statement declara;
        declara = con.createStatement();
        ResultSet respuesta = declara.executeQuery(consulta);
        return respuesta;
    }
    
    public static ResultSet ejecutaQuery(String op) throws SQLException, ClassNotFoundException{
          ejecutar = getConexion();
          PreparedStatement comando;
          comando = ejecutar.prepareStatement(op);
          ResultSet rs = comando.executeQuery();
          return rs;
    }

    public static void ejecutaInstruccion(String op) throws SQLException, ClassNotFoundException{
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
    /*
    public static int getAridadTabla(String nombreTabla){
        
    }
    
    public static ArrayList<String> getAtributosTabla(String nombreTabla){
        
    }
    */
    
    public static String getNombreTabla(String nombre) {
        for(Tuple<String, String> rename : renames){
            if(rename.x.equals(nombre))
                return rename.y;
            else if(rename.y.equals(nombre))
                return rename.x;
        }
        return nombre;
    }
    
    private static void print_state(){
        System.out.println("Estado actual de la lista de tuplas de renames:");
        for(Tuple<String, String> tupla : renames){
            System.out.println("\t"+tupla.x+" -> "+tupla.y);
        }
    }
    
    public static ArrayList<String> getNombresTablasTemporales() throws SQLException, ClassNotFoundException{
        ArrayList<String> nombres = new ArrayList();
        ResultSet rs = Conexion.ejecutaQuery("SELECT TABLE_NAME FROM tempdb.INFORMATION_SCHEMA.TABLES");
        String nombre;
        while(rs.next()){
            nombre = rs.getString(1);
            if(nombre.contains("##"))
                nombres.add(nombre);
        }
        return nombres;
    }
    
    public static ArrayList<String> getNombresTablasPermanentes() throws SQLException, ClassNotFoundException{
        ArrayList<String> nombres = new ArrayList();
        ResultSet rs = Conexion.ejecutaQuery("select name from sysobjects where type='U'"); 
        String nombre;
        while(rs.next()){
            nombre = rs.getString(1);
            nombres.add(nombre);
        }
        return nombres;
    }
    
    public static String getDiccionarioTabla(String nombre){
        String metadatosTabla = "";
        
        return metadatosTabla;
    }
    
    public static String getDiccionarioPermanentes() throws SQLException, ClassNotFoundException{
        String diccionario = "";
        for(String nombreTabla : Conexion.getNombresTablasPermanentes()){
            diccionario = getDiccionarioTabla(nombreTabla)+"\n";
        }
        return diccionario;
    }
    
    public static String getDiccionarioTemporales()throws SQLException, ClassNotFoundException{
        String diccionario = "";
        for(String nombreTabla : Conexion.getNombresTablasTemporales()){
            diccionario = getDiccionarioTabla(nombreTabla)+"\n";
        }
        return diccionario;
    }
    
    public static ArrayList<String> getNombresTablas() throws SQLException, ClassNotFoundException{
        ArrayList<String> nombres = getNombresTablasPermanentes();
        getNombresTablasPermanentes().addAll(getNombresTablasTemporales());
        return nombres;
    }
    
    private static boolean existeEnRenames(String nombre){
        for(Tuple<String, String> tupla : renames){
                if(tupla.y.equals(nombre)){
                    return true;
                }
        }
        return false;
    }
    
    public static void agregarRename(String nombre, String rename) throws IllegalArgumentException, SQLException, ClassNotFoundException{
        if(!getNombresTablas().contains(nombre))
            throw new IllegalArgumentException("El nombre de tabla \""+nombre+"\" no existe en el sistema.");
        else if(getNombresTablas().contains(rename))
            throw new IllegalArgumentException("El nombre de tabla \""+rename+"\" para renombrar a \""+nombre
                                           +"\" ya existe en el sistema de base de datos.");
        else if(existeEnRenames(nombre)){
            for(Tuple<String, String> tupla : renames){
                if(tupla.y.equals(nombre)){
                    tupla.y = rename;
                    return;
                }
            }
        }
        else
            renames.add(new Tuple(nombre, rename));
        print_state();
    }
    
    private static class Tuple<X, Y> { 
        public X x; 
        public Y y; 
        public Tuple(X x, Y y) { 
            this.x = x; 
            this.y = y; 
        } 
    } 
}
