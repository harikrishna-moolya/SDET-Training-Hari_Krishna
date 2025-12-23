// 2.4 Async / Await with Error Handling

function getData() {
  return new Promise((resolve, reject) => {
    setTimeout(() => {
      //Simulating an error
      reject("Failed to fetch data from server");
    }, 2000);
  });
}

async function processData() {
  try {
    console.log("Fetching data...");

    const result = await getData(); // waits for Promise

    console.log(result); // will not execute if error occurs

  } catch (error) {
    // Error handling
    console.error("Error occurred:", error);
  } finally {
    //  Cleanup logic
    console.log("Process completed");
  }
}

// Function call
processData();
