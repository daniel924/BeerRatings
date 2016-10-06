package com.example.ladenheim.beerratings;

import android.app.Fragment;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SearchActivity extends AppCompatActivity {

    private static String LOG_TAG = SearchActivity.class.getSimpleName();
    ListView listView = null;
    public PlaceAdapter adapter = null;
    // GpsFinder gpsFinder = null;

    /*
    class SearchTask extends AsyncTask<String, Void, List<Bar>> {
        ProgressDialog progress = null;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progress = new ProgressDialog(FindBarActivity.this);
            progress.setTitle("Searching");
            progress.setMessage("In Progress");
            progress.show();
        }

        @Override
        protected List<Beer> doInBackground(String... params) {
            try {
                List<Beer> response = null;
                if (gpsFinder.canGetLocation()) {
                    double lat = gpsFinder.getLatitude();
                    double lon = gpsFinder.getLongitude();
                    response = ApiUtils.FindBarByName(params[0], lat, lon);
                } else {
                    response = ApiUtils.FindBarByName(params[0], -1, -1);
                }
                final List<Bar> bars = response;
                if (response == null) {
                    final String notFound = "Could not find results for: " + params[0];
                    FindBarActivity.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            listView.setVisibility(View.GONE);
                            TextView notFoundText = (TextView) findViewById(R.id.not_found_txt);
                            notFoundText.setVisibility(View.VISIBLE);
                            notFoundText.setText(notFound);
                        }
                    });
                    progress.dismiss();
                    return null;
                }
                Log.d(LOG_TAG, "Response: " + response.toString());
                FindBarActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        findViewById(R.id.not_found_txt).setVisibility(View.GONE);
                        adapter.setList(bars);
                        listView.setVisibility(View.VISIBLE);
                    }
                });
                return response;
            } catch (IOException ex) {
                Log.d(LOG_TAG, ex.getMessage());
                ex.printStackTrace();
                return null;
            }
        }

        @Override
        protected void onPostExecute(List<Bar> result) {
            if(progress.isShowing()) {
                progress.dismiss();
            }
        }
    }
    */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        final List<Beer> beers = Arrays.asList(new Beer("bud light limarita", 5.1, 4.9), new Beer("molsens", 1.0, 1.3));
        adapter = new PlaceAdapter(this, beers);
        listView = (ListView) findViewById(R.id.list_view);
        listView.setAdapter(adapter);

        /*
        Button searchButton = (Button) findViewById(R.id.search_button);
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new SearchTask().execute(searchView.getQuery().toString());
            }
        });
        */

    }

    public static class BeerFragment extends Fragment {
        @Override
        public View onCreateView(
                LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            return inflater.inflate(R.layout.fragment_beer, container, false);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
    }

    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
    }


}