import kong.unirest.GetRequest;
import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import kong.unirest.Unirest;
import kong.unirest.json.JSONArray;
import kong.unirest.json.JSONObject;

import java.util.ArrayList;

public class JsonVat {
    public static void main(String[] args) {
        GetRequest request = Unirest.get("http://jsonvat.com/");
        HttpResponse<JsonNode> jsonResponse = request.asJson();
        JsonNode json = jsonResponse.getBody();
        JSONObject jsonObject = json.getObject();

        JSONArray list = jsonObject.getJSONArray("rates");
        ArrayList<Country> countries = new ArrayList<>();

        for (Object elements : list) {
            JSONObject jsonElements = (JSONObject) elements;
            String name = jsonElements.getString("name");
            String code = jsonElements.getString("code");

            JSONObject periods = jsonElements.getJSONArray("periods").getJSONObject(0);
            JSONObject rates = periods.getJSONObject("rates");
            double standard = rates.getInt("standard");
            Country country = new Country(name, code, standard);
            countries.add(country);

        }

        ArrayList<Country> highestVAT = new ArrayList<>();
        ArrayList<Country> lowestVAT = new ArrayList<>();
        Country highestCountry = new Country();
        Country lowestCountry = new Country();

        for (int i = 0; i < 3; i++) {
            double highestRate = Double.MIN_VALUE;
            double lowestRate = Double.MAX_VALUE;
            for (Country oneCountry : countries) {
                if (oneCountry.getStandardRate() > highestRate) {
                    highestRate = oneCountry.getStandardRate();
                    highestCountry = oneCountry;
                }
                if (oneCountry.getStandardRate() < lowestRate) {
                    lowestRate = oneCountry.getStandardRate();
                    lowestCountry = oneCountry;
                }
            }
            highestVAT.add(new Country(highestCountry.getName(), highestCountry.getCode(), highestCountry.getStandardRate()));
            lowestVAT.add(new Country(lowestCountry.getName(), lowestCountry.getCode(), lowestCountry.getStandardRate()));
            countries.remove(highestCountry);
            countries.remove(lowestCountry);
        }

        System.out.println("Countries with highest standard interest rates are following: ");
        for (Country country : highestVAT) {
            System.out.println(country);
        }

        System.out.println("Countries with lowest standard interest rates are following: ");
        for (Country country : lowestVAT) {
            System.out.println(country);
        }
    }
}
