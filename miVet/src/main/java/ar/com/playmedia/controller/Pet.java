package ar.com.playmedia.controller;

import java.util.ArrayList;

public class Pet extends ConnectionControler {
    private String queryString;

    public Pet() {
    }

    public void createPet(ar.com.playmedia.model.Pet pet) {
        this.queryString = String.format("SELECT pet('%s', '%s', %s)", pet.getName(),
                format.format(pet.getBirth_date()), pet.getOwner());

        connect();
        execute(queryString);
        disconnect();

    }

    public void deletePet(Integer petId) {
        this.queryString = String.format("SELECT * FROM pet_destroy(%s)", petId);

        connect();
        execute(queryString);
        disconnect();
    }

    public ArrayList<ar.com.playmedia.model.Pet> listPetsByOwner(Integer ownerId) {
        ar.com.playmedia.model.Pet newPet;
        ArrayList<ar.com.playmedia.model.Pet> petsList = new ArrayList<ar.com.playmedia.model.Pet>();

        this.queryString = String.format("SELECT * FROM pet_search_by_owner(%s)", ownerId);

        connect();
        executeQuery(this.queryString);
        try {
            while (this.result.next()) {
                newPet = new ar.com.playmedia.model.Pet(this.result.getInt(1), this.result.getString(2),
                        this.result.getDate(3), this.result.getInt(4));
                petsList.add(newPet);
            }
        } catch (Exception e) {
            System.out.println("PetController.listPetsByOwner -> ERROR: " + e);
        }

        return petsList;
    }

}