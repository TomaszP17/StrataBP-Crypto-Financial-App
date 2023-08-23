public class CryptoPrices {
    private static double bitcoinRate=26406.00;
    private static double etherumRate=1673.83;
    private static double cardanoRate=0.23;

    public CryptoPrices(double bitcoinRate, double etherumRate, double cardanoRate) {
        this.bitcoinRate = bitcoinRate;
        this.etherumRate = etherumRate;
        this.cardanoRate = cardanoRate;
    }

    public double getBitcoinRate() {
        return bitcoinRate;
    }

    public static void setBitcoinRate(double bitcoinRate) {
        bitcoinRate = bitcoinRate;
    }

    public static double getEtherumRate() {
        return etherumRate;
    }

    public static void setEtherumRate(double etherumRate) {
       etherumRate = etherumRate;
    }

    public static double getCardanoRate() {
        return cardanoRate;
    }

    public static void setCardanoRate(double cardanoRate) {
       cardanoRate = cardanoRate;
    }
    /*
    return amount of value of user's bitcoin in USD
     */
    public static double getAllBtcUserInUSD(Client client){
             return Math.round(client.getWallet().get("BTC") * bitcoinRate);
    }
    public static double getAllEthUserInUSD(Client client){

        return Math.round(client.getWallet().get("ETH") * etherumRate);
    }
    public static double getAllAdaUserInUSD(Client client){

        return  Math.round(client.getWallet().get("ADA") * cardanoRate);
    }
}
