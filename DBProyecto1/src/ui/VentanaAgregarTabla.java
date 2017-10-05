package ui;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.DefaultListModel;
import logic.Conexion;

public class VentanaAgregarTabla extends javax.swing.JFrame {
    public static final boolean TEMPORAL = true;
    public static final boolean PERMANENTE = false;
    
    private final boolean tipo;
    private final VentanaPrincipal padre;
    private final DefaultListModel listaAtributosModelo;
    
    public static String schemaName = "proy1.";
    
    public VentanaAgregarTabla(VentanaPrincipal padre, boolean tipo) {
        this.tipo = tipo;
        this.padre = padre;
        this.listaAtributosModelo = new DefaultListModel();
        initComponents();
        this.listaAtributos.setModel(listaAtributosModelo);
        if(tipo ==  TEMPORAL)
            this.setTitle("Agregar Tabla Temporal");
        else
            this.setTitle("Agregar Tabla Permanente");
    }
    private class Atributo{
        private final String nombre;
        private final String tipo;
        private final boolean esLlavePrimaria;
        private final boolean esLlaveForanea;
        private final String apunta_a;
        
        Atributo(String nombre, String tipo, boolean esLlavePrimaria, boolean esLlaveForanea, String apunta_a){
            this.nombre = nombre;
            this.tipo = tipo;
            this.esLlavePrimaria = esLlavePrimaria;
            this.esLlaveForanea = esLlaveForanea;
            this.apunta_a = apunta_a;
        }

        @Override
        public String toString(){
            String toString = nombre+" "+tipo;
            if(esLlavePrimaria)
                toString += " primary key";
            if(esLlaveForanea)
                toString += " references "+apunta_a;
            return toString;
        }
    }

    private class Tabla{
        String nombre;
        boolean tipo;
        ArrayList<Atributo> atributos;
        
        Tabla(String nombre, boolean tipo, ArrayList<Atributo> atributos){
            this.nombre = nombre;
            this.tipo = tipo;
            this.atributos = atributos;
        }
        @Override
        public String toString(){
            String toString;
            if(tipo == TEMPORAL)
                toString = "create table ##"+nombre+"(";
            else
                toString = "create table "+nombre+"(";
            
            for(Atributo atributo : atributos){
                toString += atributo.toString()+",";
            }
            toString += ")";
            return toString;
        }
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        popupMenuListaAtributos = new javax.swing.JPopupMenu();
        menuItemEliminarAtributo = new javax.swing.JMenuItem();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        textfieldNombreTabla = new javax.swing.JTextField();
        textfieldNombreAtributo = new javax.swing.JTextField();
        textfieldTipoAtributo = new javax.swing.JTextField();
        textfieldLlaveForanea = new javax.swing.JTextField();
        checkboxLlaveForanea = new javax.swing.JCheckBox();
        checkboxLlavePrimaria = new javax.swing.JCheckBox();
        botonAgregarAtributo = new javax.swing.JButton();
        scrollpaneAtributos = new javax.swing.JScrollPane();
        listaAtributos = new javax.swing.JList();
        botonCrearTabla = new javax.swing.JButton();

        menuItemEliminarAtributo.setText("Eliminar Atributo Seleccionado");
        menuItemEliminarAtributo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemEliminarAtributoActionPerformed(evt);
            }
        });
        popupMenuListaAtributos.add(menuItemEliminarAtributo);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setText("Nombre de Tabla:");

        jLabel2.setText("Lista de Atributos:");

        jLabel3.setText("Nombre de Atributo:");

        jLabel4.setText("Tipo de Atributo:");

        textfieldLlaveForanea.setEditable(false);

        checkboxLlaveForanea.setText("Llave Foranea:");
        checkboxLlaveForanea.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkboxLlaveForaneaActionPerformed(evt);
            }
        });

        checkboxLlavePrimaria.setText("Llave Primaria");

        botonAgregarAtributo.setText("Agregar Atributo");
        botonAgregarAtributo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonAgregarAtributoActionPerformed(evt);
            }
        });

        scrollpaneAtributos.setComponentPopupMenu(popupMenuListaAtributos);

        listaAtributos.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        listaAtributos.setInheritsPopupMenu(true);
        scrollpaneAtributos.setViewportView(listaAtributos);

        botonCrearTabla.setText("Crear Tabla");
        botonCrearTabla.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonCrearTablaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(botonAgregarAtributo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(textfieldTipoAtributo)
                    .addComponent(textfieldNombreAtributo, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(scrollpaneAtributos)
                    .addComponent(textfieldNombreTabla)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(checkboxLlavePrimaria)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4))
                        .addGap(0, 346, Short.MAX_VALUE))
                    .addComponent(botonCrearTabla, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(checkboxLlaveForanea)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(textfieldLlaveForanea)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(textfieldNombreTabla, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(textfieldNombreAtributo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel4)
                .addGap(7, 7, 7)
                .addComponent(textfieldTipoAtributo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(checkboxLlaveForanea)
                    .addComponent(textfieldLlaveForanea, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(checkboxLlavePrimaria)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(botonAgregarAtributo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(scrollpaneAtributos, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(botonCrearTabla)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void botonAgregarAtributoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonAgregarAtributoActionPerformed
        String nombre = textfieldNombreAtributo.getText();
        String tipoAtributo = textfieldTipoAtributo.getText();
        String referencia = textfieldLlaveForanea.getText();
        boolean llavePrimaria = checkboxLlavePrimaria.isSelected();
        boolean llaveForanea = checkboxLlaveForanea.isSelected();
        if(!nombre.equals("") || !tipoAtributo.equals("") || (llaveForanea && !referencia.equals(""))){
            listaAtributosModelo.addElement(new Atributo(nombre, tipoAtributo, llavePrimaria, llaveForanea, referencia));
            textfieldNombreAtributo.setText("");
            textfieldTipoAtributo.setText("");
            textfieldLlaveForanea.setText("");
            textfieldLlaveForanea.setEditable(false);
            checkboxLlavePrimaria.setSelected(false);
            checkboxLlaveForanea.setSelected(false);
        }
        else
            UI.getInstance().displayError("Los campos de nombre y tipo de atributo deben tener valores.\n"
                                        + "Si el atributo es una llave foránea, el campo de texto al lado del check de llave foranea debe tener un valor.");
    }//GEN-LAST:event_botonAgregarAtributoActionPerformed

    private void botonCrearTablaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonCrearTablaActionPerformed
        ArrayList<Atributo> al = new ArrayList(Arrays.asList(listaAtributosModelo.toArray()));
        Tabla tabla = new Tabla(schemaName + textfieldNombreTabla.getText(), tipo, al);
        try{
            Conexion.ejecutaInstruccion(tabla.toString());
            UI.getInstance().displayInfo("Tabla agregada exitosamente.");
            padre.updatear();
            this.dispose();
        }catch(SQLException ex){
            UI.getInstance().displayError("Al crear la tabla: "+ex.getMessage());
        }
    }//GEN-LAST:event_botonCrearTablaActionPerformed

    private void checkboxLlaveForaneaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkboxLlaveForaneaActionPerformed
        if(checkboxLlaveForanea.isSelected())
            textfieldLlaveForanea.setEditable(true);
        else{
            textfieldLlaveForanea.setEditable(false);
            textfieldLlaveForanea.setText("");
        }
    }//GEN-LAST:event_checkboxLlaveForaneaActionPerformed

    private void menuItemEliminarAtributoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemEliminarAtributoActionPerformed
        if(listaAtributos.getSelectedValue() == null){
            UI.getInstance().displayError("No ha seleccionado ningún atributo para borrar.");
        }else{
            listaAtributosModelo.removeElement(listaAtributos.getSelectedValue());
        }
    }//GEN-LAST:event_menuItemEliminarAtributoActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(VentanaAgregarTabla.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VentanaAgregarTabla.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VentanaAgregarTabla.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VentanaAgregarTabla.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VentanaAgregarTabla(null, false).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botonAgregarAtributo;
    private javax.swing.JButton botonCrearTabla;
    private javax.swing.JCheckBox checkboxLlaveForanea;
    private javax.swing.JCheckBox checkboxLlavePrimaria;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JList listaAtributos;
    private javax.swing.JMenuItem menuItemEliminarAtributo;
    private javax.swing.JPopupMenu popupMenuListaAtributos;
    private javax.swing.JScrollPane scrollpaneAtributos;
    private javax.swing.JTextField textfieldLlaveForanea;
    private javax.swing.JTextField textfieldNombreAtributo;
    private javax.swing.JTextField textfieldNombreTabla;
    private javax.swing.JTextField textfieldTipoAtributo;
    // End of variables declaration//GEN-END:variables
}
