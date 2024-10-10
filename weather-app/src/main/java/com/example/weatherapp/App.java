package com.example.weatherapp;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.util.Scanner;

public class App {
    private static final String API_KEY = "170536281f69273a792b8ffb624d0596";
    private static final String BASE_URL = "https://api.openweathermap.org/data/2.5/weather";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String city = "Moscow";
        
        String url = BASE_URL + "?q=" + city + "&appid=" + API_KEY + "&units=metric";
	System.out.println("Запрос: " + url);

        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            HttpGet request = new HttpGet(url);
            try (CloseableHttpResponse response = httpClient.execute(request)) {
                String jsonResponse = EntityUtils.toString(response.getEntity());
                JsonObject weatherData = JsonParser.parseString(jsonResponse).getAsJsonObject();

                if (weatherData.has("main")) {
                    double temperature = weatherData.getAsJsonObject("main").get("temp").getAsDouble();
                    System.out.println("Температура в " + city + ": " + temperature + "°C");
                } else {
                    System.out.println("Город не найден.");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
