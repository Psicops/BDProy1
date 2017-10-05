package launcher;

import javax.swing.JOptionPane;
import javax.swing.UIManager;

import logic.*;
/*SELECCION = "σ";
  PROYECCION = "Π";
  RENOMBRAMIENTO = "ρ";
  PRODUCTO_CARTESIANO = "×";
  UNION = "∪";
  INTERSECCION = "∩";
  DIFERENCIA = "−";
  DIVISION = "÷";
  JOIN = "⨝";
  AGREGACION = "Ģ";
  SUM = "sum";
  COUNT = "count";
  MIN = "min";
  MAX = "max";
  AVG = "avg";
  AS = "=>";
  AND = "&";
  OR = "¡";
  ARITMETICA = ">|<|=|<=|>=|\\+|-|\\*|/";  
 */
public class LauncherParser {
    public static void main(String[] args) {
        ParserAR.parsear("'b' ¡ b");
    }
}
