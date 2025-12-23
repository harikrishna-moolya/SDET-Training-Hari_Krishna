"use strict";
var __extends = (this && this.__extends) || (function () {
    var extendStatics = function (d, b) {
        extendStatics = Object.setPrototypeOf ||
            ({ __proto__: [] } instanceof Array && function (d, b) { d.__proto__ = b; }) ||
            function (d, b) { for (var p in b) if (Object.prototype.hasOwnProperty.call(b, p)) d[p] = b[p]; };
        return extendStatics(d, b);
    };
    return function (d, b) {
        if (typeof b !== "function" && b !== null)
            throw new TypeError("Class extends value " + String(b) + " is not a constructor or null");
        extendStatics(d, b);
        function __() { this.constructor = d; }
        d.prototype = b === null ? Object.create(b) : (__.prototype = b.prototype, new __());
    };
})();
Object.defineProperty(exports, "__esModule", { value: true });
exports.Manager = exports.Employee = void 0;
var Employee = /** @class */ (function () {
    function Employee(name, salary) {
        this.name = name;
        this.salary = salary;
    }
    Employee.prototype.getDetails = function () {
        return "Employee: ".concat(this.name);
    };
    return Employee;
}());
exports.Employee = Employee;
var Manager = /** @class */ (function (_super) {
    __extends(Manager, _super);
    function Manager(name) {
        return _super.call(this, name, 80000) || this;
    }
    return Manager;
}(Employee));
exports.Manager = Manager;
var emp = new Employee("Hari", 50000);
console.log(emp.getDetails());
var manager = new Manager("Krishna");
console.log(manager.getDetails());
