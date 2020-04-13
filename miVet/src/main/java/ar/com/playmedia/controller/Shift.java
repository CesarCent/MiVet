package ar.com.playmedia.controller;

import java.util.ArrayList;
import java.util.Date;

public class Shift extends ConnectionControler {
    private String queryString;

    public Shift() {
    }

    public void createShift(ar.com.playmedia.model.Shift shift) {
        this.queryString = String.format("SELECT shift(%s, %s, '%s')", shift.getClientId(), shift.getPetId(),
                shift.getShiftDate());

        connect();
        execute(queryString);
        disconnect();

    }

    public void deleteShift(Integer shiftId) {
        this.queryString = String.format("SELECT shift_destroy( %s ) ", shiftId);

        connect();
        execute(queryString);
        disconnect();
    }

    public ArrayList<ar.com.playmedia.model.Shift> listShiftsByClient(Integer clientId) {
        ar.com.playmedia.model.Shift newShift;
        ArrayList<ar.com.playmedia.model.Shift> shiftsList = new ArrayList<ar.com.playmedia.model.Shift>();

        this.queryString = String.format("SELECT * FROM shift_seach_by_client(%s)", clientId);

        connect();
        executeQuery(queryString);
        try {
            while (this.result.next()) {
                newShift = new ar.com.playmedia.model.Shift(this.result.getInt(1), this.result.getInt(2),
                        this.result.getInt(3), this.result.getDate(4));

                shiftsList.add(newShift);
            }
        } catch (Exception e) {
            System.out.println("ShiftController.listShiftsByClient -> ERROR: " + e);
        }

        return shiftsList;
    }

    public ArrayList<ar.com.playmedia.model.Shift> listShiftsByDate(Date shiftDate) {
        ar.com.playmedia.model.Shift newShift;
        ArrayList<ar.com.playmedia.model.Shift> shiftsList = new ArrayList<ar.com.playmedia.model.Shift>();

        this.queryString = String.format("SELECT * FROM shift_seach_by_date('%s')", shiftDate);

        connect();
        executeQuery(queryString);
        try {
            while (this.result.next()) {
                newShift = new ar.com.playmedia.model.Shift(this.result.getInt(1), this.result.getInt(2),
                        this.result.getInt(3), this.result.getDate(4), this.result.getBoolean(5),
                        this.result.getString(6));

                shiftsList.add(newShift);
            }
        } catch (Exception e) {
            System.out.println("ShiftController.listShiftsByDate -> ERROR: " + e);
        }

        return shiftsList;
    }

    public ArrayList<ar.com.playmedia.model.Shift> listShiftsByPet(Integer petId) {
        ar.com.playmedia.model.Shift newShift;
        ArrayList<ar.com.playmedia.model.Shift> shiftsList = new ArrayList<ar.com.playmedia.model.Shift>();

        this.queryString = String.format("SELECT * FROM shift_seach_by_pet(%s)", petId);

        connect();
        executeQuery(queryString);
        try {
            while (this.result.next()) {
                newShift = new ar.com.playmedia.model.Shift(this.result.getInt(1), this.result.getInt(2),
                        this.result.getInt(3), this.result.getDate(4), this.result.getBoolean(5),
                        this.result.getString(6));

                shiftsList.add(newShift);
            }
        } catch (Exception e) {
            System.out.println("ShiftController.listShiftsByDate -> ERROR: " + e);
        }

        return shiftsList;
    }

    public Boolean isShiftAvailable(ar.com.playmedia.model.Shift shift) {
        Integer shiftsForDate = 0;

        this.queryString = String.format("SELECT * FROM shifts_for_date('%s')", format.format(shift.getShiftDate()));

        connect();
        executeQuery(queryString);
        try {
            while (this.result.next()) {
                shiftsForDate = this.result.getInt(1);
            }
        } catch (Exception e) {
            System.out.println("ShiftController.isShiftAvailable -> ERROR " + e);
        }
        disconnect();

        if (shiftsForDate < 20) {
            return true;
        } else {
            return false;
        }
    }

    public void updateShift(ar.com.playmedia.model.Shift shift) {
        this.queryString = String.format("SELECT * FROM updateShift( %s, '%s' )", shift.getId(), shift.getDiagnosis());

        connect();
        executeQuery(queryString);
        disconnect();
    }

}