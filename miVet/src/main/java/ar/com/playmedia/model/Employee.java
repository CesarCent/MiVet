package ar.com.playmedia.model;

import java.util.Date;

public class Employee extends VetUser {
    private Integer file;
    private String job;
    private Integer salary;
    private Date birth_date;

    public Employee() {

    }

    public Employee(String dni, String name, String surname, String password, Date birth_date, Integer file, String job,
            Integer salary) {
        super(dni, name, surname, password);

        this.file = file;
        this.job = job;
        this.birth_date = birth_date;
        this.salary = salary;
    }

    /**
     * @return the file
     */
    public Integer getFile() {
        return file;
    }

    /**
     * @return the job
     */
    public String getJob() {
        return job;
    }

    /**
     * @return the birth_date
     */
    public Date getBirth_date() {
        return birth_date;
    }

    /**
     * @return the salary
     */
    public Integer getSalary() {
        return salary;
    }

    /**
     * @param file the file to set
     */
    public void setFile(Integer file) {
        this.file = file;
    }

    /**
     * @param job the job to set
     */
    public void setJob(String job) {
        this.job = job;
    }

    /**
     * @param salary the salary to set
     */
    public void setSalary(Integer salary) {
        this.salary = salary;
    }

    /**
     * @param birth_date the birth_date to set
     */
    public void setBirth_date(Date birth_date) {
        this.birth_date = birth_date;
    }

}