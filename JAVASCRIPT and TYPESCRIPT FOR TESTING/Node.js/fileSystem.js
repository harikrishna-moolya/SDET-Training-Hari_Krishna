// fileSystem.js
// Node.js File System Operations

const fs = require("fs");

// Write file
fs.writeFileSync("data.txt", "Hello from Node.js");

// Read file
const content = fs.readFileSync("data.txt", "utf8");

// Print file content
console.log("File Content:", content);
