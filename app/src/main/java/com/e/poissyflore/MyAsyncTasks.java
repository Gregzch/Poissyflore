package com.e.poissyflore;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.LinearLayout;

import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
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

public class MyAsyncTasks extends AsyncTask<String, JSONArray, JSONArray> {


    @Override
    protected JSONArray doInBackground(String... strings) {
        String url = "http://192.168.43.222/Connect.php";
        HttpResponse response = null;
        try {
            HttpClient client = new DefaultHttpClient();
            HttpGet request = new HttpGet();
            request.setURI(new URI(url));
            HttpParams httpParameters = new BasicHttpParams();
            int timeoutConnection = 3000;
            HttpConnectionParams.setConnectionTimeout(httpParameters, timeoutConnection);
            int timeoutSocket = 5000;
            HttpConnectionParams.setSoTimeout(httpParameters, timeoutSocket);
            response = client.execute(request);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        String responseText = null;
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
        LinearLayout.LayoutParams lparams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        lparams.setMargins(150,0,50,10);
        JSONArray arr = null;
        Log.d("Avant","Récupération de Json");
        try {
            Log.d("JSON","Récupération de Json");
            arr = new JSONObject(responseText).getJSONArray("Parts");

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return arr;
    }

    protected void onPreExecute() {
        super.onPreExecute();
        /*ProgressDialog progressDialog = new ProgressDialog(DashboardFragment.class);
        progressDialog.setMessage("Please Wait");
        progressDialog.setCancelable(false);
        progressDialog.show();*/
    }



}
