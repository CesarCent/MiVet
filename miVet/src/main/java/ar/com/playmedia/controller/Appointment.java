package ar.com.playmedia.controller;

import java.util.ArrayList;
import java.util.Date;

public class Appointment extends ConnectionControler {
    private String queryString;

    public Appointment() {
    }

    public void createAppointment(ar.com.playmedia.model.Appointment appointment) {
        this.queryString = String.format("SELECT appointment(%s, %s, '%s')", appointment.getClientId(), appointment.getPetId(),
                appointment.getAppointmentDate());

        connect();
        execute(queryString);
        disconnect();

    }

    public void deleteAppointment(Integer appointmentId) {
        this.queryString = String.format("SELECT appointment_destroy( %s ) ", appointmentId);

        connect();
        execute(queryString);
        disconnect();
    }

    public ArrayList<ar.com.playmedia.model.Appointment> listAppointmentsByClient(Integer clientId) {
        ar.com.playmedia.model.Appointment newAppointment;
        ArrayList<ar.com.playmedia.model.Appointment> appointmentsList = new ArrayList<ar.com.playmedia.model.Appointment>();

        this.queryString = String.format("SELECT * FROM appointment_seach_by_client(%s)", clientId);

        connect();
        executeQuery(queryString);
        try {
            while (this.result.next()) {
                newAppointment = new ar.com.playmedia.model.Appointment(this.result.getInt(1), this.result.getInt(2),
                        this.result.getInt(3), this.result.getDate(4));

                appointmentsList.add(newAppointment);
            }
        } catch (Exception e) {
            System.out.println("AppointmentController.listAppointmentsByClient -> ERROR: " + e);
        }

        return appointmentsList;
    }

    public ArrayList<ar.com.playmedia.model.Appointment> listAppointmentsByDate(Date appointmentDate) {
        ar.com.playmedia.model.Appointment newAppointment;
        ArrayList<ar.com.playmedia.model.Appointment> appointmentsList = new ArrayList<ar.com.playmedia.model.Appointment>();

        this.queryString = String.format("SELECT * FROM appointment_seach_by_date('%s')", appointmentDate);

        connect();
        executeQuery(queryString);
        try {
            while (this.result.next()) {
                newAppointment = new ar.com.playmedia.model.Appointment(this.result.getInt(1), this.result.getInt(2),
                        this.result.getInt(3), this.result.getDate(4), this.result.getBoolean(5),
                        this.result.getString(6));

                appointmentsList.add(newAppointment);
            }
        } catch (Exception e) {
            System.out.println("AppointmentController.listAppointmentsByDate -> ERROR: " + e);
        }

        return appointmentsList;
    }

    public ArrayList<ar.com.playmedia.model.Appointment> listAppointmentsByPet(Integer petId) {
        ar.com.playmedia.model.Appointment newAppointment;
        ArrayList<ar.com.playmedia.model.Appointment> appointmentsList = new ArrayList<ar.com.playmedia.model.Appointment>();

        this.queryString = String.format("SELECT * FROM appointment_seach_by_pet(%s)", petId);

        connect();
        executeQuery(queryString);
        try {
            while (this.result.next()) {
                newAppointment = new ar.com.playmedia.model.Appointment(this.result.getInt(1), this.result.getInt(2),
                        this.result.getInt(3), this.result.getDate(4), this.result.getBoolean(5),
                        this.result.getString(6));

                appointmentsList.add(newAppointment);
            }
        } catch (Exception e) {
            System.out.println("AppointmentController.listAppointmentsByDate -> ERROR: " + e);
        }

        return appointmentsList;
    }

    public Boolean isAppointmentAvailable(ar.com.playmedia.model.Appointment appointment) {
        Integer appointmentsForDate = 0;

        this.queryString = String.format("SELECT * FROM appointments_for_date('%s')", format.format(appointment.getAppointmentDate()));

        connect();
        executeQuery(queryString);
        try {
            while (this.result.next()) {
                appointmentsForDate = this.result.getInt(1);
            }
        } catch (Exception e) {
            System.out.println("AppointmentController.isAppointmentAvailable -> ERROR " + e);
        }
        disconnect();

        if (appointmentsForDate < 20) {
            return true;
        } else {
            return false;
        }
    }

    public void updateAppointment(ar.com.playmedia.model.Appointment appointment) {
        this.queryString = String.format("SELECT * FROM updateAppointment( %s, '%s' )", appointment.getId(), appointment.getDiagnosis());

        connect();
        executeQuery(queryString);
        disconnect();
    }

}