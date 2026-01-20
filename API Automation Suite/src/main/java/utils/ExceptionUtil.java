package utils;

import exception.FrameworkException;

public final class ExceptionUtil {

    private ExceptionUtil() {}

    public static void fail(String message, Throwable t) {
        throw new FrameworkException(message, t);
    }

    public static void fail(String message) {
        throw new FrameworkException(message);
    }
}
