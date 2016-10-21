/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import buscas.Busca;
import buscas.BuscaProfundidade;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import mapa.Mapa;
import mapa.No;
import mapa.Terreno;
import mouse.MousePaint;

/**
 *
 * @author FREE
 */
public class CriadorMapaView extends javax.swing.JFrame {
    List< JPanel > mapaPanel = new ArrayList<>();
    List< JLabel > mapaLabel = new ArrayList<>();
    
    int nLinhas = 1;
    int nColunas = 1;
    /**
     * Creates new form CriadorMapa
     */
    public CriadorMapaView(int linhas,int colunas) {
        initComponents();
        this.nLinhas = linhas;
        this.nColunas = colunas;
        rbPlano.setSelected(true);
        rbPlanoActionPerformed(null);
        meuInit(linhas,colunas);
        fullscreen();
        this.addMouseListener(MousePaint.getInstance());
    }
    
    public CriadorMapaView(Mapa mapa)
    {
        initComponents();
        this.nLinhas = mapa.getLinhas();
        this.nColunas = mapa.getColunas();
        rbPlano.setSelected(true);
        rbPlanoActionPerformed(null);
        meuInit(mapa);
        fullscreen();
        this.addMouseListener(MousePaint.getInstance());
    }
    
    private void meuInit(int linhas, int colunas)
    {
        Mapa mapa = new Mapa(linhas,colunas);
        meuInit(mapa);
        /*
        pMiddle.setLayout(new GridLayout(linhas,colunas,1,1));
        JPanel aux;
        JLabel label;
        No[][] noMatrix = mapa.getMatriz();
        
        int linhaRelacionada;
        int colunaRelacionada;
        int fontSize;
        fontSize = 8;
        Font fonteMinha = new Font("Dialog", Font.PLAIN, fontSize);
        for (int i=0;i<linhas*colunas;i++)
        {
            linhaRelacionada = (i-i%colunas)/colunas;
            colunaRelacionada = i%colunas;
            No noRelacionado = noMatrix[linhaRelacionada][colunaRelacionada];
            
            aux = new JPanel();
            aux.setName("canvas");
            aux.setLayout(new GridLayout(1,1));
            aux.setBackground(noRelacionado.getTipo().getColor());
            aux.addMouseListener(MousePaint.getInstance());
            
            label = new JLabel(Integer.toString(i));
            label.setHorizontalAlignment(SwingConstants.CENTER);
            label.setVerticalAlignment(SwingConstants.CENTER);
            label.setFont(fonteMinha);
            aux.add(label);
            
            mapaPanel.add(aux);
            mapaLabel.add(label);
            
            pMiddle.add(aux);
        }
        */
    }
    
    private void meuInit(Mapa mapa)
    {
        pMiddle.setLayout(new GridLayout(mapa.getLinhas(),mapa.getColunas(),1,1));
        JPanel aux;
        JLabel label;
        No[][] noMatrix = mapa.getMatriz();
        
        int linhaRelacionada;
        int colunaRelacionada;
        int fontSize;
        fontSize = 8;
        Font fonteMinha = new Font("Dialog", Font.PLAIN, fontSize);
        for (int i=0;i<mapa.getLinhas()*mapa.getColunas();i++)
        {
            linhaRelacionada = (i-i%mapa.getColunas())/mapa.getColunas();
            colunaRelacionada = i%mapa.getColunas();
            No noRelacionado = noMatrix[linhaRelacionada][colunaRelacionada];
            
            aux = new JPanel();
            aux.setName("canvas");
            aux.setLayout(new GridLayout(1,1));
            aux.setBackground(noRelacionado.getTipo().getColor());
            aux.addMouseListener(MousePaint.getInstance());
            
            label = new JLabel(Integer.toString(i));
            label.setHorizontalAlignment(SwingConstants.CENTER);
            label.setVerticalAlignment(SwingConstants.CENTER);
            label.setFont(fonteMinha);
            aux.add(label);
            
            mapaPanel.add(aux);
            mapaLabel.add(label);
            
            pMiddle.add(aux);
        }
    }
    
    private Mapa gerarMapaDeDesenho()
    {
        Terreno[][] tipoMatrix = new Terreno[nLinhas][nColunas];
        int linhaLocal;
        int colunaLocal;
        for (int i=0;i<nLinhas*nColunas;i++)
        {
            
            colunaLocal = i%nColunas;
            linhaLocal = (i-i%nColunas)/nColunas; 
            
            //System.out.println("i = " + i + ", = linhacoluna = [" + linhaLocal+","+colunaLocal+"]");
            
            tipoMatrix[linhaLocal][colunaLocal] = Terreno.getTerrenoFromColor( mapaPanel.get(i).getBackground() );
        }
        return( new Mapa(nLinhas,nColunas,tipoMatrix) );
    }
    
    private void fullscreen()
    {
        float upperPaneSize = 0.05f;//peso do painel de cima
        
        float bottomPaneSize = 0.05f;//peso painel de baixo
        
        float middlePaneSize = 1.0f - upperPaneSize - bottomPaneSize;//peso painel do meio
        
        //aqui começa bruxaria
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setPreferredSize( dim );
        this.pack();
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
        this.toFront();
        this.setResizable(false);
        
        this.setLayout(new GridBagLayout());
        
        GridBagConstraints g = new GridBagConstraints();
        g.fill = GridBagConstraints.BOTH;
        g.gridwidth = g.gridheight = 1;
        g.weightx = 1.0f;
        
        g.weighty = upperPaneSize;
        g.gridx = 0;
        g.gridy = 0;
        this.add(pUpper,g);
        
        g.weighty = middlePaneSize;
        g.gridx = 0;
        g.gridy = 1;
        this.add(pMiddle,g);
        
        g.weighty = bottomPaneSize;
        g.gridx = 0;
        g.gridy = 2;
        this.add(pBottom,g);
        
        
        pBottom.setLayout(new GridLayout(1,4));
        pBottom.add(btLimpar);
        pBottom.add(btSalvar);
        pBottom.add(btVoltar);
        pBottom.add(pBuscarPanel);
        //fim bruxaria
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        bgTerrenos = new javax.swing.ButtonGroup();
        pUpper = new javax.swing.JPanel();
        rbPlano = new javax.swing.JRadioButton();
        rbMontanha = new javax.swing.JRadioButton();
        rbLava = new javax.swing.JRadioButton();
        rbPantano = new javax.swing.JRadioButton();
        pMiddle = new javax.swing.JPanel();
        pBottom = new javax.swing.JPanel();
        btLimpar = new javax.swing.JButton();
        btSalvar = new javax.swing.JButton();
        pBuscarPanel = new javax.swing.JPanel();
        btRealizarBusca = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        tfCelulaFim = new javax.swing.JTextField();
        tfCelulaInicio = new javax.swing.JTextField();
        btVoltar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        pUpper.setBackground(new java.awt.Color(204, 255, 255));
        pUpper.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 5), "Ferramentas"));

        bgTerrenos.add(rbPlano);
        rbPlano.setText("Plano");
        rbPlano.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbPlanoActionPerformed(evt);
            }
        });

        bgTerrenos.add(rbMontanha);
        rbMontanha.setText("Montanha");
        rbMontanha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbMontanhaActionPerformed(evt);
            }
        });

        bgTerrenos.add(rbLava);
        rbLava.setText("Lava");
        rbLava.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbLavaActionPerformed(evt);
            }
        });

        bgTerrenos.add(rbPantano);
        rbPantano.setText("Pantano");
        rbPantano.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbPantanoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pUpperLayout = new javax.swing.GroupLayout(pUpper);
        pUpper.setLayout(pUpperLayout);
        pUpperLayout.setHorizontalGroup(
            pUpperLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pUpperLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pUpperLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(rbMontanha)
                    .addComponent(rbPlano))
                .addGap(28, 28, 28)
                .addGroup(pUpperLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(rbPantano)
                    .addComponent(rbLava))
                .addContainerGap(228, Short.MAX_VALUE))
        );
        pUpperLayout.setVerticalGroup(
            pUpperLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pUpperLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pUpperLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rbPlano)
                    .addComponent(rbPantano))
                .addGap(18, 18, 18)
                .addGroup(pUpperLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rbMontanha)
                    .addComponent(rbLava))
                .addContainerGap(29, Short.MAX_VALUE))
        );

        pMiddle.setBackground(new java.awt.Color(102, 102, 255));
        pMiddle.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 5));

        javax.swing.GroupLayout pMiddleLayout = new javax.swing.GroupLayout(pMiddle);
        pMiddle.setLayout(pMiddleLayout);
        pMiddleLayout.setHorizontalGroup(
            pMiddleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        pMiddleLayout.setVerticalGroup(
            pMiddleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        pBottom.setBackground(new java.awt.Color(51, 153, 255));
        pBottom.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 5));

        btLimpar.setText("Limpar");
        btLimpar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btLimparActionPerformed(evt);
            }
        });

        btSalvar.setText("salvar");
        btSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btSalvarActionPerformed(evt);
            }
        });

        pBuscarPanel.setBackground(new java.awt.Color(204, 0, 102));

        btRealizarBusca.setText("RealizarBusca");
        btRealizarBusca.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btRealizarBuscaActionPerformed(evt);
            }
        });

        jLabel1.setText("Celula Inicio");

        jLabel2.setText("Celula Fim");

        tfCelulaFim.setText("0");

        tfCelulaInicio.setText("0");

        javax.swing.GroupLayout pBuscarPanelLayout = new javax.swing.GroupLayout(pBuscarPanel);
        pBuscarPanel.setLayout(pBuscarPanelLayout);
        pBuscarPanelLayout.setHorizontalGroup(
            pBuscarPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btRealizarBusca, javax.swing.GroupLayout.DEFAULT_SIZE, 126, Short.MAX_VALUE)
            .addGroup(pBuscarPanelLayout.createSequentialGroup()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(tfCelulaInicio))
            .addGroup(pBuscarPanelLayout.createSequentialGroup()
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addComponent(tfCelulaFim))
        );
        pBuscarPanelLayout.setVerticalGroup(
            pBuscarPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pBuscarPanelLayout.createSequentialGroup()
                .addGroup(pBuscarPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(tfCelulaInicio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addGroup(pBuscarPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(tfCelulaFim, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
                .addComponent(btRealizarBusca))
        );

        btVoltar.setText("Voltar");
        btVoltar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btVoltarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pBottomLayout = new javax.swing.GroupLayout(pBottom);
        pBottom.setLayout(pBottomLayout);
        pBottomLayout.setHorizontalGroup(
            pBottomLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pBottomLayout.createSequentialGroup()
                .addComponent(btLimpar, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pBottomLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btSalvar, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btVoltar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(pBuscarPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        pBottomLayout.setVerticalGroup(
            pBottomLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pBottomLayout.createSequentialGroup()
                .addComponent(btLimpar, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 68, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pBottomLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pBottomLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(pBottomLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btSalvar)
                        .addGap(52, 52, 52)
                        .addComponent(btVoltar))
                    .addComponent(pBuscarPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(40, 40, 40))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pUpper, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(pMiddle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(pBottom, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(pUpper, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(pMiddle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(pBottom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void rbPlanoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbPlanoActionPerformed
        // TODO add your handling code here:
        //System.out.println("eventoPlano");
        MousePaint.changePaint(Terreno.PLANO);
    }//GEN-LAST:event_rbPlanoActionPerformed

    private void rbMontanhaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbMontanhaActionPerformed
        // TODO add your handling code here:
        //System.out.println("eventoMontanha");
        MousePaint.changePaint(Terreno.MONTANHOSO);
    }//GEN-LAST:event_rbMontanhaActionPerformed

    private void rbPantanoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbPantanoActionPerformed
        // TODO add your handling code here:
        //System.out.println("evento pantano");
        MousePaint.changePaint(Terreno.PANTANO);
    }//GEN-LAST:event_rbPantanoActionPerformed

    private void rbLavaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbLavaActionPerformed
        // TODO add your handling code here:
        //System.out.println("evento lava");
        MousePaint.changePaint(Terreno.LAVA);
    }//GEN-LAST:event_rbLavaActionPerformed

    private void btRealizarBuscaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btRealizarBuscaActionPerformed
        // TODO add your handling code here:
        try {
            int linhaInicio = Integer.parseInt(  tfCelulaInicio.getText() );
            int linhaFim = Integer.parseInt( tfCelulaFim.getText() );

            if (linhaInicio >= nLinhas*nColunas || linhaInicio < 0 || linhaFim >= nLinhas*nColunas || linhaFim<0)
            {
                //celula invalida
                JOptionPane.showMessageDialog(this, "Numero da celula invalida ... valores validos entre 0(incluso) e " + Integer.toString( nColunas*nLinhas-1 ) + "(incluso)");
            }
            else
            {
                //fazer busca
                Mapa mapaCriado = gerarMapaDeDesenho();
                PercursoFrame.setInstanceDim(mapaCriado);
                
                java.awt.EventQueue.invokeLater(new Runnable(){
                    @Override
                    public void run()
                    {
                        Busca busca = new BuscaProfundidade(linhaInicio,linhaFim,mapaCriado);
                        busca.iniciarBusca();
                    }
                });
                this.dispose();
            }
        }
        catch( Exception e)
        {
            e.printStackTrace();
            System.out.println("erro = " + e);
            tfCelulaInicio.setText("0");
            tfCelulaFim.setText("0");
        }
    }//GEN-LAST:event_btRealizarBuscaActionPerformed

    private void btLimparActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btLimparActionPerformed
        // TODO add your handling code here:
        for (JPanel panel : mapaPanel)
        {
            panel.setBackground(Terreno.PLANO.getColor());
        }
    }//GEN-LAST:event_btLimparActionPerformed

    private void btVoltarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btVoltarActionPerformed
        // TODO add your handling code here:
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run()
            {
                TelaInicial tela = new TelaInicial();
                tela.setVisible(true);
            }
        });
        this.dispose();
    }//GEN-LAST:event_btVoltarActionPerformed

    private void btSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btSalvarActionPerformed
        // TODO add your handling code here:
        this.setEnabled(false);
        
        SalvarMapaView viewSalvar = new SalvarMapaView(this,gerarMapaDeDesenho());
        viewSalvar.setVisible(true);
        viewSalvar.toFront();
    }//GEN-LAST:event_btSalvarActionPerformed

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
            java.util.logging.Logger.getLogger(CriadorMapaView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CriadorMapaView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CriadorMapaView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CriadorMapaView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                CriadorMapaView view = new CriadorMapaView(10,10);
                view.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup bgTerrenos;
    private javax.swing.JButton btLimpar;
    private javax.swing.JButton btRealizarBusca;
    private javax.swing.JButton btSalvar;
    private javax.swing.JButton btVoltar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel pBottom;
    private javax.swing.JPanel pBuscarPanel;
    private javax.swing.JPanel pMiddle;
    private javax.swing.JPanel pUpper;
    private javax.swing.JRadioButton rbLava;
    private javax.swing.JRadioButton rbMontanha;
    private javax.swing.JRadioButton rbPantano;
    private javax.swing.JRadioButton rbPlano;
    private javax.swing.JTextField tfCelulaFim;
    private javax.swing.JTextField tfCelulaInicio;
    // End of variables declaration//GEN-END:variables
}
