package ar.com.playmedia.view;

public class MiVet extends View {
    private Integer selectedOption;

    private ar.com.playmedia.view.Client clientView;
    private ar.com.playmedia.view.Employee employeeView;

    public MiVet() {
        selectedOption = 0;
        clientView = new ar.com.playmedia.view.Client();
        employeeView = new ar.com.playmedia.view.Employee();

    }

    public void mainMenu() {

        clearScreen();

        do {
            System.out.println("            ___      __  _   \n" + "           (_) \\    / / | |  \n"
                    + "  _ __ ___  _ \\ \\  / /__| |_ \n" + " | '_ ` _ \\| | \\ \\/ / _ \\ __|\n"
                    + " | | | | | | |  \\  /  __/ |_ \n" + " |_| |_| |_|_|   \\/ \\___|\\__|\n");
            System.out.println("\nBienvenido a miVet! Quien eres?\n");

            System.out.println("1 - Soy un cliente.");
            System.out.println("2 - Soy un empleado.");
            System.out.println("\n0 - Salir.");

            selectedOption = keyboard.nextInt();
            switch (selectedOption) {
                case 1:
                    clientView.mainMenu();
                    break;
                case 2:
                    employeeView.mainMenu();
                    break;
                case 0:
                    System.out.println("Adios!");
                    isOptionValid = !isOptionValid;
                    break;
                default:
                    clearScreen();
                    System.out.println("---Opcion Invalida, intentelo de nuevo.---");
                    break;
            }

        } while (!isOptionValid);
    }

}