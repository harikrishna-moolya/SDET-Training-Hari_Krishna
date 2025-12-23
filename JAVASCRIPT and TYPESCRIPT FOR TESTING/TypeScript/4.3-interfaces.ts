export interface User {
  id: number;
  name: string;
  email: string;
  isAdmin?: boolean; // optional
}

export const user: User = {
  id: 1,
  name: "Hari",
  email: "hari@email.com"
};
console.log("User:", user);

// Type alias
export type Product = {
  id: number;
  name: string;
  price: number;
};

export const product: Product = {
  id: 101,
  name: "Laptop",
  price: 50000
};
console.log("Product:", product);
