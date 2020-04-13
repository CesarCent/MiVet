package ar.com.playmedia.model;

public abstract class VetUser {
    private String dni;
    private String name;
    private String surname;
    private String password;

    public VetUser() {
    }

    public VetUser(String dni, String name, String surname, String password) {
        this.dni = dni;
        this.name = name;
        this.surname = surname;
        this.password = password;

    }

    public String getDni() {
        return dni;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getPassword() {
        return password;
    }

    /**
     * @param dni the dni to set
     */
    public void setDni(String dni) {
        this.dni = dni;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @param surname the surname to set
     */
    public void setSurname(String surname) {
        this.surname = surname;
    }

}