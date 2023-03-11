package org.example.data.implementations;

import org.example.data.exceptions.DataException;
import org.example.model.entities.Department;
import org.example.model.entities.Employee;
import org.example.model.entities.Sex;

import static org.example.data.JDBCConstants.*;

import java.sql.*;

public class TestUtilities {
    // ---Constants---
    public static String DEPARTMENT1_NAME = "Physics";
    public static String DEPARTMENT2_NAME = "Applied Science";
    public static String DEPARTMENT1_ADDRESS = "Wonderful Road, 235";
    public static String DEPARTMENT2_ADDRESS = "Sunny Plaza, 2";
    public static int DEPARTMENT1_CAPACITY = 10;
    public static int DEPARTMENT2_CAPACITY = 5;
    public static String EMPLOYEE1_FIRSTNAME = "Mickey";
    public static String EMPLOYEE2_FIRSTNAME = "Minnie";
    public static String EMPLOYEE12_LASTNAME = "Mouse";
    public static String EMPLOYEE3_FIRSTNAME = "Donald";
    public static String EMPLOYEE3_LASTNAME = "Duck";
    public static String EMPLOYEE4_FIRSTNAME = "Goofy";
    public static String EMPLOYEE4_LASTNAME = "Goof";
    public static Sex EMPLOYEE1_SEX = Sex.MALE;
    public static Sex EMPLOYEE2_SEX = Sex.FEMALE;
    public static Sex EMPLOYEE3_SEX = Sex.MALE;
    public static Sex EMPLOYEE4_SEX = Sex.MALE;

    // ---Helpers---
    public static long insertDepartment(Department department, Connection con) throws DataException {
        try(PreparedStatement ps = con.prepareStatement(INSERT_DEPARTMENT_RETURNING_ID)){
            ps.setString(1, department.getName());
            ps.setString(2, department.getAddress());
            ps.setString(3, department.getAddress());
            ps.setInt(4, department.getMaxCapacity());
            ps.executeUpdate();
            try (ResultSet keys = ps.getGeneratedKeys()) {
                keys.next();
                long key = keys.getLong(1);
                department.setId(key);
                return department.getId();
            }
        } catch (SQLException e) {
            throw new DataException("Error connecting to database", e);
        }
    }
    public static long insertEmployee(Employee employee, Connection con) throws DataException {
        try(PreparedStatement ps = con.prepareStatement(INSERT_EMPLOYEE_RETURNING_ID)){
            ps.setString(1, employee.getFirstname());
            ps.setString(2, employee.getLastname());
            ps.setObject(3, employee.getSex(), Types.OTHER);
            ps.setLong(4, employee.getDepartment().getId());
            ps.executeUpdate();
            try (ResultSet keys = ps.getGeneratedKeys()) {
                keys.next();
                long key = keys.getLong(1);
                employee.setId(key);
                return employee.getId();
            }
        } catch (SQLException e) {
            throw new DataException("Error connecting to database", e);
        }
    }
}
