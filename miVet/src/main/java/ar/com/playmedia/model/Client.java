package ar.com.playmedia.model;

import java.util.Date;

public class Client extends VetUser {
    private Integer id;
    private String phoneNumber;
    private String address;
    private Date birth_date;
    private String email;

    public Client() {
    }

    public Client(String dni, String name, String surname, String password, Integer id, String phoneNumber,
            String address, Date birth_date, String email) {
        super(dni, name, surname, password);

        this.id = id;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.birth_date = birth_date;
        this.email = email;

    }

    public Integer getId() {
        return id;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public Date getBirth_date() {
        return birth_date;
    }

    public String getEmail() {
        return email;
    }

    /**
     * @param id the id to set
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @param address the address to set
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * @param birth_date the birth_date to set
     */
    public void setBirth_date(Date birth_date) {
        this.birth_date = birth_date;
    }

    /**
     * @param phoneNumber the phoneNumber to set
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }
}