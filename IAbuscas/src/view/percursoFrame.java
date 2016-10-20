/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import mapa.CriadorMapa;
import mapa.Mapa;
import mapa.No;

/**
 *
 * @author FREE
 */
public class percursoFrame extends javax.swing.JFrame {
    List< JPanel > mapaPanel = new ArrayList<>();
    List< JLabel > mapaLabel = new ArrayList<>();
    private static percursoFrame instancia = null;
    /**
     * Creates new form percursoFrame
     */
    
    private Color bufferColor;
    private JPanel selecionado = null;
    
    private percursoFrame(Mapa mapa) {
        initComponents();
        meuInit(mapa);
    }
    
    public static percursoFrame getInstance()
    {
        if (instancia == null)
        {
            throw new IllegalArgumentException("Nao foi setado!!!");
        }
        return( instancia );
    }
    
    private void fullScreen()
    {
        //hard code, uma hora eu conserto
        getContentPane().setPreferredSize( Toolkit.getDefaultToolkit().getScreenSize());
        pack();
        setResizable(false);
        this.toFront();
        
        int upperPaneSize = 10;
        
        int bottomPaneSize = 10;
        
        int middlePaneSize = 100 - upperPaneSize - bottomPaneSize;
        
        int totalHeight = Toolkit.getDefaultToolkit().getScreenSize().height;
        
        int totalWidth = Toolkit.getDefaultToolkit().getScreenSize().width;
        
        this.setLayout(null);
        
        taMsg.setLocation(0,0);
        taMsg.setSize(new Dimension(totalWidth,new Double(totalHeight*0.1).intValue()));
        
        pDesenho.setLocation(0,taMsg.getSize().height);
        pDesenho.setSize(new Dimension(totalWidth,new Double(totalHeight*0.8).intValue()));
        
        pOptionsPane.setLocation(0,pDesenho.getLocation().y + pDesenho.getSize().height);
        pOptionsPane.setSize(new Dimension(totalWidth,totalHeight - taMsg.getSize().height - pDesenho.getSize().height));
        
        GridBagConstraints g = new GridBagConstraints();
    }
    
    public static void setInstanceDim(Mapa mapa)
    {
        if (instancia != null)
        {
            instancia.dispose();
        }
        instancia = new percursoFrame(mapa);
        System.out.println("instancia de visualizacao criada!");
        System.out.println("deixando visualização visivel ja...mudar dps");
        instancia.setVisible(true);
    }
    
    private void meuInit(Mapa mapa)
    {
        int linhas = mapa.getLinhas();
        int colunas = mapa.getColunas();

        pDesenho.setLayout(new GridLayout(linhas,colunas,1,1));
        JPanel aux;
        JLabel label;
        No[][] noMatrix = mapa.getMatriz();
        
        int linhaRelacionada;
        int colunaRelacionada;
        for (int i=0;i<linhas*colunas;i++)
        {
            linhaRelacionada = (i-i%colunas)/colunas;
            colunaRelacionada = i%colunas;
            No noRelacionado = noMatrix[linhaRelacionada][colunaRelacionada];
            
            aux = new JPanel();
            aux.setLayout(new GridLayout(1,1));
            
            switch (noRelacionado.getTipo())
            {
                case LAVA :
                    aux.setBackground(Color.RED);
                    break;
                case PANTANO :
                    aux.setBackground(Color.BLUE);
                    break;
                case MONTANHOSO :
                    aux.setBackground(new Color(156, 93, 82));//MARROM
                    break;
                case PLANO :
                    aux.setBackground(Color.GREEN);
                    break;
            }
            
            label = new JLabel(Integer.toString(i));
            label.setHorizontalAlignment(SwingConstants.CENTER);
            label.setVerticalAlignment(SwingConstants.CENTER);
            aux.add(label);
            
            mapaPanel.add(aux);
            mapaLabel.add(label);
            
            pDesenho.add(aux);
        }
        
        fullScreen();
        
    }
    
    public void changeCell(int id)
    {
        if (selecionado != null)
        {
            selecionado.setBackground(bufferColor);
        }
        selecionado = mapaPanel.get(id);
        bufferColor = selecionado.getBackground();
        selecionado.setBackground(Color.BLACK);
    }
    
    public void changeMsg(String msg)
    {
        taMsg.setText(msg);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pDesenho = new javax.swing.JPanel();
        pOptionsPane = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        btMostrarCell = new javax.swing.JRadioButton();
        pTopPanel = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        taMsg = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        pDesenho.setBackground(new java.awt.Color(204, 204, 204));

        javax.swing.GroupLayout pDesenhoLayout = new javax.swing.GroupLayout(pDesenho);
        pDesenho.setLayout(pDesenhoLayout);
        pDesenhoLayout.setHorizontalGroup(
            pDesenhoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        pDesenhoLayout.setVerticalGroup(
            pDesenhoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 213, Short.MAX_VALUE)
        );

        pOptionsPane.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setText("Control Label");

        btMostrarCell.setSelected(true);
        btMostrarCell.setText("Mostrar valor de celulas");
        btMostrarCell.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btMostrarCellActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pOptionsPaneLayout = new javax.swing.GroupLayout(pOptionsPane);
        pOptionsPane.setLayout(pOptionsPaneLayout);
        pOptionsPaneLayout.setHorizontalGroup(
            pOptionsPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pOptionsPaneLayout.createSequentialGroup()
                .addGroup(pOptionsPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pOptionsPaneLayout.createSequentialGroup()
                        .addGap(153, 153, 153)
                        .addComponent(jLabel1))
                    .addGroup(pOptionsPaneLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(btMostrarCell)))
                .addContainerGap(184, Short.MAX_VALUE))
        );
        pOptionsPaneLayout.setVerticalGroup(
            pOptionsPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pOptionsPaneLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btMostrarCell)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 56, Short.MAX_VALUE)
                .addComponent(jLabel1))
        );

        pTopPanel.setBackground(new java.awt.Color(51, 255, 51));

        taMsg.setEditable(false);
        taMsg.setColumns(20);
        taMsg.setRows(5);
        jScrollPane1.setViewportView(taMsg);

        javax.swing.GroupLayout pTopPanelLayout = new javax.swing.GroupLayout(pTopPanel);
        pTopPanel.setLayout(pTopPanelLayout);
        pTopPanelLayout.setHorizontalGroup(
            pTopPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
        );
        pTopPanelLayout.setVerticalGroup(
            pTopPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 107, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pDesenho, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(pOptionsPane, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(pTopPanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(pTopPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(pDesenho, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(pOptionsPane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btMostrarCellActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btMostrarCellActionPerformed
        // TODO add your handling code here:
        btMostrarCell.setEnabled(false);
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run(){
                for (JLabel label : mapaLabel)
                {
                    label.setVisible(btMostrarCell.isSelected());
                }
                btMostrarCell.setEnabled(true);
            }
        });
        
        
    }//GEN-LAST:event_btMostrarCellActionPerformed

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
            java.util.logging.Logger.getLogger(percursoFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(percursoFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(percursoFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(percursoFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Mapa mapa = CriadorMapa.criarMapa();
                percursoFrame f = new percursoFrame(mapa);
                f.setVisible(true);
                f.changeCell(0);
                f.changeCell(1);
                f.changeCell(0);
                f.changeCell(5);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JRadioButton btMostrarCell;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel pDesenho;
    private javax.swing.JPanel pOptionsPane;
    private javax.swing.JPanel pTopPanel;
    private javax.swing.JTextArea taMsg;
    // End of variables declaration//GEN-END:variables
}
