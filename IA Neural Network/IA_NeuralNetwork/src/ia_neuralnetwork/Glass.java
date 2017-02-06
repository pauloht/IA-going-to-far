/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ia_neuralnetwork;

/**
 * Enumeração para os
 * tipos de vidros.
 * 
 * Facilitação uma vez
 * que os vidros estão
 * codificados como inteiros
 * no arquivo de dados.
 * 
 * @author JFPS
 */
public enum Glass {
    BUILDING_WINDOWS_FLOAT_PROCESSED    (1, "building_windows_float_processed",     "Building windows float processed"),
    BUILDING_WINDOWS_NON_FLOAT_PROCESSED(2, "building_windows_non_float_processed", "Building windows non float processed"),
    VEHICLE_WINDOWS__FLOAT_PROCESSED    (3, "vehicle_windows_float_processed",      "Vehicle windows float processed"),
    VEHICLE_WINDOWS_NON_FLOAT_PROCESSED (4, "vehicle_windows_non_float_processed",  "Vehicle windows non float processed"),
    CONTAINERS                          (5, "containers",                           "Containers"),
    TABLEWARE                           (6, "tableware",                            "Tableware"),
    HEADLAMPS                           (7, "headlamps",                            "Headlamps");
    
    private final int ID;
    private final String undNome;
    private final String nome;
    

    private Glass(int ID, String undNome, String nome) {
        this.ID = ID;
        this.undNome = undNome;
        this.nome = nome;
    }
    
    public static Glass viaID(int ID){
        for (Glass glass : Glass.values()){
            if (glass.ID == ID)
                return glass;
        }
        throw new IllegalArgumentException("ID inválido.");
    }
    
    public static Glass viaNome(String nome){
        for (Glass glass : Glass.values()){
            if (glass.nome == null ? nome == null : glass.nome.equals(nome))
                return glass;
        }
        throw new IllegalArgumentException("Nome inválido.");
    }
    
    public static Glass viaUndNome(String undNome){
        for (Glass glass : Glass.values()){
            if (glass.undNome == null ? undNome == null : glass.undNome.equals(undNome))
                return glass;
        }
        throw new IllegalArgumentException("UndNome inválido.");
    }
}
