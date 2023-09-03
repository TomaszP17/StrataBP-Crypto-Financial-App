package code.classes;

import code.classes.enums.Cryptocurrency;

public class Test {
    public Test(){
        CryptoPrices.setAllCryptoRates();
        System.out.println(CryptoPrices.getCryptoRateFromProgram(Cryptocurrency.BTC));
        System.out.println(CryptoPrices.getCryptoRateFromProgram(Cryptocurrency.ETH));
        System.out.println(CryptoPrices.getCryptoRateFromProgram(Cryptocurrency.ADA));
        System.out.println(CryptoPrices.getCryptoRateFromProgram(Cryptocurrency.USDT));
    }
}
