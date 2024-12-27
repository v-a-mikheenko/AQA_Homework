
public class Main {

    public static void main(String[] args) {

        Employee[] employees = new Employee[5];
        employees[0] = new Employee("Иванов Иван иванович","Директор","ivanov@gmail.com","+79998877666",500_000,50);
        employees[1] = new Employee("Петров Петр Петрович","Менеджер","petrov@outlook.com","+79997766555",220_000,35);
        employees[2] = new Employee("Сидорова Евгения Евгеньевна","Бугалтер","sidorova@mail.ru","+79996655444",160_000,55);
        employees[3] = new Employee("Попов Пётр Петрович","Билетёр","popov@inbox.ru","+79995544333",32_000,28);
        employees[4] = new Employee("Васильева Александра Александровна","Аниматор","vasilyeva@ya.ru","+79994433222",45_000,24);

        for (Employee employee : employees) {
            employee.employeesInfo();
        }

        System.out.println();

        Park park = new Park();
        park.addNewAttraction("Колесо обозрения", "10:00 - 20:00", 180.0);
        park.addNewAttraction("Цепочная карусель", "9:00 - 18:00", 160.0);
        park.addNewAttraction("Летающая тарелка", "11:00 - 18:00", 200.0);

        park.allAttraction();
    }
}