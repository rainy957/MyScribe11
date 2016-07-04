package app.com.example.android.myscribe;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class Test extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        Intent intent = getIntent();
        String username = intent.getStringExtra("username");

        TextView name_textview = (TextView)findViewById(R.id.name_textview);
        String text = "Hello "+username;

        name_textview.setText(text);
    }
}
