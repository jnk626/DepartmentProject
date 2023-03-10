package org.example.data.implementations;

import org.example.data.abstractions.DepartmentRepository;
import org.example.model.entities.Department;

public class JDBCDepartmentRepository implements DepartmentRepository {
    @Override
    public void addDepartment(Department department) {

    }

    @Override
    public void deleteDepartment(Department department) {

    }

    @Override
    public Department findDepartmentByName(String departmentNamePart) {
        return null;
    }
}
