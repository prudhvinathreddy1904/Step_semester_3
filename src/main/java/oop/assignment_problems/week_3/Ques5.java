class Employee {
    private int empId;
    private String empName;
    private double salary;

    Employee(int empId, String empName, double salary) {
        this.empId = empId;
        this.empName = empName;
        this.salary = salary;
    }

    double getSalary() {
        return salary;
    }
}

class ManagerEmployee extends Employee {
    private double teamBonus;

    ManagerEmployee(int empId, String empName, double salary, double teamBonus) {
        super(empId, empName, salary);
        this.teamBonus = teamBonus;
    }

    double effectiveSalary() {
        return getSalary() + teamBonus;
    }
}

class InternEmployee extends Employee {
    private double stipendCap;

    InternEmployee(int empId, String empName, double salary, double stipendCap) {
        super(empId, empName, salary);
        this.stipendCap = stipendCap;
    }

    double effectiveSalary() {
        return Math.min(getSalary(), stipendCap);
    }
}

class ParkingSlot {
    String slotNo;
    int capacity;
    int occupiedCount;

    ParkingSlot(String slotNo, int capacity, int occupiedCount) {
        this.slotNo = slotNo;
        this.capacity = capacity;
        this.occupiedCount = occupiedCount;
    }

    void allot(String vehicleNo) {
        if (occupiedCount < capacity) {
            occupiedCount++;
        }
    }

    static ParkingSlot findAvailableSlot(ParkingSlot[] slots) {
        for (ParkingSlot slot : slots) {
            if (slot.occupiedCount < slot.capacity) {
                return slot;
            }
        }
        return null;
    }

    static ParkingSlot safeAllot(ParkingSlot[] slots, String vehicleNo) {
        ParkingSlot slot = findAvailableSlot(slots);

        if (slot != null) {
            slot.allot(vehicleNo);
            return slot;
        }

        return null;
    }
}

class CompanyEmployeeRecord {
    String name;
    String empId;
    Employee employee;
    ParkingSlot slot;

    static int totalRecords = 0;

    CompanyEmployeeRecord(String name, String empId, Employee employee, ParkingSlot slot) {
        this.name = name;
        this.empId = empId;
        this.employee = employee;
        this.slot = slot;
        totalRecords++;
    }

    String fullProfile() {
        double pay;

        if (employee instanceof ManagerEmployee) {
            pay = ((ManagerEmployee) employee).effectiveSalary();
        } else if (employee instanceof InternEmployee) {
            pay = ((InternEmployee) employee).effectiveSalary();
        } else {
            pay = employee.getSalary();
        }

        String parking = slot == null ? "no parking assigned" : slot.slotNo;

        return name + " | Pay: Rs " + pay + " | Slot: " + parking;
    }
}

class Main {
    public static void Ques5(String[] args) {
        Employee manager =
                new ManagerEmployee(101, "Divya", 70000, 8000);

        Employee employee =
                new Employee(102, "Karan", 40000);

        Employee intern =
                new InternEmployee(103, "Meera", 12000, 10000);

        ParkingSlot[] slots = {
            new ParkingSlot("A1", 2, 0),
            new ParkingSlot("A2", 2, 0)
        };

        ParkingSlot slot1 =
                ParkingSlot.safeAllot(slots, "TN01AA1111");

        ParkingSlot slot2 =
                ParkingSlot.safeAllot(slots, "TN01AA2222");

        CompanyEmployeeRecord r1 =
                new CompanyEmployeeRecord("Divya", "101", manager, slot1);

        CompanyEmployeeRecord r2 =
                new CompanyEmployeeRecord("Karan", "102", employee, slot2);

        CompanyEmployeeRecord r3 =
                new CompanyEmployeeRecord("Meera", "103", intern, null);

        System.out.println(r1.fullProfile());
        System.out.println(r2.fullProfile());
        System.out.println(r3.fullProfile());
        System.out.println("Total records: " +
                CompanyEmployeeRecord.totalRecords);
    }
}