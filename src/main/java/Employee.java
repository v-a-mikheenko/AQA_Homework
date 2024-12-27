public class Employee {
    private String fullName;
    private String position;
    private String email;
    private String phoneNumber;
    private double salary;
    private int age;

    public Employee(String fullName,
                    String position,
                    String email,
                    String phoneNumber,
                    double salary,
                    int age)
    {
        this.fullName = fullName;
        this.position = position;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.salary = salary;
        this.age = age;
    }

    public void employeesInfo() {
        System.out.println(
                "Full Name: " +fullName+
                " | Position: " +position+
                " | email: " +email+
                " | phone: " +phoneNumber+
                " | Salary: " +salary+
                " | age: " +age);
    }
}
