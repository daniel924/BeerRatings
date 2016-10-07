package com.example.ladenheim.beerratings;

import android.support.annotation.NonNull;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by ladenheim on 10/5/16.
 */
public class ApiUtils {

    private static final int CONNECTION_TIMEOUT_MS = 15000;
    private static final int READ_TIMEOUT_MS = 5000;
    private static String LOG_TAG = ApiUtils.class.getSimpleName();
    private static String baseUrl = "https://www.beermenus.com";

    public static List<String> FindPlace(String searchVal) throws IOException  {
        searchVal = "fools gold";
        String request = baseUrl + "/search?q=" + URLEncoder.encode(searchVal, "UTF-8");
        String response = "";
        try {
            response = getRequest(request);
        }
        catch(SocketTimeoutException ex) {
            Log.d(LOG_TAG, "Response timed out with connection_timeout=%s" + CONNECTION_TIMEOUT_MS +
                    " and read_timeout=" + READ_TIMEOUT_MS);
        }

        Pattern p = Pattern.compile("<a data-screen-title=(.*?)>");
        Matcher m = p.matcher(response);
        String place_html = "";
        while(m.find()) {
            place_html = m.group();
            break;
        }
        String place_url = "";
        if(!place_html.equals("")) {
            int start = place_html.indexOf("href=\"") + 7;
            int end = place_html.indexOf("\">");
            place_url = place_html.substring(start, end);
        }

        String placeResponse = getRequest(baseUrl + "/" + place_url);

        p = Pattern.compile("<h3 class=\"mb-0\">(.*?)</h3>");
        m = p.matcher(placeResponse);
        List<String> beers = new ArrayList<String>();

        while(m.find()) {
            beers.add(m.group(1).trim());
        }
        return beers;
    }

    private static String getRequest(String url) throws IOException {
        URLConnection conn = new URL(url).openConnection();
        conn.setConnectTimeout(CONNECTION_TIMEOUT_MS);
        conn.setReadTimeout(READ_TIMEOUT_MS);
        InputStream inputStream = conn.getInputStream();

        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
        StringBuilder result = new StringBuilder();
        String line;
        while((line = reader.readLine()) != null ) {
            result.append(line);
        }
        return result.toString();

    }


}
