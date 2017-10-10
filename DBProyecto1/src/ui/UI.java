package ui;

import javax.swing.JOptionPane;

public class UI {
    private static UI instancia;
    
    private UI(){}
    
    public static UI getInstance(){
        if(instancia == null)
            instancia = new UI();
        return instancia;
    }
    
    public void displayUI(){}
    
    public void displayError(String message){
        JOptionPane.showMessageDialog(null, message, "ERROR",
                                      JOptionPane.ERROR_MESSAGE);
    }
    public void displayInfo(String message){
        JOptionPane.showMessageDialog(null, message, "Información",
                                      JOptionPane.INFORMATION_MESSAGE);
    }
    
    public boolean displayConfirm(String message){
        int reply =  JOptionPane.showConfirmDialog(null, message, "Pregunta", 
                                      JOptionPane.YES_NO_OPTION);
        return reply == JOptionPane.YES_OPTION;
    }
}
