package org.example.data;

public class JDBCConstants {
    public static final String URL = "jdbc:postgresql://localhost:5432/org";
    public static final String USERNAME = "postgresMaster";
    public static final String PASSWORD = "goPostgresGo";
    public static final String INSERT_DEPARTMENT = """
            INSERT INTO department(id_department, name, address, max_capacity)
            VALUES (nextval('department_sequence'), ?, ?, ?)
            """;
    public static final String INSERT_DEPARTMENT_RETURNING_ID = """
            INSERT INTO department(id_department, name, address, max_capacity)
            VALUES (nextval('department_sequence'), ?, ?, ?)
            RETURNING id_department
            """;

    public static final String INSERT_EMPLOYEE_RETURNING_ID = """
            INSERT INTO employee(id_employee, firstname, lastname, sex, id_department)
            VALUES (nextval('employee_sequence'), ?, ?, ?, ?)
            RETURNING id_employee
            """;
    public static final String DELETE_DEPARTMENT = """
            DELETE FROM department WHERE id_department = ?
            """;

    public static final String FIND_DEPARTMENT_BY_NAME = """
            SELECT id_department, name, address, max_capacity,
            WHERE name LIKE ?
            """;

    public static final String GET_EMPLOYEES_BY_DEPARTMENT_ID = """
            SELECT id_employee, firstname, lastname, hire_date, e.sex, id_department
                        FROM department JOIN employee
                        USING (id_department)
                        WHERE id_department = ?
            """;
}
