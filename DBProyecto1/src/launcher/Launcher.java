package launcher;

import javax.swing.JOptionPane;
import javax.swing.UIManager;

import logic.*;
import ui.*;

public class Launcher {
    public static void main(String[] args) {
        //setting windows look and feel for the ui
        try { 
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        } catch (Exception ex) { 
            JOptionPane.showMessageDialog(null, "Usted no está usando windows, entonces puede que la interfaz se vea un poco mal. :(", "ERROR",
                                          JOptionPane.ERROR_MESSAGE); 
        }
        
        UI.getInstance();
        LogIn vn = new LogIn();
        vn.show();
    }
}
