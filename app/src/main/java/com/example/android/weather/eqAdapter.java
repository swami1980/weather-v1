package com.example.android.weather;

import android.annotation.TargetApi;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.drawable.GradientDrawable;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

/**
 * Created by 118168 on 6/14/2017.
 */

public class eqAdapter extends ArrayAdapter<Weather>   {
    public eqAdapter(Activity con, ArrayList<Weather> earthquakes) {
        // Here, we initialize the ArrayAdapter's internal storage for the context and the list.
        // the second argument is used when the ArrayAdapter is populating a single TextView.
        // Because this is a custom adapter for two TextViews and an ImageView, the adapter is not
        // going to use this second argument, so it can be any value. Here, we used 0.
        super(con, 0, earthquakes);

        this.con = con;
        this.earthquakes=earthquakes;

    }

    static MediaPlayer mPlayer;
    Activity con;
    String magcolors;
    ArrayList<Weather>   earthquakes;
    Bitmap weatherImage;
    Bitmap finalImage;

    /**
     * Provides a view for an AdapterView (ListView, GridView, etc.)
     *
     * @param position    The position in the list of data that should be displayed in the
     *                    list item view.
     * @param convertView The recycled view to populate.
     * @param parent      The parent ViewGroup that is used for inflation.
     * @return The View for the position in the AdapterView.
     */

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Override

    public View getView(int position, View convertView, ViewGroup parent) {
        Bitmap testData;
        // Check if the existing view is being reused, otherwise inflate the view
          View listItemView = convertView;
        if (listItemView == null) {
             listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false);
        }

            // Get the {@link AndroidFlavor} object located at this position in the list
            final Weather curLoc = getItem(position);

        downloadImage dw =  new downloadImage(String.valueOf((curLoc.getImg())));

        dw.execute();
            // Find the short description
            TextView tempfTextView = (TextView) listItemView.findViewById(R.id.mag1);
        tempfTextView.setText(String.valueOf(curLoc.getTemp_f()));

        ImageView WtrImgView = (ImageView) listItemView.findViewById(R.id.Img1);

        Log.v("test image 111",String.valueOf((curLoc.getImg())));


        try {
             finalImage= new downloadImage(String.valueOf((curLoc.getImg()))).execute().get();
        }
        catch (ExecutionException | InterruptedException ei) {
            ei.printStackTrace();
        }
        WtrImgView.setImageBitmap(finalImage);

            GradientDrawable gd = (GradientDrawable) tempfTextView.getBackground();

            if (curLoc.getTemp_f()> 100) {

                gd.setColor(ContextCompat.getColor(getContext(), R.color.magnitude9));
            }
                    else if(curLoc.getTemp_f()> 90 &&curLoc.getTemp_f()< 100) {

                gd.setColor(ContextCompat.getColor(getContext(), R.color.magnitude8));
            }
            else if(curLoc.getTemp_f()> 80 &&curLoc.getTemp_f()< 90) {

                gd.setColor(ContextCompat.getColor(getContext(), R.color.magnitude7));
            }
            else if(curLoc.getTemp_f()> 70 &&curLoc.getTemp_f()< 80) {
                gd.setColor(ContextCompat.getColor(getContext(), R.color.magnitude6));
            }
            else if(curLoc.getTemp_f()> 60 &&curLoc.getTemp_f()< 70) {
                gd.setColor(ContextCompat.getColor(getContext(), R.color.magnitude5));
            }
            else if(curLoc.getTemp_f()> 50 &&curLoc.getTemp_f()< 60) {
                gd.setColor(ContextCompat.getColor(getContext(), R.color.magnitude4));

            }
            else if(curLoc.getTemp_f()> 40 &&curLoc.getTemp_f()< 50) {
                gd.setColor(ContextCompat.getColor(getContext(), R.color.magnitude3));
            }
            else if(curLoc.getTemp_f()> 30 &&curLoc.getTemp_f()< 40) {
                gd.setColor(ContextCompat.getColor(getContext(), R.color.magnitude2));
            }
            else if(curLoc.getTemp_f()> 20 &&curLoc.getTemp_f()< 30) {
                gd.setColor(ContextCompat.getColor(getContext(), R.color.magnitude1));
            }

        TextView tempfeelsfTextView = (TextView) listItemView.findViewById(R.id.feelslike);
        tempfeelsfTextView.setTextColor(0b11111111111111110000000000000000);
        tempfeelsfTextView.setText("Feels like " +String.valueOf(curLoc.feelsLike_f));

        TextView conditionsTextView = (TextView) listItemView.findViewById(R.id.conditions);
        conditionsTextView.setTextColor(0b11111111111111110000000000000000);
        conditionsTextView.setText(String.valueOf(curLoc.conditions));

        TextView DisLocTextView = (TextView) listItemView.findViewById(R.id.head1);
        DisLocTextView.setTextColor(0b11111111111111110000000000000000);
        DisLocTextView.setText(String.valueOf(curLoc.Location));
        DisLocTextView.setTextSize((float) 22.0);

        TextView relativeHumidityTextView = (TextView) listItemView.findViewById(R.id.relativehumidityVal);
        relativeHumidityTextView.setTextColor(0b11111111111111110000000000000000);
        relativeHumidityTextView.setText(String.valueOf(curLoc.relativeHumidity));

        TextView elevationTextView = (TextView) listItemView.findViewById(R.id.elevationVal);
        elevationTextView.setTextColor(0b11111111111111110000000000000000);
        elevationTextView.setText(String.valueOf(curLoc.elevation)+ " FT");

        TextView latitudeTextView = (TextView) listItemView.findViewById(R.id.latitudeVal);
        latitudeTextView.setTextColor(0b11111111111111110000000000000000);
        latitudeTextView.setText(String.valueOf(curLoc.latitude));

        TextView longitudeTextView = (TextView) listItemView.findViewById(R.id.longitudeVal);
        longitudeTextView.setTextColor(0b11111111111111110000000000000000);
        longitudeTextView.setText(String.valueOf(curLoc.longitude));

        TextView pressureTextView = (TextView) listItemView.findViewById(R.id.pressureVal);
        pressureTextView.setTextColor(0b11111111111111110000000000000000);
        pressureTextView.setText(String.valueOf(curLoc.pressure));

          /*  // Find the short description
            TextView shortTextView = (TextView) listItemView.findViewById(R.id.text1);
            // Get the version number from the current AndroidFlavor object and
            // set this text on the number TextView

            TextView longTextView = (TextView) listItemView.findViewById(R.id.text2);
            Boolean split = curLoc.getLocation().contains("of");
            if (split == false) {
                shortTextView.setText("Near to");
                longTextView.setText(curLoc.getLocation());
            } else if (split == true) {
                String[] secondString = curLoc.getLocation().split("of");
                shortTextView.setText(secondString[0]);
                longTextView.setText(secondString[1]);
            }
            TextView dateTextView = (TextView) listItemView.findViewById(date1);
            java.util.Date date = new java.util.Date(curLoc.getDate());
            DateFormat eDate = DateFormat.getDateInstance();
            dateTextView.setText(String.valueOf(eDate.format(curLoc.getDate())));

            TextView timeTextView = (TextView) listItemView.findViewById(time1);
            DateFormat eTime = DateFormat.getTimeInstance(DateFormat.MEDIUM, Locale.ENGLISH);


            timeTextView.setText(String.valueOf(eTime.format(curLoc.getDate())));

            listItemView.setOnClickListener(new AdapterView.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(curLoc.getUrl()));

                    con.startActivity(browserIntent);

                }
            });*/


            return listItemView;
/* On click listener to call the URL intent */


    }










    public void releaseMediaPlayer() {
        // If the media player is not null, then it may be currently playing a sound.
        if (mPlayer != null) {
            // Regardless of the current state of the media player, release its resources
            // because we no longer need it.
            mPlayer.release();

            // Set the media player back to null. For our code, we've decided that
            // setting the media player to null is an easy way to tell that the media player
            // is not configured to play an audio file at the moment.
            mPlayer = null;
        }
    }
    private  class downloadImage extends AsyncTask<String, Void, Bitmap> {
        private static final String IMG_URL = "http://icons.wxug.com/i/c/k/cloudy.gif";
        String weatherURL;
        ImageView weatherResult;

        public downloadImage(String s) {
            this.weatherURL =s;
        }

        @Override
        protected Bitmap doInBackground(String... urls) {
            URL url = null;
            boolean imageLoader = true;

            try {
                url = new URL(weatherURL);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }

            // Perform HTTP request to the URL and receive a JSON response back

            try {
                weatherImage = ImageUtils.makeHttpRequest(url);
            } catch (IOException e) {
                // TODO Handle the IOException
            }

            return weatherImage;
        }

        @Override
        protected void onPostExecute(Bitmap weatherImage) {
             Log.v("test image", String.valueOf(weatherImage));
            finalImage=Bitmap.createScaledBitmap(weatherImage,  600 ,600, true);

        }
    }
}



