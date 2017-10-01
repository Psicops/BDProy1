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
    public static final String JOIN = "⨝";
    public static final String AGREGACION = "Ģ";
    public static final String SUM = "sum";
    public static final String COUNT = "count";
    public static final String MIN = "min";
    public static final String MAX = "max";
    public static final String AVG = "avg";
    public static final String AS = "=>";
    public static final String AND = "&";
    public static final String OR = "\\|";
    
    public static final String SELECCION_FUNCION = SELECCION+"[]()";
    public static final String PROYECCION_FUNCION = PROYECCION+"[]()";
    public static final String RENOMBRAMIENTO_FUNCION = RENOMBRAMIENTO+"[]()";
    public static final String PRODUCTO_CARTESIANO_FUNCION = PRODUCTO_CARTESIANO;
    public static final String UNION_FUNCION = UNION;
    public static final String INTERSECCION_FUNCION = INTERSECCION;
    public static final String DIFERENCIA_FUNCION = DIFERENCIA;
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
    
    private static final String SELECCION_REGEX = SELECCION + "\\[([^\\[]+?)\\]\\((.+)\\)";
    private static final String PROYECCION_REGEX = PROYECCION + "\\[([^\\[]+?)\\]\\((.+)\\)";
    private static final String RENOMBRAMIENTO_REGEX = RENOMBRAMIENTO + "\\[([^\\[]+?)\\]\\((.+)\\)";
    private static final String PRODUCTO_CARTESIANO_REGEX = "(.+)["+PRODUCTO_CARTESIANO+"](.+)";
    private static final String UNION_REGEX = "(.+)["+UNION+"](.+)";
    private static final String INTERSECCION_REGEX = "(.+)["+INTERSECCION+"](.+)";
    private static final String DIFERENCIA_REGEX = "(.+)["+DIFERENCIA+"](.+)";
    private static final String JOIN_REGEX = "(.+)(?:"+JOIN+")\\[([^\\[]+?)\\](.+)";
    private static final String NATURAL_JOIN_REGEX = "(.+)(?:"+JOIN+")(.+)";
    private static final String AGREGACION_REGEX = "["+AGREGACION+"]\\[([^\\[]+?)\\]\\((.+)\\)";
    private static final String AGRUPACION_REGEX = "(.+)["+AGREGACION+"]\\[([^\\[]+?)\\]\\((.+)\\)";
    private static final String ATOMO_REGEX = "\\(*[a-zA-Z_][\\w]*\\)*";
    private static final String ALIAS_REGEX = "(.+)"+AS+"(.+)";
    private static final String FUNCION_AGREGACION_REGEX = "("+SUM+"|"+COUNT+"|"+MIN+"|"+MAX+"|"+AVG+")\\((.+)\\)";
    private static final String LISTA_REGEX = "(.+?),(.+)";
    private static final String PREDICADO_REGEX = "(.+)("+AND+"|"+OR+")(.+)";
    
    public static String parsear(String expresion) throws IllegalArgumentException{
        expresion = expresion.replaceAll("\\s+", "");    
        Matcher matcher;
        if(expresion.matches(ATOMO_REGEX)){
            System.out.println("Atomo encontrado: "+expresion);
            return expresion;
        }
        else if(expresion.matches(SELECCION_REGEX)){
            matcher = Pattern.compile(SELECCION_REGEX).matcher(expresion);
            matcher.find();
            System.out.println("Seleccion encontrada: "+expresion);
            return "select * from "+parsear(matcher.group(2))+" where "+parsear(matcher.group(1));
        }
        else if(expresion.matches(PROYECCION_REGEX)){
            matcher = Pattern.compile(PROYECCION_REGEX).matcher(expresion);
            matcher.find();
            System.out.println("Proyeccion encontrada: "+expresion);
            return "select "+parsear(matcher.group(1))+" from "+parsear(matcher.group(2));
        }
        else if(expresion.matches(RENOMBRAMIENTO_REGEX)){
            matcher = Pattern.compile(RENOMBRAMIENTO_REGEX).matcher(expresion);
            matcher.find();
            System.out.println("Rename encontrado: "+expresion);
            return "exec sp_rename \'"+parsear(matcher.group(2))+"\', \'"+parsear(matcher.group(1))+"\'";
        }
        else if(expresion.matches(PRODUCTO_CARTESIANO_REGEX)){
            matcher = Pattern.compile(PRODUCTO_CARTESIANO_REGEX).matcher(expresion);
            matcher.find();
            System.out.println("Producto Cartesiano encontrado: "+expresion);
            return "select * from "+parsear(matcher.group(1))+", "+parsear(matcher.group(2));
        }
        else if(expresion.matches(UNION_REGEX)){
            matcher = Pattern.compile(UNION_REGEX).matcher(expresion);
            matcher.find();
            System.out.println("Union encontrada: "+expresion);
            return "select * from "+parsear(matcher.group(1))+" union select * from "+parsear(matcher.group(2));
        }
        else if(expresion.matches(INTERSECCION_REGEX)){
            matcher = Pattern.compile(INTERSECCION_REGEX).matcher(expresion);
            matcher.find();
            System.out.println("Interseccion encontrada: "+expresion);
            return "select * from "+parsear(matcher.group(1))+" intersect select * from "+parsear(matcher.group(2));
        }
        else if(expresion.matches(DIFERENCIA_REGEX)){
            matcher = Pattern.compile(DIFERENCIA_REGEX).matcher(expresion);
            matcher.find();
            System.out.println("Diferencia encontrada: "+expresion);
            return "select * from "+parsear(matcher.group(1))+" except select * from "+parsear(matcher.group(2));
        }
        else if(expresion.matches(JOIN_REGEX)){
            matcher = Pattern.compile(JOIN_REGEX).matcher(expresion);
            matcher.find();
            System.out.println("Join encontrado: "+expresion);
            return "select * from "+parsear(matcher.group(1))+" join "+parsear(matcher.group(3))+" on "+parsear(matcher.group(2));
        }
        else if(expresion.matches(NATURAL_JOIN_REGEX)){
            matcher = Pattern.compile(JOIN_REGEX).matcher(expresion);
            matcher.find();
            System.out.println("Join natural encontrado: "+expresion);
            return "select * from "+parsear(matcher.group(1))+" join "+parsear(matcher.group(2));
        }
        else if(expresion.matches(AGREGACION_REGEX)){
            matcher = Pattern.compile(AGREGACION_REGEX).matcher(expresion);
            matcher.find();
            System.out.println("Rename encontrado: "+expresion);
            return "select "+parsear(matcher.group(1))+" from "+parsear(matcher.group(2));
        }
        else if(expresion.matches(AGRUPACION_REGEX)){
            matcher = Pattern.compile(AGRUPACION_REGEX).matcher(expresion);
            matcher.find();
            System.out.println("Agrupacion encontrada: "+expresion);
            return "select "+parsear(matcher.group(1))+","+parsear(matcher.group(2))+" from "+parsear(matcher.group(3));
        }
        else if(expresion.matches(LISTA_REGEX)){
            matcher = Pattern.compile(LISTA_REGEX).matcher(expresion);
            matcher.find();
            System.out.println("Lista encontrada, partes:\n\t1: "+matcher.group(1)+"\n\t2: "+matcher.group(2));
            return parsear(matcher.group(1))+","+(parsear(matcher.group(2)));
        }
        else if(expresion.matches(PREDICADO_REGEX)){
            matcher = Pattern.compile(PREDICADO_REGEX).matcher(expresion);
            matcher.find();
            System.out.println("Predicado encontrado: "+expresion);
            return parsear(matcher.group(1))+" "+parseBool(matcher.group(2))+" "+parsear(matcher.group(3));
        }
        else if(expresion.matches(FUNCION_AGREGACION_REGEX)){
            matcher = Pattern.compile(FUNCION_AGREGACION_REGEX).matcher(expresion);
            matcher.find();
            System.out.println("Funcion de agregacion encontrada: "+expresion);
            return matcher.group(1)+"("+parsear(matcher.group(2))+")";
        }
        else if(expresion.matches(ALIAS_REGEX)){
            matcher = Pattern.compile(ALIAS_REGEX).matcher(expresion);
            matcher.find();
            System.out.println("Alias encontrado: "+expresion);
            return parsear(matcher.group(1))+" as "+parsear(matcher.group(2));
        }
        else
            throw new IllegalArgumentException("La expresión \""+expresion+"\" no es válida.");
    }
    
    private static String parseBool(String bool)throws IllegalArgumentException{
        if(bool.equals(AND))
            return "and";
        else if(bool.equals(OR))
            return "or";
        else
            throw new IllegalArgumentException("Error al parsear lo que debería ser un operador booleano: "+bool);
    }
}
