package org.example.model.entities;

import java.time.LocalDate;

public class Employee {
    private long id;
    private String firstname;
    private String lastname;
    private LocalDate hireDate;
    private Sex sex;
    private Department department;

    public Employee(long id, String firstname, String lastname, LocalDate hireDate, Sex sex, Department department) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.hireDate = hireDate;
        this.sex = sex;
        this.department = department;
    }
}
