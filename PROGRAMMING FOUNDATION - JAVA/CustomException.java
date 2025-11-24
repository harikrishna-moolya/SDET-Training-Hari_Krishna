package fundamentals;


class InvalidInputException extends Exception {
    public InvalidInputException(String message) {
        super(message);
    }
}


class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String message) {
        super(message);
    }
    public ResourceNotFoundException(String User,String message) {
        super(User);
    }
}


class DatabaseConnectionException extends Exception {
    public DatabaseConnectionException(String message) {
        super(message);
    }
}


public class CustomException {
	public static String R = "\u001B[31m";
	public static String RE = "\u001B[0m";
	public static String G = "\u001B[32m";
    
    public static void validateInput(int age) throws InvalidInputException {
        if (age<18 || age>65) {
            throw new InvalidInputException(R+"Age Cannot be less than 18 and greater than 65!!!"+RE);
        }
        System.out.println(G+"Valid Age: " + age);
        System.out.println("Eligible for Marriage"+RE);
    }
    //CODE ResourceNotFoundException
    public static void fetchResource(String resourceName) {
        if (!resourceName.equals("HK.PDF")) {
            throw new ResourceNotFoundException(R+"RESOURCE '" + resourceName + "' NOT FOUND...!!!"+RE);
        }
        System.out.println(G+"Resource found: " + resourceName+RE);
    }
    public static void fetchResource(String User,String resourceName) {
        if (!User.equals("HARI")) {
        	throw new ResourceNotFoundException(R+"USER '" + User + "' NOT FOUND...!!!"+RE);
        }
        if (!resourceName.equals("HK.PDF")) {
            throw new ResourceNotFoundException(R+"RESOURCE '" + resourceName + "' NOT FOUND...!!!"+RE);
        }
        System.out.println(G+"VALID USER, RESOURCE FOUND: " + resourceName+RE);
    }

    //CODE FOR DataBaseConnectionException
    public static void connectToDatabase(boolean connected) throws DatabaseConnectionException {
        if (!connected) {
            throw new DatabaseConnectionException(R+"DATABASE CONNECTION FAILED"+RE);
        }
        System.out.println(G+"DATABASE CONNECTED SUCCESSFULLY"+RE);
    }

    public static void main(String[] args) {
        try {
            //InvalidInputException
            validateInput(80);
            connectToDatabase(false);
            fetchResource("HARI","HK.PDF");
        }
        catch (ResourceNotFoundException e) {
             System.out.println(" Resource Not Found Error: " + e.getMessage());
         }
        catch (InvalidInputException e) {
            System.out.println(" Invalid Input Error: " + e.getMessage());
         }    
          
         catch (DatabaseConnectionException e) {
             System.out.println(" Database Connection Error: " + e.getMessage());
         } 
        
         finally {
            System.out.println("CUSTOM EXCEPTIONS CREATED SUCCESSFULLY...!!!");
        }
    }
}
