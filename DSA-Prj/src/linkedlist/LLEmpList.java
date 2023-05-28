package linkedlist;

import java.util.Scanner;

public class LLEmpList extends SLL {

    Scanner sc = null;

    public LLEmpList() {
        super();
        sc = new Scanner(System.in);
    }

    private SLLNode<Employee> find(String code) {
        SLLNode<Employee> ref;
        for (ref = this.getHead(); ref != null; ref = ref.next) 
            if (ref.info.getCode().equals(code)) return ref;
        return null;
    }

    public void add() {
        String code = "", name = "";
        int salary = 0;
        System.out.println("Add new employee");
        boolean proceed = false;
        do {
            System.out.println("Enter employee's code: ");
            code = sc.nextLine().toUpperCase();
            proceed = find(code) != null;
            if (proceed == true) {
                System.out.println("This code is duplicated !");
            }
        } while (proceed);
        do {
            System.out.println("Enter employee's name: ");
            name = sc.nextLine().toUpperCase();
            proceed = (name.length() == 0);
            if (proceed == true) {
                System.out.println("Name must be inputted. ");
            }
        } while (proceed);
        do {
            System.out.println("Enter employee's salary: ");
            salary = Integer.parseInt(sc.nextLine());
            if (salary <= 0) {
                System.out.println("Salary must be greater than 0");
            }
        } while (salary <= 0);
        Employee emp = new Employee(code, name, salary);
        this.addToTail(emp);
        System.out.println("Added Successful");
    }

    public void remove() {
        if (this.isEmpty()) System.out.println("The list is empty.");
        else {
            String code = "";
            System.out.print("Enter the code of employee who will be removed: ");
            code = sc.nextLine().toUpperCase();
            SLLNode<Employee> ref = find(code);
            if (ref == null) System.out.println("He/she does not exist.");
            else {
                this.delete(ref.info);
                System.out.print("This employee has been removed.");
            }
        }
    }

    public void increaseSalary() {
        if (this.isEmpty()) {
            System.out.println("The list is empty.");
        } else {
            String code = "";
            System.out.print("Enter the code of employee who will be promoted: ");
            code = sc.nextLine().toUpperCase();
            SLLNode<Employee> ref = find(code);
            if (ref == null) {
                System.out.println("He/she does not exist.");
            } else {
                int oldSal = ref.info.getSalary();
                int newSal;
                do {
                    System.out.print("Old salary: " + oldSal + ", new salary: ");
                    newSal = Integer.parseInt(sc.nextLine());
                } while (newSal <= oldSal);
                ref.info.setSalary(newSal);
                System.out.print("His / her salary has been updated.");
            }
        }
    }
    
    public void print() {
        if (this.isEmpty()) System.out.println("The list is emmpty");
        else {
            System.out.println("EMPLOYEE LIST");
            this.printAll();
        }
    }
}
