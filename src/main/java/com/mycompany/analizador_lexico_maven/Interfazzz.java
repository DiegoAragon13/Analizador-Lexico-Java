/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.analizador_lexico_maven;

import javax.swing.*;
import java.awt.event.*;
import java.util.List;
/**
 *
 * @author diego
 */
public class Interfazzz extends javax.swing.JFrame {
    
    NumeroLinea numeroLinea;
    private Analizador analizador;
    
    public Interfazzz() {
        initComponents();
        numeroLinea = new NumeroLinea(jTextArea1);
        jScrollPane1.setRowHeaderView(numeroLinea);
        analizador = new Analizador();
        
        // Agregar el ActionListener al botón Analizar
        btnAnalizar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                analizarTexto();
            }
        });
        
        // l botón Limpiar
        btnLimpiar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jTextArea1.setText("");
                resultados.setText("");
            }
        });
        
        
        // Agregar el ActionListener al botón Salir
        btnSalir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }
    
   private void analizarTexto() {
    // Obtener el texto del TextArea
    String texto = jTextArea1.getText();
    
    // Limpiar el área de resultados
    resultados.setText("");
    StringBuilder resultado = new StringBuilder();//Crear una cadena de resultados eficiente tipo buffer 
    
    // Realizar el análisis
    analizador = new Analizador(); // Crear nueva instancia para limpiar las listas anteriores
    
    // Detectar todos los elementos
    resultado.append("Palabras reservadas encontradas(100): ")
            .append(analizador.detectarPalabrasReservadas(texto))//append para agregar al stringbuilder tipo concadenar pero eficiente
            .append("\n\n");
    
    analizador.detectarNumeros(texto);
    resultado.append("Números encontrados(700): ")
            .append(analizador.getNumeros())
            .append("\n\n");
    
    analizador.detectarIdentificadores(texto);
    resultado.append("Identificadores encontrados(900): ")
            .append(analizador.getIdentificadores())
            .append("\n\n");
    
    analizador.detectarOperadoresAsignacion(texto);
    resultado.append("Operadores de asignación encontrados(600): ")
            .append(analizador.getOperadoresAsignacion())
            .append("\n\n");
    
    // Nueva detección de operadores aritméticos
    analizador.detectarOperadoresAritmeticos(texto);
    resultado.append("Operadores aritméticos encontrados(500): ")
            .append(analizador.getOperadoresAritmeticos())
            .append("\n\n");
    
    analizador.detectarSignosPuntuacion(texto);
    resultado.append("Signos de puntuación encontrados(400): ")
            .append(analizador.getSignosPuntuacion())
            .append("\n\n");
    
    // Mostrar errores si existen
    List<String> errores = analizador.obtenerErrores();
    if (!errores.isEmpty()) {
        resultado.append("Errores encontrados:\n");
        for (String error : errores) {
            resultado.append(error).append("\n");
        }
    } else {
        resultado.append("No se encontraron errores.");
    }
    
    // Mostrar los resultados en el área de resultados
    resultados.setText(resultado.toString());
}
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        btnLimpiar = new javax.swing.JButton();
        btnAnalizar = new javax.swing.JButton();
        btnSalir = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        resultados = new javax.swing.JTextArea();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(96, 86, 120));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 80, 510, 350));

        btnLimpiar.setBackground(new java.awt.Color(138, 191, 163));
        btnLimpiar.setText("Limpiar");
        jPanel1.add(btnLimpiar, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 350, 250, 30));

        btnAnalizar.setBackground(new java.awt.Color(138, 191, 163));
        btnAnalizar.setText("Analizar");
        jPanel1.add(btnAnalizar, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 300, 250, 30));

        btnSalir.setBackground(new java.awt.Color(138, 191, 163));
        btnSalir.setText("Salir");
        jPanel1.add(btnSalir, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 400, 250, 30));

        resultados.setEditable(false);
        resultados.setColumns(20);
        resultados.setRows(5);
        jScrollPane2.setViewportView(resultados);

        jPanel1.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 80, 270, 200));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel1.setText("ANALIZADOR LÉXICO");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 10, -1, -1));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 880, 510));

        pack();
    }// </editor-fold>//GEN-END:initComponents

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
            java.util.logging.Logger.getLogger(Interfazzz.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Interfazzz.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Interfazzz.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Interfazzz.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Interfazzz().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAnalizar;
    private javax.swing.JButton btnLimpiar;
    private javax.swing.JButton btnSalir;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextArea resultados;
    // End of variables declaration//GEN-END:variables
}
