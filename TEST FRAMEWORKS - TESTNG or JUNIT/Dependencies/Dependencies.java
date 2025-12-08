import org.testng.annotations.Test;

public class Dependencies {

    @Test
    public void startServer() {
        System.out.println("Server started");
    }

    @Test(dependsOnMethods = {"startServer"})
    public void runSanityTests() {
        System.out.println("Sanity tests executed");
    }

    @Test(dependsOnGroups = {"login"})
    public void dependentOnGroup() {
        System.out.println("Runs after login group");
    }

    @Test(groups = "login")
    public void loginUser() {
        System.out.println("User logged in");
    }

    @Test(groups = "login")
    public void validateUser() {
        System.out.println("Login validated");
    }

    @Test(dependsOnMethods = "runSanityTests", alwaysRun = true)
    public void alwaysExecute() {
        System.out.println("This runs regardless of failures");
    }
}
