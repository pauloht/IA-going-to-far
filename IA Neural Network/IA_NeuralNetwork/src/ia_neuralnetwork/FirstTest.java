/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ia_neuralnetwork;

/**
 *
 * @author JFPS
 */
public final class FirstTest {
    /*
    Neste teste, a "Hidden Layer" possuirá o número de neurônios
    igual a média entre o número de entradas e saídas, como a maioria 
    das pesquisas realizadas. Considera-se:
    
    - Como o a função de saída será "Softmax", temos 7 neurônios de saída.
    - Como o número de colunas na matriz de entrada é 9, temos o tal número.
    - Adiciona-se um neurônio "Bias" na camada de entrada e na camada oculta.
    
    Finalmente, temos que o número de neurônios nas 3 camadas são distribuídos
    como:
    
    Entrada - 9 mais um bias.
    Oculta  - 8 mais um bias.
    Saída   - 7.
    */
    
    private static final int ENTRADA=9;
}
