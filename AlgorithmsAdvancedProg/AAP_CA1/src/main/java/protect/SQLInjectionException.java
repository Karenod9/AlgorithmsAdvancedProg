/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package protect;

/**
 *
 *
 */
public class SQLInjectionException extends Exception {
    public SQLInjectionException(String errorMessage){
        super("Possible SQL injection attempted : " + errorMessage);
    }
}
