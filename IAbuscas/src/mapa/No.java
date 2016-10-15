/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mapa;

/**
 *
 * @author FREE
 */
public class No {
    No norte = null;
    No sul = null;
    No oeste = null;
    No leste = null;
    
    No pai = null;
    
    boolean visitado = false;
    
    Terreno tipo = Terreno.INVALIDO;
    
    int id = 0;

    /**
     * Impede acesso a esse nó pelos nós ao redor
     * @param no 
     */
    public void quebrarConexoes()
    {
        if (norte != null)
        {
            norte.setSul(null);
        }
        
        if (leste !=null)
        {
            leste.setOeste(null);
        }
        
        if (sul !=null)
        {
            sul.setNorte(null);
        }
        
        if (oeste!=null)
        {
            oeste.setLeste(null);
        }
        
        if (pai != null)
        {
            if (pai.norte == this)
            {
                pai.norte = null;
            }
            else if (pai.sul == this)
            {
                pai.sul = null;
            }
            else if (pai.leste == this)
            {
                pai.leste = null;
            }
            else if (pai.oeste == this)
            {
                pai.oeste = null;
            }
            else
            {
                System.err.println("Pode acontecer? alvo nao existe ?");
                throw new IllegalArgumentException();
            }
        }
    }
    
    public String caminhoAteEsseNo()
    {
        StringBuilder sb = new StringBuilder();
        StringBuilder aux = new StringBuilder();
        No atual = this;
        aux.setLength(0);
        aux.append(Integer.toString(atual.getId()));
        aux.reverse();
        sb.append(aux);
        while (atual.getPai()!=null)
        {
            atual = atual.getPai();
            aux.setLength(0);
            aux.append(Integer.toString(atual.getId()));
            aux.reverse();
            sb.append('-').append(aux) ;
        }
        sb.reverse();
        return(sb.toString());
    }
    
    public void printMe()
    {
        No atual = this;
        System.out.println("ID atual : " + atual.getId());
        System.out.print("Norte : ");
        if (atual.getNorte()==null)
        {
            System.out.println("NULL");
        }
        else
        {
            System.out.println(atual.getNorte().getId());
        }

        System.out.print("Leste : ");
        if (atual.getLeste()==null)
        {
            System.out.println("NULL");
        }
        else
        {
            System.out.println(atual.getLeste().getId());
        }

        System.out.print("Sul : ");
        if (atual.getSul()==null)
        {
            System.out.println("NULL");
        }
        else
        {
            System.out.println(atual.getSul().getId());
        }

        System.out.print("Oeste : ");
        if (atual.getOeste() == null)
        {
            System.out.println("NULL");
        }
        else
        {
            System.out.println(atual.getOeste().getId());
        }
    }
    
    //<editor-fold defaultstate="collapsed" desc="coisas basicas">
    

    public No(Terreno tipo, int id)
    {
        this.tipo = tipo;
        this.id = id;
    }
    
    public No getNorte() {
        return norte;
    }

    public void setNorte(No norte) {
        this.norte = norte;
    }

    public No getSul() {
        return sul;
    }

    public void setSul(No sul) {
        this.sul = sul;
    }

    public No getOeste() {
        return oeste;
    }

    public void setOeste(No oeste) {
        this.oeste = oeste;
    }

    public No getLeste() {
        return leste;
    }

    public void setLeste(No leste) {
        this.leste = leste;
    }

    public Terreno getTipo() {
        return tipo;
    }

    public void setTipo(Terreno tipo) {
        this.tipo = tipo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public No getPai() {
        return pai;
    }

    public void setPai(No pai) {
        this.pai = pai;
    }
    
    public boolean isVisitado() {
        return visitado;
    }

    public void setVisitado(boolean visitado) {
        this.visitado = visitado;
    }
    
    //</editor-fold>

    
}
