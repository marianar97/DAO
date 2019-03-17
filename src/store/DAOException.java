package store;

/**
 * This class extends the Exception class
 * @author  Mariana Ramirez Duque
 * @version 1.0
 */
public class DAOException extends Exception{


    public DAOException(String message) {
        super(message);
    }

    public DAOException(String message, Throwable cause) {
        super(message, cause);
    }

    public DAOException(Throwable cause) {
        super(cause);
    }


}

