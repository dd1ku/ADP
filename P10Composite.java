import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
public class P10Composite{
    public static void main(String[] args){
        Scanner in = new Scanner(System.in);

        Employee e1 = new Employee("Иван", "Разработчик", 70000);
        Employee e2 = new Employee("Анна", "Тестировщик", 50000);
        Employee e3 = new Employee("Мария", "Менеджер", 80000);
        Employee e4 = new Employee("Павел", "Аналитик", 60000);

        Department development = new Department("Разработка");
        Department testing = new Department("Тестирование");
        Department management = new Department("Управление");
        Department company = new Department("Компания");

        development.add(e1);
        testing.add(e2);
        management.add(e3);
        management.add(e4);

        company.add(development);
        company.add(testing);
        company.add(management);

        System.out.println("Иерархия компании:");
        company.displayHierarchy("");

        System.out.println("\nОбщий бюджет компании: " + company.getBudget());

        System.out.println("Общее количество сотрудников в компании: " + company.getEmployeeCount());

        e1.setSalary(75000);
        System.out.println("\nОбновленный бюджет компании после повышения зарплаты:");
        System.out.println("Общий бюджет компании: " + company.getBudget());

        OrganizationComponent foundEmployee = company.findByName("Анна");
        if (foundEmployee != null) {
            System.out.println("\nСотрудник найден: " + foundEmployee.getName());
        } else {
            System.out.println("\nСотрудник не найден.");
        }

        System.out.println("\nСписок всех сотрудников компании:");
        company.listAllEmployees();

        in.close();
    }
}

abstract class OrganizationComponent{
    protected String name;

    public OrganizationComponent(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }

    public abstract void displayHierarchy(String indent);
    public abstract int getEmployeeCount();
    public abstract double getBudget();

    public void add(OrganizationComponent component){
        throw new UnsupportedOperationException();
    }

    public void remove(OrganizationComponent component){
        throw new UnsupportedOperationException();
    }

    public OrganizationComponent findByName(String name){
        return this.name.equals(name) ? this : null;
    }
}

class Employee extends OrganizationComponent{
    private String position;
    private double salary;

    public Employee(String name, String position, double salary){
        super(name);
        this.position = position;
        this.salary = salary;
    }

    public String getPosition(){
        return position;
    }

    public double getSalary(){
        return salary;
    }

    public void setSalary(double salary){
        this.salary = salary;
    }

    public void displayHierarchy(String indent){
        System.out.println(indent + "- " + name + ", " + position + ", Зарплата: " + salary);
    }

    public int getEmployeeCount(){
        return 1;
    }

    public double getBudget(){
        return salary;
    }
}

class Department extends OrganizationComponent{
    private List<OrganizationComponent> components = new ArrayList<>();

    public Department(String name){
        super(name);
    }

    public void add(OrganizationComponent component){
        components.add(component);
    }

    public void remove(OrganizationComponent component){
        components.remove(component);
    }

    public void displayHierarchy(String indent){
        System.out.println(indent + "Отдел: " + name);
        for (OrganizationComponent component : components){
            component.displayHierarchy(indent + "    ");
        }
    }

    public int getEmployeeCount(){
        int count = 0;
        for (OrganizationComponent component : components){
            count += component.getEmployeeCount();
        }
        return count;
    }

    public double getBudget(){
        double budget = 0;
        for (OrganizationComponent component : components){
            budget += component.getBudget();
        }
        return budget;
    }

    public OrganizationComponent findByName(String name){
        if (this.name.equals(name)) {
            return this;
        }
        for (OrganizationComponent component : components){
            OrganizationComponent found = component.findByName(name);
            if (found != null) {
                return found;
            }
        }
        return null;
    }

    public void listAllEmployees(){
        for (OrganizationComponent component : components){
            if (component instanceof Employee){
                System.out.println(component.getName());
            } else if (component instanceof Department){
                ((Department) component).listAllEmployees();
            }
        }
    }
}