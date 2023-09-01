package code.classes.enums;

public enum Cryptocurrency {
    BTC("Bitcoin"),
    ETH("Ethereum"),
    ADA("Cardano"),
    USDT("Tether USD");
    private final String name;
    Cryptocurrency(String name) {
        this.name = name;
    }
    /*@Override
    public String toString() {
        return name;
    }*/

    /**
     * Method returns Key of the enum constant
     */
    public String getKey(){
        return name();
    }
}
