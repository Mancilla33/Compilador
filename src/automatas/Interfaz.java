package automatas;

import automatas.analizadorLS.Analizador;
import automatas.analizadorLS.CodigoIntermedio;

import java.awt.Component;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import static java.util.Locale.filter;
import java.util.Map;
import java.util.StringTokenizer;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

public class Interfaz extends javax.swing.JFrame implements KeyListener {

    JFileChooser seleccionar = new JFileChooser();
    File archivo;
    FileInputStream entrada;
    FileOutputStream salida;
    Automatas auto;
    String tx = "";
    boolean guarda = true;
    Analizador an;
    NumeroLineaC nL;
    CodigoIntermedio codigo;
    segunda s;

    public Interfaz(Automatas a) {
        initComponents();
        nL = new NumeroLineaC(Cuadro, espec1);
        an = new Analizador(this);
        Scroll.setRowHeaderView(nL);
        Cuadro.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createEmptyBorder(),
                javax.swing.BorderFactory.createEmptyBorder(1, 5, 1, 1)));
        Scroll.getVerticalScrollBar().setUI(new MyScrollBarUI());
        Scroll.getHorizontalScrollBar().setUI(new MyScrollBarUI());
        setVisible(true);
        setLocationRelativeTo(null);
        auto = a;
        Cuadro.addKeyListener(this);
        java.awt.Image icon = new ImageIcon(getClass().getResource("logo.png")).getImage();
        setIconImage(icon);
    }

    public String AbrirArchivo(File archivo) {
        String documento = "";
        try {
            entrada = new FileInputStream(archivo);
            int ascci;
            while ((ascci = entrada.read()) != -1) {
                char caracter = (char) ascci;
                documento += caracter;
            }
        } catch (Exception e) {

        }
        return documento;
    }

    public String GuardarArchivo(File archivo, String documento) {
        String mensaje = null;
        try {
            salida = new FileOutputStream(archivo);
            byte[] bytxt = documento.getBytes();
            salida.write(bytxt);
            mensaje = "Archivo Guardado";
            tx = documento;
            guarda = true;
        } catch (Exception e) {

        }
        return mensaje;
    }

    // <editor-fold defaultstate="collapsed" desc="Generated
    // Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Guardar = new javax.swing.JButton();
        Cerrar = new javax.swing.JButton();
        Scroll = new javax.swing.JScrollPane();
        Cuadro = new javax.swing.JTextArea();
        jLabel2 = new javax.swing.JLabel();
        espec1 = new javax.swing.JLabel();
        espec = new javax.swing.JLabel();
        NombreArchivo = new javax.swing.JLabel();
        nuevo = new javax.swing.JButton();
        Abrir = new javax.swing.JButton();
        cFuente = new javax.swing.JButton();
        bdebug = new javax.swing.JButton();
        fondoL = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setBackground(new java.awt.Color(80, 80, 80));
        setMaximumSize(new java.awt.Dimension(750, 470));
        setMinimumSize(new java.awt.Dimension(750, 470));
        setUndecorated(true);
        setResizable(false);
        setSize(new java.awt.Dimension(750, 470));
        getContentPane().setLayout(null);

        Guardar.setBackground(new java.awt.Color(80, 80, 80));
        Guardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/automatas/guardar.png"))); // NOI18N
        Guardar.setToolTipText(" Guardar archivo JJS");
        Guardar.setBorder(null);
        Guardar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Guardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                GuardarActionPerformed(evt);
            }
        });
        getContentPane().add(Guardar);
        Guardar.setBounds(10, 50, 30, 30);

        Cerrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/automatas/btnC.jpg"))); // NOI18N
        Cerrar.setBorder(null);
        Cerrar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Cerrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CerrarActionPerformed(evt);
            }
        });
        getContentPane().add(Cerrar);
        Cerrar.setBounds(720, 10, 20, 20);

        Scroll.setBackground(new java.awt.Color(80, 80, 80));
        Scroll.setBorder(null);

        Cuadro.setBackground(new java.awt.Color(80, 80, 80));
        Cuadro.setColumns(20);
        Cuadro.setFont(new java.awt.Font("Consolas", 0, 14)); // NOI18N
        Cuadro.setForeground(new java.awt.Color(255, 255, 255));
        Cuadro.setRows(5);
        Cuadro.setToolTipText("");
        Cuadro.setBorder(null);
        Cuadro.setCaretColor(new java.awt.Color(0, 168, 255));
        Scroll.setViewportView(Cuadro);

        getContentPane().add(Scroll);
        Scroll.setBounds(10, 100, 730, 340);

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/automatas/logoPNG.png"))); // NOI18N
        getContentPane().add(jLabel2);
        jLabel2.setBounds(10, 10, 20, 20);

        espec1.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        espec1.setForeground(new java.awt.Color(204, 204, 204));
        espec1.setText(" ");
        getContentPane().add(espec1);
        espec1.setBounds(10, 440, 620, 20);

        espec.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        espec.setForeground(new java.awt.Color(204, 204, 204));
        espec.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        espec.setText("   0 líneas");
        getContentPane().add(espec);
        espec.setBounds(640, 440, 90, 20);

        NombreArchivo.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        NombreArchivo.setForeground(new java.awt.Color(204, 204, 204));
        NombreArchivo.setText("NuevoArchivo.jjs");
        getContentPane().add(NombreArchivo);
        NombreArchivo.setBounds(40, 10, 670, 20);

        nuevo.setBackground(new java.awt.Color(80, 80, 80));
        nuevo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/automatas/nuevo.png"))); // NOI18N
        nuevo.setToolTipText("Crear nuevo archivo JJS");
        nuevo.setBorder(null);
        nuevo.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        nuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nuevoActionPerformed(evt);
            }
        });
        getContentPane().add(nuevo);
        nuevo.setBounds(90, 50, 30, 30);

        Abrir.setBackground(new java.awt.Color(80, 80, 80));
        Abrir.setForeground(new java.awt.Color(80, 80, 80));
        Abrir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/automatas/abrir.png"))); // NOI18N
        Abrir.setToolTipText("Abrir archivo JJS");
        Abrir.setBorder(null);
        Abrir.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Abrir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AbrirActionPerformed(evt);
            }
        });
        getContentPane().add(Abrir);
        Abrir.setBounds(50, 50, 30, 30);

        cFuente.setBackground(new java.awt.Color(80, 80, 80));
        cFuente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/automatas/fuente.png"))); // NOI18N
        cFuente.setToolTipText("Cambiar formato de fuente");
        cFuente.setBorder(null);
        cFuente.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cFuente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cFuenteActionPerformed(evt);
            }
        });
        getContentPane().add(cFuente);
        cFuente.setBounds(130, 50, 30, 30);

        bdebug.setBackground(new java.awt.Color(80, 80, 80));
        bdebug.setIcon(new javax.swing.ImageIcon(getClass().getResource("/automatas/debug.png"))); // NOI18N
        bdebug.setToolTipText("Codigo Intermedio");
        bdebug.setBorder(null);
        bdebug.setVisible(false);
        bdebug.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        bdebug.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bdebugActionPerformed(evt);
            }
        });
        getContentPane().add(bdebug);
        bdebug.setBounds(170, 50, 30, 30);

        fondoL.setIcon(new javax.swing.ImageIcon(getClass().getResource("/automatas/Fondo.jpg"))); // NOI18N
        fondoL.setText("1");
        fondoL.setMaximumSize(new java.awt.Dimension(750, 470));
        fondoL.setMinimumSize(new java.awt.Dimension(750, 470));
        fondoL.setPreferredSize(new java.awt.Dimension(750, 470));
        getContentPane().add(fondoL);
        fondoL.setBounds(0, 0, 750, 470);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void AbrirActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_AbrirActionPerformed
        // seleccionar.addChoosableFileFilter(new FileNameExtensionFilter("*.jjs",
        // "JJS"));
        FileNameExtensionFilter filter = new FileNameExtensionFilter("*.jjs", "JJS");
        seleccionar.setFileFilter(filter);
        if (seleccionar.showDialog(null, "Abrir") == JFileChooser.APPROVE_OPTION) {
            archivo = seleccionar.getSelectedFile();
            if (archivo.canRead()) {
                if (archivo.getName().endsWith(".jjs")) {
                    String documento = AbrirArchivo(archivo);
                    Cuadro.setText(documento);

                    guarda = true;
                    this.setTitle(archivo.getName());
                    NombreArchivo.setText(archivo.getName());
                    espec.setText((Cuadro.getText().length() + 1) + " carácteres     "
                            + (new StringTokenizer(Cuadro.getText()).countTokens()) + " palabras     "
                            + (lineas(Cuadro.getText())) + " líneas");
                    codigo = new CodigoIntermedio();
                    nL.errores = an.Analiza(Cuadro.getText() + "\n", codigo);
                    nL.repaint();
                } else {
                    JOptionPane.showMessageDialog(null, "Solo se aceptan archivos .jjs");
                }
            }
        }
    }// GEN-LAST:event_AbrirActionPerformed

    private void CerrarActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_CerrarActionPerformed
        auto.cerrar(this);
    }// GEN-LAST:event_CerrarActionPerformed

    private void GuardarActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_GuardarActionPerformed
        FileNameExtensionFilter filter = new FileNameExtensionFilter("*.jjs", "JJS");
        seleccionar.setFileFilter(filter);
        if (seleccionar.showDialog(null, "Guardar") == JFileChooser.APPROVE_OPTION) {
            archivo = seleccionar.getSelectedFile();
            System.out.print(seleccionar.getSelectedFile());
            if (archivo.getName().endsWith(".jjs")) {
                String Documento = Cuadro.getText();
                String mensaje = GuardarArchivo(archivo, Documento);
                if (mensaje != null) {
                    NombreArchivo.setText(archivo.getName());
                    this.setTitle(archivo.getName());
                    JOptionPane.showMessageDialog(null, mensaje);
                } else {
                    JOptionPane.showMessageDialog(null, "Solo se aceptan archivos .jjs");
                }
            } else {
                if (archivo.getName().contains("."))
                    JOptionPane.showMessageDialog(null, "Solo se aceptan archivos .jjs");
                else {
                    archivo = seleccionar.getSelectedFile();
                    archivo = new File(archivo + ".jjs");
                    String Documento = Cuadro.getText();
                    String mensaje = GuardarArchivo(archivo, Documento);
                    if (mensaje != null) {
                        NombreArchivo.setText(archivo.getName());
                        this.setTitle(archivo.getName());
                        JOptionPane.showMessageDialog(null, mensaje);

                    }
                }
            }
        }
    }// GEN-LAST:event_GuardarActionPerformed

    private void cFuenteActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_cFuenteActionPerformed
        JFontChooser jFC = new JFontChooser();
        int res = jFC.showDialog(Cuadro);
        if (res == 0)
            Cuadro.setFont(jFC.getSelectedFont());
    }// GEN-LAST:event_cFuenteActionPerformed

    private void bdebugActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_bdebugActionPerformed
        s = new segunda();
        s.setVisible(true);
        s.Cuadro.setText(codigo.codigo);
        s.Cuadro2.setText(Cuadro.getText());

    }// GEN-LAST:event_bdebugActionPerformed

    private void nuevoActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_nuevoActionPerformed
        auto.newInterfaz();
    }// GEN-LAST:event_nuevoActionPerformed

    public void keyTyped(KeyEvent e) {
        if (e.getKeyChar() == (char) 9) {
            int pos = Cuadro.getCaretPosition();

            Cuadro.setText(Cuadro.getText().replace((char) 9 + "", "   "));
            Cuadro.setCaretPosition(pos + 2);
        }

    }

    public void keyPressed(KeyEvent e) {

        if (e.getKeyChar() == '\n') {
            String cuad = Cuadro.getText();
            int pos = Cuadro.getCaretPosition();
            if (pos != 0) {
                if (cuad.charAt(pos - 1) == '{') {
                    String p = cuad.substring(0, pos);
                    p += "   \n}";
                    p += cuad.substring(pos);
                    Cuadro.setText(p);
                    Cuadro.setCaretPosition(pos + 2);
                }
            }

        }

    }

    int contadorRel = 0;

    public void keyReleased(KeyEvent e) {
        String cadena = Cuadro.getText();

        if (guarda) {
            if (!tx.equals(cadena)) {
                // System.out.println("h");
                this.setTitle(this.getTitle() + " (Sin guardar)");
                NombreArchivo.setText(this.getTitle());
                guarda = false;

            }
        }

        espec.setText((lineas(Cuadro.getText())) + " líneas");

        contadorRel++;
        if (contadorRel == 6 || e.getKeyChar() == 10) {
            contadorRel = 0;
            codigo = new CodigoIntermedio();
            nL.errores = an.Analiza(Cuadro.getText() + "\n", codigo);
        }
        nL.repaint();
    }

    public int lineas(String cadena) {
        int count = 1;
        for (int i = 0; i < cadena.length(); i++) {
            if (cadena.charAt(i) == 10) {
                count++;
            }
        }
        return count;
    }

    // public void marcar(){
    // Cuadro.add();
    // }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Abrir;
    private javax.swing.JButton Cerrar;
    private javax.swing.JTextArea Cuadro;
    private javax.swing.JButton Guardar;
    private javax.swing.JLabel NombreArchivo;
    private javax.swing.JScrollPane Scroll;
    private javax.swing.JButton cFuente;
    public javax.swing.JButton bdebug;
    private javax.swing.JLabel espec;
    private javax.swing.JLabel espec1;
    private javax.swing.JLabel fondoL;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JButton nuevo;
    // End of variables declaration//GEN-END:variables
}

class segunda extends javax.swing.JFrame {

    private javax.swing.JButton Cerrar;
    public javax.swing.JTextArea Cuadro;
    private javax.swing.JScrollPane Scroll;
    public javax.swing.JTextArea Cuadro2;
    private javax.swing.JScrollPane Scroll2;
    private javax.swing.JLabel fondoL;

    public segunda() {
        initComponent();

        Cuadro.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createEmptyBorder(),
                javax.swing.BorderFactory.createEmptyBorder(1, 5, 1, 1)));
        Scroll.getVerticalScrollBar().setUI(new MyScrollBarUI());
        Scroll.getHorizontalScrollBar().setUI(new MyScrollBarUI());
        setLocationRelativeTo(null);

        Cuadro2.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createEmptyBorder(),
                javax.swing.BorderFactory.createEmptyBorder(1, 5, 1, 1)));
        Scroll2.getVerticalScrollBar().setUI(new MyScrollBarUI());
        Scroll2.getHorizontalScrollBar().setUI(new MyScrollBarUI());
        setLocationRelativeTo(null);

    }

    private void initComponent() {
        Cerrar = new javax.swing.JButton();
        Scroll = new javax.swing.JScrollPane();
        Scroll2 = new javax.swing.JScrollPane();
        Cuadro = new javax.swing.JTextArea();
        Cuadro2 = new javax.swing.JTextArea();
        fondoL = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setBackground(new java.awt.Color(80, 80, 80));
        setMaximumSize(new java.awt.Dimension(750, 470));
        setMinimumSize(new java.awt.Dimension(750, 470));
        setUndecorated(true);
        setResizable(false);
        setSize(new java.awt.Dimension(750, 470));
        getContentPane().setLayout(null);

        Cerrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/automatas/btnC.jpg"))); // NOI18N
        Cerrar.setBorder(null);
        Cerrar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Cerrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CerrarActionPerformed(evt);
            }
        });
        getContentPane().add(Cerrar);
        Cerrar.setBounds(720, 10, 20, 20);
        

        Scroll.setBackground(new java.awt.Color(80, 80, 80));
        Scroll.setBorder(null);

        Cuadro.setBackground(new java.awt.Color(80, 80, 80));
        Cuadro.setColumns(20);
        Cuadro.setFont(new java.awt.Font("Consolas", 0, 14)); // NOI18N
        Cuadro.setForeground(new java.awt.Color(255, 255, 255));
        Cuadro.setRows(5);
        Cuadro.setToolTipText("");
        Cuadro.setBorder(null);
        Cuadro.setCaretColor(new java.awt.Color(0, 168, 255));
        Scroll.setViewportView(Cuadro2);

        getContentPane().add(Scroll);
        Scroll.setBounds(10, 50, 730, 50);

        Scroll2.setBackground(new java.awt.Color(80, 80, 80));
        Scroll2.setBorder(null);

        Cuadro2.setBackground(new java.awt.Color(80, 80, 80));
        Cuadro2.setColumns(20);
        Cuadro2.setFont(new java.awt.Font("Consolas", 0, 14)); // NOI18N
        Cuadro2.setForeground(new java.awt.Color(255, 255, 255));
        Cuadro2.setRows(5);
        Cuadro2.setToolTipText("");
        Cuadro2.setBorder(null);
        Cuadro2.setCaretColor(new java.awt.Color(0, 168, 255));
        Scroll2.setViewportView(Cuadro);

        getContentPane().add(Scroll2);
        Scroll2.setBounds(10, 110, 730, 350);

        fondoL.setIcon(new javax.swing.ImageIcon(getClass().getResource("/automatas/Fondo2.jpg"))); // NOI18N
        fondoL.setText("1");
        fondoL.setMaximumSize(new java.awt.Dimension(750, 470));
        fondoL.setMinimumSize(new java.awt.Dimension(750, 470));
        fondoL.setPreferredSize(new java.awt.Dimension(750, 470));
        getContentPane().add(fondoL);
        fondoL.setBounds(0, 0, 750, 470);

        

        
        
    }

    private void CerrarActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_CerrarActionPerformed
        this.setVisible(false);
        this.dispose();
    }// GEN-LAST:event_CerrarActionPerformed

}
