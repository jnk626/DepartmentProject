package org.example.data.implementations;

import org.example.data.exceptions.DataException;
import org.example.model.entities.Department;
import org.example.model.entities.Employee;
import org.example.model.entities.Sex;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static org.example.data.JDBCConstants.URL;
import static org.example.data.JDBCConstants.USERNAME;
import static org.example.data.JDBCConstants.PASSWORD;
import static org.example.data.implementations.TestUtilities.*;
import static org.junit.jupiter.api.Assertions.*;

public class JDBCDepartmentRepositoryTest {
    private Department d1;
    private Department d2;
    private Department d3;
    private Employee e1;
    private Employee e2;
    private Employee e3;
    private Employee e4;
    private Connection con;
    private JDBCDepartmentRepository repo;
    @BeforeEach
    void setUp() throws SQLException {
        // ---Setting Connection---
        con = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        con.setAutoCommit(false);
        // ---Departments---
        d1 = new Department(0, DEPARTMENT1_NAME, DEPARTMENT1_ADDRESS, DEPARTMENT1_CAPACITY, null);
        d2 = new Department(0, DEPARTMENT2_NAME, DEPARTMENT2_ADDRESS, DEPARTMENT2_CAPACITY, null);
        d3 = new Department(0, DEPARTMENT3_NAME, DEPARTMENT3_ADDRESS, DEPARTMENT3_CAPACITY, null);
        // ---Departments Insertions---
        d1.setId(insertDepartment(d1, con));
        d2.setId(insertDepartment(d2, con));
        // ---Employees---
        e1 = new Employee(0, EMPLOYEE1_FIRSTNAME, EMPLOYEE12_LASTNAME, null, EMPLOYEE1_SEX, d1);
        e2 = new Employee(0, EMPLOYEE2_FIRSTNAME, EMPLOYEE12_LASTNAME, null, EMPLOYEE2_SEX, d1);
        e3 = new Employee(0, EMPLOYEE3_FIRSTNAME, EMPLOYEE3_LASTNAME, null, EMPLOYEE3_SEX, d2);
        e4 = new Employee(0, EMPLOYEE4_FIRSTNAME, EMPLOYEE4_LASTNAME, null, EMPLOYEE4_SEX, d1);
        // ---Employees Insertions---
        e1.setId(insertEmployee(e1, con));
        e2.setId(insertEmployee(e2, con));
        e3.setId(insertEmployee(e3, con));
        e4.setId(insertEmployee(e4, con));
        // ---Setting Repo---
        repo = new JDBCDepartmentRepository(con);
    }
    @AfterEach
    void tearDown() {
        try {
            if (con != null) {
                con.rollback();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Test
    void addDepartment_checks_if_added() {
        try {
            int added = repo.addDepartment(d3);
            assertEquals(1,added);
        } catch (DataException e) {
            e.printStackTrace();
        }

    }

    @Test
    void deleteDepartmentById_checks_if_still_present(){

    }

    @Test
    void findDepartmentByName_checks_if_retrieves_employees(){

    }

}
