import java.util.Scanner;

class Employee {
    String name;
    int empid;
    double monthlySalary;

    // Default constructor
    Employee() {
        empid = 0;
        name = "";
        monthlySalary = 0.0;
    }

    // Parameterized constructor
    Employee(int empid, String name, double monthlySalary) {
        this.empid = empid;
        this.name = name;
        this.monthlySalary = monthlySalary;
    }

    // Calculate annual salary
    double annualSalary() {
        return monthlySalary * 12;
    }

    // Calculate bonus
    double bonus() {
        if (monthlySalary >= 20000) {
            return annualSalary() * 0.10;
        } else {
            return 0;
        }
    }

    // Check eligibility
    void eligible() {
        if (annualSalary() >= 300000) {
            System.out.println("Eligible");
        } else {
            System.out.println("Not Eligible");
        }
    }

    // Display employee details
    void display() {
        System.out.println("Employee Name : " + name);
        System.out.println("Employee ID   : " + empid);
        System.out.println("Monthly Salary: " + monthlySalary);
    }
}

public class EmployeeManagement {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        Employee[] e = new Employee[5];

        for (int i = 0; i < e.length; i++) {

            System.out.println("Enter Employee Name:");
            String name = sc.next();

            System.out.println("Enter Employee ID:");
            int empid = sc.nextInt();

            System.out.println("Enter Monthly Salary:");
            double monthlySalary = sc.nextDouble();

            e[i] = new Employee(empid, name, monthlySalary);
        }

        System.out.println("\nEmployee Details");

        for (int i = 0; i < e.length; i++) {

            e[i].display();

            System.out.println("Annual Salary : " + e[i].annualSalary());

            System.out.println("Bonus         : " + e[i].bonus());

            e[i].eligible();

            System.out.println();
        }

        sc.close();
    }
}