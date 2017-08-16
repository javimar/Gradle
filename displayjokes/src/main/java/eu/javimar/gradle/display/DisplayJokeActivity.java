package eu.javimar.gradle.display;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class DisplayJokeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_joke);

        TextView tv_joke = (TextView)findViewById(R.id.tv_joke);
        String joke;
        if(getIntent().hasExtra("joke"))
        {
            joke = getIntent().getExtras().getString("joke");
            tv_joke.setText(joke);
        }


    }




}
