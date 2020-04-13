package ar.com.playmedia.controller;

public class Client extends ConnectionControler {
    private String queryString;

    public Client() {
    }

    public void createClient(ar.com.playmedia.model.Client client) {
        this.queryString = String.format("SELECT client('%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s')",
                client.getDni(), client.getName(), client.getSurname(), client.getPassword(),
                format.format(client.getBirth_date()), client.getAddress(), client.getPhoneNumber(), client.getEmail());

        connect();
        execute(queryString);
        disconnect();

    }

    public void destroyClient(String dni) {
        this.queryString = String.format("SELECT client_destroy('%s')", dni);

        connect();
        execute(queryString);
        disconnect();
    }

    public ar.com.playmedia.model.Client getClient(String dni) {
        ar.com.playmedia.model.Client getedClient = new ar.com.playmedia.model.Client();
        this.queryString = String.format("SELECT * FROM get_client_by_dni('%s');", dni);

        connect();

        executeQuery(queryString);
        try {

            while (this.result.next()) {
                getedClient.setDni(result.getString(1));
                getedClient.setName(result.getString(2));
                getedClient.setSurname(result.getString(3));
                getedClient.setPassword(result.getString(4));
                getedClient.setBirth_date(result.getDate(5));
                getedClient.setId(result.getInt(6));
                getedClient.setPhoneNumber(result.getString(7));
                getedClient.setAddress(result.getString(8));
                getedClient.setEmail(result.getString(9));
            }

        } catch (Exception e) {
            System.out.println("ClientController.getClient -> ERROR: " + e);
        }
        disconnect();

        return getedClient;
    }

    public ar.com.playmedia.model.Client loginClient(String dni, String pass) {
        ar.com.playmedia.model.Client getedClient = new ar.com.playmedia.model.Client();
        this.queryString = String.format("SELECT * FROM get_client_with_password('%s', '%s')", dni, pass);

        connect();
        executeQuery(this.queryString);
        try {

            while (this.result.next()) {
                getedClient = new ar.com.playmedia.model.Client(result.getString(1), result.getString(2),
                        result.getString(3), result.getString(4), result.getInt(6), result.getString(7),
                        result.getString(8), result.getDate(5), result.getString(9));
            }

        } catch (Exception e) {
            System.out.println("ClientController.loginClient -> ERROR: " + e);
        }
        disconnect();

        return getedClient;

    }

}