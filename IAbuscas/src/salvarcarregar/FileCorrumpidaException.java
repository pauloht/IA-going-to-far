/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package salvarcarregar;

/**
 *
 * @author FREE
 */
public class FileCorrumpidaException extends Exception{
    public FileCorrumpidaException()
    {
        super();
    }
    
    public FileCorrumpidaException(String msg)
    {
        super(msg);
    }
}
