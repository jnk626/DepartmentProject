package org.example.model.entities;

import java.util.Set;

public class Department {
    private long id;
    private String name;
    private String address;
    private int maxCapacity;
    private Set<Employees> employeesSet;
}
