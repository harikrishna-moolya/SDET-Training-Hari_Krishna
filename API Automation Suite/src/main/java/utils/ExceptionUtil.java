package utils;

public class ExceptionUtil {

    public static RuntimeException throwException(String message, Exception e) {
        System.err.println("ERROR: " + message);
        e.printStackTrace();
        return new RuntimeException(message, e);
    }
}
