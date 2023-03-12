package org.example.data.abstractions;

import org.example.data.exceptions.DataException;
import org.example.data.exceptions.EntityNotFoundException;
import org.example.model.entities.Department;

public interface DepartmentRepository {
    int addDepartment(Department department) throws DataException;
    void deleteDepartmentById(long id) throws EntityNotFoundException, DataException;
    Iterable<Department> findDepartmentByName(String departmentNamePart) throws DataException;
}
