package eu.javimar.gradle.builditbigger;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ProgressBar;


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
        new JokesAsyncTask(this, (ProgressBar) findViewById(R.id.progressBar))
                .execute();

    }



}
