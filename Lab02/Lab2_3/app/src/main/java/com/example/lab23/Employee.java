package com.example.lab23;

public abstract class Employee {
    private String Id;
    private String Name;


    public Employee(String id, String name) {
        Id = id;
        Name = name;
    }

    public Employee() {

    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }



    public abstract double tinhLuong();

    public String toString() {
        return Id + " - " + Name ;
    }
}
class EmployeeParttime extends Employee {
    public EmployeeParttime(String id, String name, int type) {
        super(id, name);
    }

    public EmployeeParttime() {

    }

    @Override
    public double tinhLuong() {return 150;};
    @Override
    public String toString() {return super.toString() + " -->PartTime=150.0";}
}
class EmployeeFulltime extends Employee {
    public EmployeeFulltime(String id, String name, int type) {
        super(id, name);
    }

    public EmployeeFulltime() {
        super();
    }

    @Override
    public double tinhLuong() {
        return 500;
    }

    @Override
    public String toString() {
        return super.toString()+ " -->FullTime=500.0";
    }
}

