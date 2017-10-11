package ui;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import javax.swing.DefaultListModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.BadLocationException;

import logic.Conexion;
import logic.ParserAR;

public class VentanaPrincipal extends javax.swing.JFrame {
    private ResultSet tabla_actual;
    
    public VentanaPrincipal() {
        initComponents();
        tabla_actual = null;
        textfieldInstruccionSQL.setText("(En espera de instrucción.)");
    }
    
    public void updatear(){
        DefaultListModel listModelP = new DefaultListModel();
        DefaultListModel listModelT = new DefaultListModel();
	listaPermanentes.setModel(listModelP);
	listaTemporales.setModel(listModelT);
        try{ 
            for(String nombre : Conexion.getNombresTablasPermanentes()){
                listModelP.addElement(Conexion.getRename(nombre));
            }
            for(String nombre : Conexion.getNombresTablasTemporales()){
                listModelT.addElement(Conexion.getRename(nombre));
            }
        } catch (SQLException | ClassNotFoundException ex) {
            UI.getInstance().displayError("Al ejecutar la instruccion en SQL:\n"+ex.getMessage());
        }
    }
    
    public void mensajeError(String ex){
        if("El nombre de objeto '".equals(ex.substring(0, 21))){
            String tabla = "";
            int i = 21;
            while(!"'".equals(ex.substring(i,i+1))){
                tabla += ex.substring(i,i+1);
                i++;
            }
            UI.getInstance().displayError("ERROR: NO EXISTE LA TABLA "+ tabla);
        }
        else{
            UI.getInstance().displayError("Al ejecutar la instruccion en SQL:\n"+ex);
        }
    }
    
    public void displayInfoTabla(ResultSet tabla) throws SQLException{
        ResultSetMetaData metadatos = tabla.getMetaData();
        DefaultTableModel modelo = new DefaultTableModel();
        for(int i = 1; i <= metadatos.getColumnCount(); i++){
            modelo.addColumn(metadatos.getColumnName(i));
            System.out.println(metadatos.getColumnName(i));
        }
        while(tabla.next()){
            String tupla[] = new String[metadatos.getColumnCount()];
            for(int j=0; j < metadatos.getColumnCount(); j++)
                tupla[j] = tabla.getString(j+1);
            modelo.addRow(tupla);
        }
        tablaGeneral.setModel(modelo);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        popupPermanentes = new javax.swing.JPopupMenu();
        menuItemCopiarNombrePermanentes = new javax.swing.JMenuItem();
        menuItemVerTablaPermanentes = new javax.swing.JMenuItem();
        menuItemAgregarTablaPermanentes = new javax.swing.JMenuItem();
        menuItemDiccionarioPermanentes = new javax.swing.JMenuItem();
        menuItemReferenciasCruzadasPermanentes = new javax.swing.JMenuItem();
        popupTemporales = new javax.swing.JPopupMenu();
        menuItemCopiarNombreTemporales = new javax.swing.JMenuItem();
        menuItemVerTablaTemporales = new javax.swing.JMenuItem();
        menuItemAgregarTablaTemporales = new javax.swing.JMenuItem();
        menuItemDiccionarioTemporales = new javax.swing.JMenuItem();
        menuItemReferenciasCruzadasTemporales = new javax.swing.JMenuItem();
        jMenuBar2 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();
        jMenuBar3 = new javax.swing.JMenuBar();
        jMenu3 = new javax.swing.JMenu();
        jMenu4 = new javax.swing.JMenu();
        jMenuBar4 = new javax.swing.JMenuBar();
        jMenu5 = new javax.swing.JMenu();
        jMenu6 = new javax.swing.JMenu();
        jLabel5 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        scrollpanePermanentes = new javax.swing.JScrollPane();
        listaPermanentes = new javax.swing.JList();
        scrollpaneTemporales = new javax.swing.JScrollPane();
        listaTemporales = new javax.swing.JList();
        scrollpaneGeneral = new javax.swing.JScrollPane();
        tablaGeneral = new javax.swing.JTable();
        textfieldInstruccionAR = new javax.swing.JTextField();
        textfieldInstruccionSQL = new javax.swing.JTextField();
        botonEjecutar = new javax.swing.JButton();
        comboboxOperaciones = new javax.swing.JComboBox();
        botonAgregar = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        menuAcercaDe = new javax.swing.JMenu();
        menuAyuda = new javax.swing.JMenu();
        menuSalir = new javax.swing.JMenu();

        menuItemCopiarNombrePermanentes.setText("Copiar Nombre");
        menuItemCopiarNombrePermanentes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemCopiarNombrePermanentesActionPerformed(evt);
            }
        });
        popupPermanentes.add(menuItemCopiarNombrePermanentes);

        menuItemVerTablaPermanentes.setText("Ver Tabla");
        menuItemVerTablaPermanentes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemVerTablaPermanentesActionPerformed(evt);
            }
        });
        popupPermanentes.add(menuItemVerTablaPermanentes);

        menuItemAgregarTablaPermanentes.setText("Agregar Tabla");
        menuItemAgregarTablaPermanentes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemAgregarTablaPermanentesActionPerformed(evt);
            }
        });
        popupPermanentes.add(menuItemAgregarTablaPermanentes);

        menuItemDiccionarioPermanentes.setText("Ver Diccionario de Datos");
        menuItemDiccionarioPermanentes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemDiccionarioPermanentesActionPerformed(evt);
            }
        });
        popupPermanentes.add(menuItemDiccionarioPermanentes);

        menuItemReferenciasCruzadasPermanentes.setText("Ver Referencias Cruzadas de Ambas Tablas");
        menuItemReferenciasCruzadasPermanentes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemReferenciasCruzadasPermanentesActionPerformed(evt);
            }
        });
        popupPermanentes.add(menuItemReferenciasCruzadasPermanentes);

        menuItemCopiarNombreTemporales.setText("Copiar Nombre");
        menuItemCopiarNombreTemporales.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemCopiarNombreTemporalesActionPerformed(evt);
            }
        });
        popupTemporales.add(menuItemCopiarNombreTemporales);

        menuItemVerTablaTemporales.setText("Ver Tabla");
        menuItemVerTablaTemporales.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemVerTablaTemporalesActionPerformed(evt);
            }
        });
        popupTemporales.add(menuItemVerTablaTemporales);

        menuItemAgregarTablaTemporales.setText("Agregar Tabla");
        menuItemAgregarTablaTemporales.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemAgregarTablaTemporalesActionPerformed(evt);
            }
        });
        popupTemporales.add(menuItemAgregarTablaTemporales);

        menuItemDiccionarioTemporales.setText("Ver Diccionnario de Datos");
        menuItemDiccionarioTemporales.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemDiccionarioTemporalesActionPerformed(evt);
            }
        });
        popupTemporales.add(menuItemDiccionarioTemporales);

        menuItemReferenciasCruzadasTemporales.setText("Ver Referencias Cruzadas de Ambas Tablas");
        menuItemReferenciasCruzadasTemporales.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemReferenciasCruzadasTemporalesActionPerformed(evt);
            }
        });
        popupTemporales.add(menuItemReferenciasCruzadasTemporales);

        jMenu1.setText("File");
        jMenuBar2.add(jMenu1);

        jMenu2.setText("Edit");
        jMenuBar2.add(jMenu2);

        jMenu3.setText("File");
        jMenuBar3.add(jMenu3);

        jMenu4.setText("Edit");
        jMenuBar3.add(jMenu4);

        jMenu5.setText("File");
        jMenuBar4.add(jMenu5);

        jMenu6.setText("Edit");
        jMenuBar4.add(jMenu6);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jLabel5.setText("Tablas Temporales:");

        jLabel1.setText("Escriba la Instrucción de Álgebra Relacional:");

        jLabel4.setText("Tablas Permanentes:");

        jLabel2.setText("Instrucción Equivalente en SQL:");

        scrollpanePermanentes.setComponentPopupMenu(popupPermanentes);

        listaPermanentes.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        listaPermanentes.setInheritsPopupMenu(true);
        scrollpanePermanentes.setViewportView(listaPermanentes);

        scrollpaneTemporales.setComponentPopupMenu(popupTemporales);

        listaTemporales.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        listaTemporales.setInheritsPopupMenu(true);
        scrollpaneTemporales.setViewportView(listaTemporales);

        tablaGeneral.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        scrollpaneGeneral.setViewportView(tablaGeneral);

        textfieldInstruccionAR.setFont(new java.awt.Font("Cambria Math", 0, 13)); // NOI18N

        textfieldInstruccionSQL.setEditable(false);
        textfieldInstruccionSQL.setFont(new java.awt.Font("Cambria Math", 0, 13)); // NOI18N

        botonEjecutar.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        botonEjecutar.setText("Ejecutar");
        botonEjecutar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonEjecutarActionPerformed(evt);
            }
        });

        comboboxOperaciones.setFont(new java.awt.Font("Cambria Math", 1, 12)); // NOI18N
        comboboxOperaciones.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Selección (σ)", "Proyección (Π)", "Guardar (~)", "Renombramiento (ρ)", "Producto Cartesiano (×)", "Union (∪)", "Intersección (∩)", "Diferencia (−)", "División (÷)", "Join (⨝)", "Natural Join(⨝)", "Agregación (Ģ)", "Agrupación (Ģ)", "Alias (=>)", "And (&)", "Or (¡)", "SUM", "COUNT", "MIN", "MAX", "AVG" }));

        botonAgregar.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        botonAgregar.setText("Agregar");
        botonAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonAgregarActionPerformed(evt);
            }
        });

        menuAcercaDe.setText("Acerca de");
        menuAcercaDe.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                menuAcercaDeMouseClicked(evt);
            }
        });
        menuAcercaDe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuAcercaDeActionPerformed(evt);
            }
        });
        jMenuBar1.add(menuAcercaDe);

        menuAyuda.setText("Ayuda");
        menuAyuda.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                menuAyudaMouseClicked(evt);
            }
        });
        jMenuBar1.add(menuAyuda);

        menuSalir.setText("Salir");
        menuSalir.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                menuSalirMouseClicked(evt);
            }
        });
        jMenuBar1.add(menuSalir);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(scrollpaneGeneral)
                            .addComponent(textfieldInstruccionAR)
                            .addComponent(textfieldInstruccionSQL)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(comboboxOperaciones, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(botonAgregar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(botonEjecutar)))
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(scrollpanePermanentes, javax.swing.GroupLayout.PREFERRED_SIZE, 476, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(scrollpaneTemporales, javax.swing.GroupLayout.PREFERRED_SIZE, 495, javax.swing.GroupLayout.PREFERRED_SIZE)))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(scrollpanePermanentes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(scrollpaneTemporales, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(textfieldInstruccionAR, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(botonEjecutar)
                    .addComponent(comboboxOperaciones, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(botonAgregar)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(textfieldInstruccionSQL, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(scrollpaneGeneral, javax.swing.GroupLayout.DEFAULT_SIZE, 296, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void botonEjecutarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonEjecutarActionPerformed
         try {
             System.out.println(ParserAR.parsear(textfieldInstruccionAR.getText()));
            textfieldInstruccionSQL.setText(ParserAR.parsear(textfieldInstruccionAR.getText()));
            tabla_actual = Conexion.ejecutaQuery(textfieldInstruccionSQL.getText());
            displayInfoTabla(tabla_actual);
            this.updatear();
        } catch (SQLException | ClassNotFoundException ex) {
            mensajeError(ex.getMessage());
        } catch (IllegalArgumentException ex){
            UI.getInstance().displayError("Al parsear la instruccion \""+textfieldInstruccionAR.getText()+"\":\n"+ex.getMessage());
        }
    }//GEN-LAST:event_botonEjecutarActionPerformed

    private void botonAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonAgregarActionPerformed
        try{
            String instruccion;
            switch(comboboxOperaciones.getSelectedItem().toString()){
                case "Selección (σ)":
                    instruccion = ParserAR.SELECCION_FUNCION;
                    break;
                case "Proyección (Π)":
                    instruccion = ParserAR.PROYECCION_FUNCION;
                    break;
                case "Guardar (~)":
                    instruccion = ParserAR.GUARDAR_FUNCION;
                    break;
                case "Renombramiento (ρ)":
                    instruccion = ParserAR.RENOMBRAMIENTO_FUNCION;
                    break;
                case "Producto Cartesiano (×)":
                    instruccion = ParserAR.PRODUCTO_CARTESIANO_FUNCION;
                    break;
                case "Union (∪)":
                    instruccion = ParserAR.UNION_FUNCION;
                    break;
                case "Intersección (∩)":
                    instruccion = ParserAR.INTERSECCION_FUNCION;
                    break;
                case "Diferencia (−)":
                    instruccion = ParserAR.DIFERENCIA_FUNCION;
                    break;
                case "División (÷)":
                    instruccion = ParserAR.DIVISION_FUNCION;
                    break;
                case "Join (⨝)":
                    instruccion = ParserAR.JOIN_FUNCION;
                    break;
                case "Natural Join(⨝)":
                    instruccion = ParserAR.NATURAL_JOIN_FUNCION;
                    break;
                case "Agregación (Ģ)":
                case "Agrupación (Ģ)":
                    instruccion = ParserAR.AGREGACION_FUNCION;
                    break;
                case "Alias (=>)":
                    instruccion = ParserAR.ALIAS_FUNCION;
                    break;
                case "And (&)":
                    instruccion = ParserAR.AND_FUNCION;
                    break;
                case "Or (¡)":
                    instruccion = ParserAR.OR_FUNCION;
                    break;
                case "SUM":
                    instruccion = ParserAR.SUM_FUNCION;
                    break;
                case "COUNT":
                    instruccion = ParserAR.COUNT_FUNCION;
                    break;
                case "MIN":
                    instruccion = ParserAR.MIN_FUNCION;
                    break;
                case "MAX":
                    instruccion = ParserAR.MAX_FUNCION;
                    break;
                case "AVG":
                    instruccion = ParserAR.AVG_FUNCION;
                    break;
                default:
                    instruccion = "";
            }
            textfieldInstruccionAR.getDocument().insertString(textfieldInstruccionAR.getCaretPosition(), instruccion, null);
        }catch(BadLocationException exception){
            UI.getInstance().displayError("Interfaz Gráfica: "+exception.getMessage());
        }
    }//GEN-LAST:event_botonAgregarActionPerformed

    private void menuItemAgregarTablaPermanentesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemAgregarTablaPermanentesActionPerformed
        new VentanaAgregarTabla(this, VentanaAgregarTabla.PERMANENTE).setVisible(true);
    }//GEN-LAST:event_menuItemAgregarTablaPermanentesActionPerformed

    private void menuItemAgregarTablaTemporalesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemAgregarTablaTemporalesActionPerformed
        new VentanaAgregarTabla(this, VentanaAgregarTabla.TEMPORAL).setVisible(true);
    }//GEN-LAST:event_menuItemAgregarTablaTemporalesActionPerformed

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
    }//GEN-LAST:event_formWindowClosed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
    }//GEN-LAST:event_formWindowClosing

    private void menuItemVerTablaPermanentesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemVerTablaPermanentesActionPerformed
        try {
            displayInfoTabla(Conexion.ejecutaQuery("select * from "+listaPermanentes.getSelectedValue().toString()));
        } catch (SQLException | ClassNotFoundException ex) {
            UI.getInstance().displayError("Error al desplegar informacion de tabla: "+ex.getMessage());
        }
    }//GEN-LAST:event_menuItemVerTablaPermanentesActionPerformed

    private void menuItemVerTablaTemporalesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemVerTablaTemporalesActionPerformed
        try {
            displayInfoTabla(Conexion.ejecutaQuery("select * from "+listaTemporales.getSelectedValue().toString()));
        } catch (SQLException | ClassNotFoundException ex) {
            UI.getInstance().displayError("Error al desplegar informacion de tabla: "+ex.getMessage());
        }
    }//GEN-LAST:event_menuItemVerTablaTemporalesActionPerformed

    private void menuItemCopiarNombreTemporalesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemCopiarNombreTemporalesActionPerformed
        try{
            textfieldInstruccionAR.getDocument().insertString(textfieldInstruccionAR.getCaretPosition(), listaTemporales.getSelectedValue().toString(), null);
        }catch(BadLocationException exception){
            UI.getInstance().displayError("Interfaz Gráfica: "+exception.getMessage());
        }
    }//GEN-LAST:event_menuItemCopiarNombreTemporalesActionPerformed

    private void menuItemCopiarNombrePermanentesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemCopiarNombrePermanentesActionPerformed
        try{
            textfieldInstruccionAR.getDocument().insertString(textfieldInstruccionAR.getCaretPosition(), listaPermanentes.getSelectedValue().toString(), null);
        }catch(BadLocationException exception){
            UI.getInstance().displayError("Interfaz Gráfica: "+exception.getMessage());
        }
    }//GEN-LAST:event_menuItemCopiarNombrePermanentesActionPerformed

    private void menuItemDiccionarioPermanentesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemDiccionarioPermanentesActionPerformed
        VentanaDiccionario vn = new VentanaDiccionario(VentanaDiccionario.PERMANENTE);
        vn.setVisible(true);
    }//GEN-LAST:event_menuItemDiccionarioPermanentesActionPerformed

    private void menuItemDiccionarioTemporalesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemDiccionarioTemporalesActionPerformed
        VentanaDiccionario vn = new VentanaDiccionario(VentanaDiccionario.TEMPORAL);
        vn.setVisible(true);
    }//GEN-LAST:event_menuItemDiccionarioTemporalesActionPerformed

    private void menuAcercaDeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuAcercaDeActionPerformed

    }//GEN-LAST:event_menuAcercaDeActionPerformed

    private void menuAcercaDeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuAcercaDeMouseClicked
        UI.getInstance().displayInfo("Instituto Tecnológico de Costa Rica. \n\nIngeniería en Computación."
                +"\t"+ "\n\nInterprete Álgebra Relacional.\n\nVersión 5.\n\nFecha de Creación: \n30 de Setiembre 2017."
                + "\n\nCreada por:\nAlejandro Schmidt.\nKenneth Fernández.");
    }//GEN-LAST:event_menuAcercaDeMouseClicked

    private void menuAyudaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuAyudaMouseClicked
        try {
            Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler "
                    + "C:\\Users\\D\\Desktop\\manual_de_usuario_interpretador_álgebra_relacional.pdf");
        } catch (IOException e) {
            UI.getInstance().displayError(e.getMessage());
        }
    }//GEN-LAST:event_menuAyudaMouseClicked

    private void menuItemReferenciasCruzadasPermanentesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemReferenciasCruzadasPermanentesActionPerformed
        VentanaDiccionario vn = new VentanaDiccionario(VentanaDiccionario.REFERENCIAS_CRUZADAS);
        vn.setVisible(true);
    }//GEN-LAST:event_menuItemReferenciasCruzadasPermanentesActionPerformed

    private void menuItemReferenciasCruzadasTemporalesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemReferenciasCruzadasTemporalesActionPerformed
        VentanaDiccionario vn = new VentanaDiccionario(VentanaDiccionario.REFERENCIAS_CRUZADAS);
        vn.setVisible(true);
    }//GEN-LAST:event_menuItemReferenciasCruzadasTemporalesActionPerformed

    private void menuSalirMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuSalirMouseClicked
        this.dispose();
    }//GEN-LAST:event_menuSalirMouseClicked

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
            java.util.logging.Logger.getLogger(VentanaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VentanaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VentanaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VentanaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VentanaPrincipal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botonAgregar;
    private javax.swing.JButton botonEjecutar;
    private javax.swing.JComboBox comboboxOperaciones;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenu jMenu6;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuBar jMenuBar2;
    private javax.swing.JMenuBar jMenuBar3;
    private javax.swing.JMenuBar jMenuBar4;
    private javax.swing.JList listaPermanentes;
    private javax.swing.JList listaTemporales;
    private javax.swing.JMenu menuAcercaDe;
    private javax.swing.JMenu menuAyuda;
    private javax.swing.JMenuItem menuItemAgregarTablaPermanentes;
    private javax.swing.JMenuItem menuItemAgregarTablaTemporales;
    private javax.swing.JMenuItem menuItemCopiarNombrePermanentes;
    private javax.swing.JMenuItem menuItemCopiarNombreTemporales;
    private javax.swing.JMenuItem menuItemDiccionarioPermanentes;
    private javax.swing.JMenuItem menuItemDiccionarioTemporales;
    private javax.swing.JMenuItem menuItemReferenciasCruzadasPermanentes;
    private javax.swing.JMenuItem menuItemReferenciasCruzadasTemporales;
    private javax.swing.JMenuItem menuItemVerTablaPermanentes;
    private javax.swing.JMenuItem menuItemVerTablaTemporales;
    private javax.swing.JMenu menuSalir;
    private javax.swing.JPopupMenu popupPermanentes;
    private javax.swing.JPopupMenu popupTemporales;
    private javax.swing.JScrollPane scrollpaneGeneral;
    private javax.swing.JScrollPane scrollpanePermanentes;
    private javax.swing.JScrollPane scrollpaneTemporales;
    private javax.swing.JTable tablaGeneral;
    private javax.swing.JTextField textfieldInstruccionAR;
    private javax.swing.JTextField textfieldInstruccionSQL;
    // End of variables declaration//GEN-END:variables
}
