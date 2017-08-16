package eu.javimar.gradle.builditbigger;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;

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

        private ProgressBar progressBar = (ProgressBar) findViewById(R.id.progressBar);

        @Override
        protected void onPreExecute() {
            progressBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected String doInBackground(Void... params)
        {
            if(myApiService == null)
            {  // Only do this once
                MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(),
                        new AndroidJsonFactory(), null)
                        // 10.0.2.2 is localhost's IP address in AVD emulator
                        .setRootUrl("http://10.0.2.2:8080/_ah/api/")
                        .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer()
                        {
                            @Override
                            public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest)
                                    throws IOException {
                                abstractGoogleClientRequest.setDisableGZipContent(true);
                            }
                        });
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
            progressBar.setVisibility(View.GONE);

            Intent intent = new Intent(MainActivity.this, DisplayJokeActivity.class);
            intent.putExtra("joke", joke);
            startActivity(intent);
        }
    }

}
