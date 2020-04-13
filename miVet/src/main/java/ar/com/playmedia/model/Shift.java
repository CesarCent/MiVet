package ar.com.playmedia.model;

import java.util.Date;

public class Shift {
    private Integer id;
    private Integer clientId;
    private Integer petId;
    private Date shiftDate;
    private Boolean taken;
    private String diagnosis;

    public Shift() {
    }

    public Shift(Integer id, Integer clientId, Integer petId, Date shiftDate) {
        this.id = id;
        this.clientId = clientId;
        this.petId = petId;
        this.shiftDate = shiftDate;

        this.taken = false;
        this.diagnosis = "";
    }

    public Shift(Integer id, Integer clientId, Integer petId, Date shiftDate, Boolean taken, String diagnosis) {
        this.id = id;
        this.clientId = clientId;
        this.petId = petId;
        this.shiftDate = shiftDate;

        this.taken = taken;
        this.diagnosis = diagnosis;
    }

    /**
     * @return the shiftDate
     */
    public Date getShiftDate() {
        return shiftDate;
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
     * @param shiftDate the shiftDate to set
     */
    public void setShiftDate(Date shiftDate) {
        this.shiftDate = shiftDate;
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