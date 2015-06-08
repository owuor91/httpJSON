package com.example.johno_000.json;

import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;


public class Postdata extends ActionBarActivity {
    protected EditText etName, etCountry, etTwitter;
    protected Button btnPost;
    private String name, country, twitter;
    Person person;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_postdata);

        etName = (EditText) findViewById(R.id.name);
        etCountry = (EditText) findViewById(R.id.country);
        etTwitter = (EditText) findViewById(R.id.twitter);

        btnPost = (Button) findViewById(R.id.btnPost);

        btnPost.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        name = etName.getText().toString();
                        country = etCountry.getText().toString();
                        twitter = etTwitter.getText().toString();
                        new HttpAsyncTask().execute("http://hmkcode.appspot.com/jsonservlet");
                    }
                }
        );
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_postdata, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public static String POST(String url, Person person){
        InputStream inputStream = null;
        String result = "";

        try {
            HttpClient httpClient = new DefaultHttpClient();
            HttpPost httpPost = new HttpPost(url);

            String json= "";

            JSONObject jsonObject = new JSONObject();
            jsonObject.accumulate("name", person.getName());
            jsonObject.accumulate("country", person.getCountry());
            jsonObject.accumulate("twitter", person.getTwitter());

            json = jsonObject.toString();

            StringEntity se = new StringEntity(json);
            httpPost.setEntity(se);
            httpPost.setHeader("Accept", "application/json");
            httpPost.setHeader("Content-type", "application/json");

            HttpResponse httpResponse = httpClient.execute(httpPost);

            inputStream = httpResponse.getEntity().getContent();

            if (inputStream != null){
                result = convertInputStreamToString(inputStream);
            }
            else {
                result = "Posting Failed!";
            }

        }
        catch (Exception e){
            Log.d("Inputstream", e.getLocalizedMessage());
        }

        return result;
    }

    private class HttpAsyncTask extends AsyncTask<String, Void,String>{
        @Override
        protected String doInBackground(String... params) {
            person = new Person();
            person.setName(name);
            person.setCountry(country);
            person.setTwitter(twitter);
            return POST(params[0],person);
        }

        @Override
        protected void onPostExecute(String s) {
            Toast.makeText(getBaseContext(), "Data Sent!", Toast.LENGTH_SHORT).show();
        }
    }


    private static String convertInputStreamToString(InputStream inputStream) throws IOException{
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        String line = "";
        String result = "";

        while ((line=bufferedReader.readLine())!= null ){
            result +=line;
        }
        inputStream.close();
        return result;
    }
}
