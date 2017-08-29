package com.example.android.weather;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

/**
 * Helper methods related to requesting and receiving earthquake data from USGS.
 */
public final class ImageUtils {

    public static ArrayList<Weather> curLocs = new ArrayList<Weather>();

    /**
     * Create a private constructor because no one should ever create a {@link ImageUtils} object.
     * This class is only meant to hold static variables and methods, which can be accessed
     * directly from the class name QueryUtils (and an object instance of QueryUtils is not needed).
     */


    /* Make the HTTP connection */
    public static Bitmap makeHttpRequest(URL url ) throws IOException {
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
                weatherImage = BitmapFactory.decodeStream(inputStream);
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
        return weatherImage;
    }




}




