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

    /**
     * Adding Crypto to use wallet
     * @param keyCrypto e.x BTC, ADA, ETH
     * @param amount
     */
    public void addCrypto(String keyCrypto, double amount){
        double sum = amount + wallet.get(keyCrypto);
        wallet.put(keyCrypto, sum);
    }

    /**
     * Deleting Crypto from user
     * @param keyOfCrypto
     * @param ammount
     */
    public void deleteCrypto(String keyOfCrypto, double ammount){
        double sum = wallet.get(keyOfCrypto)-ammount;
        wallet.put(keyOfCrypto, sum);
    }
}
