export class Employee {
  public name: string;
  private salary: number;

  constructor(name: string, salary: number) {
    this.name = name;
    this.salary = salary;
  }

  getDetails(): string {
    return `Employee: ${this.name}`;
  }
}

export class Manager extends Employee {
  constructor(name: string) {
    super(name, 80000);
  }
}

const emp = new Employee("Hari", 50000);
console.log(emp.getDetails());

const manager = new Manager("Krishna");
console.log(manager.getDetails());
