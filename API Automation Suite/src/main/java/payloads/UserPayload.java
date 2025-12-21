package payloads;

public class UserPayload {

    public static String createUser(String username) {
        return "{\n" +
                "  \"id\": 101,\n" +
                "  \"username\": \"" + username + "\",\n" +
                "  \"firstName\": \"Test\",\n" +
                "  \"lastName\": \"User\",\n" +
                "  \"email\": \"Hari01@gmail.com\",\n" +
                "  \"password\": \"HK@123\",\n" +
                "  \"phone\": \"9999999999\",\n" +
                "  \"userStatus\": 1\n" +
                "}";
    }

    public static String updateUser(String username) {
        return "{\n" +
                "  \"id\": 101,\n" +
                "  \"username\": \"" + username + "\",\n" +
                "  \"firstName\": \"Updated\",\n" +
                "  \"lastName\": \"User\",\n" +
                "  \"email\": \"HK1432@gmail.com\",\n" +
                "  \"password\": \"HK1432\",\n" +
                "  \"phone\": \"8888888888\",\n" +
                "  \"userStatus\": 1\n" +
                "}";
    }

    public static String emptyPayload() {
        return "{}";
    }
}
