// string
let username: string = "Hari";
console.log("username:", username);

// number
let age: number = 25;
console.log("age:", age);

// boolean
let isActive: boolean = true;
console.log("isActive:", isActive);

// array
let skills: string[] = ["JavaScript", "TypeScript"];
console.log("skills:", skills);

let scores: number[] = [80, 90, 100];
console.log("scores:", scores);

// tuple
let userInfo: [string, number] = ["Hari", 25];
console.log("userInfo:", userInfo);

// any type
let data: any = "Hello";
console.log("data (string):", data);
data = 123;
console.log("data (number):", data);

// union type
let id: number | string;
id = 101;
console.log("id (number):", id);
id = "A101";
console.log("id (string):", id);
