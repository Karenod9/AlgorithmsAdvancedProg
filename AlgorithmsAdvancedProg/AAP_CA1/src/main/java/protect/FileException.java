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
public class FileException extends Exception {
    public FileException (String errorMessage) {
		super("File Error : " + errorMessage);
    }
}
