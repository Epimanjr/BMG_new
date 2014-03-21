package exceptions;


public class DecodeException extends Exception {
    
    public DecodeException() {
        printStackTrace();
    }
    
    public DecodeException(String str) {
        super(str);
        printStackTrace();
    }
}
