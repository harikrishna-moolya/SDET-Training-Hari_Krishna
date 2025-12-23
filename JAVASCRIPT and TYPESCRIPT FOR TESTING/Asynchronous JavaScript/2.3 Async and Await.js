//2.3 Async / Await
function getData() {
  return new Promise((resolve, reject) => {
    setTimeout(() => {
      resolve("Data received successfully");
    }, 2000);
  });
}

async function processData() {
  console.log("Fetching data...");
  const result = await getData();
  console.log(result);
}

processData();
