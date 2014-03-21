package exceptions;

public class AlreadyExistsException extends Exception
{
    public AlreadyExistsException() {}
    
    public AlreadyExistsException(String s) {super(s);}
}
