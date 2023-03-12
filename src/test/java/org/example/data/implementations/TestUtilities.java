package org.example.data.implementations;

import org.example.model.entities.Department;
import org.example.model.entities.Employee;
import org.example.model.entities.Sex;

import static org.example.data.JDBCConstants.*;

import java.sql.*;

public class TestUtilities {
    // ---Constants---
    public static String DEPARTMENT1_NAME = "Physics";
    public static String DEPARTMENT2_NAME = "Applied Science";
    public static String DEPARTMENT3_NAME = "TEST";
    public static String DEPARTMENT1_ADDRESS = "Wonderful Road, 235";
    public static String DEPARTMENT2_ADDRESS = "Sunny Plaza, 2";
    public static String DEPARTMENT3_ADDRESS = "TEST_ADDRESS";
    public static int DEPARTMENT1_CAPACITY = 10;
    public static int DEPARTMENT2_CAPACITY = 5;
    public static int DEPARTMENT3_CAPACITY = 1;
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
    public static long insertDepartment(Department department, Connection con) {
        try(PreparedStatement ps = con.prepareStatement(INSERT_DEPARTMENT_RETURNING_ID, Statement.RETURN_GENERATED_KEYS)){
            ps.setString(1, department.getName());
            ps.setString(2, department.getAddress());
            ps.setInt(3, department.getMaxCapacity());
            ps.executeUpdate();
            try (ResultSet rs = ps.getGeneratedKeys()) {
                rs.next();
                return rs.getLong(1);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static long insertEmployee(Employee employee, Connection con) {
        try(PreparedStatement ps = con.prepareStatement(INSERT_EMPLOYEE_RETURNING_ID, Statement.RETURN_GENERATED_KEYS)){
            ps.setString(1, employee.getFirstname());
            ps.setString(2, employee.getLastname());
            ps.setObject(3, employee.getSex(), Types.OTHER);
            ps.setLong(4, employee.getDepartment().getId());
            ps.executeUpdate();
            try (ResultSet rs = ps.getGeneratedKeys()) {
                rs.next();
                return rs.getLong(1);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
