/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpaneltests;

import Data.Base_Data.Edge;
import Data.Base_Data.Face;
import Data.Base_Data.Vertex;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;

/**
 *
 * @author Paulo.Tenorio
 */
public class pauloDraw {
    private Test panel;
    private int xlimitemenor = 0;
    private int ylimitemenor = 0;

    private int xlimitemaior = 0;
    private int ylimitemaior = 0;
    
    private List< Integer > valoresX; 
    private List< Integer > valoresY;
    
    public pauloDraw(Test panel)
    {
        setPanel(panel);
    }
    
    private void setPanel(Test panel)
    {
        this.panel = panel;
        xlimitemaior = panel.getSize().width - 1;
        ylimitemaior = panel.getSize().height - 1;
        valoresX = new ArrayList<>();
        valoresY = new ArrayList<>();
        
        this.panel.xPaintList = this.valoresX;
        this.panel.yPaintList = this.valoresY;
        
        System.out.println("limite x [" + xlimitemenor + "," + xlimitemaior + "]");
        System.out.println("limite y [" + ylimitemenor + "," + ylimitemaior + "]");
    }
    
    private void addXYValue(int xvalue, int yvalue)
    {
        //System.out.println("Tentando adicionar " + xvalue + "," + yvalue);
        if (xvalue>=xlimitemenor && xvalue<=xlimitemaior)
        {
            if (yvalue>=ylimitemenor && yvalue<=ylimitemaior)
            {
                valoresX.add(xvalue);
                valoresY.add(yvalue);
            }
        }
    }
    
    private boolean isVertexDentro( Vertex vertex)
    {
        int valorX = vertex.getPosXDummy().intValue();
        int valorY = vertex.getPosYDummy().intValue();

        if (valorX >= xlimitemenor && valorX <= xlimitemaior && valorY >= ylimitemenor && valorY <= ylimitemaior)
        {
            return( true );
        }
        return( false );
    }
    
    public void drawPoly(JPanel ondeDesenhar,List< Face > faces)
    {
        for (Face face : faces)
        {
            for (Edge edge : face.getEdgeList())
            {
                Vertex vertexInicio = edge.getStart_vertex();
                Vertex vertexFim = edge.getEnd_vertex();
                
                double xinicio = vertexInicio.getPosXDummy();
                double yinicio = vertexInicio.getPosYDummy();

                double xfim = vertexFim.getPosXDummy();
                double yfim = vertexFim.getPosYDummy();
                
                if (isVertexDentro(vertexInicio) && isVertexDentro(vertexFim))
                {
                        drawLine( ondeDesenhar , xinicio , yinicio , xfim , yfim );
                }
                else
                {
                    double xinicioprox = xinicio + 0.5;
                    double yinicioprox = yinicio + 0.5;
                    
                    double xfimprox = xfim + 0.5;
                    double yfimprox = yfim + 0.5;
                    
                    boolean temUmDentro;
                    
                    double m1 = Math.abs(yfimprox - yinicioprox)/Math.abs(xfimprox - xinicioprox);
                    
                    double m2 = Math.abs(xfimprox - xinicioprox)/Math.abs(yfimprox - yinicioprox);
                    
                    Vertex corteSuperiorX = new Vertex();//Y=0
                    double posicaoX = xinicioprox + m2*(0 - yinicioprox);
                    corteSuperiorX.setPosXDummy(posicaoX);
                    corteSuperiorX.setPosYDummy(0.00);
                    
                    Vertex corteInferiorX = new Vertex();//Y=limitemaximo-1
                    posicaoX = xinicioprox + m2*(ylimitemaior-1 - yinicioprox);
                    corteInferiorX.setPosXDummy(posicaoX);
                    corteInferiorX.setPosYDummy(ylimitemaior-1.00);
                    
                    Vertex corteEsquerdaY = new Vertex();//X = 0
                    double posicaoY = yinicioprox + m1*(0 - xinicioprox);
                    corteEsquerdaY.setPosXDummy(0.00);
                    corteEsquerdaY.setPosYDummy(posicaoY);
                    
                    Vertex corteDireitaY = new Vertex();//X = limitemaximo-1
                    posicaoY = yinicioprox + m1*(xlimitemaior-1.00 - xinicioprox);
                    corteDireitaY.setPosXDummy(0.00);
                    corteDireitaY.setPosYDummy(posicaoY);
                    
                    if (!isVertexDentro(vertexInicio) && !isVertexDentro(vertexFim))
                    {
                        
                    }
                }  
            }
        }
    }
    
    private List< Vertex > retornarVetoresDentro(List< Vertex > vetor)
    {
        List< Vertex > retorno = new ArrayList<>();
        for (Vertex vertex : vetor)
        {
            if (isVertexDentro(vertex))
            {
                retorno.add(vertex);
            }
        }
        return(retorno);
    }
    
    public void drawLine(JPanel ondeDesenhar,Double xinicio,Double yinicio,Double xfim,Double yfim)
    {
        
        int intxinicio = new Double(Math.round(xinicio)).intValue();
        int intxfim = new Double(Math.round(xfim)).intValue();
        
        int intyinicio = new Double(Math.round(yinicio)).intValue();
        int intyfim = new Double(Math.round(yfim)).intValue();
        
        Double xinicioprox = Math.round( xinicio ) + 0.5;
        Double xfimprox = Math.round( xfim ) + 0.5;
        
        Double yinicioprox = Math.round( yinicio ) + 0.5;
        Double yfimprox = Math.round( yfim ) + 0.5;
        
        addXYValue(intxinicio , intyinicio);
        boolean foraDoPanel = false;
        
        if (!xinicioprox.equals( xfimprox ) && !yinicioprox.equals( yfimprox ))
        {
            double m = Math.abs(yfimprox - yinicioprox)/Math.abs(xfimprox - xinicioprox);
        
            System.out.println("m = " + m);
            
            if ( m > 1)
            {
                if (intyinicio > intyfim)
                {
                    int aux = intyinicio;
                    intyinicio = intyfim;
                    intyfim = aux;
                }
                for (int i=intyinicio+1 ; i<intyfim ; i++)
                {
                    Double valorX = xinicioprox + (i+0.5 - yinicioprox)/m;
                    addXYValue(valorX.intValue(),i);
                }
            }
            else
            {
                if (intxinicio > intxfim)
                {
                    int aux = intxinicio;
                    intxinicio = intxfim;
                    intxfim = aux;
                }
                for (int i=intxinicio+1 ; i<intxfim ; i++)
                {
                    Double valorY = yinicioprox + m*(i+0.5 - xinicioprox);
                    addXYValue(i,valorY.intValue());
                }
            }
        }
        else if (yinicioprox.equals(yfimprox)) //y1 == y2 -> m = infinito
        {
            for (int i=intxinicio+1 ; i<intxfim ; i++)
            {
                Double valorY = yinicioprox;
                addXYValue(i,valorY.intValue());
            }
        }
        else if (xinicioprox.equals(xfimprox)) //x1 == x2 -> m = 0
        {
            for (int i=intyinicio+1 ; i<intyfim ; i++)
            {
                Double valorX = xinicioprox;
                addXYValue(valorX.intValue(),i);
            }
        }
        
        if (!xinicio.equals(xfim) || !yinicio.equals(yfim))
        {
            addXYValue(intxfim, intyfim);
        }
        
        //System.out.println("valores x : " + valoresX);
        //System.out.println("valores y : " + valoresY);
        
        //this.panel.repaint();
    }
}
