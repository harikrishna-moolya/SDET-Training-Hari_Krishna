//2.1 Callbacks

function fetchData(callback) {
  setTimeout(() => {
    callback("Data received");
  }, 1000);
}

fetchData(result => console.log(result));
