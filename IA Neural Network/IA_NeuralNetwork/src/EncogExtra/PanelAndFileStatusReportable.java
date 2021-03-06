/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EncogExtra;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTextPane;
import org.encog.StatusReportable;

/**
 *
 * @author JFPS
 */
public class PanelAndFileStatusReportable implements StatusReportable{
    public final BufferedWriter FILE_STREAM;
    public static final Logger CONSOLE_LOG = Logger.getLogger("IANEURAL");

    public PanelAndFileStatusReportable(JTextPane pane, File resultFile) throws FileNotFoundException, IOException {
        pane.setEditable(false);
        
        BufferedPaneOutputStream oStream = new BufferedPaneOutputStream(pane);
        CONSOLE_LOG.addHandler(new PaneHandler(oStream));
        
        this.FILE_STREAM = new BufferedWriter(new FileWriter(resultFile));
    }
    
    
    
    @Override
    public void report(int i, int i1, String string) {
        StringBuilder rep = new StringBuilder();
        if (i == 0) { //i = total and i1=current
            rep.append(i1).append(" : ").append(string);
        } else {
            rep.append(i1).append("/").append(i).append(" : ").append(string);
        }
        
        try {
            FILE_STREAM.write(rep.toString());
            CONSOLE_LOG.info(rep.toString());
        } catch (IOException ex) {
            Logger.getLogger(PanelAndFileStatusReportable.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void print(String st){
        CONSOLE_LOG.info(st);
        try {
            FILE_STREAM.write(st);
        } catch (IOException ex) {
            Logger.getLogger(PanelAndFileStatusReportable.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
