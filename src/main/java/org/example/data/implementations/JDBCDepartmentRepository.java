package org.example.data.implementations;

import org.example.data.abstractions.DepartmentRepository;
import org.example.data.exceptions.DataException;
import org.example.data.exceptions.EntityNotFoundException;
import org.example.model.entities.Department;
import org.example.model.entities.Employee;
import org.example.model.entities.Sex;

import static org.example.data.JDBCConstants.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class JDBCDepartmentRepository implements DepartmentRepository {
    private Connection con;
    public JDBCDepartmentRepository(Connection con) {
        this.con = con;
    }
    @Override
    public void addDepartment(Department department) throws DataException {
        try(PreparedStatement ps = con.prepareStatement(INSERT_DEPARTMENT_RETURNING_ID)) {
            ps.setString(1, department.getName());
            ps.setString(2, department.getAddress());
            ps.setInt(3, department.getMaxCapacity());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DataException("Error accessing the database",e);
        }

    }

    @Override
    public void deleteDepartmentById(long id) throws EntityNotFoundException, DataException {
        try(PreparedStatement ps = con.prepareStatement(DELETE_DEPARTMENT)){
            ps.setLong(1, id);
            int result = ps.executeUpdate();
            if (result != 1) throw new EntityNotFoundException("Department not found");
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DataException("Error accessing the database",e);
        }
    }

    @Override
    public Iterable<Department> findDepartmentByName(String departmentNamePart) throws DataException {
        try(PreparedStatement ps = con.prepareStatement(FIND_DEPARTMENT_BY_NAME)){
            ps.setString(1, "%"+ departmentNamePart +"%");
            List<Department> result = new ArrayList<>();
            try(ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Department d = databaseToDepartment(rs);
                    d.setEmployeesSet(departmentEmployees(d.getId(), d));
                    result.add(d);
                }
                return result;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DataException("Error accessing the database", e);
        }
    }

    public Set<Employee> departmentEmployees(long departmentId, Department department) throws DataException {
        try(PreparedStatement ps = con.prepareStatement(GET_EMPLOYEES_BY_DEPARTMENT_ID)){
            ps.setLong(1, departmentId);
            Set<Employee> result = new HashSet<>() ;
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                result.add(new Employee(
                        rs.getLong("id_employee"),
                        rs.getString("firstname"),
                        rs.getString("lastname"),
                        rs.getDate("hire_date").toLocalDate(),
                        rs.getObject("sex", Sex.class),
                        department
                ));
            }
            return result;
        } catch (SQLException e) {
            throw new DataException("Error accessing the database", e);
        }
    }

    public Department databaseToDepartment(ResultSet rs) throws DataException {
        try {
            return new Department(
                    rs.getLong("id_department"),
                    rs.getString("name"),
                    rs.getString("address"),
                    rs.getInt("max_capacity")
            );
        } catch (SQLException e) {
            throw new DataException("Error accessing the database", e);
        }
    }

}
