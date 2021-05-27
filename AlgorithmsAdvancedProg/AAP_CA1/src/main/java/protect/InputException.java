/*
                                PART 2. QUESTION 2 & 3 - INPUT EXCEPTION

 */
package protect;


public class InputException extends Exception {
    public InputException (String errorMessage) {
		super("Input Error : " + errorMessage);
    }
}
