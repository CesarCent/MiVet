package ar.com.playmedia.view;

import java.text.ParseException;

public class Client extends View {
    private ar.com.playmedia.model.Client currentClient;
    private ar.com.playmedia.controller.Client clientController;

    private ar.com.playmedia.view.Pet petView;

    public Client() {
        clientController = new ar.com.playmedia.controller.Client();
    }

    public void mainMenu() {
        Integer option = -1;
        clearScreen();
        while (option != 0) {
            System.out.println(">>CLIENTES<<\n");
            System.out.println(" 1) Ingresar");
            System.out.println(" 2) Crear Usuario");
            System.out.println(" 3) Eliminar Usuario");
            System.out.println();
            System.out.println(" 0) Salir");

            System.out.println("Elija una opcion:");

            option = Integer.parseInt(keyboard.nextLine());
            clearScreen();

            switch (option) {
                case 1:
                    login();
                    break;
                case 2:
                    createClient();
                    break;
                case 3:
                    deleteClient();
                    break;
                case 0:
                    currentClient = null;
                    clearScreen();
                    break;
                default:
                    clearScreen();
                    System.out.println("Opcion invalida, intentelo de nuevo.");
                    break;
            }
        }
    }

    public void login() {
        String dni;
        String pass;
        System.out.println("Ingrese su numero de documento:");
        dni = keyboard.nextLine();
        System.out.println("Ingrese su contraseña:");
        pass = keyboard.nextLine();

        currentClient = clientController.loginClient(dni, pass);

        if (currentClient.getDni() == null) {
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
        do {
            System.out.println("Bienvenido " + currentClient.getName() + "!\n");
            System.out.println("Seleccione el menu al que deseas acceder:\n");

            System.out.println("1-Mis Mascotas");
            System.out.println("2-Mi Usuario\n");
            System.out.println("0-Salir");

            option = Integer.parseInt(keyboard.nextLine());
            switch (option) {
                case 1:
                    System.out.println("Pet View.");
                    petView = new ar.com.playmedia.view.Pet(currentClient.getId());
                    petView.mainMenu();
                    break;
                case 2:
                    System.out.println("Edit User.");
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
    }

    public void createClient() {
        String input;
        Boolean terminated = true;
        currentClient = new ar.com.playmedia.model.Client();
        do {

            System.out.println("Ingrese los siguientes datos para crear su cuenta:");
            System.out.println("Numero de Documento (DNI):");
            input = keyboard.nextLine();
            currentClient.setDni(input);

            if (!(clientController.getClient(input).getDni() == null)) {
                System.out.println("Ya existe un usuario con ese DNI!");
            } else {
                System.out.println("Puedes continuar.");

                System.out.println("Nombre:");
                input = keyboard.nextLine();
                currentClient.setName(input);
                System.out.println("Apellido:");
                input = keyboard.nextLine();
                currentClient.setSurname(input);
                System.out.println("Contraseña:");
                input = keyboard.nextLine();
                currentClient.setPassword(input);
                System.out.println("Direccion:");
                input = keyboard.nextLine();
                currentClient.setAddress(input);
                System.out.println("Numero de Telefono:");
                input = keyboard.nextLine();
                currentClient.setPhoneNumber(input);
                System.out.println("Email:");
                input = keyboard.nextLine();
                currentClient.setEmail(input);
                System.out.println("Fecha de nacimiento(dd-MM-yyyy):");
                input = keyboard.nextLine();
                try {
                    currentClient.setBirth_date(format.parse(input));
                } catch (ParseException e) {

                    e.printStackTrace();
                }

                do {
                    clearScreen();
                    showClient(currentClient);
                    System.out.println("Seleccione una opcion:");
                    System.out.println("1-Crear un usuario con los datos ingresados.");
                    System.out.println("2-Volver a completar formulario.\n");
                    System.out.println("0-Salir");
                    input = keyboard.nextLine();
                    if (input.equals("1")) {
                        clientController.createClient(currentClient);
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

    public void deleteClient() {
        Boolean terminated = false;
        String dni;
        String pass;
        Integer input;

        System.out.println("Ingrese el DNI del ususario a eliminar:");
        dni = keyboard.nextLine();
        System.out.println("Ingrese la contraseña del ususario a eliminar:");
        pass = keyboard.nextLine();

        currentClient = clientController.loginClient(dni, pass);
        if (currentClient.getDni() != null) {
            do {
                showClient(currentClient);
                System.out.println("El siguiente usuario se eliminara, estas de acuerdo?");
                System.out.println("1-Si, eliminar ususario.\t0-No, cambie de opinion.");
                input = Integer.parseInt(keyboard.nextLine());

                switch (input) {
                    case 1:
                        clientController.destroyClient(currentClient.getDni());
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

    public void showClient(ar.com.playmedia.model.Client client) {
        System.out.println("===================");
        System.out.println("DNI: " + client.getDni());
        System.out.println("Nombre: " + client.getName());
        System.out.println("Apellido: " + client.getSurname());
        System.out.println("Contrasenna: " + client.getPassword());
        if (client.getId() != null) {
            System.out.println("ID: " + client.getId());
        }
        System.out.println("Fecha de Nacimiento: " + format.format(client.getBirth_date()));
        System.out.println("Direccion: " + client.getAddress());
        System.out.println("Numero de Telefono: " + client.getPhoneNumber());
        System.out.println("Email: " + client.getEmail());
        System.out.println("===================");
    }
}