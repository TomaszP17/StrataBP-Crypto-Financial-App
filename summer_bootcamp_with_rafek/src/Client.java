import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class Client extends User {
    Map<String, Double> wallet = new HashMap<>();

    @Override
    public String toString() {
        return "Client{" +
                "wallet=" + wallet +
                '}';
    }

    public Client(String name, String lastname, String dateOfBirth, String email, String pesel, String password) {
        super(name, lastname, dateOfBirth, email, pesel, password);
        wallet.put("BTC", 10.0);
        wallet.put("ETH", 10.0);
        wallet.put("ADA", 10.0);

    }
    public void addCrypto(String keyOfCrypto, double ammount){
        double sum = ammount + wallet.get(keyOfCrypto);
        wallet.put(keyOfCrypto, sum);
    }
    public void deleteCrypto(String keyOfCrypto, double ammount){
        double sum = wallet.get(keyOfCrypto)-ammount;
        wallet.put(keyOfCrypto, sum);
    }


}
