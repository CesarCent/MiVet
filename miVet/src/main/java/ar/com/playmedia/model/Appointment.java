package ar.com.playmedia.model;

import java.util.Date;

public class Appointment {
    private Integer id;
    private Integer clientId;
    private Integer petId;
    private Date appointmentDate;
    private Boolean taken;
    private String diagnosis;

    public Appointment() {
    }

    public Appointment(Integer id, Integer clientId, Integer petId, Date appointmentDate) {
        this.id = id;
        this.clientId = clientId;
        this.petId = petId;
        this.appointmentDate = appointmentDate;

        this.taken = false;
        this.diagnosis = "";
    }

    public Appointment(Integer id, Integer clientId, Integer petId, Date appointmentDate, Boolean taken, String diagnosis) {
        this.id = id;
        this.clientId = clientId;
        this.petId = petId;
        this.appointmentDate = appointmentDate;

        this.taken = taken;
        this.diagnosis = diagnosis;
    }

    /**
     * @return the appointmentDate
     */
    public Date getAppointmentDate() {
        return appointmentDate;
    }

    /**
     * @return the clientId
     */
    public Integer getClientId() {
        return clientId;
    }

    /**
     * @return the petId
     */
    public Integer getPetId() {
        return petId;
    }

    /**
     * @return the diagnosis
     */
    public String getDiagnosis() {
        return diagnosis;
    }

    /**
     * @return the id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @return the taken
     */
    public Boolean getTaken() {
        return taken;
    }

    /**
     * @param id the id to set
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @param clientId the clientId to set
     */
    public void setClientId(Integer clientId) {
        this.clientId = clientId;
    }

    /**
     * @param petId the petId to set
     */
    public void setPetId(Integer petId) {
        this.petId = petId;
    }

    /**
     * @param appointmentDate the appointmentDate to set
     */
    public void setAppointmentDate(Date appointmentDate) {
        this.appointmentDate = appointmentDate;
    }

    /**
     * @param taken the taken to set
     */
    public void setTaken(Boolean taken) {
        this.taken = taken;
    }

    /**
     * @param diagnosis the diagnosis to set
     */
    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

}