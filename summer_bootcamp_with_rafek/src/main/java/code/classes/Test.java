package code.classes;

import code.classes.enums.Cryptocurrency;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.time.LocalDateTime;

public class Test {
    private static final String APIkey="4379B04E-45C7-4A00-BB38-9E4D64A9E371";

    public Test(){
        OkHttpClient client = new OkHttpClient();

        Cryptocurrency crypto = Cryptocurrency.BTC;
        LocalDateTime current_date = LocalDateTime.now().withNano(0);
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
                System.out.println(jsonNode);

                // Now you can access the JSON data
              //  double rate = (double) Math.round(jsonNode.get("rate").asDouble()*100)/100;
//                switch (crypto){
//                    case BTC -> bitcoinRate=rate;
//                    case ETH -> etherumRate=rate;
//                    case ADA -> cardanoRate=rate;
//                    case USDT -> tetherRate=rate;
//                }

              //  String assetIdBase = jsonNode.get("asset_id_base").asText();
            //    String assetIdQuote = jsonNode.get("asset_id_quote").asText();
//
//                System.out.println("Rate: " + rate);
//                System.out.println("Base Asset: " + assetIdBase);
//                System.out.println("Quote Asset: " + assetIdQuote);
            } else {
             System.out.println("Error: " + response.code() + " - " + response.message());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
