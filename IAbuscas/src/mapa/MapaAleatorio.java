/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mapa;

import java.awt.Color;
import java.util.Random;

/**
 *  Cria um mapa aleatório
 * @author 
 */
public class MapaAleatorio {
    private static final int      DEFAULT_HEIGHT= 10;
    private static final int      DEFAULT_WIDTH = 10;
    private static final TipoMapa DEFAULT_TYPE  = TipoMapa.FULL_RANDOM;
    
    private int linhas;
    private int colunas;
    private TipoMapa tipo;
    
    public MapaAleatorio(){
        this(DEFAULT_HEIGHT, DEFAULT_WIDTH, DEFAULT_TYPE);
    }
    
    public MapaAleatorio(int height, int width, TipoMapa mapa){
        this.linhas = height;
        this.colunas = width;
        tipo = mapa;
    }
    
    public Mapa generate(){
        switch(tipo){
            case HORIZONTAL_STIPES:
                return horizontalStripes();
            case VERTICAL_STRIPES:
                return verticalStripes();
            /*case PRESET_MAZE:
                return presetMaze();*/
            default:
                return randomGenerate();
        }
    }
    
    private Mapa verticalStripes(){
        Mapa mapa = new Mapa(linhas, colunas);
        No[][] nos = mapa.matriz;
        
        for (int i=0; i<nos.length; i++){
            for (int j=0; j<nos[i].length; j++){
                if (j%2==0){
                    nos[i][j].setTipo(Terreno.PLANO);
                } else {
                    nos[i][j].setTipo(decidirTerreno());
                }
            }
        }
        return mapa;
    }
            
    private Mapa horizontalStripes(){
        Mapa mapa = new Mapa(linhas, colunas);
        No[][] nos = mapa.matriz;

        for (int i=0; i<nos.length; i++){
            for (int j=0; j<nos[i].length; j++){
                if (i%2==0){
                    nos[i][j].setTipo(Terreno.PLANO);
                } else {
                    nos[i][j].setTipo(decidirTerreno());
                }
            }
        }
        return mapa;
    }

    /*private Mapa presetMaze(){

    }*/

    private Mapa randomGenerate(){
        Mapa mapa = new Mapa(linhas, colunas);
        No[][] nos = mapa.matriz;
        for (No[] upper : nos){
            for (No single : upper){
                single.setTipo(decidirTerreno());
            }
        }
        return mapa;
    }
    
    private Terreno decidirTerreno(){
        final int LIMIAR = 10;
        final int MAX_ITERATIONS = 500;
        int i=0;
        
        final int min = 0;

        while(i < MAX_ITERATIONS){
            int max = tipo.getProbPlano() + LIMIAR;
            Random rand = new Random();
            int randomIndex = rand.nextInt((max - min) + 1) + min;
            if (randomIndex <= tipo.probPlano){
                return Terreno.PLANO;
            }

            max = tipo.getProbPantano() + LIMIAR;
            rand = new Random();
            randomIndex = rand.nextInt((max - min) + 1) + min;
            if (randomIndex <= tipo.probPantano){
                return Terreno.PANTANO;
            }

            max = tipo.getProbMontanha()+ LIMIAR;
            rand = new Random();
            randomIndex = rand.nextInt((max - min) + 1) + min;
            if (randomIndex <= tipo.probMontanha){
                return Terreno.MONTANHOSO;
            }

            max = tipo.getProbLava()+ LIMIAR;
            rand = new Random();
            randomIndex = rand.nextInt((max - min) + 1) + min;
            if (randomIndex <= tipo.probLava){
                return Terreno.LAVA;
            }
            
            i++;
        }
        return Terreno.PLANO;
    }

    //<editor-fold defaultstate="collapsed" desc="STD">

    public TipoMapa getTipo() {
        return tipo;
    }

    public void setTipo(TipoMapa tipo) {
        this.tipo = tipo;
    }
    
    
    public int getLinhas() {
        return linhas;
    }
    
    public void setLinhas(int linhas) {
        this.linhas = linhas;
    }
    
    public int getColunas() {
        return colunas;
    }
    
    public void setColunas(int colunas) {
        this.colunas = colunas;
    }
//</editor-fold>
    
    /*
    From the language specification section 8.9:

    "Nested enum types are implicitly static. It is permissable to explicitly declare a nested enum type to be static."

    */
    public static enum TipoMapa{
        //ID, custo, Color
        PLANICIE          ( 7,  2,  1, -1, "Planície"),
        PANTANO           ( 0, 10,  1, -1, "Pantanoso"),
        MONTANHOSO        ( 1,  0, 10, -1, "Montanhoso"),
        VULCANICO         ( 0, -1,  1, 10, "Vulcânico"),
        
        //FACIL             ( 7, 5, 2,-1, "Fácil"),
        //MEDIO             ( 4, 5, 3, 1, "Médio"),
        //DIFICIL           (-1, 5, 5, 7, "Difícil"),
        FULL_RANDOM       ( 2, 2, 2, 2, "Full Random"),
        
        HORIZONTAL_STIPES (-1, 0, 0, 0, "Linhas Horizontais"),
        VERTICAL_STRIPES  (-1, 0, 0, 0, "Linhas Verticais");
        //PRESET_MAZE       (-1, 0, 0, 0, "Espiral");
    
        private final int probPlano;
        private final int probPantano;
        private final int probMontanha;
        private final int probLava;
        private final String desc;
        private TipoMapa(int probPlano, int probPantano, int probMontanha, int probLava, String desc) {
            this.probPlano = probPlano;
            this.probPantano = probPantano;
            this.probMontanha = probMontanha;
            this.probLava = probLava;
            this.desc = desc;
        }

        public int getProbPlano() {
            return probPlano;
        }

        public int getProbPantano() {
            return probPantano;
        }

        public int getProbMontanha() {
            return probMontanha;
        }

        public int getProbLava() {
            return probLava;
        }

        public String getDesc() {
            return desc;
        }
        
        public static TipoMapa fromDesc(String comp){
            for (MapaAleatorio.TipoMapa tipo : MapaAleatorio.TipoMapa.values()){
                if (tipo.desc.equals(comp)){
                    return tipo;
                }

            }
            throw new IllegalArgumentException();
        }
    
    }
    
}
