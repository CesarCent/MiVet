package ar.com.playmedia.controller;

public class Employee extends ConnectionControler {
    private String queryString;

    public Employee() {

    }

    public void createEmployee(ar.com.playmedia.model.Employee employee) {
        this.queryString = String.format(" SELECT * FROM employee('%s', '%s', '%s', '%s', '%s', '%s', %s) ",
                employee.getDni(), employee.getName(), employee.getSurname(), employee.getPassword(),
                employee.getBirth_date(), employee.getJob(), employee.getSalary());

        connect();
        execute(queryString);
        disconnect();
    }

    public void destroyEmployee(String dni) {
        this.queryString = String.format("SELECT employee_destroy('%s')", dni);

        connect();
        execute(queryString);
        disconnect();
    }

    public ar.com.playmedia.model.Employee getEmployee(String dni) {
        ar.com.playmedia.model.Employee getedEmployee = new ar.com.playmedia.model.Employee();
        this.queryString = String.format("SELECT * FROM get_employee_by_dni('%s');", dni);

        connect();

        executeQuery(queryString);
        try {

            while (this.result.next()) {
                getedEmployee = new ar.com.playmedia.model.Employee(result.getString(1), result.getString(2),
                        result.getString(3), result.getString(4), result.getDate(5), result.getInt(6),
                        result.getString(7), result.getInt(8));
            }

        } catch (Exception e) {
            System.out.println("EmployeeController.getEmployee -> ERROR: " + e);
        }
        disconnect();

        return getedEmployee;
    }

    public ar.com.playmedia.model.Employee loginEmployee(String dni, String pass) {
        ar.com.playmedia.model.Employee getedEmployee = new ar.com.playmedia.model.Employee();
        this.queryString = String.format("SELECT * FROM get_employee_with_password('%s', '%s')", dni, pass);

        connect();
        executeQuery(this.queryString);
        try {

            while (this.result.next()) {
                getedEmployee = new ar.com.playmedia.model.Employee(result.getString(1), result.getString(2),
                        result.getString(3), result.getString(4), result.getDate(5), result.getInt(6),
                        result.getString(7), result.getInt(8));
            }

            // .out.println("loginEMployee-> getedEmployee.dni: " + getedEmployee.getDni());

        } catch (Exception e) {
            System.out.println("EmployeeController.loginEmployee -> ERROR: " + e);
        }
        disconnect();

        return getedEmployee;

    }

}