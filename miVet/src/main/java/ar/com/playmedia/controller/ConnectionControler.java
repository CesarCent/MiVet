package ar.com.playmedia.controller;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;

import java.text.SimpleDateFormat;

public class ConnectionControler {
    private String url;
    private String username;
    private String password;
    protected Connection dbConnection;
    protected Statement query;
    protected ResultSet result;

    protected SimpleDateFormat format;

    public ConnectionControler() {
        url = "jdbc:postgresql://127.0.0.1:5432/miVet";
        username = "dba";
        password = "1234";

        format = new SimpleDateFormat("dd-MM-yyyy");
    }

    public void connect() {
        try {
            Class.forName("org.postgresql.Driver");
            dbConnection = DriverManager.getConnection(url, username, password);
        } catch (Exception e) {
            System.out.println("ERROR: " + e);
        }
    }

    public void execute(String queryString) {
        try {
            this.query = dbConnection.createStatement();
            this.query.execute(queryString);
            this.query.close();
        } catch (Exception e) {
            System.out.println("ERROR: " + e);
        }
    }

    public void executeQuery(String queryString) {
        try {
            query = dbConnection.createStatement();
            this.result = query.executeQuery(queryString);
        } catch (Exception e) {
            System.out.println("ConnectionController.executeQuery -> ERROR: " + e);
        }
    }

    public void disconnect() {
        try {
            this.dbConnection.close();
            this.result.close();
            this.query.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

}