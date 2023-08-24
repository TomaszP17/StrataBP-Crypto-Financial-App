import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ClientsController {
    private static List<Client> clients = new ArrayList<>();

    static {
        clients.add(new Client("Rafal", "Przybyl", "12-33-00",
                "rafal@gmail.com","111","haslo1" ));
        clients.add(new Client("thommy", "Shelby", "12-33-00",
                "thommy@gmail.com","222","haslo2" ));
        clients.add(new Client("John", "Przybyl", "12-33-00",
                "John@gmail.com","456","haslo3" ));
        clients.add(new Client("test", "Przybyl", "12-33-00",
                "test","333","test" ));
    }

    /**
     * Sending crypto to others users
     * @param email - email of user who is sending
     * @param email1 - email of user who is receiving
     * @param keyCrypto - e.x BTC, ADA, ETH
     * @param amount - amount of cryptos
     */
    public static void sendFromTo(String email, String email1, String keyCrypto, double amount) {
        Client client1 = ClientsController.findByEmail(email);
        Client client2 = ClientsController.findByEmail(email1);
        client1.deleteCrypto(keyCrypto, amount);
        client2.addCrypto(keyCrypto, amount);
    }
    //need method which donateMoneyToAccount without deleting method

    /**
     * Donating crypto to client account
     * @param email - email of user
     * @param keyCrypto - e.x BTC, ETH, ADA
     * @param amount - amount of cryptos
     */
    public static void donateMoney(String email, String keyCrypto, double amount){
        Client client = ClientsController.findByEmail(email);
        client.addCrypto(keyCrypto, amount);
    }
    /**
     * Finding all users
      * @return
     */
    public List<Client> findAll(){
        for(Client client: clients){
            System.out.println(client.toString());
        }
        return clients;
    }

    /**
     * Creating client
     * @param name
     * @param lastname
     * @param dateOfBirth
     * @param email
     * @param pesel
     * @param password
     * @return
     */
    public Client clientCreate(String name, String lastname, String dateOfBirth, String email, String pesel, String password){
        Client client = new Client(name, lastname, dateOfBirth, email, pesel, password);
        System.out.println(name);
        System.out.println(lastname);
        System.out.println(dateOfBirth);
        System.out.println(email);
        System.out.println(pesel);
        System.out.println(password);

        clients.add(client);
        return client;
    }

    /**
     * Finding user by email
     * @param email
     * @return
     */
    public static Client findByEmail(String email){
        for(Client client : clients){
            if(client.getEmail().equals(email)){
                return client;
            }
        }
        return null;
    }

    /**
     * Finding user by client
     * @param user
     * @return
     */
    public static Client findClientByUser(User user){

        return findByEmail(user.getEmail());
    }
}
