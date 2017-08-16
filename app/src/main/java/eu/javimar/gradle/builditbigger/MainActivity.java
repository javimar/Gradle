package eu.javimar.gradle.builditbigger;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;

import java.io.IOException;

import eu.javimar.gradle.backend.jokesbackend.myApi.MyApi;
import eu.javimar.gradle.display.DisplayJokeActivity;


public class MainActivity extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void tellJoke(View view)
    {
        // call AsynTask to retrieve jokes
        new JokeAsynTask().execute();
    }


    private class JokeAsynTask extends AsyncTask <Void, Void, String>
    {
        private MyApi myApiService = null;

        @Override
        protected String doInBackground(Void... params)
        {
            if(myApiService == null)
            {  // Only do this once
                MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(),
                        new AndroidJsonFactory(), null)
                        // options for running against local devappserver
                        // - 10.0.2.2 is localhost's IP address in Android emulator
                        .setRootUrl("http://10.0.2.2:8080/_ah/api/");

                myApiService = builder.build();
            }

            try {
                return myApiService.myEndpoint().tellAJoke().execute().getData();
            }
            catch (IOException e) {
                Log.e(JokeAsynTask.class.getSimpleName(), e.getMessage());
                return null;
            }
        }


        @Override
        protected void onPostExecute(String joke)
        {
            Intent intent = new Intent(MainActivity.this, DisplayJokeActivity.class);
            intent.putExtra("joke", joke);
            startActivity(intent);
        }
    }

}
