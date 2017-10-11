package logic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

public class Conexion {
    
    public static Connection ejecutar;
    static Connection contacto = null;
    public static String usser;
    public static String pass;
    public static boolean status = false;
    
    private static ArrayList<Rename> renames = new ArrayList();
    
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
    
    public static ArrayList<String> getAtributos(String nombreTabla) throws SQLException, ClassNotFoundException{
        ResultSetMetaData metadatos = Conexion.ejecutaQuery("select * from "+nombreTabla).getMetaData();
        ArrayList<String> atributos = new ArrayList();
        for(int i = 1; i <= metadatos.getColumnCount(); i++)
            atributos.add(metadatos.getColumnName(i));
        return atributos;
    }
    
    public static int getAridad(String nombreTabla) throws SQLException, ClassNotFoundException{
        return Conexion.ejecutaQuery("select * from "+nombreTabla).getMetaData().getColumnCount();
    }
    
    public static ArrayList<String> getLlavesPrimarias(String nombreTabla) throws SQLException, ClassNotFoundException{
        ArrayList<String> llavesPrimarias = new ArrayList();
        ResultSet rs = getConexion().getMetaData().getPrimaryKeys(null, null, nombreTabla);
        while(rs.next()){
            llavesPrimarias.add(rs.getString("COLUMN_NAME"));
        }
        return llavesPrimarias;
    }
    
    public static ArrayList<String> getLlavesForaneas(String nombreTabla) throws SQLException, ClassNotFoundException{
        ArrayList<String> llavesForaneas = new ArrayList();
        ResultSet rs = getConexion().getMetaData().getImportedKeys(null, null, nombreTabla);
        while(rs.next()){
            llavesForaneas.add(rs.getString("FKCOLUMN_NAME"));
        }
        return llavesForaneas;
    }
    
    public static String getDiccionarioTabla(String nombre) throws SQLException, ClassNotFoundException{
        ArrayList<String> llavesPrimarias = getLlavesPrimarias(nombre);
        ArrayList<String> llavesForaneas = getLlavesForaneas(nombre);
        ResultSet tabla = Conexion.ejecutaQuery("select * from "+nombre);
        ResultSetMetaData metadatos = tabla.getMetaData();
        String diccionario = nombre+":\n";
        for(int i = 1; i <= metadatos.getColumnCount(); i++){
            diccionario += "\t"+metadatos.getColumnName(i)+" "+metadatos.getColumnTypeName(i);
            if(llavesPrimarias.contains(metadatos.getColumnName(i)))
                diccionario += " PK";
            if(llavesForaneas.contains(metadatos.getColumnName(i)))
                diccionario += " FK";
            diccionario +="\n";
        }
        return diccionario;
    }

    public static String getDiccionarioPermanentes() throws SQLException, ClassNotFoundException{
        String diccionario = "";
        for(String nombreTabla : Conexion.getNombresTablasPermanentes()){
            diccionario += getDiccionarioTabla(nombreTabla)+"\n";
        }
        return diccionario;
    }
    
    public static String getDiccionarioTemporales()throws SQLException, ClassNotFoundException{
        String diccionario = "";
        for(String nombreTabla : Conexion.getNombresTablasTemporales()){
            diccionario += getDiccionarioTabla(nombreTabla)+"\n";
        }
        return diccionario;
    }
    
    public static ArrayList<String> getNombresTablas() throws SQLException, ClassNotFoundException{
        ArrayList<String> nombres = getNombresTablasPermanentes();
        getNombresTablasPermanentes().addAll(getNombresTablasTemporales());
        return nombres;
    }
    
    public static String getReferenciasCruzadas() throws SQLException, ClassNotFoundException{
        HashMap<String, ArrayList<String>> referencias = new HashMap();
        for(String tabla : getNombresTablas()){
            for(String llave : getLlavesForaneas(tabla)){
                if(!referencias.containsKey(llave))
                    referencias.put(llave, new ArrayList());
                referencias.get(llave).add(tabla);
            }
        }
        String referenciasStr = "";
        for(String llave : new ArrayList<String>(referencias.keySet())){
            referenciasStr += llave+":\n";
            for(String tabla : referencias.get(llave))
                referenciasStr +="\t"+tabla+"\n";
            referenciasStr +="\n";
        }
        return referenciasStr;
    }
    
    private static boolean existeEnRenames(String nombre){
        for(Rename tupla : renames){
                if(tupla.nombreNuevo.equals(nombre)){
                    return true;
                }
        }
        return false;
    }
    
    public static void agregarRename(String nombre, String nombreNuevo, ArrayList<String> nuevosAtributos) throws IllegalArgumentException, SQLException, ClassNotFoundException{
        if(!getNombresTablas().contains(nombre))
            throw new IllegalArgumentException("ERROR: NO EXISTE LA TABLA \""+nombre+"\".");
        else if(getNombresTablas().contains(nombreNuevo))
            throw new IllegalArgumentException("El nombre de tabla \""+nombreNuevo+"\" para renombrar a \""+nombre
                                           +"\" ya existe en el sistema de base de datos.");
        if(nuevosAtributos.size() != getAridad(nombre))
            throw new IllegalArgumentException("No hay correspondencia en la cantidad de atributos.\n"+
                                               "La tabla "+nombre+" tiene "+getAridad(nombre)+
                                               " atributos y se están dando "+nuevosAtributos.size());
        else if(existeEnRenames(nombre)){
            for(Rename rename : renames){
                if(rename.nombreNuevo.equals(nombre)){
                    String nombreViejo = rename.nombreViejo;
                    renames.remove(rename);
                    renames.add(new Rename(nombreViejo, nombreNuevo, nuevosAtributos));
                    return;
                }
            }
        }
        else{
            renames.add(new Rename(nombre, nombreNuevo, nuevosAtributos));
        }
    }
    
    public static String getRename(String nombre){
        for(Rename rename : renames){
            if(rename.nombreNuevo.equals(nombre))
                return rename.nombreNuevo;
            
        }
        return nombre;
    }
    
    public static ArrayList<String> getAtributosRename(String nombre) throws SQLException, ClassNotFoundException{
        for(Rename rename : renames){
            if(rename.nombreNuevo.equals(nombre))
                return rename.atributos;
        }
        return getAtributos(nombre);
    }
    
    private static class Rename{ 
        public String nombreViejo;
        public String nombreNuevo;
        public ArrayList<String> atributos; 
        public Rename(String nombreViejo, String nombreNuevo, ArrayList<String> atributos) { 
            this.nombreViejo = nombreViejo;
            this.nombreNuevo = nombreNuevo;
            this.atributos = atributos; 
        } 
    } 
}
