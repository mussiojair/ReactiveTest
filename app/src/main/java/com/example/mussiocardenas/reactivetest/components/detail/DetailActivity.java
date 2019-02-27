package com.example.mussiocardenas.reactivetest.components.detail;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.mussiocardenas.reactivetest.R;
import com.example.mussiocardenas.reactivetest.ReactiveTestApp;
import com.example.mussiocardenas.reactivetest.domain.Robot;
import com.google.gson.Gson;

import javax.inject.Inject;

import static com.example.mussiocardenas.reactivetest.Constants.ROBOT_DATA;

public class DetailActivity extends AppCompatActivity {

    @Inject Gson gson;
    private ImageView image;
    private TextView name, description;
    private Robot robot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ReactiveTestApp.getApplication().getAppComponent().inject(this);
        if(!getIntent().hasExtra(ROBOT_DATA))
            finish();
        robot = gson.fromJson(getIntent().getStringExtra(ROBOT_DATA), Robot.class);
        name = findViewById(R.id.name);
        description = findViewById(R.id.description);
        image = findViewById(R.id.image);
        fillData();
    }

    private void fillData(){
        name.setText(robot.name);
        description.setText(robot.description);
        Glide.with(this).load(robot.image).into(image);
    }
}
