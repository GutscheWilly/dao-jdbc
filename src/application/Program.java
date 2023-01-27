package application;

import entities.Department;

public class Program {

    public static void main(String[] args) throws Exception {
        Department department = new Department(1, "RH");
        System.out.println(department);
    }
}
