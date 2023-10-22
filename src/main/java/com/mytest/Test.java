package com.mytest;


import java.io.*;

public class Test {
   static String str;
   public void C
}

import java.io.*;
        import java.math.*;
        import java.security.*;
        import java.text.*;
        import java.util.*;
        import java.util.concurrent.*;
        import java.util.function.*;
        import java.util.regex.*;
        import java.util.stream.*;
        import static java.util.stream.Collectors.joining;
        import static java.util.stream.Collectors.toList;
        import java.net.*;
        import org.json.simple.*;
        import org.json.simple.parser.*;



class Result {
    private static final String HACKR_COUNT_URL = "https://jsonmock.hackerrank.com/api/countries?name=%s";
    /*
     * Complete the 'getCapitalCity' function below.
     *
     * The function is expected to return a STRING.
     * The function accepts STRING country as parameter.
     * API URL: https://jsonmock.hackerrank.com/api/countries?name=<country>
     */

    public static String getCapitalCity(String country)  {
        if(Objects.isNull(country)) {
            return "";
        }
        try {
            final JSONParser parser = new JSONParser();
            JSONObject countryData = (JSONObject) parser.parse(getCountryResponse(country));
            final JSONArray datas  = (JSONArray) countryData.get("data");
            System.out.println(datas);
            for(Object data: datas) {
                final String capitalCity  = (String) ((JSONObject)data).get("capital");
                if(Objects.isNull(capitalCity)) {
                    continue;
                }
                return capitalCity;
            }

        }catch(Exception ex) {
            System.out.println(ex.getMessage());
        }
        return "";
    }

    public static String getCountryResponse(final String country) {
        StringBuilder response = new StringBuilder();
        try {
            URL url = new URL(String.format(HACKR_COUNT_URL,country));
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String line;
                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }
                reader.close();
            } else {
                System.out.println("HTTP Error: " + responseCode);
            }
            connection.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return response.toString();
    }

}

