abstract class Employee {
    protected String name;
    protected int id;

    Employee(String name, int id) {
        this.name = name;
        this.id = id;
    }

    abstract double monthlySalary();
}

class FullTime extends Employee {
    private double fixedSalary;
    FullTime(String name, int id, double fixedSalary) {
        super(name, id);
        this.fixedSalary = fixedSalary;
    }
    @Override
    double monthlySalary() {
        return fixedSalary;
    }
}

class PartTime extends Employee {
    private int hours;
    private double rate;
    PartTime(String name, int id, int hours, double rate) {
        super(name, id);
        this.hours = hours;
        this.rate = rate;
    }
    @Override
    double monthlySalary() {
        return hours * rate;
    }
}

class Intern extends Employee {
    private double stipend;
    Intern(String name, int id, double stipend) {
        super(name, id);
        this.stipend = stipend;
    }
    @Override
    double monthlySalary() {
        return stipend;
    }
}

public class PayrollTest {
    public static void main(String[] args) {
        Employee[] employees = {
            new FullTime("Alice", 101, 50000),
            new PartTime("Bob", 102, 80, 200),
            new Intern("Charlie", 103, 5000)
        };

        double totalSalary = 0;

        for (Employee e : employees) {
            double salary = e.monthlySalary();
            System.out.print(e.name + " (ID: " + e.id + ") Salary: " + salary);
            if (e instanceof Intern) {
                System.out.print(" [Intern]");
            }
            System.out.println();
            totalSalary += salary;
        }

        System.out.println("Total Salary = " + totalSalary);
    }
}
