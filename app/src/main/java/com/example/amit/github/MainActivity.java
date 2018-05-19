package com.example.amit.github;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.amit.github.githubfeed.FeedActivity;

public class MainActivity extends AppCompatActivity {


    EditText etUsername;
    Button btnView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();

        btnView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getBaseContext(), FeedActivity.class);
                i.putExtra(FeedActivity.INTENT_USERNAME, etUsername.getText().toString().trim());
                startActivity(i);
                finish();
            }
        });

    }


    void initViews(){
        etUsername = (EditText) findViewById(R.id.et_username);
        btnView = (Button) findViewById(R.id.btn_view);
    }



}
