package logic;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ParserAR {
    public static final String SELECCION = "σ";
    public static final String PROYECCION = "Π";
    public static final String RENOMBRAMIENTO = "ρ";
    public static final String PRODUCTO_CARTESIANO = "×";
    public static final String UNION = "∪";
    public static final String INTERSECCION = "∩";
    public static final String DIFERENCIA = "−";
    public static final String DIVISION = "÷";
    public static final String JOIN = "⨝";
    public static final String AGREGACION = "Ģ";
    public static final String SUM = "sum";
    public static final String COUNT = "count";
    public static final String MIN = "min";
    public static final String MAX = "max";
    public static final String AVG = "avg";
    public static final String AS = "=>";
    public static final String AND = "&";
    public static final String OR = "¡";
    public static final String ARITMETICA = ">|<|=|<=|>=|\\+|-|\\*|/";
    public static final String GUARDAR = "<-";
    
    public static final String SELECCION_FUNCION = SELECCION+"[]()";
    public static final String PROYECCION_FUNCION = PROYECCION+"[]()";
    public static final String RENOMBRAMIENTO_FUNCION = RENOMBRAMIENTO+"[]()";
    public static final String PRODUCTO_CARTESIANO_FUNCION = PRODUCTO_CARTESIANO;
    public static final String UNION_FUNCION = UNION;
    public static final String INTERSECCION_FUNCION = INTERSECCION;
    public static final String DIFERENCIA_FUNCION = DIFERENCIA;
    public static final String DIVISION_FUNCION = DIVISION;
    public static final String JOIN_FUNCION = JOIN+"[]";
    public static final String NATURAL_JOIN_FUNCION = JOIN;
    public static final String AGREGACION_FUNCION = AGREGACION+"[]()";
    public static final String ALIAS_FUNCION = AS;
    public static final String AND_FUNCION = AND;
    public static final String OR_FUNCION = OR;
    public static final String SUM_FUNCION = SUM+"()";
    public static final String COUNT_FUNCION = COUNT+"()";
    public static final String MIN_FUNCION = MIN+"()";
    public static final String MAX_FUNCION = MAX+"()";
    public static final String AVG_FUNCION = AVG+"()";
    public static final String GUARDAR_FUNCION = GUARDAR+"()";
    
    private static final String SELECCION_REGEX = SELECCION + "\\[([^\\[]+?)\\]\\((.+)\\)";
    private static final String PROYECCION_REGEX = PROYECCION + "\\[([^\\[]+?)\\]\\((.+)\\)";
    private static final String RENOMBRAMIENTO_REGEX = RENOMBRAMIENTO + "\\[([^\\[]+?)\\]\\((.+)\\)";
    private static final String PRODUCTO_CARTESIANO_REGEX = "(.+)["+PRODUCTO_CARTESIANO+"](.+)";
    private static final String UNION_REGEX = "(.+)["+UNION+"](.+)";
    private static final String INTERSECCION_REGEX = "(.+)["+INTERSECCION+"](.+)";
    private static final String DIFERENCIA_REGEX = "(.+)["+DIFERENCIA+"](.+)";
    private static final String DIVISION_REGEX = "(.+)"+DIVISION+"(.+)";
    private static final String JOIN_REGEX = "(.+)(?:"+JOIN+")\\[([^\\[]+?)\\](.+)";
    private static final String NATURAL_JOIN_REGEX = "(.+)(?:"+JOIN+")(.+)";
    private static final String AGREGACION_REGEX = "["+AGREGACION+"]\\[([^\\[]+?)\\]\\((.+)\\)";
    private static final String AGRUPACION_REGEX = "(.+)["+AGREGACION+"]\\[([^\\[]+?)\\]\\((.+)\\)";
    private static final String ATOMO_REGEX = "[\\w][\\w\\.]*";
    private static final String ALIAS_REGEX = "(.+)"+AS+"(.+)";
    private static final String FUNCION_AGREGACION_REGEX = "("+SUM+"|"+COUNT+"|"+MIN+"|"+MAX+"|"+AVG+")\\((.+)\\)";
    private static final String LISTA_REGEX = "(.+?),(.+)";
    private static final String PREDICADO_REGEX = "(.+)("+AND+"|"+OR+")(.+)";
    private static final String ARITMETICA_REGEX = "(.+)("+ARITMETICA+")(.+)";
    private static final String PARENTESIS_REGEX = "\\((.+)\\)";
    private static final String STRING_REGEX = "['](.+)[']";
    
    public static String parsear(String expresion) throws IllegalArgumentException{
        return parsear (expresion, true);
    }
    
    private static String parsear(String expresion, boolean primero) throws IllegalArgumentException{
        expresion = expresion.replaceAll("\\s+", "");    
        Matcher matcher;
        if(expresion.matches(ATOMO_REGEX)){
            System.out.println("Atomo encontrado: "+expresion);
            return expresion;
        }
        if(expresion.matches(STRING_REGEX)){
            System.out.println("String encontrado: "+expresion);
            return expresion;
        }
        if(expresion.matches(PARENTESIS_REGEX)){
            matcher = Pattern.compile(PARENTESIS_REGEX).matcher(expresion);
            matcher.find();
            System.out.println("Parentesis encontrado: "+expresion);
            return "("+parsear(matcher.group(1), false)+")";
        }
        else if(expresion.matches(RENOMBRAMIENTO_REGEX)){
            matcher = Pattern.compile(RENOMBRAMIENTO_REGEX).matcher(expresion);
            matcher.find();
            System.out.println("Rename encontrado: "+expresion);
            return "exec sp_rename \'"+parsear(matcher.group(2), false)+"\', \'"+parsear(matcher.group(1), false)+"\'";
        }
        else if(expresion.matches(PRODUCTO_CARTESIANO_REGEX)){
            matcher = Pattern.compile(PRODUCTO_CARTESIANO_REGEX).matcher(expresion);
            matcher.find();
            System.out.println("Producto Cartesiano encontrado: "+expresion);
            if(primero)
                return "select * from "+parsear(matcher.group(1), false)+", "+parsear(matcher.group(2), false);
            else
                return "(select * from "+parsear(matcher.group(1), false)+", "+parsear(matcher.group(2), false)+")";
        }
        else if(expresion.matches(UNION_REGEX)){
            matcher = Pattern.compile(UNION_REGEX).matcher(expresion);
            matcher.find();
            System.out.println("Union encontrada: "+expresion);
            if(primero)
                return parsear(matcher.group(1), false)+" union "+parsear(matcher.group(2), false);
            else
                return "("+parsear(matcher.group(1), false)+" union "+parsear(matcher.group(2), false)+")";
        }
        else if(expresion.matches(INTERSECCION_REGEX)){
            matcher = Pattern.compile(INTERSECCION_REGEX).matcher(expresion);
            matcher.find();
            System.out.println("Interseccion encontrada: "+expresion);
            if(primero)
                return parsear(matcher.group(1), false)+" intersect "+parsear(matcher.group(2), false);
            else
                return "(" + parsear(matcher.group(1), false)+" intersect "+parsear(matcher.group(2), false)+")";
        }
        else if(expresion.matches(DIFERENCIA_REGEX)){
            matcher = Pattern.compile(DIFERENCIA_REGEX).matcher(expresion);
            matcher.find();
            System.out.println("Diferencia encontrada: "+expresion);
            if(primero)
                return parsear(matcher.group(1), false)+" except "+parsear(matcher.group(2), false);
            else
                return "("+parsear(matcher.group(1), false)+" except "+parsear(matcher.group(2), false)+")";
        }
        else if(expresion.matches(DIVISION_REGEX)){
            matcher = Pattern.compile(DIVISION_REGEX).matcher(expresion);
            matcher.find();
            System.out.println("Division encontrada: "+expresion);
            if(primero)
                return "NO IMPLEMENTADO";
            else
                return "NO IMPLEMENTADO";
        }
        else if(expresion.matches(JOIN_REGEX)){
            matcher = Pattern.compile(JOIN_REGEX).matcher(expresion);
            matcher.find();
            System.out.println("Join encontrado: "+expresion);
            if(primero)
                return "select * from "+parsear(matcher.group(1), false)+" join "+parsear(matcher.group(3), false)+" on "+parsear(matcher.group(2), false);
            else
                return "(select * from "+parsear(matcher.group(1), false)+" join "+parsear(matcher.group(3), false)+" on "+parsear(matcher.group(2), false)+")";
        }
        else if(expresion.matches(NATURAL_JOIN_REGEX)){
            matcher = Pattern.compile(JOIN_REGEX).matcher(expresion);
            matcher.find();
            System.out.println("Join natural encontrado: "+expresion);
            if(primero)
                return "select * from "+parsear(matcher.group(1), false)+" join "+parsear(matcher.group(2), false);
            else
                return "(select * from "+parsear(matcher.group(1), false)+" join "+parsear(matcher.group(2), false)+")";
        }
        else if(expresion.matches(AGREGACION_REGEX)){
            matcher = Pattern.compile(AGREGACION_REGEX).matcher(expresion);
            matcher.find();
            System.out.println("Rename encontrado: "+expresion);
            if(primero)
                return "select "+parsear(matcher.group(1), false)+" from "+parsear(matcher.group(2), false);
            else
                return "(select "+parsear(matcher.group(1), false)+" from "+parsear(matcher.group(2), false)+")";
        }
        else if(expresion.matches(AGRUPACION_REGEX)){
            matcher = Pattern.compile(AGRUPACION_REGEX).matcher(expresion);
            matcher.find();
            System.out.println("Agrupacion encontrada: "+expresion);
            if(primero)
                return "select "+parsear(matcher.group(1), false)+","+parsear(matcher.group(2), false)+" from "+parsear(matcher.group(3), false);
            else
                return "(select "+parsear(matcher.group(1), false)+","+parsear(matcher.group(2), false)+" from "+parsear(matcher.group(3), false)+")";
        }
        else if(expresion.matches(SELECCION_REGEX)){
            matcher = Pattern.compile(SELECCION_REGEX).matcher(expresion);
            matcher.find();
            System.out.println("Seleccion encontrada: "+expresion);
            if(primero)
                return "select * from "+parsear(matcher.group(2), false)+" where "+parsear(matcher.group(1), false);
            else 
                return parsear(matcher.group(2), false)+" where "+parsear(matcher.group(1), false);
        }
        else if(expresion.matches(PROYECCION_REGEX)){
            matcher = Pattern.compile(PROYECCION_REGEX).matcher(expresion);
            matcher.find();
            System.out.println("Proyeccion encontrada: "+expresion);
            if(primero)
                return "select "+parsear(matcher.group(1), false)+" from "+parsear(matcher.group(2), false);
            else
                return "(select "+parsear(matcher.group(1), false)+" from "+parsear(matcher.group(2), false)+")";
        }
        else if(expresion.matches(LISTA_REGEX)){
            matcher = Pattern.compile(LISTA_REGEX).matcher(expresion);
            matcher.find();
            System.out.println("Lista encontrada, partes:\n\t1: "+matcher.group(1)+"\n\t2: "+matcher.group(2));
            return parsear(matcher.group(1), false)+","+parsear(matcher.group(2), false);
        }
        else if(expresion.matches(PREDICADO_REGEX)){
            matcher = Pattern.compile(PREDICADO_REGEX).matcher(expresion);
            matcher.find();
            System.out.println("Predicado encontrado: "+expresion);
            return parsear(matcher.group(1), false)+" "+parseBool(matcher.group(2))+" "+parsear(matcher.group(3), false);
        }
        else if(expresion.matches(FUNCION_AGREGACION_REGEX)){
            matcher = Pattern.compile(FUNCION_AGREGACION_REGEX).matcher(expresion);
            matcher.find();
            System.out.println("Funcion de agregacion encontrada: "+expresion);
            return matcher.group(1)+"("+parsear(matcher.group(2), false)+")";
        }
        else if(expresion.matches(ALIAS_REGEX)){
            matcher = Pattern.compile(ALIAS_REGEX).matcher(expresion);
            matcher.find();
            System.out.println("Alias encontrado: "+expresion);
            return parsear(matcher.group(1), false)+" as "+parsear(matcher.group(2), false);
        }
        else if(expresion.matches(ARITMETICA_REGEX)){
            matcher = Pattern.compile(ARITMETICA_REGEX).matcher(expresion);
            matcher.find();
            System.out.println("Operacion aritmetica encontrada: "+expresion);
            return parsear(matcher.group(1), false)+matcher.group(2)+parsear(matcher.group(3), false);
        } 
        else
            throw new IllegalArgumentException("La expresión \""+expresion+"\" no es válida.");
    }
    
    private static String parseBool(String bool)throws IllegalArgumentException{
        if(bool.equals(AND))
            return "and";
        else if(bool.equals(OR))
            return "or";
        else{
            System.out.println("Se cayo en el parse bool con el valor: "+bool);
            throw new IllegalArgumentException("Error al parsear lo que debería ser un operador booleano: "+bool);
        }
    }
}