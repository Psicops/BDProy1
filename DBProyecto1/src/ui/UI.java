package ui;

import javax.swing.JOptionPane;

public class UI {
    private static UI instancia;
    
    private UI(){
        //FALTA
    }
    
    public static UI getInstance(){
        if(instancia == null)
            instancia = new UI();
        return instancia;
    }
    
    public void displayUI(){
        //FALTA
    }
    
    public void displayError(String message){
        JOptionPane.showMessageDialog(null, message, "ERROR",
                                      JOptionPane.ERROR_MESSAGE);
    }
    public void displayInfo(String message){
        JOptionPane.showMessageDialog(null, message, "HS Stats Tracker",
                                      JOptionPane.INFORMATION_MESSAGE);
    }
    
    public boolean displayConfirm(String message){
        int reply =  JOptionPane.showConfirmDialog(null, message, "HS StatsTracker", 
                                      JOptionPane.YES_NO_OPTION);
        return reply == JOptionPane.YES_OPTION;
    }
}
