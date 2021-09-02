package com.afpa.recordandroidapplication;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private String TAG = MainActivity.class.getSimpleName();

    private ProgressDialog pDialog;

    private ListView listView;
    private List<Disc> discs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView =(ListView) findViewById(R.id.listView);
        listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);

        discs = new ArrayList<>();

        new GetDiscs().execute();

    }

    /**
     * Async task class to get json by making HTTP call
     */
    private class GetDiscs extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Showing progress dialog
            pDialog = new ProgressDialog(MainActivity.this);
            pDialog.setMessage("Please wait...");
            pDialog.setCancelable(false);
            pDialog.show();

        }
        @Override
        protected Void doInBackground(Void... arg0) {
            RequestHandler sh = new RequestHandler();

            // Making a request to url and getting response
            String jsonStr = sh.makeServiceCall(Constants.URL_READ);
            Log.e(TAG, "Response from url: " + jsonStr);

            if (jsonStr != null) {
                try {
                    JSONObject jsonObj = new JSONObject(jsonStr);

                    JSONArray discs_arr = jsonObj.getJSONArray("records");

                    // looping through All Discs
                    for (int i = 0; i < discs_arr.length(); i++) {
                        JSONObject disc = discs_arr.getJSONObject(i);

                        String disc_id = disc.getString("disc_id");
                        String artist_name = disc.getString("artist_name");
                        String disc_title = disc.getString("disc_title");
                        String disc_label = disc.getString("disc_label");
                        String disc_year = disc.getString("disc_year");
                        String disc_genre = disc.getString("disc_genre");
                        String disc_picture = disc.getString("disc_picture");
                        Disc d = new Disc(Integer.parseInt(disc_id), artist_name, disc_title, disc_label,Integer.parseInt(disc_year) , disc_genre, disc_picture);

                        // adding discs to discs list
                        discs.add(d);
                    }
                } catch (final JSONException e) {
                    Log.e(TAG, "Json parsing error: " + e.getMessage());
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getApplicationContext(),
                                    "Json parsing error: " + e.getMessage(),
                                    Toast.LENGTH_LONG)
                                    .show();
                        }
                    });

                }
            } else {
                Log.e(TAG, "Couldn't get json from server.");
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(),
                                "Couldn't get json from server. Check LogCat for possible errors!",
                                Toast.LENGTH_LONG)
                                .show();
                    }
                });

            }

            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            // Dismiss the progress dialog
            if (pDialog.isShowing())
                pDialog.dismiss();

            /**
             * Updating parsed JSON data into ListView
             * */

            listView.setAdapter(new DiscListAdapter(discs,  MainActivity.this));
        }
    }
}