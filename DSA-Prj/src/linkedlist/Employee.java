package linkedlist;

public class Employee {

    String code = "";
    String name = "";
    int salary = 0;

    public Employee() {
    }

    public Employee(String c, String n, int s) {
        code = c;
        name = n;
        salary = s;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public boolean equalS(Employee emp) {
        return emp.getCode().equals(this.getCode())
                && emp.getName().equals(this.getName())
                && emp.getSalary() == this.getSalary();
    }

    @Override
    public String toString() {
        return code +"\t"+name+"\t"+salary;
    }
    
    
}
