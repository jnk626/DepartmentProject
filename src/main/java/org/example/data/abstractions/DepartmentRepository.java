package org.example.data.abstractions;

import org.example.model.entities.Department;

public interface DepartmentRepository {
    void addDepartment(Department department);
    void deleteDepartment(Department department);
    Department findDepartmentByName(String departmentNamePart);
}
