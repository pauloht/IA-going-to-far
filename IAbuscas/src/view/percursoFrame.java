/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import controle.Controle;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import mapa.CriadorMapa;
import mapa.Mapa;
import mapa.No;

/**
 *
 * @author FREE
 */
public class PercursoFrame extends javax.swing.JFrame {
    List< JPanel > mapaPanel = new ArrayList<>();
    List< JLabel > mapaLabel = new ArrayList<>();
    private static PercursoFrame instancia = null;
    public static boolean autostatus = false;
    public static int microsegundosdelay = 1000;
    private static boolean buscaterminou = false;
    Mapa buffer = null;
    private List< No > caminhoFinal = null;
    /**
     * Creates new form percursoFrame
     */
    
    private Color bufferColor;
    private JPanel selecionado = null;
    
    PercursoFrame(Mapa mapa) {
        initComponents();
        meuInit(mapa);
        buffer = mapa;
        buscaterminou = false;
    }

    public void setCaminhoFinal(List<No> caminhoFinal) {
        this.caminhoFinal = caminhoFinal;
    }
    
    
    
    public static void buscaTerminou()
    {
        buscaterminou = true;
        if (instancia != null)
        {
            instancia.btProximoPasso.setText("Sair\n");
            instancia.btProximoPasso.setEnabled(true);
            instancia.tbAuto.setSelected(false);
            instancia.tbAuto.setEnabled(false);
            
            for ( int i=0;i<instancia.mapaPanel.size();i++ )
            {
                instancia.mapaPanel.get(i).setBackground(Color.RED);
                instancia.mapaLabel.get(i).setText("");
            }
            
            if (instancia.caminhoFinal!=null)
            {
                int aux = 0;
                for (int i=instancia.caminhoFinal.size()-1;i>=0;i--)
                {
                    int posicao = instancia.caminhoFinal.get(i).getId();
                    instancia.mapaPanel.get(posicao).setBackground(Color.GREEN);
                    instancia.mapaLabel.get(posicao).setText(Integer.toString(aux));
                    aux = aux+1;
                }
            }
        }
    }
    
    public static PercursoFrame getInstance()
    {
        if (instancia == null)
        {
            throw new IllegalArgumentException("Nao foi setado!!!");
        }
        return( instancia );
    }
    
    private void fullScreen()
    {
        //+ soft code o maximo possivel :p, só mexer nos 2 pesos que deve tudo se ajeitar;
        float upperPaneSize = 0.10f;//peso do painel de cima
        
        float bottomPaneSize = 0.05f;//peso painel de baixo
        
        float middlePaneSize = 1.0f - upperPaneSize - bottomPaneSize;//peso painel do meio
        
        //aqui começa bruxaria
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setPreferredSize( dim );
        pack();
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
        setResizable(false);
        this.toFront();
        
        
        int totalHeight = Toolkit.getDefaultToolkit().getScreenSize().height;
        
        int totalWidth = Toolkit.getDefaultToolkit().getScreenSize().width;
        
        this.setLayout(new GridBagLayout());
        
        GridBagConstraints g = new GridBagConstraints();
        g.fill = GridBagConstraints.BOTH;
        g.gridwidth = g.gridheight = 1;
        g.weightx = 1.0f;
        
        g.weighty = upperPaneSize;
        g.gridx = 0;
        g.gridy = 0;
        this.add(pTopPanel,g);
        
        g.weighty = middlePaneSize;
        g.gridx = 0;
        g.gridy = 1;
        this.add(spDesenho,g);
        
        spDesenho.setViewportView(pDesenho);
        
        g.weighty = bottomPaneSize;
        g.gridx = 0;
        g.gridy = 2;
        this.add(pBottomPane,g);
        
        pBottomPane.setLayout(new GridLayout(1,3));
        pBottomPane.add(pOptions);
        pBottomPane.add(btProximoPasso);
        pBottomPane.add(tbAuto);
        //fim bruxaria
    }
    
    public static void setInstanceDim(Mapa mapa)
    {
        if (instancia != null)
        {
            instancia.dispose();
        }
        instancia = new PercursoFrame(mapa);
        System.out.println("instancia de visualizacao criada!");
        System.out.println("deixando visualização visivel ja...mudar dps");
        instancia.setVisible(true);
    }
    
    private void meuInit(Mapa mapa)
    {
        this.caminhoFinal = null;
        int linhas = mapa.getLinhas();
        int colunas = mapa.getColunas();

        tfAutotempo.setText(Integer.toString(microsegundosdelay));
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
            aux.setBackground(noRelacionado.getTipo().getColor());
            
            label = new JLabel(Integer.toString(i));
            label.setHorizontalAlignment(SwingConstants.CENTER);
            label.setVerticalAlignment(SwingConstants.CENTER);
            aux.add(label);
            
            mapaPanel.add(aux);
            mapaLabel.add(label);
            
            pDesenho.add(aux);
        }
        tbAutoActionPerformed(null);
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

        pBottomPane = new javax.swing.JPanel();
        pOptions = new javax.swing.JPanel();
        btMostrarCell = new javax.swing.JRadioButton();
        jLabel1 = new javax.swing.JLabel();
        tfAutotempo = new javax.swing.JTextField();
        btProximoPasso = new javax.swing.JButton();
        tbAuto = new javax.swing.JToggleButton();
        pTopPanel = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        taMsg = new javax.swing.JTextArea();
        spDesenho = new javax.swing.JScrollPane();
        pDesenho = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        pBottomPane.setBackground(new java.awt.Color(0, 102, 102));

        pOptions.setBackground(new java.awt.Color(51, 153, 255));

        btMostrarCell.setSelected(true);
        btMostrarCell.setText("Mostrar ID das celulas");
        btMostrarCell.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btMostrarCellActionPerformed(evt);
            }
        });

        jLabel1.setText("Tempo entre passos em ms(auto)");

        tfAutotempo.setText("jTextField1");
        tfAutotempo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfAutotempoActionPerformed(evt);
            }
        });
        tfAutotempo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tfAutotempoKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout pOptionsLayout = new javax.swing.GroupLayout(pOptions);
        pOptions.setLayout(pOptionsLayout);
        pOptionsLayout.setHorizontalGroup(
            pOptionsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pOptionsLayout.createSequentialGroup()
                .addGroup(pOptionsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btMostrarCell)
                    .addGroup(pOptionsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(tfAutotempo, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(0, 32, Short.MAX_VALUE))
        );
        pOptionsLayout.setVerticalGroup(
            pOptionsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pOptionsLayout.createSequentialGroup()
                .addComponent(btMostrarCell)
                .addGap(18, 18, 18)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tfAutotempo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(30, Short.MAX_VALUE))
        );

        btProximoPasso.setText("Proximo passo");
        btProximoPasso.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btProximoPassoActionPerformed(evt);
            }
        });

        tbAuto.setText("AUTO");
        tbAuto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tbAutoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pBottomPaneLayout = new javax.swing.GroupLayout(pBottomPane);
        pBottomPane.setLayout(pBottomPaneLayout);
        pBottomPaneLayout.setHorizontalGroup(
            pBottomPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pBottomPaneLayout.createSequentialGroup()
                .addComponent(pOptions, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btProximoPasso)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tbAuto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pBottomPaneLayout.setVerticalGroup(
            pBottomPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pOptions, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btProximoPasso, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(tbAuto, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pTopPanel.setBackground(new java.awt.Color(51, 255, 51));

        taMsg.setEditable(false);
        taMsg.setBackground(new java.awt.Color(255, 102, 0));
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

        spDesenho.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 5));

        pDesenho.setBackground(new java.awt.Color(204, 204, 204));

        javax.swing.GroupLayout pDesenhoLayout = new javax.swing.GroupLayout(pDesenho);
        pDesenho.setLayout(pDesenhoLayout);
        pDesenhoLayout.setHorizontalGroup(
            pDesenhoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 82, Short.MAX_VALUE)
        );
        pDesenhoLayout.setVerticalGroup(
            pDesenhoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 213, Short.MAX_VALUE)
        );

        spDesenho.setViewportView(pDesenho);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pBottomPane, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(pTopPanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(68, 68, 68)
                .addComponent(spDesenho, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(pTopPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33)
                .addComponent(spDesenho, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(pBottomPane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
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

    private void tbAutoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tbAutoActionPerformed
        // TODO add your handling code here:
        if (tbAuto.isSelected())
        {
            tbAuto.setText("Auto(Ativado)");
            Controle.getInstancia().proximoPasso();
        }
        else
        {
            tbAuto.setText("Auto(Desativado)");
        }
        autostatus = tbAuto.isSelected();
        btProximoPasso.setEnabled(!tbAuto.isSelected());
    }//GEN-LAST:event_tbAutoActionPerformed

    private void btProximoPassoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btProximoPassoActionPerformed
        // TODO add your handling code here:
        if (!buscaterminou)
        {
            Controle.getInstancia().proximoPasso();
        }
        else
        {
            int i;
            i = JOptionPane.showConfirmDialog(this, "Deseja voltar ao editor de mapa?", "voltar", JOptionPane.YES_NO_OPTION);
            if (i == JOptionPane.YES_OPTION)
            {
                CriadorMapaView telaDeMapa = new CriadorMapaView(buffer);
                telaDeMapa.setVisible(true);
                this.dispose();
            }
        }
    }//GEN-LAST:event_btProximoPassoActionPerformed

    private void tfAutotempoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfAutotempoActionPerformed
        // TODO add your handling code here:
        try{
            int newtempo = Integer.parseInt(tfAutotempo.getText());
            microsegundosdelay = newtempo;
        }
        catch(Exception e)
        {
            tfAutotempo.setText(Integer.toString(microsegundosdelay));
        }
    }//GEN-LAST:event_tfAutotempoActionPerformed

    private void tfAutotempoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfAutotempoKeyPressed
        // TODO add your handling code here:
        try{
            System.out.println("valor = " + tfAutotempo.getToolTipText());
            //int newtempo = Integer.parseInt(tfAutotempo.getSelectedText());
            //microsegundosdelay = newtempo;
        }
        catch(Exception e)
        {
            tfAutotempo.setText(Integer.toString(microsegundosdelay));
        }
    }//GEN-LAST:event_tfAutotempoKeyPressed

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
            java.util.logging.Logger.getLogger(PercursoFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PercursoFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PercursoFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PercursoFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Mapa mapa = CriadorMapa.criarMapa();
                PercursoFrame f = new PercursoFrame(mapa);
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
    private javax.swing.JButton btProximoPasso;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel pBottomPane;
    private javax.swing.JPanel pDesenho;
    private javax.swing.JPanel pOptions;
    private javax.swing.JPanel pTopPanel;
    private javax.swing.JScrollPane spDesenho;
    private javax.swing.JTextArea taMsg;
    private javax.swing.JToggleButton tbAuto;
    private javax.swing.JTextField tfAutotempo;
    // End of variables declaration//GEN-END:variables
}
