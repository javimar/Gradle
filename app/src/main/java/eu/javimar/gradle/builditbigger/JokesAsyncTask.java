package eu.javimar.gradle.builditbigger;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
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


public class JokesAsyncTask extends AsyncTask<Void, Void, String>
{
    private static MyApi myApiService = null;
    private Context context;
    private ProgressBar mProgressBar;

    // constructor for the AsyncTask to pass the ProgressBar and the context
    JokesAsyncTask(Context context, ProgressBar progressBar)
    {
        mProgressBar = progressBar;
        this.context = context;
    }

    @Override
    protected void onPreExecute()
    {
        if(mProgressBar != null) // safe check for the instrumentation test
        {
            mProgressBar.setVisibility(View.VISIBLE);
        }
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
            Log.e(JokesAsyncTask.class.getSimpleName(), e.getMessage());
            return null;
        }
    }


    @Override
    protected void onPostExecute(String joke)
    {
        if(mProgressBar != null) // safe check for the instrumentation test
        {
            mProgressBar.setVisibility(View.GONE);
        }
        Intent intent = new Intent(context, DisplayJokeActivity.class);
        intent.putExtra("joke", joke);
        context.startActivity(intent);
    }
}

