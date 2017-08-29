package com.example.android.weather;

import android.graphics.Bitmap;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;

/**
 * Helper methods related to requesting and receiving earthquake data from USGS.
 */
public final class QueryUtils {

    public static ArrayList<Weather> curLocs = new ArrayList<Weather>();

    /**
     * Create a private constructor because no one should ever create a {@link QueryUtils} object.
     * This class is only meant to hold static variables and methods, which can be accessed
     * directly from the class name QueryUtils (and an object instance of QueryUtils is not needed).
     */


    /* Make the HTTP connection */
    public static String makeHttpRequest(URL url ) throws IOException {
        String jsonResponse = "";
        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;
        int responseCode = 0;
        Bitmap weatherImage =null;
        try {
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.setReadTimeout(10000 /* milliseconds */);
            urlConnection.setConnectTimeout(15000 /* milliseconds */);
            urlConnection.connect();
            responseCode = urlConnection.getResponseCode();
            if (responseCode == 200  ) {
                inputStream = urlConnection.getInputStream();
                jsonResponse = readFromStream(inputStream);
            }
            else{
                jsonResponse = null;
            }

        } catch (IOException e) {
            Log.e("Connection exception", String.valueOf(responseCode));
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (inputStream != null) {
                // function must handle java.io.IOException here
                inputStream.close();
            }
        }
        return jsonResponse;
    }

    /* convert stream data to string data */

    private static String readFromStream(InputStream inputStream) throws IOException {
        StringBuilder output = new StringBuilder();
        if (inputStream != null) {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String line = reader.readLine();
            while (line != null) {
                output.append(line);
                line = reader.readLine();
            }
        }
        return output.toString();
    }

    /* Build list of Location objects from the JSON Response received from USGS site */
    public static ArrayList<Weather> extractJsonResponse(String JsonResponse) {
        // Try to parse the SAMPLE_JSON_RESPONSE. If there's a problem with the way the JSON
        // is formatted, a JSONException exception object will be thrown.
        // Catch the exception so the app doesn't crash, and print the error message to the logs.
        try {
            JSONObject eJson = new JSONObject(JsonResponse);



            JSONObject CurObsJson = eJson.optJSONObject("current_observation");

            JSONObject DislocJson = CurObsJson.optJSONObject("display_location");
            String displayLocation = DislocJson.optString("full");
            String latitude =DislocJson.optString("latitude");
            String longitude = DislocJson.optString("longitude");
            String elevation = DislocJson.optString("elevation");

            String temp_f = CurObsJson.optString("temp_f");
            String temp_c = CurObsJson.optString("temp_c");
            String img_url = CurObsJson.optString("icon_url");
            String feelsLike_f = CurObsJson.optString("feelslike_f");
            String conditions = CurObsJson.optString("icon");
            String relativeHumidity = CurObsJson.optString("relative_humidity");
            String pressure =CurObsJson.optString("pressure_mb");
            String precip_today_in= CurObsJson.optString("precip_today_in");
            String forecast_URL= CurObsJson.optString("forecast_url");


            // TODO: Parse the response given by the SAMPLE_JSON_RESPONSE string and
            // build up a list of Earthquake objects with the corresponding data.
            curLocs.add(new Weather(Float.parseFloat(temp_f),Float.parseFloat(temp_c),img_url ,Float.parseFloat(feelsLike_f),conditions,displayLocation,relativeHumidity, Integer.parseInt(pressure),Float.parseFloat(precip_today_in),forecast_URL,Float.parseFloat(latitude),Float.parseFloat(longitude),elevation));
            Log.v("earth", temp_f + temp_c);
        } catch (JSONException e1) {
            Log.e("QueryUtils", "Problem parsing the earthquake JSON results", e1);
            ;
        }

        Log.v("list", String.valueOf(curLocs.size()));
        // Return the list of earthquakes
        return curLocs;
    }
}




