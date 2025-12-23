 //2.5 Fetch API for HTTP requests
 // Fetch API using Async / Await with Error Handling

// Step 1: Create async function
async function fetchUsers() {
  try {
    console.log("Fetching users from API...");

    // Step 2: Make HTTP GET request
    const response = await fetch("https://jsonplaceholder.typicode.com/users");

    // Step 3: Handle HTTP errors (404, 500, etc.)
    if (!response.ok) {
      throw new Error(`HTTP Error! Status: ${response.status}`);
    }

    // Step 4: Convert response to JSON
    const users = await response.json();

    // Step 5: Use the response data
    users.forEach(user => {
      console.log("ID:", user.id);
      console.log("Name:", user.name);
      console.log("Email:", user.email);
      console.log("---------------------");
    });

  } catch (error) {
    // Step 6: Handle network or logical errors
    console.error("Error while fetching users:", error.message);

  } finally {
    // Step 7: Always executed
    console.log("Fetch API call completed");
  }
}

// Step 8: Call function
fetchUsers();
