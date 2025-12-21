package payloads;

public class UserPayload {

    public static String createUserPayload() {
        return "{\n" +
                "  \"id\": 101,\n" +
                "  \"username\": \"testuser\",\n" +
                "  \"firstName\": \"Hari\",\n" +
                "  \"lastName\": \"Krishna\",\n" +
                "  \"email\": \"hari@test.com\",\n" +
                "  \"password\": \"password123\",\n" +
                "  \"phone\": \"9999999999\",\n" +
                "  \"userStatus\": 1\n" +
                "}";
    }
}
