// api/userClient.js
// Simple API client using Axios (CommonJS)

const axios = require("axios");

// Create axios instance (best practice)
const apiClient = axios.create({
  baseURL: "https://jsonplaceholder.typicode.com",
  timeout: 5000
});

// Async function to fetch users
async function getUsers() {
  try {
    console.log("Fetching users from API...");

    const response = await apiClient.get("/users");

    response.data.forEach(user => {
      console.log("ID:", user.id);
      console.log("Name:", user.name);
      console.log("Email:", user.email);
      console.log("------------------------");
    });

    return response.data;

  } catch (error) {
    console.error("API Error:", error.message);
    throw error; // rethrow for caller if needed
  }
}

// Export function
module.exports = {
  getUsers
};
