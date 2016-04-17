package com.app.corilla.corilla;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.CorrectionInfo;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import corilla.services.CorillaActivity;
import corilla.services.MessageService;

/**
 * Created by alzi on 11.10.2015.
 */
public class CurillaCustomActivityAdapter
        extends ArrayAdapter<CorillaActivity> {

    private final Activity context;
    private final List<CorillaActivity> itemName;

    public CurillaCustomActivityAdapter(Activity context, List<CorillaActivity> items){
        super(context,R.layout.mylistviewitem,items);
        this.context = context;
        itemName = items;
    }

    @Override
    public void add(CorillaActivity item)
    {
        super.add(item);

    }

    @Override
    public void clear()
    {
        super.clear();
    }



    public View getView(int position, View view, ViewGroup parent)
    {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView = inflater.inflate(R.layout.mylistviewitem, null, true);
        TextView dateFrom = (TextView)rowView.findViewById(R.id.txtdateFrom);
        TextView dateTo = (TextView)rowView.findViewById(R.id.txtDateTo);
        TextView title = (TextView)rowView.findViewById(R.id.txtDescription);


        SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
        dateFrom.setText(formatter.format(itemName.get(position).getB()));
        dateTo.setText(formatter.format(itemName.get(position).getE()));
        title.setText(itemName.get(position).getTitle());
        return rowView;
    }


}
