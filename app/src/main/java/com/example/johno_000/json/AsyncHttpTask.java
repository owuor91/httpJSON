package com.example.johno_000.json;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;


/*
public class AsyncHttpTask extends AsyncTask<String,Void, Integer> {
    public String TAG;
    private String[] blogTitles;
    @Override
    protected Integer doInBackground(String... params) {
        InputStream inputStream = null;
        Integer result = 0;

        try {
            HttpClient httpClient = new DefaultHttpClient();
            HttpGet httpGet = new HttpGet(params[0]);

            httpGet.setHeader("Content-Type", "application/json");
            httpGet.setHeader("Accept", "application/json");

            HttpResponse httpResponse = httpClient.execute(httpGet);
            int statusCode = httpResponse.getStatusLine().getStatusCode();

            if (statusCode==200){
                inputStream= httpResponse.getEntity().getContent();
                String response = convertInputStreamToString(inputStream);
                parseResult(response);
                result = 1;
            }
            else {
                result = 0;
            }

        }
        catch(Exception e){
            Log.d(TAG, e.getLocalizedMessage());
        }

        return result;
    }

    @Override
    protected void onPostExecute(Integer result) {
        ListView listView = (ListView) findViewById(R.id.listView);
        if (result==1){
            ArrayAdapter arrayAdapter = new ArrayAdapter(MainActivity.this, android.R.layout.simple_list_item_1,blogTitles);
            listView.setAdapter(arrayAdapter);
        }
        else {
            Log.e(TAG, "Failed to fetch data");
        }
    }

    private String convertInputStreamToString(InputStream inputStream) throws IOException{
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        String line = "";
        String result = "";
        while ((line = bufferedReader.readLine()) != null){
            result+=line;
        }

        if (null!=inputStream){
            inputStream.close();
        }
        return result;
    }

    private void parseResult(String result){
        try {
            JSONObject response = new JSONObject(result);
            JSONArray posts = response.optJSONArray("posts");
            blogTitles = new String[posts.length()];

            for (int i =0; i<posts.length(); i++){
                JSONObject post = posts.optJSONObject(i);
                String title = post.optString("title");
                blogTitles[i]=title;
            }

        }
        catch (JSONException e){
            e.printStackTrace();
        }
    }
}
*/
