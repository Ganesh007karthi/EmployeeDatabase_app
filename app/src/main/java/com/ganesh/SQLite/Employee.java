package com.ganesh.SQLite;

public class Employee {

    public static String Table_Name="Employee";
    public static String Column_id="id";
    public static String Column_EmployeeName="EmployeeName";
    public static String Column_EmployeeDept="EmployeeDept";
    public static String Column_EmployeeSalary="EmployeeSalary";

    private int id;
    private String Employeename;
    private String EmployeeDept;
    private String EmmployeeSalary;

    public static final String CreateTable= "CREATE TABLE "+Table_Name+"("+Column_id+" INTEGER PRIMARY KEY AUTOINCREMENT, "
                +Column_EmployeeName+" TEXT, "+Column_EmployeeDept+" TEXT, "+Column_EmployeeSalary+" TEXT "+")";

    public Employee(int id,String employeename, String employeeDept, String emmployeeSalary) {
        this.id=id;
        this.Employeename = employeename;
        this.EmployeeDept = employeeDept;
        this.EmmployeeSalary = emmployeeSalary;
    }

    public Employee(){

    }

    public int getId() {
        return id;
    }

    public String getEmployeename() {
        return Employeename;
    }

    public String getEmployeeDept() {
        return EmployeeDept;
    }

    public String getEmmployeeSalary() {
        return EmmployeeSalary;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setEmployeename(String employeename) {
        Employeename = employeename;
    }

    public void setEmployeeDept(String employeeDept) {
        EmployeeDept = employeeDept;
    }

    public void setEmmployeeSalary(String emmployeeSalary) {
        EmmployeeSalary = emmployeeSalary;
    }
}
