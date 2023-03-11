package org.example.model.entities;

import java.util.Set;

public class Department {
    private long id;
    private String name;
    private String address;
    private int maxCapacity;
    private Set<Employee> employeeSet;

    public Department(long id, String name, String address, int maxCapacity) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.maxCapacity = maxCapacity;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public int getMaxCapacity() {
        return maxCapacity;
    }

    public void setEmployeesSet(Set<Employee> employees) {
        this.employeeSet = employees;
    }
}
