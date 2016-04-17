package com.app.corilla.corilla;

import android.app.ListActivity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import corilla.services.CorillaActivity;
import corilla.services.MessageService;

public class CorillaActivitiesView extends ListActivity {

    ListView list;
    private List<CorillaActivity> itemname ;

    private CurillaCustomActivityAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_corilla_activities_view);
        if(itemname == null)
            itemname = new ArrayList<CorillaActivity>();
        adapter = new CurillaCustomActivityAdapter(this, itemname);

        list=this.getListView();
        list.setAdapter(adapter);
        Intent intent = new Intent(this, MessageService.class);
        startService(intent);
        /*list.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                // TODO Auto-generated method stub
                String Slecteditem = itemname[+position];
                Toast.makeText(getApplicationContext(), Slecteditem, Toast.LENGTH_SHORT).show();

            }
        });*/
    }

    private BroadcastReceiver receiver = new BroadcastReceiver() {

        @Override
        public void onReceive(Context context, Intent intent) {
            Bundle bundle = intent.getExtras();
            if (bundle != null) {
                ArrayList<CorillaActivity> results = (ArrayList<CorillaActivity>)bundle.get("result");
                if(results != null) {
                    itemname.clear();
                    for (CorillaActivity k : results) {
                        adapter.add(k);
                    }
                    adapter.notifyDataSetChanged();
                }
                else
                    Toast.makeText(CorillaActivitiesView.this,"Keine Doos gefunden überprüfen Sie die Verbindung",Toast.LENGTH_LONG);
            }
        }
    };

    @Override
    protected void onResume() {
        super.onResume();
        registerReceiver(receiver, new IntentFilter(MessageService.NOTIFICATION));
    }
    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(receiver);
    }
}
