package ar.com.playmedia.view;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;

public class Employee extends View {
    private ar.com.playmedia.model.Employee currentEmployee;
    private ar.com.playmedia.controller.Employee employeeController;
    private ar.com.playmedia.controller.Shift shiftController;

    private ArrayList<ar.com.playmedia.model.Shift> todayShifts;

    public Employee() {
        employeeController = new ar.com.playmedia.controller.Employee();
        shiftController = new ar.com.playmedia.controller.Shift();
        todayShifts = new ArrayList<ar.com.playmedia.model.Shift>();
    }

    public void mainMenu() {
        String dni;
        String pass;
        clearScreen();

        System.out.println(">>EMPLEADOS<<");

        System.out.println("Ingrese su numero de documento:");
        dni = keyboard.nextLine();
        System.out.println("Ingrese su contraseña:");
        pass = keyboard.nextLine();

        currentEmployee = employeeController.loginEmployee(dni, pass);

        if (currentEmployee.getDni() == null) {
            clearScreen();
            System.out.println("DNI o Contrasenna Incorrectos.");

        } else {
            logedMenu();
        }

    }

    public void logedMenu() {
        Boolean terminated = false;
        Integer option;
        clearScreen();
        if (currentEmployee.getJob().equals("VET")) {
            do {
                System.out.println("Bienvenido " + currentEmployee.getName() + "!\n");
                System.out.println("Seleccione el menu al que deseas acceder:\n");

                System.out.println("1-Ver Turnos");
                System.out.println("2-Tomar un turno");
                System.out.println("3-Ver historial clinico de una mascota\n");

                System.out.println("0-Salir");

                option = Integer.parseInt(keyboard.nextLine());
                switch (option) {
                    case 1:
                        clearScreen();
                        listAvailableShifts();
                        break;
                    case 2:
                        clearScreen();
                        takeShift();
                        break;
                    case 3:
                        clearScreen();
                        listClinicHistoryFromPet();
                    case 0:
                        clearScreen();
                        terminated = !terminated;
                        break;
                    default:
                        clearScreen();
                        System.out.println("--Opcion invalida. Intentelo nuevamente--");
                        break;
                }

            } while (!terminated);
        } else {
            do {
                System.out.println(">>ADMIN<<\n");
                System.out.println("Seleccione el menu al que deseas acceder:\n");

                System.out.println("1-Crear Empleado");
                System.out.println("2-Eliminar Empleado\n");

                System.out.println("0-Salir");

                option = Integer.parseInt(keyboard.nextLine());
                switch (option) {
                    case 1:
                        clearScreen();
                        createEmployee();
                        break;
                    case 2:
                        deleteEmployee();
                        break;
                    case 0:
                        terminated = !terminated;
                        break;
                    default:
                        clearScreen();
                        System.out.println("--Opcion invalida. Intentelo nuevamente--");
                        break;
                }

            } while (!terminated);
        }
    }

    private void listClinicHistoryFromPet() {
        Integer input;
        System.out.println("Ingrese el ID de la mascota:");
        input = Integer.parseInt(keyboard.nextLine());

        ArrayList<ar.com.playmedia.model.Shift> historyShifts = shiftController.listShiftsByPet(input);
        System.out.println(">>Historia clinica de " + input + ":");
        for (ar.com.playmedia.model.Shift shift : historyShifts) {

            System.out.println("ID: " + shift.getId() + " Client ID: " + shift.getClientId());
            System.out.println("Diagnostico:\n " + shift.getDiagnosis());
            System.out.println("- - - - - - - - - - - -");

        }

        do {
            System.out.println("\nIngrese 0 para salir.");
            int option = Integer.parseInt(keyboard.nextLine());
            if (option == 0) {
                clearScreen();
                break;
            } else {
                clearScreen();
                System.out.println("--Opcion invalida. Intentelo nuevamente--");
            }
        } while (true);

    }

    public void createEmployee() {
        String input;
        Boolean terminated = true;
        currentEmployee = new ar.com.playmedia.model.Employee();
        do {

            System.out.println("Ingrese los siguientes datos para crear su cuenta:");
            System.out.println("Numero de Documento (DNI):");
            input = keyboard.nextLine();
            currentEmployee.setDni(input);

            if (!(employeeController.getEmployee(input).getDni() == null)) {
                System.out.println("Ya existe un usuario con ese DNI!");
            } else {
                System.out.println("Puedes continuar.");

                System.out.println("Nombre:");
                input = keyboard.nextLine();
                currentEmployee.setName(input);
                System.out.println("Apellido:");
                input = keyboard.nextLine();
                currentEmployee.setSurname(input);
                System.out.println("Contraseña:");
                input = keyboard.nextLine();
                currentEmployee.setPassword(input);
                System.out.println("Direccion:");
                input = keyboard.nextLine();
                currentEmployee.setJob("VET");
                System.out.println("Salario:");
                input = keyboard.nextLine();
                currentEmployee.setSalary(Integer.parseInt(input));
                System.out.println("Fecha de nacimiento(dd-MM-yyyy):");
                input = keyboard.nextLine();
                try {
                    currentEmployee.setBirth_date(format.parse(input));
                } catch (ParseException e) {

                    e.printStackTrace();
                }

                do {
                    clearScreen();
                    showEmployee(currentEmployee);
                    System.out.println("Seleccione una opcion:");
                    System.out.println("1-Crear un usuario con los datos ingresados.");
                    System.out.println("2-Volver a completar formulario.\n");
                    System.out.println("0-Salir");
                    input = keyboard.nextLine();
                    if (input.equals("1")) {
                        employeeController.createEmployee(currentEmployee);
                        clearScreen();
                        System.out.println("Se a creado el usuario con exito!");
                        terminated = false;
                        break;
                    }
                    if (input.equals("2")) {
                        clearScreen();
                        System.out.println("Intente nuevamente.");
                        break;
                    }
                    if (input.equals("0")) {
                        clearScreen();
                        terminated = false;
                        break;
                    }
                    System.out.println("Ingrese una opcion valida.");
                } while (true);
            }
        } while (terminated);
    }

    public void listAvailableShifts() {
        updateShifts();
        System.out.println(">>Los tunos disponibles son:");
        for (ar.com.playmedia.model.Shift shift : todayShifts) {
            if (!shift.getTaken()) {
                System.out.println("ID: " + shift.getId() + " Client ID: " + shift.getClientId());
                System.out.println("- - - - - - - - - - - -");
            }
        }

        do {
            System.out.println("\nIngrese 0 para salir.");
            int option = Integer.parseInt(keyboard.nextLine());
            if (option == 0) {
                clearScreen();
                break;
            } else {
                clearScreen();
                System.out.println("--Opcion invalida. Intentelo nuevamente--");
            }
        } while (true);
    }

    public void takeShift() {
        updateShifts();

        Boolean terminated = false;
        Integer shiftId;
        Integer input;

        System.out.println("Ingrese ID de la consulta a realizar:");
        shiftId = Integer.parseInt(keyboard.nextLine());

        for (ar.com.playmedia.model.Shift shift : todayShifts) {

            if (shift.getId() == shiftId && !shift.getTaken()) {
                clearScreen();
                System.out.println("Ingrese el diagnostico resultado de la consulta ID " + shiftId + ":");
                shift.setDiagnosis(keyboard.nextLine());
                do {
                    System.out.println("El siguiente diagnostico sera almacenado y el turno dado por completado:");
                    System.out.println("1-Si, completar turno\t0-No, cambie de opinion");

                    input = Integer.parseInt(keyboard.nextLine());
                    switch (input) {
                        case 1:
                            clearScreen();
                            shift.setTaken(true);
                            shiftController.updateShift(shift);
                            System.out.println("Turno completado con exito.");
                            terminated = !terminated;
                            break;
                        case 0:
                            clearScreen();
                            System.out.println("El turno no se ha completado.");
                            terminated = !terminated;
                            break;
                        default:
                            clearScreen();
                            System.out.println("Opcion invalida, intentelo nuevamente.");
                            break;
                    }
                } while (!terminated);
            } else {
                clearScreen();
                System.out.println("No hay turnos coincidentes");
            }

        }

    }

    public void updateShifts() {
        Date today = new Date();
        this.todayShifts = shiftController.listShiftsByDate(today);
    }

    public void showEmployee(ar.com.playmedia.model.Employee employee) {
        System.out.println("===================");
        System.out.println("DNI: " + employee.getDni());
        System.out.println("Nombre: " + employee.getName());
        System.out.println("Apellido: " + employee.getSurname());
        System.out.println("Contrasenna: " + employee.getPassword());
        if (employee.getFile() != null) {
            System.out.println("Legajo: " + employee.getFile());
        }
        System.out.println("Fecha de Nacimiento: " + format.format(employee.getBirth_date()));
        System.out.println("Trabajo: " + employee.getJob());
        System.out.println("===================");

    }

    public void deleteEmployee() {
        Boolean terminated = false;
        String dni;
        String pass;
        Integer input;

        System.out.println("Ingrese el DNI del ususario a eliminar:");
        dni = keyboard.nextLine();
        System.out.println("Ingrese la contraseña del ususario a eliminar:");
        pass = keyboard.nextLine();

        currentEmployee = employeeController.loginEmployee(dni, pass);
        if (currentEmployee.getDni() != null) {
            do {
                showEmployee(currentEmployee);
                System.out.println("El siguiente usuario se eliminara, estas de acuerdo?");
                System.out.println("1-Si, eliminar ususario.\t0-No, cambie de opinion.");
                input = Integer.parseInt(keyboard.nextLine());

                switch (input) {
                    case 1:
                        employeeController.destroyEmployee(currentEmployee.getDni());
                        System.out.println("Usuario eliminado con exito.");
                        terminated = !terminated;
                        break;
                    case 0:
                        System.out.println("Eliminacion de ususario cancelada.");
                        terminated = !terminated;
                        break;
                    default:
                        System.out.println("Opcion invalida, intentelo nuevamente.");
                        break;
                }

            } while (!terminated);
        } else {
            System.out.println("--DNI o Contraseña incorrectos--");
        }
    }
}