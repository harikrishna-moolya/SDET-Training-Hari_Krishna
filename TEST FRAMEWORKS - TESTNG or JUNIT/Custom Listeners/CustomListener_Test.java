
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(CustomListener.class)
public class CustomListener_Test {

    @Test
    public void testExample() {
        System.out.println("Running testExample");
    }
}
