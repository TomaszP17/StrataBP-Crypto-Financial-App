import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ClientsController {
    private static List<Client> clients = new ArrayList<>();

    static {
        clients.add(new Client("Rafal", "Przybyl", LocalDate.now().minusYears(19),
                "rafal@gmail.com","111","haslo1" ));
        clients.add(new Client("thommy", "Shelby", LocalDate.now().minusYears(30),
                "thommy@gmail.com","222","haslo2" ));
        clients.add(new Client("John", "Przybyl", LocalDate.now().minusYears(50),
                "John@gmail.com","333","haslo3" ));
    }
    public List<Client> findAll(){
        return clients;
    }






}
