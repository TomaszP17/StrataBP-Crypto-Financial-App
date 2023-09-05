package code.classes;

import code.classes.enums.Cryptocurrency;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class CryptoPrices {
    private static final String APIkey="4379B04E-45C7-4A00-BB38-9E4D64A9E371";
    private static double bitcoinRate;
    private static double etherumRate;
    private static double cardanoRate;
    private static double tetherRate;

    public CryptoPrices() {

    }



    public static ArrayList<Double> getCryptoRatesFromWeek(Cryptocurrency crypto){
        ArrayList<Double> cryptoRates = new ArrayList<>();
        double rate;

        OkHttpClient client = new OkHttpClient();


        LocalDateTime current_date = LocalDateTime.now().minusHours(24).withNano(0);
        LocalDateTime previous_week_date = LocalDateTime.now().minusDays(7).withNano(0);

        System.out.println(current_date);
        Request request = new Request.Builder()
                .url("https://rest.coinapi.io/v1/exchangerate/"+crypto+"/USDT/history/apikey-4379B04E-45C7-4A00-BB38-9E4D64A9E371?period_id=1DAY&time_start="+previous_week_date+"&time_end="+current_date)
                .get()
                .addHeader("X-CoinAPI-Key", APIkey)
                .build();
        System.out.println(request);

        try (Response response = client.newCall(request).execute()) {
            if (response.isSuccessful()) {
                // Parse the JSON response
                String jsonResponse = response.body().string();
                ObjectMapper objectMapper = new ObjectMapper();
                JsonNode jsonNode = objectMapper.readTree(jsonResponse);

                for(int i = 0; i<jsonNode.size(); i++){

                    cryptoRates.add((double) Math.round(jsonNode.get(i).get("rate_close").asDouble()*1000)/1000);
                }

            } else {
                System.out.println("Error: " + response.code() + " - " + response.message());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return cryptoRates;
    }


    /**
     *
     * @param crypto
     * @return rate of crypto
     * if API doesn't work it's return -1;
     */
    public static double getCryptoRate(Cryptocurrency crypto){
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url("https://rest.coinapi.io/v1/exchangerate/"+crypto+"/USDT/apikey-F57399AD-BD59-438E-BC05-5563053EA8D7/")
                .get()
                .addHeader("X-CoinAPI-Key", APIkey)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (response.isSuccessful()) {
                // Parse the JSON response
                String jsonResponse = response.body().string();
                ObjectMapper objectMapper = new ObjectMapper();
                JsonNode jsonNode = objectMapper.readTree(jsonResponse);

                // Now you can access the JSON data
                double rate = (double) Math.round(jsonNode.get("rate").asDouble()*100)/100;
                switch (crypto){
                    case BTC -> bitcoinRate=rate;
                    case ETH -> etherumRate=rate;
                    case ADA -> cardanoRate=rate;
                    case USDT -> tetherRate=rate;
                }

                String assetIdBase = jsonNode.get("asset_id_base").asText();
                String assetIdQuote = jsonNode.get("asset_id_quote").asText();

                System.out.println("Rate: " + rate);
                System.out.println("Base Asset: " + assetIdBase);
                System.out.println("Quote Asset: " + assetIdQuote);
            } else {
                System.out.println("Error: " + response.code() + " - " + response.message());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        switch (crypto){
            case BTC -> {
                return bitcoinRate;
            }
            case ETH -> {
                return etherumRate;
            }
            case ADA -> {
                return cardanoRate;
            }
            case USDT -> {
                return tetherRate;
            }
            default -> {
                return -1;
            }
        }
    }

    /**
     * setting all crypto rates.
     */
    public static void setAllCryptoRates(){
        Cryptocurrency[] cryptos = {Cryptocurrency.BTC, Cryptocurrency.ETH,Cryptocurrency.ETH,Cryptocurrency.ADA, Cryptocurrency.USDT};
        OkHttpClient client = new OkHttpClient();

        for(Cryptocurrency crypto : cryptos){
            Request request = new Request.Builder()
                    .url("https://rest.coinapi.io/v1/exchangerate/"+crypto+"/USD/apikey-F57399AD-BD59-438E-BC05-5563053EA8D7/")
                    .get()
                    .addHeader("X-CoinAPI-Key", APIkey)
                    .build();

            try (Response response = client.newCall(request).execute()) {
                if (response.isSuccessful()) {
                    // Parse the JSON response
                    String jsonResponse = response.body().string();
                    ObjectMapper objectMapper = new ObjectMapper();
                    JsonNode jsonNode = objectMapper.readTree(jsonResponse);

                    // Now you can access the JSON data
                    double rate = (double) Math.round(jsonNode.get("rate").asDouble()*100)/100;
                    switch (crypto){
                        case BTC -> bitcoinRate=rate;
                        case ETH -> etherumRate=rate;
                        case ADA -> cardanoRate=rate;
                        case USDT -> tetherRate=rate;
                    }


                    String assetIdBase = jsonNode.get("asset_id_base").asText();
                    String assetIdQuote = jsonNode.get("asset_id_quote").asText();

                    System.out.println("Rate: " + rate);
                    System.out.println("Base Asset: " + assetIdBase);
                    System.out.println("Quote Asset: " + assetIdQuote);
                } else {
                    System.out.println("Error: " + response.code() + " - " + response.message());
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }


        }
        System.out.println(bitcoinRate);
        System.out.println(etherumRate);
        System.out.println(cardanoRate);
        System.out.println(tetherRate);
    }

    /**
     * it's getting rate of crypto without requesting Api. You can save your daily limits using this method
     * @param crypto
     * @return
     */
    public static double getCryptoRateFromProgram(Cryptocurrency crypto){
        switch (crypto){
            case BTC -> {
                return bitcoinRate;
            }
            case ETH -> {
                return etherumRate;
            }
            case ADA -> {
                return cardanoRate;
            }
            case USDT -> {
                return tetherRate;
            }
            default -> {
                return 1;
            }
        }
    }
    /*
    return amount of value of user's bitcoin in USD
     */
    public static double getAllBtcUserInUSD(Client client){
        return client.getWallet().get("BTC") * CryptoPrices.getCryptoRateFromProgram(Cryptocurrency.BTC);
    }
    public static double getAllEthUserInUSD(Client client){

        return  client.getWallet().get("ETH") * CryptoPrices.getCryptoRateFromProgram(Cryptocurrency.ETH);
    }
    public static double getAllAdaUserInUSD(Client client){

        return client.getWallet().get("ADA") * CryptoPrices.getCryptoRateFromProgram(Cryptocurrency.ADA);
    }

    public static double getAllUsdtUserInUSD(Client client){

        return client.getWallet().get("Tether USD") * CryptoPrices.getCryptoRateFromProgram(Cryptocurrency.USDT);
    }
}
