package utils;

public class ExceptionUtil {

    public static void handle(Exception e) {
        throw new RuntimeException("Test failed: " + e.getMessage());
    }
}
