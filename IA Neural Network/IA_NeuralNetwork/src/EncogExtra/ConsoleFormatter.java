/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EncogExtra;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Locale;
import java.util.logging.Formatter;
import java.util.logging.LogRecord;

/**
 * @author Juliano Felipe da Silva
 */
public class ConsoleFormatter extends Formatter{
    private final Locale locale; 

    public ConsoleFormatter() {
        this(new Locale("pt", "BR")); //Locale para o Brasil
    }


    public ConsoleFormatter(Locale locale) {
        this.locale = locale;
    }

    /*
        Quick reference:
        - record.getClass: java.util.logging.LogRecord.
        - record.getThreadID: int
        - record.getLoggerName(): 
        - record.getResourceBundleName():
        - getSourceClassName: .
        - getSourceMethodName: <init>
        - 
    */
    @Override
    public String format(LogRecord record) {
        return record.getMessage();
    }
    
}
