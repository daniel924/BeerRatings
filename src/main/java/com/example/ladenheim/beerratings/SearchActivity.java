package com.example.ladenheim.beerratings;

import android.app.Fragment;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.SystemClock;
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
import android.widget.SearchView;
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


    class SearchTask extends AsyncTask<String, Void, List<Beer>> {
        ProgressDialog progress = null;


        @Override
        protected List<Beer> doInBackground(String... params) {
            List<Beer> response = new ArrayList<Beer>();
            try {
                response = ApiUtils.getBeers(params[0]);
            } catch (Exception ex) {
                Log.d(LOG_TAG, ex.getMessage());
                ex.printStackTrace();
                return null;
            }
            return response;
        }


    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        final List<Beer> beers = Arrays.asList(new Beer("bud light limarita", 5.1, 4.9), new Beer("molsens", 1.0, 1.3));
        adapter = new PlaceAdapter(this, beers);
        listView = (ListView) findViewById(R.id.list_view);
        listView.setAdapter(adapter);


        final SearchView searchView = (SearchView) findViewById(R.id.search_text);

        Button searchButton = (Button) findViewById(R.id.search_button);
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new SearchTask().execute(searchView.getQuery().toString());
            }
        });


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