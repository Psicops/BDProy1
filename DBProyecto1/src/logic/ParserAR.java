package logic;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ParserAR {
    public static final char seleccion = 'σ';
    public static final char proyeccion = 'Π';
    public static final char renombramiento = 'ρ';
    public static final char union = '∪';
    public static final char interseccion = '∩';
    public static final char diferencia = '-';
    public static final char join = '⨝';
    public static final char agregacion = 'Ģ';
    
    private static final String seleccion_regex = seleccion + "\\s+([^\\(]+)\\s*\\(([^\\)]+)\\)\\s*";
    private static final String proyeccion_regex = proyeccion+ "\\s+(([^\\(]\\,*)+)\\s*\\(([^\\)]+)\\)\\s*";
    
    public static String parsear(String expresion){
        Matcher matcher;
        if(expresion.matches(seleccion_regex)){
            matcher = Pattern.compile(seleccion_regex).matcher(expresion);
            matcher.find();
            String tabla = matcher.group(2);
            String predicado = matcher.group(1);
            return "select * from "+tabla+" where "+predicado;
        }/*
        else if(expresion.matches(proyeccion_regex)){
                
        }
        else if(){
        }*/
        else
            return "no";
    }
}
