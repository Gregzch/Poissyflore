package com.e.poissyflore;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static android.app.PendingIntent.getActivity;

public class MyAsyncTasks extends AsyncTask<ArrayList<String>,ArrayList<String>, String> {

    private JSONArray arr;
    @Override
    protected String doInBackground(ArrayList<String>... strings) {
        String url = "http://192.168.43.57/Connect.php";
        String url2 = "http://192.168.43.57/new_plant.php";
        String url3 = "http://192.168.43.57/recolte.php";
        HttpResponse response = null;
        try {
            if(strings[0].get(0).equals("Infos")) {
                HttpGet request = new HttpGet(url);
                HttpParams httpParameters = new BasicHttpParams();
                int timeoutConnection = 1000;
                HttpConnectionParams.setConnectionTimeout(httpParameters, timeoutConnection);
                int timeoutSocket = 1000;
                HttpConnectionParams.setSoTimeout(httpParameters, timeoutSocket);
                HttpClient client = new DefaultHttpClient(httpParameters);
                response = client.execute(request);
            }
            if (strings[0].get(0).equals("new")){
                HttpPost post = new HttpPost(url2);
                HttpParams httpParameters = new BasicHttpParams();
                int timeoutConnection = 1000;
                HttpConnectionParams.setConnectionTimeout(httpParameters, timeoutConnection);
                int timeoutSocket = 1000;
                HttpConnectionParams.setSoTimeout(httpParameters, timeoutSocket);
                HttpClient client = new DefaultHttpClient(httpParameters);
                List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(4);
                nameValuePairs.add(new BasicNameValuePair("Plante", strings[0].get(1)));
                nameValuePairs.add(new BasicNameValuePair("Date", strings[0].get(2)));
                nameValuePairs.add(new BasicNameValuePair("Quantite", strings[0].get(3)));
                nameValuePairs.add(new BasicNameValuePair("Date_Recolte",strings[0].get(4)));
                Log.d("Date", strings[0].get(2));
                Log.d("Planned",  strings[0].get(4));
                post.setEntity(new UrlEncodedFormEntity(nameValuePairs, "UTF-8"));
                client.execute(post);
            }
            if(strings[0].get(0).equals("recolte")){
                HttpPost post = new HttpPost(url3);
                HttpParams httpParameters = new BasicHttpParams();
                int timeoutConnection = 1000;
                HttpConnectionParams.setConnectionTimeout(httpParameters, timeoutConnection);
                int timeoutSocket = 1000;
                HttpConnectionParams.setSoTimeout(httpParameters, timeoutSocket);
                HttpClient client = new DefaultHttpClient(httpParameters);
                List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(4);
                nameValuePairs.add(new BasicNameValuePair("Plante", strings[0].get(1)));
                post.setEntity(new UrlEncodedFormEntity(nameValuePairs, "UTF-8"));
                client.execute(post);

            }
            Log.d("Essai", "Envoi");
        } catch (ConnectTimeoutException e) {
            Log.d("Co", "Timeout connection");
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String responseText = null;
        if(response!=null){
            try {
                responseText = EntityUtils.toString(response.getEntity());
            } catch (ParseException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                Log.i("Parse Exception", e + "");

            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                Log.i("IO Exception 2", e + "");

            }
            }
        return responseText;
    }

    protected void onPreExecute() {
        super.onPreExecute();
    }



}
