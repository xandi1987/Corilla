package com.app.corilla.corilla;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import corilla.services.CorillaActivity;
import corilla.services.MessageService;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void btnGetCorillaActivitiesClickEvent(View view) {
        Intent intent = new Intent(this, CorillaActivitiesView.class);
        startActivity(intent);
    }
}
