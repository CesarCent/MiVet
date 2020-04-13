package ar.com.playmedia.model;

import java.util.Date;

public class Pet {
    private Integer id;
    private String name;
    private Date birth_date;
    private Integer owner;

    public Pet() {
    }

    public Pet(Integer id, String name, Date birth_date, Integer owner) {
        this.id = id;
        this.name = name;
        this.birth_date = birth_date;
        this.owner = owner;
    }

    /**
     * @return the id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @return the birth_date
     */
    public Date getBirth_date() {
        return birth_date;
    }

    /**
     * @return the owner
     */
    public Integer getOwner() {
        return owner;
    }

    /**
     * @param id the id to set
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @param birth_date the birth_date to set
     */
    public void setBirth_date(Date birth_date) {
        this.birth_date = birth_date;
    }

    /**
     * @param owner the owner to set
     */
    public void setOwner(Integer owner) {
        this.owner = owner;
    }

}