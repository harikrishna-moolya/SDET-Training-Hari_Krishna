//1.5 Loops and Iterations
//for loop
for (let i = 1; i <= 5; i++) {
  console.log(i);
}

//foreach
const fruits = ["apple", "banana", "orange"];
fruits.forEach(fruit => console.log(fruit));

//while loop
let i = 1;

while (i <= 5) {
  console.log("While loop:", i);
  i++;
}

//do - while loop
let k = 1;

do {
  console.log("Do-While loop:", k);
  k++;
} while (k <= 5);
