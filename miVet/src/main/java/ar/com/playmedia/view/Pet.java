package ar.com.playmedia.view;

import java.text.ParseException;

import java.util.ArrayList;

public class Pet extends View {
    private Integer clientId;
    private ar.com.playmedia.controller.Pet petController;

    private ArrayList<ar.com.playmedia.model.Pet> pets;

    private ar.com.playmedia.view.Shift shiftView;

    public Pet(Integer clientId) {
        this.clientId = clientId;
        petController = new ar.com.playmedia.controller.Pet();
        pets = petController.listPetsByOwner(clientId);

        shiftView = new ar.com.playmedia.view.Shift(clientId, pets);
    }

    public void mainMenu() {
        Boolean terminated = false;
        Integer option;
        clearScreen();
        do {
            System.out.println(">> Mis Mascotas <<");
            System.out.println("Selecciona la accion a realizar:\n ");
            System.out.println("1-Ingresar una nueva mascota");
            System.out.println("2-Ver mis mascotas ingresadas");
            System.out.println("3-Eliminar una mascota");
            System.out.println("4-Mis turnos\n");

            System.out.println("0-Salir");

            option = Integer.parseInt(keyboard.nextLine());

            switch (option) {
                case 1:
                    clearScreen();
                    createPet();
                    break;
                case 2:
                    clearScreen();
                    listMyPets();
                    clearScreen();
                    break;
                case 3:
                    clearScreen();
                    deletePet();
                    break;
                case 4:
                    clearScreen();
                    shiftView.mainMenu();
                case 0:
                    clearScreen();
                    terminated = !terminated;

                default:
                    clearScreen();
                    System.out.println("--Opcion invalida. Intentelo nuevamente--");
                    break;
            }
        } while (terminated);
        // clearScreen();
    }

    public void createPet() {
        Boolean terminated = false;
        String input;
        ar.com.playmedia.model.Pet newPet = new ar.com.playmedia.model.Pet();
        clearScreen();
        do {
            newPet.setOwner(this.clientId);
            System.out.println("Ingrese el nombre de su mascota:");
            input = keyboard.nextLine();
            newPet.setName(input);
            System.out.println("Ingrese la fecha de nacimiento su mascota(dd-MM-yyyy):");
            input = keyboard.nextLine();
            try {
                newPet.setBirth_date(format.parse(input));
            } catch (ParseException e) {
                e.printStackTrace();
            }

            do {
                showPet(newPet);
                System.out.println("Seleccione una opcion:");
                System.out.println("1 - Crear una mascota con los datos ingresados");
                System.out.println("2 - Volver a completar el formulario");
                System.out.println("\n0 - Salir");

                input = keyboard.nextLine();
                switch (Integer.parseInt(input)) {
                    case 1:
                        petController.createPet(newPet);
                        clearScreen();
                        System.out.println("Se a añadido la mascota con exito.");
                        terminated = !terminated;
                        updatePets();
                        break;
                    case 2:
                        clearScreen();
                        System.out.println("Intentelo nuevamente.");
                    default:
                        clearScreen();
                        System.out.println("Ingrese una opcion valida.");
                        break;
                }
                if (input.equals("1") || input.equals("0")) {
                    break;
                }

            } while (true);

        } while (!terminated);

    }

    public void deletePet() {
        Boolean terminated = false;
        Integer petId;
        Integer input;

        System.out.println("Ingrese el ID de la mascota a eliminar:");
        petId = Integer.parseInt(keyboard.nextLine());

        for (ar.com.playmedia.model.Pet pet : pets) {
            if (pet.getId() == petId) {
                do {
                    showPet(pet);
                    System.out.println("La siguiente mascota sera eliminada, estas de acuerdo?");
                    System.out.println("1-Si, eliminar mascota\t0-No, cambie de opinion");

                    input = Integer.parseInt(keyboard.nextLine());
                    switch (input) {
                        case 1:
                            clearScreen();
                            petController.deletePet(petId);
                            System.out.println("Mascota eliminada con exito.");
                            terminated = !terminated;
                            break;
                        case 0:
                            clearScreen();
                            System.out.println("Eliminacion de mascota cancelada.");
                            terminated = !terminated;
                            break;
                        default:
                            clearScreen();
                            System.out.println("Opcion invalida, intentelo nuevamente.");
                            break;
                    }
                } while (!terminated);
                break;
            }
        }

        updatePets();

    }

    public void updatePets() {
        this.pets = petController.listPetsByOwner(clientId);
    }

    public void listMyPets() {

        System.out.println(">>Tus mascotas son:");
        for (ar.com.playmedia.model.Pet pet : pets) {
            System.out.println("ID: " + pet.getId() + " Nombre: " + pet.getName() + " Fecha de nacimiento: "
                    + format.format(pet.getBirth_date()));
            System.out.println("- - - - - - - - - - - -");
        }

        do {
            System.out.println("\nIngrese 0 para salir.");
            int option = Integer.parseInt(keyboard.nextLine());
            if (option == 0) {
                break;
            } else {
                clearScreen();
                System.out.println("--Opcion invalida. Intentelo nuevamente--");
            }
        } while (true);
    }

    public void showPet(ar.com.playmedia.model.Pet pet) {
        System.out.println("Nombre: " + pet.getName() + " Fecha de nacimiento: " + format.format(pet.getBirth_date()));
    }

}