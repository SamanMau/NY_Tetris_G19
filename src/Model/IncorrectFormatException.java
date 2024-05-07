/**
 * This class represents a custom made exception which
 * is thrown if the user chooses a file which is not
 * a wav file when trying to pick a song.
 * @author Saman
 */

package Model;

public class IncorrectFormatException extends Exception{

    public IncorrectFormatException(String exception){
        super(exception);
    }
}
