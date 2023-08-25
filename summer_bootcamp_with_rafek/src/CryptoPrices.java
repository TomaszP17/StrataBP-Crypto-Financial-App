import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;


import java.io.IOException;

public class CryptoPrices {
    private static final String APIkey="F57399AD-BD59-438E-BC05-5563053EA8D7";
    private static double bitcoinRate;
    private static double etherumRate;
    private static double cardanoRate;

    public CryptoPrices() {

    }
    public static double getBitcoinRate() {
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url("https://rest.coinapi.io/v1/exchangerate/BTC/USD/apikey-F57399AD-BD59-438E-BC05-5563053EA8D7/")
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
                double rate = jsonNode.get("rate").asDouble();
                bitcoinRate = rate;
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
        return bitcoinRate;
    }


    public static double getEtherumRate() {
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url("https://rest.coinapi.io/v1/exchangerate/ETH/USD/apikey-F57399AD-BD59-438E-BC05-5563053EA8D7/")
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
                double rate = jsonNode.get("rate").asDouble();
                etherumRate = rate;
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
        return etherumRate;
    }

    public static double getCardanoRate() {
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url("https://rest.coinapi.io/v1/exchangerate/ADA/USD/apikey-F57399AD-BD59-438E-BC05-5563053EA8D7/")
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
                double rate = jsonNode.get("rate").asDouble();
                cardanoRate = rate;
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
        return cardanoRate;
    }

    /*
    return amount of value of user's bitcoin in USD
     */
    public static double getAllBtcUserInUSD(Client client){
        return Math.round(client.getWallet().get("BTC") * getBitcoinRate());
    }
    public static double getAllEthUserInUSD(Client client){

        return Math.round(client.getWallet().get("ETH") * getEtherumRate());
    }
    public static double getAllAdaUserInUSD(Client client){

        return  Math.round(client.getWallet().get("ADA") * getCardanoRate());
    }
}
