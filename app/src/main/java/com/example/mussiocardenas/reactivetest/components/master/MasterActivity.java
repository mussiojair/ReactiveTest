package com.example.mussiocardenas.reactivetest.components.master;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import com.example.mussiocardenas.reactivetest.R;
import com.example.mussiocardenas.reactivetest.ReactiveTestApp;
import com.example.mussiocardenas.reactivetest.components.detail.DetailActivity;
import com.example.mussiocardenas.reactivetest.domain.Robot;
import com.example.mussiocardenas.reactivetest.domain.repositories.RobotsRepository;
import com.google.gson.Gson;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.android.schedulers.AndroidSchedulers;

import static com.example.mussiocardenas.reactivetest.Constants.ROBOT_DATA;

public class MasterActivity extends AppCompatActivity implements RobotsAdapter.OnClickRobotsListener {

    @Inject RobotsRepository robotsRepository;
    @Inject Gson gson;

    @BindView(R.id.recycler) RecyclerView recycler;
    @BindView(R.id.toolbar) Toolbar toolbar;
    @BindView(R.id.fab) FloatingActionButton fab;

    private RobotsAdapter robotsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_master);
        ButterKnife.bind(this);
        ReactiveTestApp.getApplication().getAppComponent().inject(this);

        setSupportActionBar(toolbar);

        fab.setOnClickListener(view -> {
            Snackbar.make(view, "Updating robots...", Snackbar.LENGTH_SHORT)
                    .setAction("Action", null).show();
            loadData();
        });
        configureViews();
    }

    private void configureViews(){
        recycler.setHasFixedSize(true);
        robotsAdapter = new RobotsAdapter(this, this);
        recycler.setLayoutManager(new LinearLayoutManager(this));
        recycler.setAdapter(robotsAdapter);
        loadData();
    }

    private void loadData(){
        robotsRepository.getRobots()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe( list -> robotsAdapter.setItems(list), error -> Toast.makeText(this, getString(R.string.error_load_robots), Toast.LENGTH_LONG).show());
    }

    @Override
    public void onClick(Robot robot) {
        Intent intent = new Intent(this, DetailActivity.class);
        intent.putExtra(ROBOT_DATA, gson.toJson(robot));
        startActivity(intent);
    }
}
