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
                "John@gmail.com","333","haslo3" ));
    }
    public List<Client> findAll(){
        for(Client client: clients){
            System.out.println(client.toString());
        }
        return clients;
    }

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
    public Client findByEmail( String email){
        for(Client client : clients){
            if(client.getEmail().equals(email)){
                return client;
            }
        }
        return null;
    }






}
