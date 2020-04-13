package ar.com.playmedia.view;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;

public class Shift extends View {
    private Integer clientId;
    private ArrayList<ar.com.playmedia.model.Pet> clientPets;

    private ArrayList<ar.com.playmedia.model.Shift> clientShifts;

    private ar.com.playmedia.controller.Shift shiftController;

    public Shift(Integer clientId, ArrayList<ar.com.playmedia.model.Pet> clientPets) {
        this.clientId = clientId;
        this.clientPets = clientPets;

        shiftController = new ar.com.playmedia.controller.Shift();
        clientShifts = shiftController.listShiftsByClient(clientId);
    }

    public void mainMenu() {
        Boolean terminated = false;
        Integer option;
        clearScreen();
        do {
            System.out.println(">> Mis Turnos <<");
            System.out.println("Selecciona la accion a realizar:\n ");
            System.out.println("1-Pedir un nuevo turno");
            System.out.println("2-Ver mis turnos");
            System.out.println("3-Cancelar un turno\n");
            System.out.println("0-Salir");

            option = Integer.parseInt(keyboard.nextLine());
            switch (option) {
                case 1:
                    clearScreen();
                    createShift();
                    break;
                case 2:
                    clearScreen();
                    listMyShifts();
                    clearScreen();
                    break;
                case 3:
                    clearScreen();
                    deleteShift();
                    break;
                case 0:
                    clearScreen();
                    terminated = !terminated;
                default:
                    clearScreen();
                    // System.out.println("--Opcion invalida. Intentelo nuevamente--");
                    break;
            }

        } while (!terminated);
    }

    public void createShift() {
        Integer input;
        Integer petId;
        Date shiftDate = new Date();
        Boolean terminated = false;
        ar.com.playmedia.model.Shift newShift = new ar.com.playmedia.model.Shift();

        newShift.setClientId(this.clientId);
        clearScreen();
        do {
            System.out.println("Ingrese el ID de la mascota por la que realizara la consulta:");
            System.out.println("Puede conocer el ID de su mascota en Mis Mascotas > Ver mascotas ingresadas. ");

            petId = Integer.parseInt(keyboard.nextLine());
            newShift.setPetId(petId);
            try {
                System.out.println("Ingrse la fecha en la que busca el turno(dd-MM-yyyy):");
                shiftDate = format.parse(keyboard.nextLine());
                newShift.setShiftDate(shiftDate);
                // System.out.println("!!!");
            } catch (ParseException e) {
                e.printStackTrace();
            }
            if (!(petExist(petId).getId() == petId)) {
                clearScreen();
                System.out.println("No existe una mascota con esa ID");
                terminated = !terminated;
            } else {
                if (shiftDate.compareTo(new Date()) < 0) {
                    clearScreen();
                    System.out.println("La fecha ingresada es invalida");
                    terminated = !terminated;
                } else {
                    do {
                        showShift(newShift);
                        System.out.println("Se creara el siguiente turno, estas de acuerdo?:");
                        System.out.println("1-Si, crear turno\t0-No, cambie de opinion");
                        input = Integer.parseInt(keyboard.nextLine());
                        switch (input) {
                            case 1:
                                shiftController.createShift(newShift);
                                clearScreen();
                                System.out.println("Se a creado el turno con exito.");
                                terminated = !terminated;
                                updateShifts();
                                break;
                            case 0:
                                clearScreen();
                                System.out.println("Se cancelo la creacion del turno.");
                                terminated = !terminated;
                                break;

                            default:
                                clearScreen();
                                System.out.println("Opcion invalida, intentelo nuevamente");
                                break;
                        }
                        if (input == 1 || input == 0) {
                            break;
                        }
                    } while (true);
                }

            }

        } while (!terminated);

    }

    public void deleteShift() {
        Boolean terminated = false;
        Integer shiftId;
        Integer input;

        System.out.println("Ingrese ID del turno a eliminar:");
        shiftId = Integer.parseInt(keyboard.nextLine());

        for (ar.com.playmedia.model.Shift shift : clientShifts) {
            if (shift.getId() == shiftId) {
                do {
                    showShift(shift);
                    System.out.println("EL siguiente turno sera eliminada, estas de acuerdo?");
                    System.out.println("1-Si, eliminar turno\t0-No, cambie de opinion");

                    input = Integer.parseInt(keyboard.nextLine());
                    switch (input) {
                        case 1:
                            clearScreen();
                            shiftController.deleteShift(shiftId);
                            System.out.println("Turno eliminado con exito.");
                            terminated = !terminated;
                            break;
                        case 0:
                            clearScreen();
                            System.out.println("Eliminacion de turno cancelada.");
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

        updateShifts();
    }

    public void updateShifts() {
        clientShifts = shiftController.listShiftsByClient(clientId);

    }

    public void listMyShifts() {
        System.out.println(">>Tus turnos son:");
        for (ar.com.playmedia.model.Shift shift : clientShifts) {
            if (!shift.getTaken()) {
                showShift(shift);
                System.out.println("- - - - - - - - - - - -");
            }
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

    public void showShift(ar.com.playmedia.model.Shift shift) {

        if (shift.getId() != null) {
            System.out.print("ID: " + shift.getId());
        }

        System.out.println(" Fecha: " + format.format(shift.getShiftDate()) + " Nombre de la Mascota: "
                + petExist(shift.getPetId()).getName());
    }

    public ar.com.playmedia.model.Pet petExist(Integer petId) {
        ar.com.playmedia.model.Pet getedPet = new ar.com.playmedia.model.Pet();
        for (ar.com.playmedia.model.Pet pet : clientPets) {
            if (pet.getId() == petId) {
                getedPet = pet;
                break;
            }
        }

        return getedPet;
    }

}