/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package libreria.utils;

import java.io.File;
import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

/**
 *
 * @author Cinthya Martinez
 */
public class Log {
    
    
    public void escribirlog(String mensaje) throws IOException{
    Logger logger = Logger.getLogger("MyLog");
    FileHandler fh;

    try {

        fh = new FileHandler("C:\\Users\\Cinthya Martinez\\Desktop\\log.log", true);
        logger.addHandler(fh);

        SimpleFormatter formatter = new SimpleFormatter();

        fh.setFormatter(formatter);

        logger.info(mensaje);

        fh.close();

    } catch (SecurityException e) {
        e.printStackTrace();
    } catch (IOException e) {
        e.printStackTrace();
    }
    }
}
