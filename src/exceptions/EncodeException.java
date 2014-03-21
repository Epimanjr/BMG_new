package exceptions;


public class EncodeException extends Exception {
    
    public EncodeException() {
        printStackTrace();
    }
    
    public EncodeException(String str) {
        super(str);
        printStackTrace();
    }
}
