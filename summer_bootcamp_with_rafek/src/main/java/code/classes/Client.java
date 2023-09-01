package code.classes;

import code.classes.enums.Limit;

import java.util.HashMap;
import java.util.Map;

public class Client extends User {


    private Map<String, Double> wallet = new HashMap<>();
    private Limit limit;

    public void setLimit(Limit limit) {
        this.limit = limit;
    }

    public Limit getLimit() {
        return limit;
    }

    public Map<String, Double> getWallet() {
        return wallet;
    }

    public void setWallet(Map<String, Double> wallet) {
        this.wallet = wallet;
    }

    @Override
    public String toString() {
        return "code.classes.Client{" +
                "wallet=" + wallet +
                '}';
    }

    public Client(String name, String lastname, String dateOfBirth, String email, String pesel, String password) {
        super(name, lastname, dateOfBirth, email, pesel, password);
        wallet.put("BTC", 1.0);
        wallet.put("ETH", 7.0);
        wallet.put("ADA", 10.0);
        wallet.put("USDT", 2.0);
        this.limit = Limit.LIMIT_1000;

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
    public String getWalletValue(){
        double result = (double) Math.round( ((wallet.get("BTC")*CryptoPrices.getBitcoinRate() ) +
                (wallet.get("ETH")*CryptoPrices.getEtherumRate() ) +
                (wallet.get("ADA")*CryptoPrices.getCardanoRate() ) +
                (wallet.get("USDT")*CryptoPrices.getTetherRate())) *
                 100) / 100;
        return String.valueOf(result);
    }
}
